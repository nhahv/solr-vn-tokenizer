package org.apache.lucene.analysis.core;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilter;

public class WhitespaceASCIIAnalyzer extends Analyzer {
    @Override
    protected TokenStreamComponents createComponents(String s) {
        final Tokenizer tokenizer = new WhitespaceTokenizer();
        TokenStream tokenStream = new LowerCaseFilter(tokenizer);
        tokenStream = new ASCIIFoldingFilter(tokenStream, true);
        return new TokenStreamComponents(tokenizer, tokenStream);
    }
}
