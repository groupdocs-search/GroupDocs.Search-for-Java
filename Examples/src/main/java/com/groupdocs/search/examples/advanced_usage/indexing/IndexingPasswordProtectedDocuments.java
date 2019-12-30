package com.groupdocs.search.examples.advanced_usage.indexing;

import com.groupdocs.search.*;
import com.groupdocs.search.common.*;
import com.groupdocs.search.dictionaries.*;
import com.groupdocs.search.events.*;
import com.groupdocs.search.highlighters.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class IndexingPasswordProtectedDocuments {
    public static void indexingUsingThePasswordDictionary() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\IndexingPasswordProtectedDocuments\\IndexingUsingThePasswordDictionary";
        String documentsFolder = Utils.PasswordProtectedDocumentsPath;

        // Creating an index
        Index index = new Index(indexFolder);

        // Adding document passwords to the dictionary
        index.getDictionaries().getDocumentPasswords().add(Utils.PasswordProtectedDocumentsPath + "English.docx", "123456");
        index.getDictionaries().getDocumentPasswords().add(Utils.PasswordProtectedDocumentsPath + "Lorem ipsum.docx", "123456");

        // Indexing documents from the specified folder
        // Passwords will be automatically retrieved from the dictionary when necessary
        index.add(documentsFolder);

        // Searching in the index
        String query = "ipsum OR increasing";
        SearchResult result = index.search(query);

        Utils.traceResult(query, result);
    }

    public static void indexingUsingThePasswordRequiredEvent() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\IndexingPasswordProtectedDocuments\\IndexingUsingThePasswordRequiredEvent";
        String documentsFolder = Utils.PasswordProtectedDocumentsPath;

        // Creating an index
        Index index = new Index(indexFolder);

        // Subscribing to the event
        index.getEvents().PasswordRequired.add(new EventHandler<PasswordRequiredEventArgs>() {
            public void invoke(Object sender, PasswordRequiredEventArgs args) {
                if (args.getDocumentFullPath().endsWith(".docx")) {
                    args.setPassword("123456"); // Providing password for DOCX files
                }
            }
        });

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Searching in the index
        String query = "ipsum OR increasing";
        SearchResult result = index.search(query);

        Utils.traceResult(query, result);
    }
}
