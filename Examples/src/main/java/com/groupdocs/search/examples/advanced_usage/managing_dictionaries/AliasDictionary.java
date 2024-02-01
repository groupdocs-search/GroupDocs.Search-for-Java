package com.groupdocs.search.examples.advanced_usage.managing_dictionaries;

import com.groupdocs.search.*;
import com.groupdocs.search.dictionaries.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class AliasDictionary {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\ManagingDictionaries\\AliasDictionary\\Index";
        String documentsFolder = Utils.DocumentsPath;

        // Creating or opening an index from the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        if (index.getDictionaries().getAliasDictionary().getCount() > 0) {
            // Deleting all existing aliases
            index.getDictionaries().getAliasDictionary().clear();
        }

        // Adding aliases to the alias dictionary
        index.getDictionaries().getAliasDictionary().add("t", "(gravida OR promotion)");
        index.getDictionaries().getAliasDictionary().add("e", "(viverra OR farther)");
        AliasReplacementPair[] pairs = new AliasReplacementPair[] {
            new AliasReplacementPair("d", "daterange(2017-01-01 ~~ 2019-12-31)"),
            new AliasReplacementPair("n", "(400 ~~ 4000)"),
        };
        index.getDictionaries().getAliasDictionary().addRange(pairs);

        if (index.getDictionaries().getAliasDictionary().contains("e")) {
            // Getting an alias replacement
            String replacement = index.getDictionaries().getAliasDictionary().getText("e");
            System.out.println("e - " + replacement);
        }

        // Export aliases to a file
        String fileName = ".\\output\\AdvancedUsage\\ManagingDictionaries\\AliasDictionary\\Aliases.dat";
        index.getDictionaries().getAliasDictionary().exportDictionary(fileName);

        // Import aliases from a file
        index.getDictionaries().getAliasDictionary().importDictionary(fileName);

        // Search in the index
        String query = "@t OR @e";
        SearchResult result = index.search(query);

        Utils.traceResult(query, result);
    }
}
