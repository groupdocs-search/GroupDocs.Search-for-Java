package com.groupdocs.search.examples.advanced_usage.searching;

import com.groupdocs.search.*;
import com.groupdocs.search.common.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class PhraseSearch {
    public static void simplePhraseSearch() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\PhraseSearch\\SimplePhraseSearch";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Search for the phrase 'sollicitudin at ligula' in text form
        String query1 = "\"sollicitudin at ligula\"";
        SearchResult result1 = index.search(query1);

        // Search for the phrase 'sollicitudin at ligula' in object form
        SearchQuery word1 = SearchQuery.createWordQuery("sollicitudin");
        SearchQuery word2 = SearchQuery.createWordQuery("at");
        SearchQuery word3 = SearchQuery.createWordQuery("ligula");
        SearchQuery query2 = SearchQuery.createPhraseSearchQuery(word1, word2, word3);
        SearchResult result2 = index.search(query2);

        Utils.traceResult(query1, result1);
        Utils.traceResult(query2.toString(), result2);
    }

    public static void phraseSearchWithWildcards() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\PhraseSearch\\PhraseSearchWithWildcards";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Search for the phrase in text form
        String query1 = "\"sollicitudin *0~~3 ligula\"";
        SearchResult result1 = index.search(query1);

        // Search for the phrase in object form
        SearchQuery word1 = SearchQuery.createWordQuery("sollicitudin");
        SearchQuery wildcard2 = SearchQuery.createWildcardQuery(0, 3);
        SearchQuery word3 = SearchQuery.createWordQuery("ligula");
        SearchQuery query2 = SearchQuery.createPhraseSearchQuery(word1, wildcard2, word3);
        SearchResult result2 = index.search(query2);

        // The search can find the following phrases:
        // "sollicitudin of ligula"
        // "sollicitudin ligula"

        Utils.traceResult(query1, result1);
        Utils.traceResult(query2.toString(), result2);
    }

    public static void phraseSearchWithWildcards2() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\PhraseSearch\\PhraseSearchWithWildcards2";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Search for the phrase in text form
        String query1 = "\"sollicitudin  *0~~3  ?(0~4)la\"";
        SearchResult result1 = index.search(query1);

        // Search for the phrase in object form
        SearchQuery word1 = SearchQuery.createWordQuery("sollicitudin");
        SearchQuery wildcard2 = SearchQuery.createWildcardQuery(0, 3);
        WordPattern pattern = new WordPattern();
        pattern.appendWildcard(0, 4);
        pattern.appendString("la");
        SearchQuery wordPattern3 = SearchQuery.createWordPatternQuery(pattern);
        SearchQuery query2 = SearchQuery.createPhraseSearchQuery(word1, wildcard2, wordPattern3);
        SearchResult result2 = index.search(query2);

        // The search can find the following phrases:
        // "sollicitudin of ligula"
        // "sollicitudin ligula"
        // "sollicitudin, nulla"

        Utils.traceResult(query1, result1);
        Utils.traceResult(query2.toString(), result2);
    }
}
