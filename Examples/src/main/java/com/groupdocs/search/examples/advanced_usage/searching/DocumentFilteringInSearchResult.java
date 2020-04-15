package com.groupdocs.search.examples.advanced_usage.searching;

import com.groupdocs.search.*;
import com.groupdocs.search.events.EventHandler;
import com.groupdocs.search.events.FileIndexingEventArgs;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

import java.util.regex.Pattern;

public class DocumentFilteringInSearchResult {
    public static void settingAFilter() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\DocumentFilteringInSearchResult\\SettingAFilter";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Creating a search options object
        SearchOptions options = new SearchOptions();
        options.setSearchDocumentFilter(SearchDocumentFilter.createFileExtension(".txt")); // Setting a document filter

        // Search in the index
        // Only text documents will be returned as the result of the search
        String query = "education";
        SearchResult result = index.search(query, options);

        Utils.traceResult(query, result);
    }

    public static void filePathFilters() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\DocumentFilteringInSearchResult\\FilePathFilters";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Creating a search options object
        SearchOptions options = new SearchOptions();

        // The filter returns only files that contain the word 'Lorem' in their paths, not case sensitive
        ISearchDocumentFilter filter = SearchDocumentFilter.createFilePathRegularExpression("Lorem", Pattern.CASE_INSENSITIVE);

        // Setting a document filter
        options.setSearchDocumentFilter(filter);

        // Search in the index
        String query = "Advantages";
        SearchResult result = index.search(query, options);

        Utils.traceResult(query, result);
    }

    public static void fileExtensionFilter() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\DocumentFilteringInSearchResult\\FileExtensionFilter";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Creating a search options object
        SearchOptions options = new SearchOptions();

        // This filter returns only PDF and DOCX documents
        ISearchDocumentFilter filter = SearchDocumentFilter.createFileExtension(".pdf", ".docx");

        // Setting a document filter
        options.setSearchDocumentFilter(filter);

        // Search in the index
        String query = "ipsum";
        SearchResult result = index.search(query, options);

        Utils.traceResult(query, result);
    }

    public static void attributeFilter() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\DocumentFilteringInSearchResult\\AttributeFilter";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        index.getEvents().FileIndexing.add(new EventHandler<FileIndexingEventArgs>() {
            public void invoke(Object sender, FileIndexingEventArgs args) {
                if (args.getDocumentFullPath().endsWith(".txt")) {
                    String[] mainAttribute = new String[] { "main" };
                    args.setAttributes(mainAttribute);
                }
            }
        });

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Creating a search options object
        SearchOptions options = new SearchOptions();

        // This filter returns only documents that have attribute "main"
        ISearchDocumentFilter filter = SearchDocumentFilter.createAttribute("main");

        // Setting a document filter
        options.setSearchDocumentFilter(filter);

        // Search in the index
        String query = "ipsum OR length";
        SearchResult result = index.search(query, options);

        Utils.traceResult(query, result);
    }

    public static void combiningFilters() {
        // Creating an AND composite filter that returns all FB2 and EPUB documents that have the word 'Einstein' in their full paths
        ISearchDocumentFilter filter1 = SearchDocumentFilter.createFilePathRegularExpression("Einstein", Pattern.CASE_INSENSITIVE);
        ISearchDocumentFilter filter2 = SearchDocumentFilter.createFileExtension(".fb2", ".epub");
        ISearchDocumentFilter andFilter = SearchDocumentFilter.createAnd(filter1, filter2);

        // Creating an OR composite filter that returns all DOC, DOCX, PDF and all documents that have the word Einstein in their full paths
        ISearchDocumentFilter filter3 = SearchDocumentFilter.createFilePathRegularExpression("Einstein", Pattern.CASE_INSENSITIVE);
        ISearchDocumentFilter filter4 = SearchDocumentFilter.createFileExtension(".doc", ".docx", ".pdf");
        ISearchDocumentFilter orFilter = SearchDocumentFilter.createOr(filter3, filter4);

        // Creating a filter that returns all found documents except of TXT documents
        ISearchDocumentFilter filter5 = SearchDocumentFilter.createFileExtension(".txt");
        ISearchDocumentFilter notFilter = SearchDocumentFilter.createNot(filter5);


        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\DocumentFilteringInSearchResult\\CombiningFilters";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Creating a search options object
        SearchOptions options = new SearchOptions();

        // Setting a document filter
        options.setSearchDocumentFilter(notFilter);

        // Search in the index
        String query = "ipsum";
        SearchResult result = index.search(query, options);

        Utils.traceResult(query, result);
    }
}
