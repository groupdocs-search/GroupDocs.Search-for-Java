package com.groupdocs.search.examples.advanced_usage.managing_dictionaries;

import com.groupdocs.search.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class SpellingCorrector {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\ManagingDictionaries\\SpellingCorrector\\Index";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index from in specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        if (index.getDictionaries().getSpellingCorrector().getCount() > 0) {
            // Removing all words from the dictionary
            index.getDictionaries().getSpellingCorrector().clear();
        }

        // Adding words to the dictionary
        String[] words = new String[] { "achieve", "accomplish", "attain", "expression", "reach" };
        index.getDictionaries().getSpellingCorrector().addRange(words);

        // Export words to a file
        String fileName = ".\\output\\AdvancedUsage\\ManagingDictionaries\\SpellingCorrector\\Words.txt";
        index.getDictionaries().getSpellingCorrector().exportDictionary(fileName);

        // Import words from a file
        index.getDictionaries().getSpellingCorrector().importDictionary(fileName);

        // Search in the index
        String query = "experssino";
        SearchOptions options = new SearchOptions();
        options.getSpellingCorrector().setEnabled(true);
        options.getSpellingCorrector().setMaxMistakeCount(2);
        SearchResult result = index.search(query, options);

        Utils.traceResult(query, result);
    }
}
