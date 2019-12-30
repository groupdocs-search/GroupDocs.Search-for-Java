package com.groupdocs.search.examples.advanced_usage.searching;

import com.groupdocs.search.*;
import com.groupdocs.search.common.*;
import com.groupdocs.search.dictionaries.*;
import com.groupdocs.search.events.*;
import com.groupdocs.search.highlighters.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class FuzzySearch {
    public static void settingFuzzySearchAlgorithm() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\FuzzySearch\\SettingFuzzySearchAlgorithm";
        String documentsFolder = Utils.DocumentsPath;
        String query = "nulla";

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        SearchOptions options = new SearchOptions();
        options.getFuzzySearch().setEnabled(true); // Enabling the fuzzy search
        options.getFuzzySearch().setFuzzyAlgorithm(new SimilarityLevel(0.8)); // Creating the fuzzy search algorithm
        // This function specifies 0 as the maximum number of mistakes for words from 1 to 4 characters.
        // It specifies 1 as the maximum number of mistakes for words from 5 to 9 characters.
        // It specifies 2 as the maximum number of mistakes for words from 10 to 14 characters. And so on.

        // Search in index
        SearchResult result = index.search(query, options);

        Utils.traceResult(query, result);
    }

    public static void settingStepFunction() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\FuzzySearch\\SettingStepFunction";
        String documentsFolder = Utils.DocumentsPath;
        String query = "nulla";

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        SearchOptions options = new SearchOptions();
        options.getFuzzySearch().setEnabled(true); // Enabling the fuzzy search
        options.getFuzzySearch().setFuzzyAlgorithm(new TableDiscreteFunction(1, new Step(5, 2), new Step(8, 3))); // Creating the fuzzy search algorithm
        // This function specifies 1 as the maximum number of mistakes for words from 1 to 4 characters.
        // It specifies 2 as the maximum number of mistakes for words from 5 to 7 characters.
        // It specifies 3 as the maximum number of mistakes for words from 8 and more characters.

        // Search in index
        SearchResult result = index.search(query, options);

        Utils.traceResult(query, result);
    }
}
