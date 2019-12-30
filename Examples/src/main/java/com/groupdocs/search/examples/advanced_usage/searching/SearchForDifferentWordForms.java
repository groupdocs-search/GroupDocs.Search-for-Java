package com.groupdocs.search.examples.advanced_usage.searching;

import com.groupdocs.search.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class SearchForDifferentWordForms {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\SearchForDifferentWordForms";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Creating a search options instance
        SearchOptions options = new SearchOptions();
        options.setUseWordFormsSearch(true); // Enabling search for word forms

        // Searching in the index
        String query = "wished";
        SearchResult result = index.search(query, options);

        // The following words can be found:
        // wished
        // wish

        Utils.traceResult(query, result);
    }
}
