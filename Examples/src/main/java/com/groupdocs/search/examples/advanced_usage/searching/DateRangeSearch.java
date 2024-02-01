package com.groupdocs.search.examples.advanced_usage.searching;

import com.groupdocs.search.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class DateRangeSearch {
    public static void creatingDateRangeSearchQueries() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\DateRangeSearch\\CreatingDateRangeSearchQueries";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Search for dates using query in text form
        String query1 = "daterange(2017-01-01 ~~ 2019-12-31)";

        SearchResult result1 = index.search(query1);
        // Search for dates using query in text form
        SearchQuery query2 = SearchQuery.createDateRangeQuery(Utils.createDate(2017, 1, 1), Utils.createDate(2019, 12, 31));
        SearchResult result2 = index.search(query2);

        Utils.traceResult(query1, result1);
        Utils.traceResult(query2.toString(), result2);
    }

    public static void specifyingDateRangeSearchFormats() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\DateRangeSearch\\SpecifyingDateRangeSearchFormats";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Setting date formats
        SearchOptions options = new SearchOptions();
        options.getDateFormats().clear(); // Removing default date formats
        DateFormatElement[] elements = new DateFormatElement[] {
            DateFormatElement.getMonthTwoDigits(),
            DateFormatElement.getDateSeparator(),
            DateFormatElement.getDayOfMonthTwoDigits(),
            DateFormatElement.getDateSeparator(),
            DateFormatElement.getYearFourDigits(),
        };
        // Creating a date format pattern 'MM/dd/yyyy'
        DateFormat dateFormat = new DateFormat(elements, "/");
        options.getDateFormats().addItem(dateFormat);

        // Searching in the index.
        // For the given query, for example, the date 09/27/2019 will be found,
        // but the date 2019-09-27 will not be found, because it is presented in a format that is not specified in the search options.
        String query = "daterange(2017-01-01 ~~ 2019-12-31)";
        SearchResult result = index.search(query, options);

        Utils.traceResult(query, result);
    }
}
