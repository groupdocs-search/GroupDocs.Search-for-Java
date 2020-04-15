package com.groupdocs.search.examples.advanced_usage.creating_an_index;

import com.groupdocs.search.*;
import com.groupdocs.search.common.*;
import com.groupdocs.search.dictionaries.*;
import com.groupdocs.search.events.*;
import com.groupdocs.search.highlighters.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

import java.text.SimpleDateFormat;

public class UsingEvents {
    public static void operationFinishedEvent() {
        String indexFolder = ".\\output\\AdvancedUsage\\CreatingAnIndex\\UsingEvents\\OperationFinishedEvent";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index
        Index index = new Index(indexFolder);

        // Subscribing to the event
        index.getEvents().OperationFinished.add(new EventHandler<OperationFinishedEventArgs>() {
            public void invoke(Object sender, OperationFinishedEventArgs args) {
                System.out.println("Operation finished: " + args.getOperationType());
                System.out.println("Message: " + args.getMessage());
                System.out.println("Index folder: " + args.getIndexFolder());
                SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                System.out.println("Time: " + df.format(args.getTime()));
            }
        });

        // Indexing documents from the specified folder
        index.add(documentsFolder);
    }

    public static void errorOccurredEvent() {
        String indexFolder = ".\\output\\AdvancedUsage\\CreatingAnIndex\\UsingEvents\\ErrorOccurredEvent";
        String documentsFolder = Utils.PasswordProtectedDocumentsPath;
        String query = "Lorem";

        // Creating an index
        Index index = new Index(indexFolder);

        // Subscribing to the event
        index.getEvents().ErrorOccurred.add(new EventHandler<IndexErrorEventArgs>() {
            public void invoke(Object sender, IndexErrorEventArgs args) {
                System.out.println(args.getMessage());
            }
        });

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Searching in the index
        SearchResult result = index.search(query);
    }

    public static void operationProgressChangedEvent() {
        String indexFolder = ".\\output\\AdvancedUsage\\CreatingAnIndex\\UsingEvents\\OperationProgressChangedEvent";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index
        Index index = new Index(indexFolder);

        // Subscribing to the event
        index.getEvents().OperationProgressChanged.add(new EventHandler<OperationProgressEventArgs>() {
            public void invoke(Object sender, OperationProgressEventArgs args) {
                System.out.println("Last processed: " + args.getLastDocumentPath());
                System.out.println("Result: " + args.getLastDocumentStatus());
                System.out.println("Processed documents: " + args.getProcessedDocuments());
                System.out.println("Progress percentage: " + args.getProgressPercentage());
            }
        });

        // Indexing documents from the specified folder
        index.add(documentsFolder);
    }

    public static void passwordRequiredEvent() {
        String indexFolder = ".\\output\\AdvancedUsage\\CreatingAnIndex\\UsingEvents\\PasswordRequiredEvent";
        String documentsFolder = Utils.PasswordProtectedDocumentsPath;

        // Creating an index
        Index index = new Index(indexFolder);

        // Subscribing to the event
        index.getEvents().PasswordRequired.add(new EventHandler<PasswordRequiredEventArgs>() {
            public void invoke(Object sender, PasswordRequiredEventArgs args) {
                if (args.getDocumentFullPath().endsWith("English.docx")) {
                    args.setPassword("123456");
                }
                else if (args.getDocumentFullPath().endsWith("Lorem ipsum.docx")) {
                    args.setPassword("123456");
                }
            }
        });

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Searching in the index
        String query = "Lorem OR sportsman";
        SearchResult result = index.search(query);

        Utils.traceResult(query, result);
    }

    public static void fileIndexingEvent() {
        String indexFolder = ".\\output\\AdvancedUsage\\CreatingAnIndex\\UsingEvents\\FileIndexingEvent";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index
        Index index = new Index(indexFolder);

        // Subscribing to the event
        index.getEvents().FileIndexing.add(new EventHandler<FileIndexingEventArgs>() {
            public void invoke(Object sender, FileIndexingEventArgs args) {
                if (args.getDocumentFullPath().endsWith("Lorem ipsum.docx")) {
                    args.setAdditionalFields(new DocumentField[] {
                            new DocumentField("Tags", "Lorem")
                    });
                }
                if (!args.getDocumentFullPath().toLowerCase().contains("lorem")) {
                    args.setSkipIndexing(true);
                }
            }
        });

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Searching in the index
        String query = "Tags:lorem";
        SearchResult result = index.search(query);

        Utils.traceResult(query, result);
    }

    public static void statusChangedEvent() {
        String indexFolder = ".\\output\\AdvancedUsage\\CreatingAnIndex\\UsingEvents\\StatusChangedEvent";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index
        Index index = new Index(indexFolder);

        // Subscribing to the event
        index.getEvents().StatusChanged.add(new EventHandler<BaseIndexEventArgs>() {
            public void invoke(Object sender, BaseIndexEventArgs args) {
                if (args.getStatus() == IndexStatus.Ready || args.getStatus() == IndexStatus.Failed) {
                    // A notification of the operation completion should be here
                    System.out.println("Operation finished!");
                }
            }
        });

        // Setting the flag for asynchronous indexing
        IndexingOptions options = new IndexingOptions();
        options.setAsync(true);

        // Asynchronous indexing documents from the specified folder
        // The method terminates before the operation completes
        index.add(documentsFolder, options);
    }

    public static void searchPhaseCompletedEvent() {
        String indexFolder = ".\\output\\AdvancedUsage\\CreatingAnIndex\\UsingEvents\\SearchPhaseCompletedEvent";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Subscribing to the event
        index.getEvents().SearchPhaseCompleted.add(new EventHandler<SearchPhaseEventArgs>() {
            public void invoke(Object sender, SearchPhaseEventArgs args) {
                System.out.println("Search phase: " + args.getSearchPhase());
                System.out.println("Words: " + args.getWords().length);
                for (int i = 0; i < args.getWords().length; i++) {
                    System.out.println("\t" + args.getWords()[i]);
                }
                System.out.println();
            }
        });

        SearchOptions options = new SearchOptions();
        options.setUseSynonymSearch(true);
        options.setUseWordFormsSearch(true);
        options.getFuzzySearch().setEnabled(true);
        options.setUseHomophoneSearch(true);
        SearchResult result = index.search("buy", options);
    }
}
