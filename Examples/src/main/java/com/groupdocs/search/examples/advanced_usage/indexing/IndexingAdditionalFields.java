package com.groupdocs.search.examples.advanced_usage.indexing;

import com.groupdocs.search.*;
import com.groupdocs.search.common.*;
import com.groupdocs.search.events.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

import java.util.HashMap;

public class IndexingAdditionalFields {
    public static void run() {
        // Defining a dictionary containing subjects of documents
        final HashMap<String, String> subjects = new HashMap();
        subjects.put((Utils.DocumentsPath + "Lorem ipsum.pdf").toLowerCase(), "Latin");
        subjects.put((Utils.DocumentsPath + "English.docx").toLowerCase(), "English");
        subjects.put((Utils.DocumentsPath + "Lorem ipsum.docx").toLowerCase(), "Latin");
        subjects.put((Utils.DocumentsPath + "English.txt").toLowerCase(), "English");
        subjects.put((Utils.DocumentsPath + "Lorem ipsum.txt").toLowerCase(), "Latin");

        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\IndexingAdditionalFields";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index
        Index index = new Index(indexFolder);

        // Subscribing to the event
        index.getEvents().FileIndexing.add(new EventHandler<FileIndexingEventArgs>() {
            @Override
            public void invoke(Object sender, FileIndexingEventArgs args) {
                String subject = subjects.get(args.getDocumentFullPath().toLowerCase()); // Getting a subject for the current document
                if (subject != null) {
                    args.setAdditionalFields(new DocumentField[] { // Setting additional fields for the current document
                        new DocumentField("Subject", subject)
                    });
                }
            }
        });

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        Utils.traceIndexedDocuments(index);

        String query = "Subject: Latin";
        SearchResult result = index.search(query);

        Utils.traceResult(query, result);
    }
}
