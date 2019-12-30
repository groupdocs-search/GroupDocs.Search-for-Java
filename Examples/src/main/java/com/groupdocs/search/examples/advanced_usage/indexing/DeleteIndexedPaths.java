package com.groupdocs.search.examples.advanced_usage.indexing;

import com.groupdocs.search.*;
import com.groupdocs.search.common.*;
import com.groupdocs.search.dictionaries.*;
import com.groupdocs.search.events.*;
import com.groupdocs.search.highlighters.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class DeleteIndexedPaths {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\DeleteIndexedPaths";
        String documentsFolder1 = Utils.DocumentsPath;
        String documentsFolder2 = Utils.DocumentsPath2;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folders
        index.add(documentsFolder1);
        index.add(documentsFolder2);

        // Getting indexed paths from the index
        String[] indexedPaths1 = index.getIndexedPaths();

        // Writing indexed paths to the console
        System.out.println("Indexed paths:");
        for (String path : indexedPaths1) {
            System.out.println("\t" + path);
        }

        // Deleting index path from the index
        DeleteResult deleteResult = index.delete(new String[] { documentsFolder1 }, new UpdateOptions());

        // Getting indexed paths after deletion
        String[] indexedPaths2 = index.getIndexedPaths();
        System.out.println("\nDeleted paths: " + deleteResult.getSuccessCount());

        System.out.println("\nIndexed paths:");
        for (String path : indexedPaths2) {
            System.out.println("\t" + path);
        }
    }
}
