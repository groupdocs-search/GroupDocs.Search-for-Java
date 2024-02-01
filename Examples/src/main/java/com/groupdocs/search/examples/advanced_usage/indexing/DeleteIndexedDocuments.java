package com.groupdocs.search.examples.advanced_usage.indexing;

import com.groupdocs.search.*;
import com.groupdocs.search.common.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import org.apache.commons.io.FilenameUtils;

public class DeleteIndexedDocuments {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\DeleteIndexedPaths";
        String filePath = Utils.DocumentsPath + "English.docx";
        String query = "moment";

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Indexing the document from stream
        DocumentLoader documentLoader = new DocumentLoader(filePath);
        Document document = Document.createLazy(DocumentSourceKind.Stream, documentLoader.getDocumentKey(), documentLoader);
        Document[] documents = new Document[] { document };
        index.add(documents, new IndexingOptions());

        // Getting indexed documents from the index
        DocumentInfo[] indexedDocuments1 = index.getIndexedDocuments();

        // Writing indexed documents to the console
        System.out.println("Indexed documents (" + indexedDocuments1.length + "):");
        for (DocumentInfo info : indexedDocuments1) {
            System.out.println("\t" + info);
        }

        // Searching in the index
        SearchResult searchResult1 = index.search(query);
        Utils.traceResult(query, searchResult1);

        // Deleting indexed document from the index
        String[] documentKeys = new String[] { documentLoader.getDocumentKey() };
        DeleteResult deleteResult = index.delete(new UpdateOptions(), documentKeys);
        System.out.println("\nDeleted documents: " + deleteResult.getSuccessCount());

        // Getting indexed paths after deletion
        DocumentInfo[] indexedDocuments2 = index.getIndexedDocuments();

        System.out.println("\nIndexed documents (" + indexedDocuments2.length + "):");
        for (DocumentInfo info : indexedDocuments2) {
            System.out.println("\t" + info);
        }

        // Searching in the index
        SearchResult searchResult2 = index.search(query);
        Utils.traceResult(query, searchResult2);
    }

    // Implementing document loader
    private static class DocumentLoader implements IDocumentLoader {
        private final String filePath;
        private final String documentKey;

        public DocumentLoader(String filePath) {
            this.filePath = filePath;
            documentKey = FilenameUtils.getName(filePath);
        }

        public final String getDocumentKey() {
            return documentKey;
        }

        @Override
        public final void closeDocument() {
        }

        @Override
        public final Document loadDocument() {
            try {
                String extension = "." + FilenameUtils.getExtension(filePath);
                Path path = Paths.get(filePath);
                byte[] buffer = Files.readAllBytes(path);
                ByteArrayInputStream stream = new ByteArrayInputStream(buffer);
                Document document = Document.createFromStream(documentKey, new Date(System.currentTimeMillis()), extension, stream);
                return document;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
