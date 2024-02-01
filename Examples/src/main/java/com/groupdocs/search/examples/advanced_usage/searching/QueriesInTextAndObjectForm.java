package com.groupdocs.search.examples.advanced_usage.searching;

import com.groupdocs.search.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class QueriesInTextAndObjectForm {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\QueriesInTextAndObjectForm";
        String documentsFolder = Utils.DocumentsPath;

        // Creating index
        Index index = new Index(indexFolder);

        // Indexing
        index.add(documentsFolder);

        // Creating subquery 1 - simple word query
        SearchQuery subquery1 = SearchQuery.createWordQuery("future");
        subquery1.setSearchOptions(new SearchOptions()); // Setting search options only for subquery 1
        subquery1.getSearchOptions().getFuzzySearch().setEnabled(true);
        subquery1.getSearchOptions().getFuzzySearch().setFuzzyAlgorithm(new TableDiscreteFunction(3)); // The maximum number of differences is 3

        // Creating subquery 2 - wildcard query
        SearchQuery subquery2 = SearchQuery.createWildcardQuery(1);

        // Creating subquery 3 - regular expression query
        SearchQuery subquery3 = SearchQuery.createRegexQuery("(.)\\1");

        // Combining subqueries into one query - phrase search query
        SearchQuery query = SearchQuery.createPhraseSearchQuery(subquery1, subquery2, subquery3);

        // Creating overall search options with increased capacity of occurrences
        SearchOptions options = new SearchOptions();
        options.setMaxOccurrenceCountPerTerm(1000000);
        options.setMaxTotalOccurrenceCount(10000000);

        // Searching
        SearchResult result = index.search(query, options);

        // The result may contain the following word sequences:
        // futile * blessed
        // father * excellent
        // tyre * assyria
        // return * 229

        Utils.traceResult(query.toString(), result);
    }
}
