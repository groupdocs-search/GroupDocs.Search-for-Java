package com.groupdocs.search.examples.highlight_in_html;

import com.groupdocs.search.Index;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class HighlightExample {
    public static void run() {
        String licensePath = "E:\\Licenses\\Conholdate\\Total\\Subscription\\Valid\\Conholdate.Total.Product.Family.lic";

        // Setting licenses
        try {
            new com.aspose.html.License().setLicense(licensePath);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        new com.groupdocs.viewer.License().setLicense(licensePath);

        String basePath = ".\\output\\HighlightInHtml\\HighlightExample";
        String viewerCacheFolderPath = basePath + "\\ViewerCache";
        String indexFolder = basePath + "\\Index";
        String documentsFolder = Utils.DocumentsPath;
        String query = "\"dapibus diam\" OR lorem";

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Search in index
        SearchResult result = index.search(query);

        Utils.traceResult(query, result);

        // Generating HTML
        FoundDocument foundDocument = result.getFoundDocument(0);
        IndexedFileInfo fileInfo = new IndexedFileInfo(viewerCacheFolderPath, foundDocument.getDocumentInfo().getFilePath());
        HighlightService highlightService = new HighlightService(fileInfo, null);

        // Highlighting in HTML
        highlightService.highlight(foundDocument, index.getDictionaries().getAlphabet());
    }
}
