package com.thinkgem.jeesite.modules.cms.service;

import com.thinkgem.jeesite.modules.cms.ArticleVo;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Value;
import org.wltea.analyzer.cfg.DefaultConfig;
import org.wltea.analyzer.dic.Dictionary;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by yangyan on 2016/12/15.
 */
public class LuceneIndexService {


    static {
        Dictionary.getSingleton();
        DefaultConfig.getInstance().setUseSmart(true);
        Dictionary.initial(DefaultConfig.getInstance());
    }


    private IndexWriter writer = null;
    private IndexReader reader = null;
    private IndexSearcher indexSearcher = null;

    @Value("${lucene.rootPath}")
    public String indexRootPath;


    public LuceneIndexService() {
        try {
            Path path = Paths.get(this.indexRootPath);
            if (!Files.isReadable(path)) {
                System.out.println("Document directory '" + path.toAbsolutePath() + "' does not exist or is not readable, please check the path");
                System.exit(1);
            }
            FSDirectory fsDir = FSDirectory.open(path);
            IKAnalyzer ikAnalyzer = new IKAnalyzer(true);
            IndexWriterConfig iwc = new IndexWriterConfig(ikAnalyzer);
            iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
            iwc.setRAMBufferSizeMB(256);
            writer = new IndexWriter(fsDir, iwc);
            reader = DirectoryReader.open(fsDir);
            indexSearcher = new IndexSearcher(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected Document convertToDocument(ArticleVo articleVo) {

        return null;
    }

    public void addIndex() {

    }
}
