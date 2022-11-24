package com.groupdocs.search.examples.advanced_usage.searching;

import com.groupdocs.search.*;
import com.groupdocs.search.common.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class GettingIndexedDocuments {
    public static void gettingDocuments() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\GettingIndexedDocuments\\GettingDocuments";
        String documentsFolder = Utils.ArchivesPath;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Getting list of indexed documents
        DocumentInfo[] documents = index.getIndexedDocuments();
        for (int i = 0; i < documents.length; i++) {
            DocumentInfo document = documents[i];
            System.out.println(document.getFilePath());
            DocumentInfo[] items = index.getIndexedDocumentItems(document); // Getting list of document items
            for (int j = 0; j < items.length; j++) {
                DocumentInfo item = items[j];
                System.out.println("\t" + item.getInnerPath());
            }
        }
    }

    public static void gettingTextOfIndexedDocuments() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\GettingIndexedDocuments\\GettingTextOfIndexedDocuments";
        String documentsFolder = Utils.ArchivesPath;

        // Creating an index settings instance
        IndexSettings settings = new IndexSettings();
        settings.setTextStorageSettings(new TextStorageSettings(Compression.High)); // Enabling the storage of extracted text in the index

        // Creating an index in the specified folder
        Index index = new Index(indexFolder, settings);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Getting list of indexed documents
        DocumentInfo[] documents = index.getIndexedDocuments();

        // Getting a document text
        if (documents.length > 0) {
            FileOutputAdapter outputAdapter = new FileOutputAdapter(OutputFormat.Html, ".\\output\\AdvancedUsage\\Searching\\GettingIndexedDocuments\\Text.html");
            index.getDocumentText(documents[0], outputAdapter);

            // Getting list of files in the archive
            DocumentInfo[] items = index.getIndexedDocumentItems(documents[0]);
            if (items.length > 0) {
                FileOutputAdapter outputAdapter2 = new FileOutputAdapter(OutputFormat.Html, ".\\output\\AdvancedUsage\\Searching\\GettingIndexedDocuments\\ItemText.html");
                index.getDocumentText(items[0], outputAdapter2);
            }
        }
    }
}
