package com.groupdocs.search.examples.advanced_usage.searching;

import com.groupdocs.search.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class HomophoneSearch {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\HomophoneSearch";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Creating a search options object
        SearchOptions options = new SearchOptions();
        options.setUseHomophoneSearch(true); // Enabling homophone search

        // Search for the word 'call'
        // In addition to the word 'call', the word 'caul' will also be found
        String query = "call";
        SearchResult result = index.search(query, options);

        Utils.traceResult(query, result);
    }
}
