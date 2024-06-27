package com.groupdocs.search.examples.advanced_usage.searching;

import com.groupdocs.search.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class NumericRangeSearch {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\NumericRangeSearch";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Search with text query
        String query1 = "400 ~~ 4000";
        SearchResult result1 = index.search(query1);

        // Search with object query
        SearchQuery query2 = SearchQuery.createNumericRangeQuery(400, 4000);
        SearchResult result2 = index.search(query2);

        Utils.traceResult(query1, result1);
        Utils.traceResult(query2.toString(), result2);
    }
}
