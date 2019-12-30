package com.groupdocs.search.examples.basic_usage;

import com.groupdocs.search.*;
import com.groupdocs.search.common.*;
import com.groupdocs.search.dictionaries.*;
import com.groupdocs.search.events.*;
import com.groupdocs.search.highlighters.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

import java.nio.file.Paths;

public class WorkWithSearchResults {
    public static void obtainSearchResultInformation() {
        String indexFolder = ".\\output\\BasicUsage\\WorkWithSearchResults\\ObtainSearchResultInformation";
        String documentFolder = Utils.DocumentsPath;

        // Creating an index
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentFolder);

        // Creating search options
        SearchOptions options = new SearchOptions();
        options.getFuzzySearch().setEnabled(true); // Enabling the fuzzy search
        options.getFuzzySearch().setFuzzyAlgorithm(new TableDiscreteFunction(3)); // Setting the maximum number of differences to 3

        // Search for documents containing the word 'favourable' or the phrase 'ipsum dolor'
        SearchResult result = index.search("favourable OR \"ipsum dolor\"", options);

        // Printing the result
        System.out.println("Documents: " + result.getDocumentCount());
        System.out.println("Total occurrences: " + result.getOccurrenceCount());
        for (int i = 0; i < result.getDocumentCount(); i++) {
            FoundDocument document = result.getFoundDocument(i);
            System.out.println("\tDocument: " + document.getDocumentInfo().getFilePath());
            System.out.println("\tOccurrences: " + document.getOccurrenceCount());
            for (int j = 0; j < document.getFoundFields().length; j++) {
                FoundDocumentField field = document.getFoundFields()[j];
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
                        for (int m = 0; m < terms.length; m++) {
                            sequence += terms[m] + " ";
                        }
                        System.out.println("\t\t\t" + sequence + " - " + field.getTermSequencesOccurrences()[k]);
                    }
                }
            }
        }
    }

    public static void highlightSearchResults() {
        String indexFolder = ".\\output\\BasicUsage\\WorkWithSearchResults\\HighlightSearchResults";
        String documentFolder = Utils.DocumentsPath;

        // Creating an index
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentFolder);

        // Search for the word 'solicitude'
        SearchResult result = index.search("solicitude");

        // Highlighting occurrences in text
        if (result.getDocumentCount() > 0) {
            FoundDocument document = result.getFoundDocument(0); // Getting the first found document
            String path = ".\\output\\BasicUsage\\WorkWithSearchResults\\Highlighted.html";
            OutputAdapter outputAdapter = new FileOutputAdapter(path); // Creating an output adapter to the file
            Highlighter highlighter = new HtmlHighlighter(outputAdapter); // Creating the highlighter object
            index.highlight(document, highlighter); // Generating HTML formatted text with highlighted occurrences

            System.out.println();
            System.out.println("Generated HTML file can be opened with Internet browser.");
            System.out.println("The file can be found by the following path:");
            System.out.println(Paths.get(path).toAbsolutePath().toString());
        }
    }
}
