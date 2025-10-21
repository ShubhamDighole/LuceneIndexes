package com.indexing;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class AnalyzerSample {
    public static void analyzeText(String text) throws Exception {
        testAnalyzer(new StandardAnalyzer(), text, "StandardAnalyzer");
        testAnalyzer(new SimpleAnalyzer(), text, "SimpleAnalyzer");
        testAnalyzer(new WhitespaceAnalyzer(), text, "WhitespaceAnalyzer");
    }

    private static void testAnalyzer(Analyzer analyzer, String text, String name) throws Exception {
        var tokenStream = analyzer.tokenStream("content", new StringReader(text));
        tokenStream.reset();
        while (tokenStream.incrementToken()) {
            CharTermAttribute attr = tokenStream.getAttribute(CharTermAttribute.class);
            System.out.print(attr.toString() + " ");
        }
        tokenStream.end();
        tokenStream.close();
    }

}
