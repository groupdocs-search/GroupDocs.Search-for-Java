package com.groupdocs.search.examples.basic_usage;

import com.groupdocs.search.*;
import com.groupdocs.search.common.*;
import com.groupdocs.search.dictionaries.*;
import com.groupdocs.search.events.*;
import com.groupdocs.search.highlighters.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class BuildSearchQuery {
    public static void run() {
        String indexFolder = ".\\output\\BasicUsage\\BuildSearchQuery";
        String documentsFolder = Utils.DocumentsPath;

        // Creating index in the specified folder
        Index index = new Index(indexFolder);

        // Subscribe to the event
        index.getEvents().ErrorOccurred.add(new EventHandler<IndexErrorEventArgs>() {
            public void invoke(Object sender, IndexErrorEventArgs args) {
                System.out.println(args.getMessage()); // Writing error messages to the console
            }
        });

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Simple search query
        {
            String query = "volutpat";
            SearchResult result = index.search(query);
            System.out.println("Query: " + query);
            System.out.println("Documents: " + result.getDocumentCount());
            System.out.println("Occurrences: " + result.getOccurrenceCount());
            System.out.println();
        }

        // Wildcard search query
        {
            String query = "?ffect";
            SearchResult result = index.search(query); // Search for words 'affect', 'effect', ets.
            System.out.println("Query: " + query);
            System.out.println("Documents: " + result.getDocumentCount());
            System.out.println("Occurrences: " + result.getOccurrenceCount());
            System.out.println();
        }

        // Faceted search query
        {
            String query = "Content: magna";
            SearchResult result = index.search(query); // Search for word 'magna' only in 'Content' field
            System.out.println("Query: " + query);
            System.out.println("Documents: " + result.getDocumentCount());
            System.out.println("Occurrences: " + result.getOccurrenceCount());
            System.out.println();
        }

        // Numeric range search query
        {
            String query = "2000 ~~ 3000";
            SearchResult result = index.search(query); // Search for numbers from 2000 to 3000
            System.out.println("Query: " + query);
            System.out.println("Documents: " + result.getDocumentCount());
            System.out.println("Occurrences: " + result.getOccurrenceCount());
            System.out.println();
        }

        // Date range search query
        {
            SearchOptions options = new SearchOptions(); // Creating a search options object
            options.getDateFormats().clear(); // Removing default date formats

            // Creating a date format pattern 'MM/dd/yyyy'
            DateFormatElement[] elements = new DateFormatElement[] {
                    DateFormatElement.getMonthTwoDigits(),
                    DateFormatElement.getDateSeparator(),
                    DateFormatElement.getDayOfMonthTwoDigits(),
                    DateFormatElement.getDateSeparator(),
                    DateFormatElement.getYearFourDigits(),
            };
            DateFormat dateFormat = new DateFormat(elements, "/");
            options.getDateFormats().addItem(dateFormat); // Adding the date format pattern to the date format collection

            String query = "daterange(2000-01-01 ~~ 2001-06-15)"; // Dates in the search query are always specified in the format 'yyyy-MM-dd'
            SearchResult result = index.search(query, options); // Search in index
            System.out.println("Query: " + query);
            System.out.println("Documents: " + result.getDocumentCount());
            System.out.println("Occurrences: " + result.getOccurrenceCount());
            System.out.println();
        }

        // Regular expression search query
        {
            String query = "^(.)\\1{2,}"; // The caret character at the beginning indicates that this is a regular expression search query
            SearchResult result = index.search(query); // Search for three or more identical characters in a row
            System.out.println("Query: " + query);
            System.out.println("Documents: " + result.getDocumentCount());
            System.out.println("Occurrences: " + result.getOccurrenceCount());
            System.out.println();
        }

        // Boolean search query
        {
            String query = "justo AND NOT 3456";
            SearchResult result = index.search(query);
            System.out.println("Query: " + query);
            System.out.println("Documents: " + result.getDocumentCount());
            System.out.println("Occurrences: " + result.getOccurrenceCount());
            System.out.println();
        }

        // Boolean search query 2
        {
            String query = "FileName: Engl?(1~3) OR Content: (3456 AND consequat)";
            // Search for documents whose paths contain 'English', 'England', ets., or documents containing both '3456' and 'consequat' in the content
            SearchResult result = index.search(query);
            System.out.println("Query: " + query);
            System.out.println("Documents: " + result.getDocumentCount());
            System.out.println("Occurrences: " + result.getOccurrenceCount());
            System.out.println();
        }

        // Phrase search query
        {
            String query = "\"ipsum dolor sit amet\"";
            SearchResult result = index.search(query); // Search for the phrase 'ipsum dolor sit amet'
            System.out.println("Query: " + query);
            System.out.println("Documents: " + result.getDocumentCount());
            System.out.println("Occurrences: " + result.getOccurrenceCount());
            System.out.println();
        }
    }
}
