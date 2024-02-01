package com.groupdocs.search.examples.advanced_usage.indexing;

import com.groupdocs.search.*;
import com.groupdocs.search.examples.Utils;

import java.util.regex.Pattern;

public class DocumentFilteringDuringIndexing {
    public static void settingAFilter() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\DocumentFilteringDuringIndexing\\SettingAFilter";
        String documentsFolder = Utils.DocumentsPath;

        // Creating a filter that skips documents with extensions '.doc', '.docx', '.rtf'
        IndexSettings settings = new IndexSettings();
        DocumentFilter fileExtensionFilter = DocumentFilter.createFileExtension(".doc", ".docx", ".rtf"); // Creating file extension filter that allows only specified extensions
        DocumentFilter invertedFilter = DocumentFilter.createNot(fileExtensionFilter); // Inverting file extension filter to allow all extensions except specified ones
        settings.setDocumentFilter(invertedFilter);

        // Creating an index in the specified folder
        Index index = new Index(indexFolder, settings);

        // Indexing documents
        index.add(documentsFolder);

        Utils.traceIndexedDocuments(index);
    }

    public static void creationTimeFilters() {
        // The first filter skips files created earlier than January 1, 2017, 00:00:00 a.m.
        DocumentFilter filter1 = DocumentFilter.createCreationTimeLowerBound(Utils.createDate(2017, 1, 1));

        // The second filter skips files created later than June 15, 2018, 00:00:00 a.m.
        DocumentFilter filter2 = DocumentFilter.createCreationTimeUpperBound(Utils.createDate(2018, 6, 15));

        // The third filter skips files created outside the range from January 1, 2017, 00:00:00 a.m. to June 15, 2018, 00:00:00 a.m.
        DocumentFilter filter3 = DocumentFilter.createCreationTimeRange(Utils.createDate(2017, 1, 1), Utils.createDate(2018, 6, 15));

        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\DocumentFilteringDuringIndexing\\CreationTimeFilters";
        String documentsFolder = Utils.DocumentsPath;

        IndexSettings settings = new IndexSettings();
        settings.setDocumentFilter(filter3); // Setting the filter

        // Creating an index in the specified folder
        Index index = new Index(indexFolder, settings);

        // Indexing documents
        index.add(documentsFolder);

        Utils.traceIndexedDocuments(index);
    }

    public static void modificationTimeFilters() {
        // The first filter skips files modified earlier than January 1, 2017, 00:00:00 a.m.
        DocumentFilter filter1 = DocumentFilter.createModificationTimeLowerBound(Utils.createDate(2017, 1, 1));

        // The second filter skips files modified later than June 15, 2018, 00:00:00 a.m.
        DocumentFilter filter2 = DocumentFilter.createModificationTimeUpperBound(Utils.createDate(2018, 6, 15));

        // The third filter skips files modified outside the range from January 1, 2017, 00:00:00 a.m. to June 15, 2018, 00:00:00 a.m.
        DocumentFilter filter3 = DocumentFilter.createModificationTimeRange(Utils.createDate(2017, 1, 1), Utils.createDate(2018, 6, 15));


        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\DocumentFilteringDuringIndexing\\ModificationTimeFilters";
        String documentsFolder = Utils.DocumentsPath;

        IndexSettings settings = new IndexSettings();
        settings.setDocumentFilter(filter2); // Setting the filter

        // Creating an index in the specified folder
        Index index = new Index(indexFolder, settings);

        // Indexing documents
        index.add(documentsFolder);

        Utils.traceIndexedDocuments(index);
    }

    public static void filePathFilters() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\DocumentFilteringDuringIndexing\\FilePathFilters";
        String documentsFolder = Utils.DocumentsPath;

        IndexSettings settings = new IndexSettings();
        // Creating a filter that skips files that do not contain the word 'Ipsum' in their paths
        DocumentFilter filter = DocumentFilter.createFilePathRegularExpression("Ipsum", Pattern.CASE_INSENSITIVE);
        settings.setDocumentFilter(filter);

        // Creating an index in the specified folder
        Index index = new Index(indexFolder, settings);

        // Indexing documents
        index.add(documentsFolder);

        Utils.traceIndexedDocuments(index);
    }

    public static void fileLengthFilters() {
        // The first filter skips documents less than 50 KB in length
        DocumentFilter filter1 = DocumentFilter.createFileLengthLowerBound(50 * 1024);

        // The second filter skips documents more than 10 MB in length
        DocumentFilter filter2 = DocumentFilter.createFileLengthUpperBound(10 * 1024 * 1024);

        // The third filter skips documents less than 50 KB and more than 100 KB in length
        DocumentFilter filter3 = DocumentFilter.createFileLengthRange(50 * 1024, 100 * 1024);


        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\DocumentFilteringDuringIndexing\\FileLengthFilters";
        String documentsFolder = Utils.DocumentsPath;

        IndexSettings settings = new IndexSettings();
        settings.setDocumentFilter(filter3); // Setting the filter

        // Creating an index in the specified folder
        Index index = new Index(indexFolder, settings);

        // Indexing documents
        index.add(documentsFolder);

        Utils.traceIndexedDocuments(index);
    }

    public static void fileExtensionFilter() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\DocumentFilteringDuringIndexing\\FileExtensionFilter";
        String documentsFolder = Utils.DocumentsPath;

        IndexSettings settings = new IndexSettings();
        // This filter allows indexing only FB2, EPUB, and TXT files
        DocumentFilter filter = DocumentFilter.createFileExtension(".fb2", ".epub", ".txt");
        settings.setDocumentFilter(filter); // Setting the filter

        // Creating an index in the specified folder
        Index index = new Index(indexFolder, settings);

        // Indexing documents
        index.add(documentsFolder);

        Utils.traceIndexedDocuments(index);
    }

    public static void logicalNotFilter() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\DocumentFilteringDuringIndexing\\LogicalNotFilter";
        String documentsFolder = Utils.DocumentsPath;

        IndexSettings settings = new IndexSettings();
        DocumentFilter filter = DocumentFilter.createFileExtension(".htm", ".html", ".pdf");
        DocumentFilter invertedFilter = DocumentFilter.createNot(filter); // Inverting file extension filter to allow all extensions except of HTM, HTML, and PDF
        settings.setDocumentFilter(invertedFilter);

        // Creating an index in the specified folder
        Index index = new Index(indexFolder, settings);

        // Indexing documents
        index.add(documentsFolder);

        Utils.traceIndexedDocuments(index);
    }

    public static void logicalAndFilter() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\DocumentFilteringDuringIndexing\\LogicalAndFilter";
        String documentsFolder = Utils.DocumentsPath;

        IndexSettings settings = new IndexSettings();
        DocumentFilter filter1 = DocumentFilter.createCreationTimeRange(Utils.createDate(2015, 1, 1), Utils.createDate(2016, 1, 1));
        DocumentFilter filter2 = DocumentFilter.createFileExtension(".txt");
        DocumentFilter filter3 = DocumentFilter.createFileLengthUpperBound(8 * 1024 * 1024);
        DocumentFilter finalFilter = DocumentFilter.createAnd(filter1, filter2, filter3);
        settings.setDocumentFilter(finalFilter); // Setting the filter

        // Creating an index in the specified folder
        Index index = new Index(indexFolder, settings);

        // Indexing documents
        index.add(documentsFolder);

        Utils.traceIndexedDocuments(index);
    }

    public static void logicalOrFilter() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\DocumentFilteringDuringIndexing\\LogicalOrFilter";
        String documentsFolder = Utils.DocumentsPath;

        IndexSettings settings = new IndexSettings();
        DocumentFilter txtFilter = DocumentFilter.createFileExtension(".txt");
        DocumentFilter notTxtFilter = DocumentFilter.createNot(txtFilter);
        DocumentFilter bound5Filter = DocumentFilter.createFileLengthUpperBound(5 * 1024 * 1024);
        DocumentFilter bound10Filter = DocumentFilter.createFileLengthUpperBound(10 * 1024 * 1024);
        DocumentFilter txtSizeFilter = DocumentFilter.createAnd(txtFilter, bound5Filter);
        DocumentFilter notTxtSizeFilter = DocumentFilter.createAnd(notTxtFilter, bound10Filter);
        DocumentFilter finalFilter = DocumentFilter.createOr(txtSizeFilter, notTxtSizeFilter);
        settings.setDocumentFilter(finalFilter); // Setting the filter

        // Creating an index in the specified folder
        Index index = new Index(indexFolder, settings);

        // Indexing documents
        index.add(documentsFolder);

        Utils.traceIndexedDocuments(index);
    }
}
