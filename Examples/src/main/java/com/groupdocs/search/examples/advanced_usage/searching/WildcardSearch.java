package com.groupdocs.search.examples.advanced_usage.searching;

import com.groupdocs.search.*;
import com.groupdocs.search.common.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class WildcardSearch {
    public static void queryInTextForm() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\WildcardSearch\\QueryInTextForm";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Search in the index
        String query1 = "m???is";
        SearchResult result1 = index.search(query1); // Search for 'mauris', 'mollis', 'mattis', 'magnis', etc.
        String query2 = "pri?(1~7)";
        SearchResult result2 = index.search(query2); // Search for 'private', 'principles', 'principle', etc.

        Utils.traceResult(query1, result1);
        Utils.traceResult(query2, result2);
    }

    public static void queryInObjectForm() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\WildcardSearch\\QueryInObjectForm";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Search with pattern "m???is"
        // Search for 'mauris', 'mollis', 'mattis', 'magnis', etc.
        WordPattern pattern1 = new WordPattern();
        pattern1.appendString("m");
        pattern1.appendOneCharacterWildcard();
        pattern1.appendOneCharacterWildcard();
        pattern1.appendOneCharacterWildcard();
        pattern1.appendString("is");
        SearchQuery query1 = SearchQuery.createWordPatternQuery(pattern1);
        SearchResult result1 = index.search(query1);

        // Search with pattern "pri?(1~7)"
        // Search for 'private', 'principles', 'principle', etc.
        WordPattern pattern2 = new WordPattern();
        pattern2.appendString("pri");
        pattern2.appendWildcard(1, 7);
        SearchQuery query2 = SearchQuery.createWordPatternQuery(pattern2);
        SearchResult result2 = index.search(query2);

        Utils.traceResult(query1.toString(), result1);
        Utils.traceResult(query2.toString(), result2);
    }
}
