package com.groupdocs.search.examples.advanced_usage.searching;

import com.groupdocs.search.*;
import com.groupdocs.search.common.*;
import com.groupdocs.search.highlighters.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HighlightingSearchResults {
    public static void highlightingInEntireDocument() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\HighlightingSearchResults\\HighlightingInEntireDocument";
        String documentFolder = Utils.ArchivesPath;

        // Creating an index settings instance
        IndexSettings settings = new IndexSettings();
        settings.setTextStorageSettings(new TextStorageSettings(Compression.High)); // Enabling the storage of extracted text in the index

        // Creating an index in the specified folder
        Index index = new Index(indexFolder, settings);

        // Indexing documents from the specified folder
        index.add(documentFolder);

        // Search for the word 'ipsum'
        SearchResult result = index.search("ipsum");

        // Highlighting occurrences in the text
        if (result.getDocumentCount() > 0) {
            {
                FoundDocument document = result.getFoundDocument(0); // Getting the first found document
                OutputAdapter outputAdapter = new FileOutputAdapter(OutputFormat.Html, ".\\output\\AdvancedUsage\\Searching\\HighlightingSearchResults\\Highlighted.html"); // Creating an output adapter to a file
                Highlighter highlighter = new DocumentHighlighter(outputAdapter); // Creating the highlighter object
                HighlightOptions options = new HighlightOptions(); // Creating the highlight options
                options.setHighlightColor(new Color(150, 255, 150)); // Setting highlight color
                options.setUseInlineStyles(false); // Using CSS styles to highlight occurrences
                options.setGenerateHead(true); // Generating Head tag in output HTML
                index.highlight(document, highlighter, options); // Generating HTML formatted text with highlighted occurrences
            }
            {
                FoundDocument document = result.getFoundDocument(0); // Getting the first found document
                StructureOutputAdapter outputAdapter = new StructureOutputAdapter(OutputFormat.PlainText); // Creating the output adapter
                Highlighter highlighter = new DocumentHighlighter(outputAdapter); // Creating the highlighter instance
                HighlightOptions options = new HighlightOptions(); // Creating the highlight options
                options.setTermHighlightStartTag("<Term>"); // Setting the start tag for the found word
                options.setTermHighlightEndTag("</Term>"); // Setting the end tag for the found word
                index.highlight(document, highlighter, options); // Generating plain text with highlighted occurrences

                DocumentField[] fields = outputAdapter.getResult();
                System.out.println(document.toString());
                for (DocumentField field : fields) {
                    // Printing field names of the found document
                    System.out.println("\t" + field.getName());
                }
            }
        }
    }

    public static void highlightingInFragments() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\HighlightingSearchResults\\HighlightingInFragments";
        String documentFolder = Utils.ArchivesPath;

        // Creating an index settings instance
        IndexSettings settings = new IndexSettings();
        settings.setTextStorageSettings(new TextStorageSettings(Compression.High)); // Enabling the storage of extracted text in the index

        // Creating an index in the specified folder
        Index index = new Index(indexFolder, settings);

        // Indexing documents from the specified folder
        index.add(documentFolder);

        // Search for the word 'ipsum'
        SearchResult result = index.search("ipsum");

        // Assigning highlight options
        HighlightOptions options = new HighlightOptions();
        options.setTermsBefore(5);
        options.setTermsAfter(5);
        options.setTermsTotal(15);
        options.setHighlightColor(new Color(127, 200, 255));
        options.setUseInlineStyles(true);

        // Highlighting found words in separate text fragments of a document
        FoundDocument document = result.getFoundDocument(0);
        FragmentHighlighter highlighter = new FragmentHighlighter(OutputFormat.Html);
        index.highlight(document, highlighter, options);

        // Getting the result
        StringBuilder stringBuilder = new StringBuilder();
        FragmentContainer[] fragmentContainers = highlighter.getResult();
        for (FragmentContainer container : fragmentContainers) {
            String[] fragments = container.getFragments();
            if (fragments.length > 0) {
                stringBuilder.append("\n<br>");
                stringBuilder.append(container.getFieldName());
                stringBuilder.append("<br>\n");
                for (String fragment : fragments) {
                    // Printing HTML markup to console
                    stringBuilder.append(fragment);
                    stringBuilder.append("\n");
                }
            }
        }
        System.out.println(stringBuilder.toString());

        try {
            String fileName = ".\\output\\AdvancedUsage\\Searching\\HighlightingSearchResults\\Fragments.html";
            Files.write(Paths.get(fileName), stringBuilder.toString().getBytes());
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
    }
}
