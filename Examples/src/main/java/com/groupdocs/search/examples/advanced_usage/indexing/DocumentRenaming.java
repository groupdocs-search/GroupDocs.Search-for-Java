package com.groupdocs.search.examples.advanced_usage.indexing;

import com.groupdocs.search.Index;
import com.groupdocs.search.Notification;
import com.groupdocs.search.examples.Utils;

import java.io.File;

public class DocumentRenaming {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\DocumentRenaming\\Index";
        String documentFolder = ".\\output\\AdvancedUsage\\Indexing\\DocumentRenaming\\Documents\\";

        // Prepare data
        Utils.cleanDirectory(documentFolder);
        Utils.copyFiles(Utils.DocumentsPath, documentFolder);

        // Creating an index
        Index index = new Index(indexFolder);

        // Indexing documents in a document folder
        index.add(documentFolder);

        System.out.println("\nBefore renaming:");
        Utils.traceIndexedDocuments(index);

        // Renaming a document
        String oldDocumentPath = documentFolder + "Lorem ipsum.txt";
        String newDocumentPath = documentFolder + "Lorem ipsum renamed.txt";
        new File(oldDocumentPath).renameTo(new File(newDocumentPath));

        // Notifying the index about renaming
        Notification notification = Notification.createRenameNotification(oldDocumentPath, newDocumentPath);
        boolean result = index.notifyIndex(notification);
        System.out.println("\nSuccessful rename: " + result);

        // Updating the index
        // The renamed document will not be reindexed
        index.update();

        System.out.println("\nAfter renaming:");
        Utils.traceIndexedDocuments(index);
    }
}
