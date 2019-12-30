package com.groupdocs.search.examples.advanced_usage.searching;

import com.groupdocs.search.*;
import com.groupdocs.search.common.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class SearchReports {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\SearchReports";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Searching in index
        String query1 = "water";
        SearchResult result1 = index.search(query1);
        String query2 = "\"Lorem ipsum\"";
        SearchResult result2 = index.search(query2);

        // Getting search reports
        SearchReport[] reports = index.getSearchReports();

        // Printing reports to the console
        for (SearchReport report : reports) {
            System.out.println("Query: " + report.getTextQuery());
            System.out.println("Time: " + report.getStartTime());
            System.out.println("Duration: " + report.getSearchDuration());
            System.out.println("Documents: " + report.getDocumentCount());
            System.out.println("Occurrences: " + report.getOccurrenceCount());
            System.out.println();
        }
    }
}
