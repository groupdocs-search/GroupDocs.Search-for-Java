package com.groupdocs.search.examples.advanced_usage.searching;

import com.groupdocs.search.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class SearchResults {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\SearchResults";
        String documentFolder = Utils.DocumentsPath;

        // Creating an index
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentFolder);

        // Creating search options
        SearchOptions options = new SearchOptions();
        options.getFuzzySearch().setEnabled(true); // Enabling the fuzzy search
        options.getFuzzySearch().setFuzzyAlgorithm(new TableDiscreteFunction(3)); // Setting the maximum number of differences to 3

        // Search for documents containing the word 'water' or the phrase 'Lorem ipsum'
        String query = "water OR \"Lorem ipsum\"";
        SearchResult result = index.search(query, options);

        // Printing the result
        System.out.println("Documents: " + result.getDocumentCount());
        System.out.println("Total occurrences: " + result.getOccurrenceCount());
        for (int i = 0; i < result.getDocumentCount(); i++) {
            FoundDocument document = result.getFoundDocument(i);
            System.out.println("\tDocument: " + document.getDocumentInfo().getFilePath());
            System.out.println("\tOccurrences: " + document.getOccurrenceCount());
            for (FoundDocumentField field : document.getFoundFields()) {
                System.out.println("\t\tField: " + field.getFieldName());
                System.out.println("\t\tOccurrences: " + document.getOccurrenceCount());
                // Printing found terms
                if (field.getTerms() != null) {
                    for (int k = 0; k < field.getTerms().length; k++) {
                        System.out.println("\t\t\t" + field.getTerms()[k] + " - " + field.getTermsOccurrences()[k]);
                    }
                }
                // Printing found phrases
                if (field.getTermSequences() != null) {
                    for (int k = 0; k < field.getTermSequences().length; k++) {
                        String[] terms = field.getTermSequences()[k];
                        String sequence = "";
                        for (String term : terms) {
                            sequence += term + " ";
                        }
                        System.out.println("\t\t\t" + sequence + " - " + field.getTermSequencesOccurrences()[k]);
                    }
                }
            }
        }
    }
}
