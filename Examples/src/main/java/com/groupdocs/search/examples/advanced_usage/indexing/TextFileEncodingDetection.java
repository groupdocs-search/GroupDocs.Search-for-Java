package com.groupdocs.search.examples.advanced_usage.indexing;

import com.groupdocs.search.*;
import com.groupdocs.search.common.*;
import com.groupdocs.search.events.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

import java.io.File;
import java.io.IOException;

public class TextFileEncodingDetection {
    public static void setEncoding() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\TextFileEncodingDetection\\SetEncoding";
        String documentsFolder = Utils.DocumentsUtf32Path;

        // Creating an index
        Index index = new Index(indexFolder);

        // Subscribing to the event
        index.getEvents().FileIndexing.add(new EventHandler<FileIndexingEventArgs>() {
            @Override
            public void invoke(Object sender, FileIndexingEventArgs args) {
                if (args.getDocumentFullPath().toLowerCase().endsWith(".txt")) {
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

    // Detecting the encoding with the external library com.github.albfernandez:juniversalchardet
    public static void externalEncodingDetection() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\TextFileEncodingDetection\\ExternalEncodingDetection";
        String documentsFolder = Utils.DocumentsUtf32Path;

        // Creating an index
        Index index = new Index(indexFolder);

        // Subscribing to the event
        index.getEvents().FileIndexing.add(new EventHandler<FileIndexingEventArgs>() {
            @Override
            public void invoke(Object sender, FileIndexingEventArgs args) {
                try {
                    String encoding = org.mozilla.universalchardet.UniversalDetector.detectCharset(new File(args.getDocumentFullPath()));
                    if (encoding != null) {
                        System.out.println("Encoding detected: " + encoding);
                        args.setEncoding(encoding);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
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
