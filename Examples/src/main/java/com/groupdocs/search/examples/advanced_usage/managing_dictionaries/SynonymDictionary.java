package com.groupdocs.search.examples.advanced_usage.managing_dictionaries;

import com.groupdocs.search.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class SynonymDictionary {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\ManagingDictionaries\\SynonymDictionary\\Index";
        String documentsFolder = Utils.DocumentsPath2;

        // Creating an index from in specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Getting synonyms for word 'make'
        String[] synonyms = index.getDictionaries().getSynonymDictionary().getSynonyms("make");
        System.out.println("Synonyms for 'make':");
        for (String synonym : synonyms) {
            System.out.println(synonym);
        }

        // Getting groups of synonyms to which word 'make' belongs to
        String[][] groups = index.getDictionaries().getSynonymDictionary().getSynonymGroups("make");
        System.out.println("Synonym groups for 'make':");
        for (String[] group : groups) {
            for (String group1 : group) {
                System.out.print(group1 + " ");
            }
            System.out.println();
        }

        if (index.getDictionaries().getSynonymDictionary().getCount() > 0) {
            // Removing all synonyms from the dictionary
            index.getDictionaries().getSynonymDictionary().clear();
        }

        // Adding synonyms to the dictionary
        String[][] synonymGroups = new String[][] {
            new String[] { "achieve", "accomplish", "attain", "reach" },
            new String[] { "accept", "take", "have" },
            new String[] { "improve", "better" },
        };
        index.getDictionaries().getSynonymDictionary().addRange(synonymGroups);

        // Export synonyms to a file
        String fileName = ".\\output\\AdvancedUsage\\ManagingDictionaries\\SynonymDictionary\\Synonyms.dat";
        index.getDictionaries().getSynonymDictionary().exportDictionary(fileName);

        // Import synonyms from a file
        index.getDictionaries().getSynonymDictionary().importDictionary(fileName);

        // Search in the index
        String query = "better";
        SearchOptions options = new SearchOptions();
        options.setUseSynonymSearch(true); // Enabling synonym search
        SearchResult result = index.search(query, options);

        Utils.traceResult(query, result);
    }
}
