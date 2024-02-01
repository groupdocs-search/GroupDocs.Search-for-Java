package com.groupdocs.search.examples.advanced_usage.managing_dictionaries;

import com.groupdocs.search.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class WordFormsProvider {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\ManagingDictionaries\\WordFormsProvider\\Index";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Setting the custom word forms provider instance
        index.getDictionaries().setWordFormsProvider(new SimpleWordFormsProvider());

        // Creating a search options instance
        SearchOptions options = new SearchOptions();
        options.setUseWordFormsSearch(true); // Enabling search for word forms

        // Searching in the index
        String query = "mrs";
        SearchResult result = index.search(query, options);

        // The following words can be found:
        // mrs
        // mr

        Utils.traceResult(query, result);
    }
}
