package com.groupdocs.search.examples.advanced_usage.indexing;

import com.groupdocs.search.*;
import com.groupdocs.search.common.*;
import com.groupdocs.search.dictionaries.*;
import com.groupdocs.search.events.*;
import com.groupdocs.search.highlighters.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class IndexingWithStopWords {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\IndexingWithStopWords";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index settings with disabled using of stop words
        IndexSettings settings = new IndexSettings();
        settings.setUseStopWords(false);

        // Creating an index in the specified folder
        Index index = new Index(indexFolder, settings);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Searching in the index
        // Now in the index it is possible to search for the stop word 'on'
        String query = "on";
        SearchResult result = index.search(query);

        Utils.traceResult(query, result);
    }
}
