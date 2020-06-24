package org.apache.lucene.analysis;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.WhitespaceASCIIAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.vi.VietnameseASCIIAnalyzer;
import org.apache.lucene.analysis.vi.VietnameseAnalyzer;

import java.io.IOException;
import java.util.Objects;

public class SolrVnAnalyzer {
    public static void main(String[] args) {
        String txt = "Trong các đẳng\nthức sau\n\n động vật trên thế giới rất phong phú và đa dạng\nCộng hoà xã hội chủ nghĩa Việt Nam\n Độc lập - Tự do - Hạnh phúc";
        if (args.length > 0) {
            txt = String.join(" \n ", args);
        }
        printTokenizedString(new VietnameseAnalyzer(), txt);
        printTokenizedString(new VietnameseASCIIAnalyzer(), txt);
        printTokenizedString(new WhitespaceASCIIAnalyzer(), txt);
    }

    public static void printTokenizedString(Analyzer analyzer, String txt) {
        TokenStream stream = analyzer.tokenStream("test", txt);
        System.out.println("======================================== " + analyzer.getClass() + " ==================================================");
        CharTermAttribute cattr = stream.addAttribute(CharTermAttribute.class);
        try {
            stream.reset();
            while (stream.incrementToken()) {
                System.out.println(cattr.toString());
            }
            stream.end();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
