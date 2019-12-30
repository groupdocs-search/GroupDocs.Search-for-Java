package com.groupdocs.search.examples.advanced_usage.searching;

import com.groupdocs.search.*;
import com.groupdocs.search.common.*;
import com.groupdocs.search.dictionaries.*;
import com.groupdocs.search.events.*;
import com.groupdocs.search.highlighters.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class WildcardSearch {
    public static void queryInTextForm() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\WildcardSearch\\QueryInTextForm";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Search in the index
        String query1 = "m???is";
        SearchResult result1 = index.search(query1); // Search for 'mauris', 'mollis', 'mattis', 'magnis', etc.
        String query2 = "pri?(1~7)";
        SearchResult result2 = index.search(query2); // Search for 'private', 'principles', 'principle', etc.

        Utils.traceResult(query1, result1);
        Utils.traceResult(query2, result2);
    }
}
