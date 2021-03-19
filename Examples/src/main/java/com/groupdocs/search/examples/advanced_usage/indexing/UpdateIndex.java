package com.groupdocs.search.examples.advanced_usage.indexing;

import com.groupdocs.search.*;
import com.groupdocs.search.common.*;
import com.groupdocs.search.dictionaries.*;
import com.groupdocs.search.events.*;
import com.groupdocs.search.highlighters.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class UpdateIndex {
    public static void updateIndexedDocuments() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\UpdateIndex\\UpdateIndexedDocuments\\Index";
        String documentFolder = ".\\output\\AdvancedUsage\\Indexing\\UpdateIndex\\UpdateIndexedDocuments\\Documents";
        String query = "son";

        // Prepare data
        Utils.cleanDirectory(documentFolder);
        Utils.copyFiles(Utils.DocumentsPath, documentFolder);


        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentFolder);

        SearchResult searchResult = index.search(query);
        Utils.traceResult(query, searchResult);


        // Change, delete, add documents in the document folder
        // ...
        // Adding documents to indexed folder
        Utils.copyFiles(Utils.DocumentsPath4, documentFolder);


        UpdateOptions options = new UpdateOptions();
        options.setThreads(2); // Setting the number of indexing threads
        index.update(options); // Updating the index

        SearchResult searchResult2 = index.search(query);
        Utils.traceResult(query, searchResult2);
    }

    public static void updateIndexVersion() {
        String oldIndexFolder = Utils.OldIndexPath;
        String sourceIndexFolder = ".\\output\\AdvancedUsage\\Indexing\\UpdateIndex\\UpdateIndexVersion\\SourceIndex";
        String targetIndexFolder = ".\\output\\AdvancedUsage\\Indexing\\UpdateIndex\\UpdateIndexVersion\\TargetIndex";

        // Prepare data
        Utils.cleanDirectory(sourceIndexFolder);
        Utils.cleanDirectory(targetIndexFolder);
        Utils.copyFiles(oldIndexFolder, sourceIndexFolder);


        // Creating updater
        IndexUpdater updater = new IndexUpdater();

        if (updater.canUpdateVersion(sourceIndexFolder)) {
            // The index of old version does not change
            VersionUpdateResult result = updater.updateVersion(sourceIndexFolder, targetIndexFolder);
        }

        // Loading index from target folder
        Index index = new Index(targetIndexFolder);

        // Searching in index
        String query = "eagerness";
        SearchResult searchResult = index.search(query);

        Utils.traceResult(query, searchResult);
    }
}
