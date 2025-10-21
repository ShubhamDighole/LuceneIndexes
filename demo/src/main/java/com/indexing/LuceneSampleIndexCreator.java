package com.indexing;
import com.indexing.helper.LuceneHelper;

import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;

public class LuceneSampleIndexCreator {
    public static void createSampleIndex() throws Exception {
        try (IndexWriter writer = LuceneHelper.getIndexWriter()) {
            addDoc(writer, "1", "Lucene Introduction", "Lucene is a search library written in Java");
            addDoc(writer, "2", "Elasticsearch Basics", "Elasticsearch is built on top of Lucene");
            addDoc(writer, "3", "Java Indexing", "Indexing text data with Lucene is powerful");
        }
    }

    private static void addDoc(IndexWriter writer, String id, String title, String content) throws Exception {
        Document doc = new Document();
        doc.add(new StringField("id", id, Field.Store.YES));
        doc.add(new TextField("title", title, Field.Store.YES));
        doc.add(new TextField("content", content, Field.Store.YES));
        writer.addDocument(doc);
    }

}
