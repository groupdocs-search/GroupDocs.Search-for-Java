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

        // Creating an index
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentFolder);

        // Search for the word 'ipsum'
        SearchResult result = index.search("ipsum");

        // Highlighting occurrences in the text
        if (result.getDocumentCount() > 0) {
            FoundDocument document = result.getFoundDocument(0); // Getting the first found document
            OutputAdapter outputAdapter = new FileOutputAdapter(".\\output\\AdvancedUsage\\Searching\\HighlightingSearchResults\\Highlighted.html"); // Creating an output adapter to a file
            Highlighter highlighter = new HtmlHighlighter(outputAdapter); // Creating the highlighter object
            HighlightOptions options = new HighlightOptions(); // Creating the highlight options
            options.setHighlightColor(new Color(0, 127, 0)); // Setting highlight color
            options.setUseInlineStyles(false); // Using CSS styles to highlight occurrences
            options.setGenerateHead(true); // Generating Head tag in output HTML
            index.highlight(document, highlighter, options); // Generating HTML formatted text with highlighted occurrences
        }
    }

    public static void highlightingInFragments() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\HighlightingSearchResults\\HighlightingInFragments";
        String documentFolder = Utils.ArchivesPath;

        // Creating an index
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentFolder);

        // Search for the word 'ipsum'
        SearchResult result = index.search("ipsum");

        // Assigning highlight options
        HighlightOptions options = new HighlightOptions();
        options.setTermsBefore(5);
        options.setTermsAfter(5);
        options.setTermsTotal(15);
        options.setHighlightColor(new Color(0, 0, 127));
        options.setUseInlineStyles(true);

        // Highlighting found words in separate text fragments of a document
        FoundDocument document = result.getFoundDocument(0);
        HtmlFragmentHighlighter highlighter = new HtmlFragmentHighlighter();
        index.highlight(document, highlighter, options);

        // Getting the result
        StringBuilder stringBuilder = new StringBuilder();
        FragmentContainer[] fragmentContainers = highlighter.getResult();
        for (int i = 0; i < fragmentContainers.length; i++) {
            FragmentContainer container = fragmentContainers[i];
            String[] fragments = container.getFragments();
            if (fragments.length > 0) {
                stringBuilder.append("\n<br>" + container.getFieldName() + "<br>\n");
                for (int j = 0; j < fragments.length; j++) {
                    // Printing HTML markup to console
                    stringBuilder.append(fragments[j] + "\n");
                }
            }
        }
        System.out.println(stringBuilder.toString());

        try {
            String fileName = ".\\output\\AdvancedUsage\\Searching\\HighlightingSearchResults\\Fragments.html";
            Files.write(Paths.get(fileName), stringBuilder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
