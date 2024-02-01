package com.groupdocs.search.examples.advanced_usage.indexing;

import com.groupdocs.search.*;
import com.groupdocs.search.common.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class Logging {
    public static void useOfStandardFileLogger() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\Logging\\UseOfStandardFileLogger";
        String documentsFolder = Utils.DocumentsPath;
        String query = "Lorem";
        String logPath = ".\\output\\AdvancedUsage\\Indexing\\Logging\\Log.txt";

        IndexSettings settings = new IndexSettings();
        settings.setLogger(new FileLogger(logPath, 4.0)); // Specifying the path to the log file and a maximum length of 4 MB

        Index index = new Index(indexFolder, settings); // Creating or loading an index from the specified folder

        index.add(documentsFolder); // Indexing documents from the specified folder

        SearchResult result = index.search(query); // Search in index

        Utils.traceResult(query, result);
    }

    public static void implementingCustomLogger() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\Logging\\ImplementingCustomLogger";
        String documentsFolder = Utils.DocumentsPath;
        String query = "Lorem";

        IndexSettings settings = new IndexSettings();
        settings.setLogger(new ConsoleLogger()); // Setting the custom logger

        Index index = new Index(indexFolder, settings); // Creating or loading an index from the specified folder

        index.add(documentsFolder); // Indexing documents from the specified folder

        SearchResult result = index.search(query); // Search in index

        Utils.traceResult(query, result);
    }
}
