package com.groupdocs.search.examples.advanced_usage.searching;

import com.groupdocs.search.*;
import com.groupdocs.search.common.*;
import com.groupdocs.search.dictionaries.*;
import com.groupdocs.search.events.*;
import com.groupdocs.search.highlighters.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class CaseSensitiveSearch {
    public static void queryInTextForm() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\CaseSensitiveSearch\\QueryInTextForm";
        String documentsFolder = Utils.DocumentsPath;

        Index index = new Index(indexFolder); // Creating index in the specified folder
        index.add(documentsFolder); // Indexing documents from the specified folder

        SearchOptions options = new SearchOptions();
        options.setUseCaseSensitiveSearch(true); // Enabling case sensitive search

        String query = "Advantages";
        SearchResult result = index.search(query, options); // Searching

        Utils.traceResult(query, result);
    }

    public static void queryInObjectForm() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\CaseSensitiveSearch\\QueryInObjectForm";
        String documentsFolder = Utils.DocumentsPath;

        Index index = new Index(indexFolder); // Creating index in the specified folder
        index.add(documentsFolder); // Indexing documents from the specified folder

        SearchOptions options = new SearchOptions();
        options.setUseCaseSensitiveSearch(true); // Enabling case sensitive search

        SearchQuery query = SearchQuery.createWordQuery("Advantages"); // Creating search query in object form

        SearchResult result = index.search(query, options); // Searching

        Utils.traceResult(query.toString(), result);
    }
}
