package com.groupdocs.search.examples.advanced_usage.indexing;

import com.groupdocs.search.Index;
import com.groupdocs.search.IndexSettings;
import com.groupdocs.search.results.SearchResult;
import com.groupdocs.search.examples.Utils;

public class CustomTextExtractors {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\CustomTextExtractors"; // Specify path to the index folder
        String documentsFolder = Utils.LogPath; // Specify path to a folder containing documents to search

        IndexSettings settings = new IndexSettings();
        settings.getCustomExtractors().addItem(new LogExtractor()); // Adding custom text extractor to the index settings

        Index index = new Index(indexFolder, settings, true); // Creating or loading an index

        index.add(documentsFolder); // Indexing documents from the specified folder

        String query1 = "objection";
        SearchResult result1 = index.search(query1); // Searching

        String query2 = "log";
        SearchResult result2 = index.search(query2); // Searching

        Utils.traceResult(query1, result1);
        Utils.traceResult(query2, result2);
    }
}
