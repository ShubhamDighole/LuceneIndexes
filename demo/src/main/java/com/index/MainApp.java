package com.index;

import com.indexing.LuceneSampleIndexCreator;
import com.indexing.search.BasicSearch;
import com.indexing.search.PhraseAndWildSearch;
import com.indexing.search.QueryParserSearch;

public class MainApp {

    public static void main(String[] args) throws Exception {
        LuceneSampleIndexCreator.createSampleIndex();

        BasicSearch.searchByTerm("content", "lucene");

        QueryParserSearch.search("Lucene AND Java");
        
        // Multi-field search
        PhraseAndWildSearch.multiFieldQuery("I am learning and exploring lucene indexes.");

        // Boolean query
        PhraseAndWildSearch.booleanQueryExample();

        // Phrase query
        PhraseAndWildSearch.phraseQueryExample();

        // Wildcard & Fuzzy
        PhraseAndWildSearch.wildcardFuzzyExample();
    }
	
}
