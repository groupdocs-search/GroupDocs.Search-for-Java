package com.groupdocs.search.examples.advanced_usage.managing_dictionaries;

import com.groupdocs.search.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class StopWordDictionary {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\ManagingDictionaries\\StopWordDictionary\\Index";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index from in specified folder
        Index index = new Index(indexFolder);

        if (index.getDictionaries().getStopWordDictionary().getCount() > 0) {
            // Removing all words from the dictionary
            index.getDictionaries().getStopWordDictionary().clear();
        }

        // Adding stop words to the dictionary
        String[] words = new String[] { "a", "an", "the", "but", "by" };
        index.getDictionaries().getStopWordDictionary().addRange(words);

        if (index.getDictionaries().getStopWordDictionary().contains("but") &&
            index.getDictionaries().getStopWordDictionary().contains("by")) {
            // Removing words from the dictionary
            index.getDictionaries().getStopWordDictionary().removeRange(new String[] { "but", "by" });
        }

        // Export words to a file
        String fileName = ".\\output\\AdvancedUsage\\ManagingDictionaries\\StopWordDictionary\\Words.txt";
        index.getDictionaries().getStopWordDictionary().exportDictionary(fileName);

        // Import words from a file
        index.getDictionaries().getStopWordDictionary().importDictionary(fileName);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Search in the index
        String query = "but";
        SearchResult result = index.search(query);

        Utils.traceResult(query, result);
    }
}
