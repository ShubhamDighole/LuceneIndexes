package com.indexing.search;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;

import com.indexing.helper.LuceneHelper;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.document.Document;

public class QueryParserSearch {

	public static void search(String queryString) throws Exception {
		Analyzer analyzer = LuceneHelper.getAnalyzer();
		QueryParser parser = new QueryParser("content", analyzer);
		Query query = parser.parse(queryString);

		DirectoryReader reader = DirectoryReader.open(LuceneHelper.getDirectory());
		IndexSearcher searcher = new IndexSearcher(reader);
		TopDocs docs = searcher.search(query, 10);

		System.out.println("Query: " + queryString);
		for (ScoreDoc scoreDoc : docs.scoreDocs) {
			Document doc = searcher.doc(scoreDoc.doc);
			System.out.println("→ " + doc.get("title"));
		}

		reader.close();
	}

}
