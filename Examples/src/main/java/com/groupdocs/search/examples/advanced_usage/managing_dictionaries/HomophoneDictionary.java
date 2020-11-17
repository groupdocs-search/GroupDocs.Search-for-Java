package com.groupdocs.search.examples.advanced_usage.managing_dictionaries;

import com.groupdocs.search.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class HomophoneDictionary {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\ManagingDictionaries\\HomophoneDictionary\\Index";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index from in specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Getting homophones for word 'braid'
        String[] homophones = index.getDictionaries().getHomophoneDictionary().getHomophones("braid");
        System.out.println("Homophones for 'braid':");
        for (String homophone : homophones) {
            System.out.println(homophone);
        }

        // Getting groups of homophones to which word 'braid' belongs to
        String[][] groups = index.getDictionaries().getHomophoneDictionary().getHomophoneGroups("braid");
        System.out.println("Homophone groups for 'braid':");
        for (String[] group : groups) {
            for (String group1 : group) {
                System.out.print(group1 + " ");
            }
            System.out.println();
        }

        if (index.getDictionaries().getHomophoneDictionary().getCount() > 0) {
            // Removing all homophones from the dictionary
            index.getDictionaries().getHomophoneDictionary().clear();
        }

        // Adding homophones to the dictionary
        String[][] homophoneGroups = new String[][] {
            new String[] { "awe", "oar", "or", "ore" },
            new String[] { "aye", "eye", "i" },
            new String[] { "call", "caul" },
        };
        index.getDictionaries().getHomophoneDictionary().addRange(homophoneGroups);

        // Export homophones to a file
        String fileName = ".\\output\\AdvancedUsage\\ManagingDictionaries\\HomophoneDictionary\\Homophones.dat";
        index.getDictionaries().getHomophoneDictionary().exportDictionary(fileName);

        // Import homophones from a file
        index.getDictionaries().getHomophoneDictionary().importDictionary(fileName);

        // Search in the index
        String query = "caul";
        SearchOptions options = new SearchOptions();
        options.setUseHomophoneSearch(true);
        SearchResult result = index.search(query, options);

        Utils.traceResult(query, result);
    }
}
