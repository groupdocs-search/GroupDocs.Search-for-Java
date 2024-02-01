package com.groupdocs.search.examples.advanced_usage.indexing;

import com.groupdocs.search.*;
import com.groupdocs.search.common.*;
import com.groupdocs.search.events.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class TextFileEncodingDetection {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\TextFileEncodingDetection";
        String documentsFolder = Utils.DocumentsUtf32Path;

        // Creating an index
        Index index = new Index(indexFolder);

        // Subscribing to the event
        index.getEvents().FileIndexing.add(new EventHandler<FileIndexingEventArgs>() {
            @Override
            public void invoke(Object sender, FileIndexingEventArgs args) {
                if (args.getDocumentFullPath().endsWith(".txt")) {
                    args.setEncoding(Encodings.utf_32); // Setting encoding for each text file
                }
            }
        });

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Searching in index
        String query = "eagerness";
        SearchResult result = index.search(query);

        Utils.traceResult(query, result);
    }
}
