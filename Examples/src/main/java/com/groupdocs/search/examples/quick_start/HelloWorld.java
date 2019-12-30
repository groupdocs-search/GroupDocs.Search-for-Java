package com.groupdocs.search.examples.quick_start;

import com.groupdocs.search.Index;
import com.groupdocs.search.results.SearchResult;
import com.groupdocs.search.examples.Utils;

public class HelloWorld {
    public static void run() {
        String indexFolder = ".\\output\\GettingStarted\\HelloWorld";
        String documentsFolder = Utils.DocumentsPath;

        // Creating index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Searching in index
        SearchResult result = index.search("Lorem");

        System.out.println("Documents found: " + result.getDocumentCount());
    }
}
