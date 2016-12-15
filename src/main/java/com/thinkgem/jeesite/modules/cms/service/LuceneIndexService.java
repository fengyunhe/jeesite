package com.thinkgem.jeesite.modules.cms.service;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;
import org.lionsoul.jcseg.analyzer.v5x.JcsegAnalyzer5X;
import org.lionsoul.jcseg.tokenizer.core.JcsegTaskConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by yangyan on 2016/12/15.
 */
public abstract class LuceneIndexService<T> {

    Logger logger = LoggerFactory.getLogger(getClass());
    public final static Analyzer analyzer;

    static {
        analyzer = new JcsegAnalyzer5X(JcsegTaskConfig.COMPLEX_MODE);
        JcsegTaskConfig config = ((JcsegAnalyzer5X) analyzer).getTaskConfig();
//追加同义词, 需要在 jcseg.properties中配置jcseg.loadsyn=1
        config.setAppendCJKSyn(true);
//追加拼音, 需要在jcseg.properties中配置jcseg.loadpinyin=1
        config.setAppendCJKPinyin(true);
//更多配置, 请查看 org.lionsoul.jcseg.tokenizer.core.JcsegTaskConfig
//        analyzer = new StandardAnalyzer();
    }

    private IndexReader reader = null;
    private IndexSearcher indexSearcher = null;
    private boolean writeUsing = false;

    @Value("${lucene.rootPath}")
    public String indexRootPath = "/var/tmp/lucene_path";


    private FSDirectory fsDir = null;

    public LuceneIndexService() {
        try {
            Path path = Paths.get(this.indexRootPath, this.getIndexName());
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }
            if (!Files.isDirectory(path)) {
                System.err.println(String.format("索引【%s】的保存路径【%s】不是一个目录", getIndexName(), path.toAbsolutePath()));
                System.exit(1);
            }
            if (!Files.isReadable(path)) {
                System.err.println(String.format("索引【%s】的保存路径【%s】无法读取", getIndexName(), path.toAbsolutePath()));
                System.exit(1);
            }

            if (!Files.isWritable(path)) {
                System.err.println(String.format("索引【%s】的保存路径【%s】无权限写入", getIndexName(), path.toAbsolutePath()));
                System.exit(1);
            }
            fsDir = FSDirectory.open(path);
            reader = DirectoryReader.open(fsDir);
            indexSearcher = new IndexSearcher(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 存储索引的子目录的名称，可以用子类重写此方法自定义子目录名称
     *
     * @return
     */
    protected String getIndexName() {
        return this.getClass().getSimpleName();
    }

    /**
     * 转换 vo 对象成 document
     *
     * @param vo
     * @return
     */
    protected abstract Document convertToDocument(T vo);

    /**
     * 转换 document 成vo对象
     *
     * @param document
     * @return
     */
    protected abstract T convertToVo(Document document);


    public final void add(Collection<T> voList) {

        if (voList != null) {
            IndexWriter writer = createWriter();
            try {
                for (T vo : voList) {
                    if (vo != null) {
                        Document doc = convertToDocument(vo);
                        try {
                            writer.addDocument(doc);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } finally {
                closeWriter(writer);
            }
        }
    }

    protected abstract boolean hasHightLight();

    public final List<T> search(Query q, int start, int limit) {
        try {
            TopFieldCollector topFieldCollector = TopFieldCollector.create(Sort.RELEVANCE, start + limit, false, false, false);
            this.indexSearcher.search(q, topFieldCollector);
            TopDocs topDocs = topFieldCollector.topDocs(start, limit);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            if (scoreDocs != null) {
//                Formatter htmlFormatter = null;
//                if (hasHightLight()) {
//                    htmlFormatter = new SimpleHTMLFormatter(
//                            "<span style='color:red;'>", "</span>");
//                } else {
//                    htmlFormatter = new SimpleHTMLFormatter("", "");
//                }
//                QueryScorer queryScorer = new QueryScorer(q);
//                Fragmenter fragmenter = new SimpleFragmenter();
//                Highlighter highlighter = new Highlighter(htmlFormatter, queryScorer);
//                highlighter.setTextFragmenter(fragmenter);
                List<T> list = new ArrayList<T>();
                for (ScoreDoc scoreDoc : scoreDocs) {
                    Document doc = indexSearcher.doc(scoreDoc.doc);
                    if (doc != null) {
//                        highlighter.getBestFragment(analyzer, "title", doc.get("title"));
                        T resultVo = convertToVo(doc);
                        list.add(resultVo);
                    }
                }
                return list;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public void deleteAll() {
        IndexWriter writer = createWriter();
        try {
            writer.deleteAll();
            closeWriter(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized IndexWriter createWriter() {
        if (writeUsing) {
            return null;
        }
        IndexWriter writer = null;
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
        iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        iwc.setRAMBufferSizeMB(256);
        try {
            writer = new IndexWriter(fsDir, iwc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeUsing = true;
        return writer;
    }

    private void closeWriter(IndexWriter writer) {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeUsing = false;
    }
}
