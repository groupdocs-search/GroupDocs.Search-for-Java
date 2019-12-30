package com.groupdocs.search.examples.advanced_usage.indexing;

import com.groupdocs.search.*;
import com.groupdocs.search.common.*;
import com.groupdocs.search.dictionaries.*;
import com.groupdocs.search.events.*;
import com.groupdocs.search.highlighters.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class MergeIndexes {
    public static void run() {
        String indexFolder1 = ".\\output\\AdvancedUsage\\Indexing\\MergeIndexes\\Index1";
        String indexFolder2 = ".\\output\\AdvancedUsage\\Indexing\\MergeIndexes\\Index2";
        String documentsFolder1 = Utils.DocumentsPath;
        String documentsFolder2 = Utils.DocumentsPath2;

        Index index1 = new Index(indexFolder1); // Creating index1
        index1.add(documentsFolder1); // Indexing documents

        Index index2 = new Index(indexFolder2); // Creating index2
        index2.add(documentsFolder2); // Indexing documents

        MergeOptions options = new MergeOptions();
        options.setCancellation(new Cancellation()); // Creating cancellation object to be able to cancel the oparation
        options.getCancellation().cancelAfter(5000); // Setting a time limit for the operation of 5 seconds

        System.out.println("\nBefore merge index1:");
        Utils.traceIndexedDocuments(index1);
        System.out.println("\nBefore merge index2:");
        Utils.traceIndexedDocuments(index2);

        // Merging index2 into index1
        // Files of index2 will not be changed
        index1.merge(index2, options);

        System.out.println("\n\nAfter merge index1:");
        Utils.traceIndexedDocuments(index1);
        System.out.println("\nAfter merge index2:");
        Utils.traceIndexedDocuments(index2);
    }
}
