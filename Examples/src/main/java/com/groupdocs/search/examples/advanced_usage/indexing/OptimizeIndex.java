package com.groupdocs.search.examples.advanced_usage.indexing;

import com.groupdocs.search.*;
import com.groupdocs.search.common.*;
import com.groupdocs.search.dictionaries.*;
import com.groupdocs.search.events.*;
import com.groupdocs.search.highlighters.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class OptimizeIndex {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\OptimizeIndex";
        String documentsFolder1 = Utils.DocumentsPath;
        String documentsFolder2 = Utils.DocumentsPath2;
        String documentsFolder3 = Utils.DocumentsPath3;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        index.add(documentsFolder1); // Indexing documents from the specified folder
        index.add(documentsFolder2); // Each call to Add creates at least one new segment in the index
        index.add(documentsFolder3);

        MergeOptions options = new MergeOptions();
        options.setCancellation(new Cancellation()); // Creating cancellation object to be able to cancel the operation
        options.getCancellation().cancelAfter(30000); // Setting maximum duration of the operation to 30 seconds

        // There are 3 segments in the index now

        // Merging segments of the index
        index.optimize(options);

        // And now in the index there is only 1 segment
    }
}
