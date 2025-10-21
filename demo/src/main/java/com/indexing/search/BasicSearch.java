package com.indexing.search;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;

import com.indexing.helper.LuceneHelper;

import org.apache.lucene.document.Document;

public class BasicSearch {

    public static void searchByTerm(String field, String termText) throws Exception {
        DirectoryReader reader = DirectoryReader.open(LuceneHelper.getDirectory());
        IndexSearcher searcher = new IndexSearcher(reader);

        Query query = new TermQuery(new Term(field, termText));
        TopDocs docs = searcher.search(query, 10);

        System.out.println("Results for: " + termText);
        for (ScoreDoc scoreDoc : docs.scoreDocs) {
            Document doc = searcher.doc(scoreDoc.doc);
            System.out.println("→ " + doc.get("title"));
        }

        reader.close();
    }
	
}
