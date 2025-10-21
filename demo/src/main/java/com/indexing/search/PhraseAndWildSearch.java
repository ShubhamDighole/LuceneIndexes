package com.indexing.search;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.search.*;

import com.indexing.helper.LuceneHelper;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;

public class PhraseAndWildSearch {
	
	   public static void multiFieldQuery(String queryString) throws Exception {

	        String[] fields = {"title", "content"};
	        Analyzer analyzer = LuceneHelper.getAnalyzer();

	        MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, analyzer);
	        Query query = parser.parse(queryString);

	        search(query);
	    }

	    public static void booleanQueryExample() throws Exception {

	        DirectoryReader reader = DirectoryReader.open(LuceneHelper.getDirectory());
	        IndexSearcher searcher = new IndexSearcher(reader);

	        // term queries
	        Query term1 = new TermQuery(new Term("content", "lucene"));
	        Query term2 = new TermQuery(new Term("content", "java"));

	        // combine with Boolean
	        BooleanQuery booleanQuery = new BooleanQuery.Builder()
	                .add(term1, BooleanClause.Occur.MUST)   // AND
	                .add(term2, BooleanClause.Occur.MUST)   // AND
	                .build();

/*+title:lucene +content:search*/
	        search(booleanQuery, searcher);
	        reader.close();
	    }

/*Exact Match*/	    
	    public static void phraseQueryExample() throws Exception {

	        DirectoryReader reader = DirectoryReader.open(LuceneHelper.getDirectory());
	        IndexSearcher searcher = new IndexSearcher(reader);

	        PhraseQuery phraseQuery = new PhraseQuery.Builder()
	                .add(new Term("content", "lucene"))
	                .add(new Term("content", "java"))
	                .build();
/*content:"quick brown"*/	        

	        search(phraseQuery, searcher);

	        reader.close();
	    }

	    
/*Match word*/	    
	    public static void wildcardFuzzyExample() throws Exception {

	        DirectoryReader reader = DirectoryReader.open(LuceneHelper.getDirectory());
	        IndexSearcher searcher = new IndexSearcher(reader);
/*partially matches or 0 matches*/
	        
	        Query wildcardQuery = new WildcardQuery(new Term("title", "Luc*"));
	        search(wildcardQuery, searcher);

	        Query fuzzyQuery = new FuzzyQuery(new Term("content", "Jav"), 2);
	        search(fuzzyQuery, searcher);

	        reader.close();
	    }

	    private static void search(Query query) throws Exception {
	        DirectoryReader reader = DirectoryReader.open(LuceneHelper.getDirectory());
	        IndexSearcher searcher = new IndexSearcher(reader);

	        TopDocs td = searcher.search(query, 10);
	        System.out.println("Total Hits: " + td.totalHits.value);
	        for (ScoreDoc sd : td.scoreDocs) {
	            Document doc = searcher.doc(sd.doc);
	            System.out.println("→ " + doc.get("title"));
	        }

	        reader.close();
	    }

	    private static void search(Query query, IndexSearcher searcher) throws Exception {
	        TopDocs td = searcher.search(query, 10);
	        System.out.println("Total Hits: " + td.totalHits.value);
	        for (ScoreDoc sd : td.scoreDocs) {
	            Document doc = searcher.doc(sd.doc);
	            System.out.println("→ " + doc.get("title"));
	        }
	    }

}
