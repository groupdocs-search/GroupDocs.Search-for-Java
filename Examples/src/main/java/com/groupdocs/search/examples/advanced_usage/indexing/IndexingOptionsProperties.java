package com.groupdocs.search.examples.advanced_usage.indexing;

import com.groupdocs.search.*;
import com.groupdocs.search.common.*;
import com.groupdocs.search.dictionaries.*;
import com.groupdocs.search.events.*;
import com.groupdocs.search.highlighters.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class IndexingOptionsProperties {
    public static void cancellationProperty() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\IndexingOptionsProperties\\CancellationProperty";
        String documentFolder = Utils.DocumentsPath;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Creating an instance of indexing options
        IndexingOptions options = new IndexingOptions();
        options.setCancellation(new Cancellation()); // Setting a cancellation object
        options.getCancellation().cancelAfter(3000); // Setting a time period of 3 seconds after which the indexing operation will be cancelled

        // Indexing documents from the specified folder
        index.add(documentFolder, options);

        Utils.traceIndexedDocuments(index);
    }

    public static void isAsyncProperty() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\IndexingOptionsProperties\\IsAsyncProperty";
        String documentFolder = Utils.DocumentsPath;

        // Creating index in the specified folder
        Index index = new Index(indexFolder);

        // Subscribing to the event
        index.getEvents().StatusChanged.add(new EventHandler<BaseIndexEventArgs>() {
            public void invoke(Object sender, BaseIndexEventArgs args) {
                if (args.getStatus() != IndexStatus.InProgress) {
                    // A notification of the operation completion should be here
                    System.out.println("Status: " + args.getStatus());
                }
            }
        });

        // Creating an instance of indexing options
        IndexingOptions options = new IndexingOptions();
        options.setAsync(true); // Specifying the asynchronous performing of the operation

        // Indexing documents from the specified folder
        // The method will return control before the indexing operation is completed
        index.add(documentFolder, options);
    }

    public static void threadsProperty() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\IndexingOptionsProperties\\ThreadsProperty";
        String documentFolder = Utils.DocumentsPath;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Creating an instance of indexing options
        IndexingOptions options = new IndexingOptions();
        options.setThreads(2); // Setting the number of indexing threads

        // Indexing documents from the specified folder
        index.add(documentFolder, options);

        Utils.traceIndexedDocuments(index);
    }
}
