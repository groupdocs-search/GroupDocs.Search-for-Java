package com.groupdocs.search.examples.advanced_usage.indexing;

import com.groupdocs.search.AttributeChangeBatch;
import com.groupdocs.search.Index;
import com.groupdocs.search.SearchDocumentFilter;
import com.groupdocs.search.events.EventHandler;
import com.groupdocs.search.events.FileIndexingEventArgs;
import com.groupdocs.search.examples.Utils;
import com.groupdocs.search.options.SearchOptions;
import com.groupdocs.search.results.DocumentInfo;
import com.groupdocs.search.results.SearchResult;

public class DocumentAttributes {
    public static void changeAttributes() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\DocumentAttributes\\ChangeAttributes";
        String documentFolder = Utils.DocumentsPath;

        // Creating an index
        Index index = new Index(indexFolder);

        // Indexing documents in a document folder
        index.add(documentFolder);

        DocumentInfo[] documents = index.getIndexedDocuments();

        // Creating an attribute change container object
        AttributeChangeBatch batch = new AttributeChangeBatch();
        // Adding one attribute to all indexed documents
        batch.addToAll("public");
        // Removing one attribute from one indexed document
        batch.remove(documents[0].getFilePath(), "public");
        // Adding two attributes to one indexed document
        batch.add(documents[0].getFilePath(), "main", "key");

        // Applying attribute changes in the index
        index.changeAttributes(batch);

        // Searching in the index
        SearchOptions options = new SearchOptions();
        // Creating a document filter by attribute
        options.setSearchDocumentFilter(SearchDocumentFilter.createAttribute("main"));
        String query = "length";
        SearchResult result = index.search(query, options);

        Utils.traceResult(query, result);
    }

    public static void addAttributesDuringIndexing() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\DocumentAttributes\\AddAttributesDuringIndexing";
        String documentFolder = Utils.DocumentsPath;

        // Creating an index
        Index index = new Index(indexFolder);

        // Subscribing to the FileIndexing event for adding attributes
        index.getEvents().FileIndexing.add(new EventHandler<FileIndexingEventArgs>() {
            public void invoke(Object sender, FileIndexingEventArgs args) {
                if (args.getDocumentFullPath().endsWith("Lorem ipsum.pdf")) {
                    // Adding two attributes
                    args.setAttributes(new String[] { "main", "key" });
                }
            }
        });

        // Indexing documents in a document folder
        index.add(documentFolder);

        // Searching in the index
        SearchOptions options = new SearchOptions();
        // Creating a document filter by attribute
        options.setSearchDocumentFilter(SearchDocumentFilter.createAttribute("main"));
        String query = "ipsum";
        SearchResult result = index.search(query, options);

        Utils.traceResult(query, result);
    }
}
