/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cms.service;

import com.drew.lang.StringUtil;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.cms.dao.ArticleDao;
import com.thinkgem.jeesite.modules.cms.dao.ArticleDataDao;
import com.thinkgem.jeesite.modules.cms.dao.CategoryDao;
import com.thinkgem.jeesite.modules.cms.entity.Article;
import com.thinkgem.jeesite.modules.cms.entity.ArticleData;
import com.thinkgem.jeesite.modules.cms.entity.Category;
import com.thinkgem.jeesite.modules.cms.vo.ArticleVo;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.flexible.core.QueryNodeException;
import org.apache.lucene.queryparser.flexible.standard.QueryParserUtil;
import org.apache.lucene.queryparser.xml.QueryBuilderFactory;
import org.apache.lucene.queryparser.xml.builders.BooleanQueryBuilder;
import org.apache.lucene.queryparser.xml.builders.TermQueryBuilder;
import org.apache.lucene.search.*;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.QueryBuilder;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.thinkgem.jeesite.modules.cms.service.ArticleService.ArticleLuceneIndexService.*;

/**
 * 文章Service
 *
 * @author ThinkGem
 * @version 2013-05-15
 */
@Service
@Transactional(readOnly = true)
public class ArticleService extends CrudService<ArticleDao, Article> {

    private ArticleLuceneIndexService articleLuceneIndexService = new ArticleLuceneIndexService();

    @Autowired
    private ArticleDataDao articleDataDao;
    @Autowired
    private CategoryDao categoryDao;


    public ArticleVo get(String id) {
        Article po = super.get(id);
        return this.convertPoToVo(po);
    }

    public List<ArticleVo> findList(ArticleVo Article) {
        List<Article> poList = super.findList(Article);
        return this.convertPoListToVoList(poList);
    }

    @Transactional(readOnly = false)
    public Page<ArticleVo> findPage(Page page, ArticleVo article, boolean isDataScopeFilter) {
        // 更新过期的权重，间隔为“6”个小时
        Date updateExpiredWeightDate = (Date) CacheUtils.get("updateExpiredWeightDateByArticle");
        if (updateExpiredWeightDate == null || (updateExpiredWeightDate != null
                && updateExpiredWeightDate.getTime() < new Date().getTime())) {
            dao.updateExpiredWeight(article);
            CacheUtils.put("updateExpiredWeightDateByArticle", DateUtils.addHours(new Date(), 6));
        }
//		DetachedCriteria dc = dao.createDetachedCriteria();
//		dc.createAlias("category", "category");
//		dc.createAlias("category.site", "category.site");
        if (article.getCategory() != null && StringUtils.isNotBlank(article.getCategory().getId()) && !Category.isRoot(article.getCategory().getId())) {
            Category category = categoryDao.get(article.getCategory().getId());
            if (category == null) {
                category = new Category();
            }
            category.setParentIds(category.getId());
            category.setSite(category.getSite());
            article.setCategory(category);
        } else {
            article.setCategory(new Category());
        }
//		if (StringUtils.isBlank(page.getOrderBy())){
//			page.setOrderBy("a.weight,a.update_date desc");
//		}
//		return dao.find(page, dc);
        //	article.getSqlMap().put("dsf", dataScopeFilter(article.getCurrentUser(), "o", "u"));

        Page poPage = super.findPage(page, article);
        List<ArticleVo> voList = this.convertPoListToVoList(poPage.getList());
        poPage.setList(voList);
        return poPage;

    }

    @Transactional(readOnly = false)
    public void save(Article article) {
        if (article.getArticleData().getContent() != null) {
            article.getArticleData().setContent(StringEscapeUtils.unescapeHtml4(
                    article.getArticleData().getContent()));
        }
        // 如果没有审核权限，则将当前内容改为待审核状态
        if (!UserUtils.getSubject().isPermitted("cms:article:audit")) {
            article.setDelFlag(Article.DEL_FLAG_AUDIT);
        }
        // 如果栏目不需要审核，则将该内容设为发布状态
        if (article.getCategory() != null && StringUtils.isNotBlank(article.getCategory().getId())) {
            Category category = categoryDao.get(article.getCategory().getId());
            if (!Global.YES.equals(category.getIsAudit())) {
                article.setDelFlag(Article.DEL_FLAG_NORMAL);
            }
        }
        article.setUpdateBy(UserUtils.getUser());
        article.setUpdateDate(new Date());
        if (StringUtils.isNotBlank(article.getViewConfig())) {
            article.setViewConfig(StringEscapeUtils.unescapeHtml4(article.getViewConfig()));
        }

        ArticleData articleData = new ArticleData();
        ;
        if (StringUtils.isBlank(article.getId())) {
            article.preInsert();
            articleData = article.getArticleData();
            articleData.setId(article.getId());
            dao.insert(article);
            articleDataDao.insert(articleData);
        } else {
            article.preUpdate();
            articleData = article.getArticleData();
            articleData.setId(article.getId());
            dao.update(article);
            articleDataDao.update(article.getArticleData());
        }
    }

    @Transactional(readOnly = false)
    public void delete(Article article, Boolean isRe) {
//		dao.updateDelFlag(id, isRe!=null&&isRe?Article.DEL_FLAG_NORMAL:Article.DEL_FLAG_DELETE);
        // 使用下面方法，以便更新索引。
        //Article article = dao.get(id);
        //article.setDelFlag(isRe!=null&&isRe?Article.DEL_FLAG_NORMAL:Article.DEL_FLAG_DELETE);
        //dao.insert(article);
        super.delete(article);
    }

    /**
     * 通过编号获取内容标题
     *
     * @return new Object[]{栏目Id,文章Id,文章标题}
     */
    public List<Object[]> findByIds(String ids) {
        if (ids == null) {
            return new ArrayList<Object[]>();
        }
        List<Object[]> list = Lists.newArrayList();
        String[] idss = StringUtils.split(ids, ",");
        Article e = null;
        for (int i = 0; (idss.length - i) > 0; i++) {
            e = dao.get(idss[i]);
            list.add(new Object[]{e.getCategory().getId(), e.getId(), StringUtils.abbr(e.getTitle(), 50)});
        }
        return list;
    }

    /**
     * 点击数加一
     */
    @Transactional(readOnly = false)
    public void updateHitsAddOne(String id) {
        dao.updateHitsAddOne(id);
    }

    /**
     * 更新索引
     */
    public void createIndex() {
        List<Article> list = this.findList(new Article());
        List<ArticleVo> articleVos = convertPoListToVoList(list);
        articleLuceneIndexService.deleteAll();
        articleLuceneIndexService.add(articleVos);
    }

    /**
     * 全文检索
     */
    public Page<ArticleVo> search(Page<ArticleVo> page, String q, String categoryId, String beginDate, String endDate) {

        Date s = null;
        if (StringUtils.isNotBlank(beginDate)) {
            try {
                s = DateUtils.parseDate(beginDate, "yyyy-MM-dd", "yyyy/MM/dd");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Date e = null;
        if (StringUtils.isNotBlank(endDate)) {
            try {
                e = DateUtils.parseDate(endDate, "yyyy-MM-dd", "yyyy/MM/dd");
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }


        List<ArticleVo> list = articleLuceneIndexService.search(this.buildQ(q, categoryId, s, e), 0, 30);
        page.setList(list);
        return page;
    }

    public List<ArticleVo> convertPoListToVoList(List<Article> poList) {
        if (poList != null) {
            List<ArticleVo> voList = new ArrayList<ArticleVo>();
            for (Article test : poList) {
                ArticleVo vo = this.convertPoToVo(test);
                voList.add(vo);
            }
            return voList;
        }
        return new ArrayList<ArticleVo>();
    }

    private ArticleVo convertPoToVo(Article po) {
        if (po == null) {
            return null;
        }
        ArticleVo vo = new ArticleVo();
        BeanUtils.copyProperties(po, vo);
        //TODO 设置其他vo中的属性
        return vo;
    }


    private Query buildQ(String q, String categoryId, Date beginDate, Date endDate) {

        BooleanQuery.Builder bQuery = new BooleanQuery.Builder();
        try {

            //标题和正文
            if (StringUtils.isNotBlank(q)) {
                Query textQuery
                        = QueryParserUtil.parse(q, new String[]{FIELD_TITLE, FIELD_CONTENT}, new BooleanClause.Occur[]{
                        BooleanClause.Occur.SHOULD, BooleanClause.Occur.SHOULD}, ArticleLuceneIndexService.analyzer);
                bQuery.add(textQuery, BooleanClause.Occur.MUST);
            }


            //分类
            if (StringUtils.isNotBlank(categoryId)) {
                bQuery.add(new TermQuery(new Term(FIELD_CATEGORY_ID, categoryId)), BooleanClause.Occur.MUST);
            }


            // 时间范围
            if (beginDate != null && endDate != null) {
                bQuery.add(LongPoint.newRangeQuery(FIELD_UPDATE_TIME, beginDate.getTime(), endDate.getTime()), BooleanClause.Occur.MUST);
            } else if (beginDate != null) {
                bQuery.add(LongPoint.newRangeQuery(FIELD_UPDATE_TIME, beginDate.getTime(), System.currentTimeMillis()), BooleanClause.Occur.MUST);
            } else if (endDate != null) {
                bQuery.add(LongPoint.newRangeQuery(FIELD_UPDATE_TIME, 0, endDate.getTime()), BooleanClause.Occur.MUST);
            }


        } catch (QueryNodeException e) {
            e.printStackTrace();
        }
        return bQuery.build();

    }


    public class ArticleLuceneIndexService extends LuceneIndexService<ArticleVo> {
        public final static String FIELD_ID = "id";
        public final static String FIELD_TITLE = "title";
        public final static String FIELD_CONTENT = "content";
        public final static String FIELD_CATEGORY_ID = "category_id";
        public final static String FIELD_UPDATE_TIME = "update_time";

        @Override
        protected Document convertToDocument(ArticleVo vo) {
            ArticleData articleData = ArticleService.this.articleDataDao.get(vo.getId());
            Document doc = new Document();
            StringField idField = new StringField(FIELD_ID, vo.getId(), Field.Store.YES);
            IndexableField titleField = new TextField(FIELD_TITLE, vo.getTitle(), Field.Store.NO);
            IndexableField contentField = new TextField(FIELD_CONTENT, articleData.getContent(), Field.Store.NO);
            IndexableField categoryField = new StringField(FIELD_CATEGORY_ID, vo.getTitle(), Field.Store.NO);
            IndexableField updateTimeField = new LongPoint(FIELD_UPDATE_TIME, vo.getUpdateDate().getTime());
//            TextField contentField = new TextField("content", vo.getArticleData().getContent(), Field.Store.NO);

            doc.add(idField);
            doc.add(titleField);
            doc.add(contentField);
            doc.add(categoryField);
            doc.add(updateTimeField);
//            doc.add(contentField);

            return doc;
        }

        @Override
        protected ArticleVo convertToVo(Document document) {
            String id = document.getField("id").stringValue();
            return ArticleService.this.get(id);
        }

        @Override
        protected boolean hasHightLight() {
            return true;
        }

        @Override
        protected String getIndexName() {
            return "article";
        }
    }


//    public static void main(String[] args) {

//        List<ArticleVo> search = new ArticleService.ArticleLuceneIndexService().search("文章", 0, 100);
//        System.out.println(search);
//    }
}


