package com.groupdocs.search.examples.basic_usage;

import com.groupdocs.search.*;
import com.groupdocs.search.common.*;
import com.groupdocs.search.events.*;
import com.groupdocs.search.highlighters.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

import java.nio.file.Paths;

public class BuildYourFirstSearchSolution {
    public static void runSynchronousIndexing() {
        String indexFolder = ".\\output\\BasicUsage\\BuildYourFirstSearchSolution\\SynchronousIndexing"; // Specify the path to the index folder
        String documentsFolder = Utils.DocumentsPath; // Specify the path to a folder containing documents to search

        // a) Create new index or
        // b) Open existing index
        Index index = new Index(indexFolder);

        // c) Subscribe to index events
        index.getEvents().ErrorOccurred.add(new EventHandler<IndexErrorEventArgs>() {
            @Override
            public void invoke(Object sender, IndexErrorEventArgs args) {
                System.out.println(args.getMessage()); // Writing error messages to the console
            }
        });

        // d) Add files synchronously
        index.add(documentsFolder); // Synchronous indexing documents from the specified folder

        // f) Perform search
        String query = "tincidunt"; // Specify a search query
        SearchResult result = index.search(query); // Searching in the index

        // g) Use search results
        // Printing the result
        System.out.println("Documents found: " + result.getDocumentCount());
        System.out.println("Total occurrences found: " + result.getOccurrenceCount());
        for (int i = 0; i < result.getDocumentCount(); i++) {
            FoundDocument document = result.getFoundDocument(i);
            System.out.println("\tDocument: " + document.getDocumentInfo().getFilePath());
            System.out.println("\tOccurrences: " + document.getOccurrenceCount());
        }

        // Highlight occurrences in text
        if (result.getDocumentCount() > 0) {
            FoundDocument document = result.getFoundDocument(0); // Getting the first found document
            String path = ".\\output\\BasicUsage\\Highlighted.html";
            OutputAdapter outputAdapter = new FileOutputAdapter(OutputFormat.Html, path); // Creating the output adapter to a file
            DocumentHighlighter highlighter = new DocumentHighlighter(outputAdapter); // Creating the highlighter object
            index.highlight(document, highlighter); // Generating output HTML formatted document with highlighted search results

            System.out.println();
            System.out.println("Generated HTML file can be opened with Internet browser.");
            System.out.println("The file can be found by the following path:");
            System.out.println(Paths.get(path).toAbsolutePath().toString());
        }
    }

    public static void runAsynchronousIndexing() {
        String indexFolder = ".\\output\\BasicUsage\\BuildYourFirstSearchSolution\\AsynchronousIndexing"; // Specify the path to the index folder
        String documentsFolder = Utils.DocumentsPath; // Specify the path to a folder containing documents to search

        // a) Create new index or
        // b) Open existing index
        Index index = new Index(indexFolder);

        // c) Subscribe to ErrorOccurred events
        index.getEvents().ErrorOccurred.add(new EventHandler<IndexErrorEventArgs>() {
            @Override
            public void invoke(Object sender, IndexErrorEventArgs args) {
                System.out.println(args.getMessage()); // Writing error messages to the console
            }
        });

        // c) Subscribe to StatusChanged event
        index.getEvents().StatusChanged.add(new EventHandler<BaseIndexEventArgs>() {
            @Override
            public void invoke(Object sender, BaseIndexEventArgs args) {
                if (args.getStatus() != IndexStatus.Ready || args.getStatus() == IndexStatus.Failed) {
                    // There should be a code indicating the completion of the operation
                    System.out.println("Indexing completed.");
                }
            }
        });

        // e) Add files asynchronously
        // Setting the flag for asynchronous indexing
        IndexingOptions options = new IndexingOptions();
        options.setAsync(true);

        // Asynchronous indexing documents from the specified folder
        // The current method terminates before the operation completes
        index.add(documentsFolder, options);
    }
}
