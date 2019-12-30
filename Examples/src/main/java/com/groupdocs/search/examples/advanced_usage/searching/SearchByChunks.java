package com.groupdocs.search.examples.advanced_usage.searching;

import com.groupdocs.search.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class SearchByChunks {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\SearchByChunks";
        String documentsFolder1 = Utils.DocumentsPath;
        String documentsFolder2 = Utils.DocumentsPath2;
        String documentsFolder3 = Utils.DocumentsPath3;
        String query = "invitation";

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder1);
        index.add(documentsFolder2);
        index.add(documentsFolder3);

        // Creating a search options instance
        SearchOptions options = new SearchOptions();
        options.setChunkSearch(true); // Enabling the search by chunks

        // Starting the search by chunks
        SearchResult result = index.search(query, options);
        System.out.println("Document count: " + result.getDocumentCount());
        System.out.println("Occurrence count: " + result.getOccurrenceCount());

        // Continuing the search by chunks
        while (result.getNextChunkSearchToken() != null) {
            result = index.searchNext(result.getNextChunkSearchToken());
            System.out.println();
            System.out.println("Document count: " + result.getDocumentCount());
            System.out.println("Occurrence count: " + result.getOccurrenceCount());
        }
    }
}
