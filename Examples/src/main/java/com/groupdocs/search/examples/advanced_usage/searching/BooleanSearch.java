package com.groupdocs.search.examples.advanced_usage.searching;

import com.groupdocs.search.*;
import com.groupdocs.search.common.*;
import com.groupdocs.search.dictionaries.*;
import com.groupdocs.search.events.*;
import com.groupdocs.search.highlighters.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class BooleanSearch {
    public static void operatorAnd() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\BooleanSearch\\OperatorAnd";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Search with text query
        String query1 = "comfort AND promotion";
        SearchResult result1 = index.search(query1);

        // Search with object query
        SearchQuery wordQuery1 = SearchQuery.createWordQuery("comfort");
        SearchQuery wordQuery2 = SearchQuery.createWordQuery("promotion");
        SearchQuery andQuery = SearchQuery.createAndQuery(wordQuery1, wordQuery2);
        SearchResult result2 = index.search(andQuery);

        Utils.traceResult(query1, result1);
        Utils.traceResult(andQuery.toString(), result2);
    }

    public static void operatorOr() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\BooleanSearch\\OperatorOr";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Search with text query
        String query1 = "comfort OR neque";
        SearchResult result1 = index.search(query1);

        // Search with object query
        SearchQuery wordQuery1 = SearchQuery.createWordQuery("comfort");
        SearchQuery wordQuery2 = SearchQuery.createWordQuery("neque");
        SearchQuery orQuery = SearchQuery.createOrQuery(wordQuery1, wordQuery2);
        SearchResult result2 = index.search(orQuery);

        Utils.traceResult(query1, result1);
        Utils.traceResult(orQuery.toString(), result2);
    }

    public static void operatorNot() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\BooleanSearch\\OperatorNot";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Search with text query
        String query1 = "sportsman AND NOT Kynynmound";
        SearchResult result1 = index.search(query1);

        // Search with object query
        SearchQuery wordQuery1 = SearchQuery.createWordQuery("sportsman");
        SearchQuery wordQuery2 = SearchQuery.createWordQuery("Kynynmound");
        SearchQuery notQuery = SearchQuery.createNotQuery(wordQuery2);
        SearchQuery andQuery = SearchQuery.createAndQuery(wordQuery1, notQuery);
        SearchResult result2 = index.search(andQuery);

        Utils.traceResult(query1, result1);
        Utils.traceResult(andQuery.toString(), result2);
    }

    public static void complexQueries() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\BooleanSearch\\ComplexQueries";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Search with text query
        String query1 = "(sportsman AND favourable) AND NOT (Kynynmound OR Murray)";
        SearchResult result1 = index.search(query1);

        // Search with object query
        SearchQuery word1Query = SearchQuery.createWordQuery("sportsman");
        SearchQuery word2Query = SearchQuery.createWordQuery("favourable");
        SearchQuery andQuery = SearchQuery.createAndQuery(word1Query, word2Query);

        SearchQuery word3Query = SearchQuery.createWordQuery("Kynynmound");
        SearchQuery word4Query = SearchQuery.createWordQuery("Murray");
        SearchQuery orQuery = SearchQuery.createOrQuery(word3Query, word4Query);
        SearchQuery notQuery = SearchQuery.createNotQuery(orQuery);

        SearchQuery rootQuery = SearchQuery.createAndQuery(andQuery, notQuery);
        SearchResult result2 = index.search(rootQuery);

        Utils.traceResult(query1, result1);
        Utils.traceResult(rootQuery.toString(), result2);
    }
}
