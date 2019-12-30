package com.groupdocs.search.examples.advanced_usage.searching;

import com.groupdocs.search.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class KeyboardLayoutCorrection {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\KeyboardLayoutCorrection";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Creating a search options object
        SearchOptions options = new SearchOptions();
        options.getKeyboardLayoutCorrector().setEnabled(true); // Enabling keyboard layout correction

        // Search for word 'ызщкеыьфт' gives documents containing word 'sportsman'
        String query = "ызщкеыьфт";
        SearchResult result = index.search(query, options);

        Utils.traceResult(query, result);
    }
}
