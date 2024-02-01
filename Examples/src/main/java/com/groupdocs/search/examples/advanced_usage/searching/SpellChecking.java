package com.groupdocs.search.examples.advanced_usage.searching;

import com.groupdocs.search.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class SpellChecking {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\SpellChecking";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Creating a search options instance
        SearchOptions options = new SearchOptions();
        options.getSpellingCorrector().setEnabled(true); // Enabling the spelling correction
        options.getSpellingCorrector().setMaxMistakeCount(1); // Setting the maximum number of mistakes
        options.getSpellingCorrector().setOnlyBestResults(true); // Enabling the option for only the best results of the spelling correction

        // Search for the word "houseohld" containing a spelling error
        // The word "household" will be found that differs from the search query in two transposed letters
        String query = "houseohld";
        SearchResult result = index.search(query, options);

        Utils.traceResult(query, result);
    }
}
