package com.groupdocs.search.examples.advanced_usage.indexing;

import com.groupdocs.search.*;
import com.groupdocs.search.common.*;
import com.groupdocs.search.dictionaries.*;
import com.groupdocs.search.events.*;
import com.groupdocs.search.highlighters.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class IndexingMetadataOfDocuments {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\IndexingMetadataOfDocuments";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an instance of index settings
        IndexSettings settings = new IndexSettings();
        settings.setIndexType(IndexType.MetadataIndex); // Setting index type

        // Creating an index in the specified folder
        Index index = new Index(indexFolder, settings);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Searching in the index
        String query = "English";
        SearchResult result = index.search(query);

        Utils.traceResult(query, result);
    }
}
