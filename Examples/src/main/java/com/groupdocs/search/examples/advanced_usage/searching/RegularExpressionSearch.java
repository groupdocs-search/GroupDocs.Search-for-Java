package com.groupdocs.search.examples.advanced_usage.searching;

import com.groupdocs.search.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class RegularExpressionSearch {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\RegularExpressionSearch";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Search for the phrase in text form
        String query1 = "^^(.)\\1{1,}"; // The first caret character at the beginning indicates that this is a regular expression search query
        SearchResult result1 = index.search(query1); // Search for two or more identical characters at the beginning of a word

        // Search for the phrase in object form
        SearchQuery query2 = SearchQuery.createRegexQuery("^(.)\\1{1,}"); // Search for two or more identical characters at the beginning of a word
        SearchResult result2 = index.search(query2);

        Utils.traceResult(query1, result1);
        Utils.traceResult(query2.toString(), result2);
    }
}
