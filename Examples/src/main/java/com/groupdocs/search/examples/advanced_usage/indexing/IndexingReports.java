package com.groupdocs.search.examples.advanced_usage.indexing;

import com.groupdocs.search.*;
import com.groupdocs.search.common.*;
import com.groupdocs.search.examples.Utils;

public class IndexingReports {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\IndexingReports";
        String documentsFolder1 = Utils.DocumentsPath;
        String documentsFolder2 = Utils.DocumentsPath2;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents
        index.add(documentsFolder1);
        index.add(documentsFolder2);

        // Getting indexing reports
        IndexingReport[] reports = index.getIndexingReports();

        // Printing information from reports to the console
        for (IndexingReport report : reports) {
            System.out.println("Time: " + report.getStartTime());
            System.out.println("Duration: " + report.getIndexingTime());
            System.out.println("Documents total: " + report.getTotalDocumentsInIndex());
            System.out.println("Terms total: " + report.getTotalTermCount());
            System.out.println("Indexed documents size (MB): " + report.getIndexedDocumentsSize());
            System.out.println("Index size (MB): " + (report.getTotalIndexSize() / 1024.0 / 1024.0));
            System.out.println();
        }
    }
}
