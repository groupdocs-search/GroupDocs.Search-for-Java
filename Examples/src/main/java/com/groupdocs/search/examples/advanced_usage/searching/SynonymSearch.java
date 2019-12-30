package com.groupdocs.search.examples.advanced_usage.searching;

import com.groupdocs.search.*;
import com.groupdocs.search.common.*;
import com.groupdocs.search.dictionaries.*;
import com.groupdocs.search.events.*;
import com.groupdocs.search.highlighters.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class SynonymSearch {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\SynonymSearch";
        String documentsFolder = Utils.DocumentsPath2;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Creating a search options object
        SearchOptions options = new SearchOptions();
        options.setUseSynonymSearch(true); // Enabling synonym search

        // Search for the word 'improve'
        // In addition to the word 'improve', the words 'better' will also be found
        String query = "improve";
        SearchResult result = index.search(query, options);

        Utils.traceResult(query, result);
    }
}
