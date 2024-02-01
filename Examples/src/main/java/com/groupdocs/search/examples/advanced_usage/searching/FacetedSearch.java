package com.groupdocs.search.examples.advanced_usage.searching;

import com.groupdocs.search.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class FacetedSearch {
    public static void simpleFacetedSearch() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\FacetedSearch\\SimpleFacetedSearch";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Search in the content field with text query
        String query1 = "content: Pellentesque";
        SearchResult result1 = index.search(query1);

        // Search in the content field with object query
        SearchQuery wordQuery = SearchQuery.createWordQuery("Pellentesque");
        SearchQuery fieldQuery = SearchQuery.createFieldQuery(CommonFieldNames.Content, wordQuery);

        SearchResult result2 = index.search(fieldQuery);
        Utils.traceResult(query1, result1);
        Utils.traceResult(fieldQuery.toString(), result2);
    }

    public static void complexQuery() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\FacetedSearch\\ComplexQuery";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Search with text query
        String query1 = "(filename: (lorem AND ipsum)) OR (content: (\"lectus eu aliquam\" OR \"dignissim turpis\"))";
        SearchResult result1 = index.search(query1);

        // Search with object query
        SearchQuery word6Query = SearchQuery.createWordQuery("lorem");
        SearchQuery word7Query = SearchQuery.createWordQuery("ipsum");
        SearchQuery andQuery = SearchQuery.createAndQuery(word6Query, word7Query);
        SearchQuery filenameQuery = SearchQuery.createFieldQuery(CommonFieldNames.FileName, andQuery);

        SearchQuery word1Query = SearchQuery.createWordQuery("lectus");
        SearchQuery word2Query = SearchQuery.createWordQuery("eu");
        SearchQuery word3Query = SearchQuery.createWordQuery("aliquam");
        SearchQuery word4Query = SearchQuery.createWordQuery("dignissim");
        SearchQuery word5Query = SearchQuery.createWordQuery("turpis");

        SearchQuery phrase1Query = SearchQuery.createPhraseSearchQuery(word1Query, word2Query, word3Query);
        SearchQuery phrase2Query = SearchQuery.createPhraseSearchQuery(word4Query, word5Query);
        SearchQuery orQuery = SearchQuery.createOrQuery(phrase1Query, phrase2Query);
        SearchQuery contentQuery = SearchQuery.createFieldQuery(CommonFieldNames.Content, orQuery);

        SearchQuery rootQuery = SearchQuery.createOrQuery(filenameQuery, contentQuery);
        SearchResult result2 = index.search(rootQuery);

        Utils.traceResult(query1, result1);
        Utils.traceResult(rootQuery.toString(), result2);
    }
}
