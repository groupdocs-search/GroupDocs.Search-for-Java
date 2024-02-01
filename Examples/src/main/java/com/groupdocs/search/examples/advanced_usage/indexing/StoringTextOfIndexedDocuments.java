package com.groupdocs.search.examples.advanced_usage.indexing;

import com.groupdocs.search.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class StoringTextOfIndexedDocuments {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\StoringTextOfIndexedDocuments";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index settings instance
        IndexSettings settings = new IndexSettings();
        settings.setTextStorageSettings(new TextStorageSettings(Compression.High)); // Setting high compression ratio for the index text storage

        // Creating an index in the specified folder
        Index index = new Index(indexFolder, settings);

        // Indexing documents
        index.add(documentsFolder);

        // Now the index contains the text of all indexed documents,
        // so the operations of getting the text of documents and highlighting occurrences are faster.

        // Searching
        String query = "Lorem";
        SearchResult result = index.search(query);

        Utils.traceResult(query, result);
    }
}
