package com.groupdocs.search.examples.advanced_usage.searching;

import com.groupdocs.search.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class UsingAliases {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\UsingAliases";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Adding aliases to the alias dictionary
        index.getDictionaries().getAliasDictionary().add("t", "(gravida OR promotion)");
        index.getDictionaries().getAliasDictionary().add("e", "(viverra OR farther)");

        // Search in the index
        String query = "@t OR @e";
        SearchResult result = index.search(query);

        Utils.traceResult(query, result);
    }
}
