package com.groupdocs.search.examples.advanced_usage.indexing;

import com.groupdocs.search.Document;
import com.groupdocs.search.Index;
import com.groupdocs.search.IndexSettings;
import com.groupdocs.search.common.DocumentField;
import com.groupdocs.search.common.DocumentSourceKind;
import com.groupdocs.search.common.IDocumentLoader;
import com.groupdocs.search.events.EventHandler;
import com.groupdocs.search.events.IndexErrorEventArgs;
import com.groupdocs.search.examples.Utils;
import com.groupdocs.search.options.CommonFieldNames;
import com.groupdocs.search.options.Compression;
import com.groupdocs.search.options.IndexingOptions;
import com.groupdocs.search.options.TextStorageSettings;
import com.groupdocs.search.results.SearchResult;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class IndexingFromDifferentSources {
    public static void indexingFromFile() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\IndexingFromDifferentSources\\IndexingFromFile";
        String documentFilePath = Utils.DocumentsPath + "Lorem ipsum.pdf";

        // Creating an index
        IndexSettings settings = new IndexSettings();
        Index index = new Index(indexFolder, settings);

        // Creating a document object
        Document document = Document.createFromFile(documentFilePath);
        Document[] documents = new Document[] {
            document,
        };

        // Indexing document from the file
        IndexingOptions options = new IndexingOptions();
        options.setUseRawTextExtraction(false);
        index.add(documents, options);

        // Searching in the index
        String query = "lorem";
        SearchResult result = index.search(query);

        Utils.traceResult(query, result);
    }

    public static void indexingFromStream() throws FileNotFoundException, IOException {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\IndexingFromDifferentSources\\IndexingFromStream";
        String documentFilePath = Utils.DocumentsPath + "Lorem ipsum.pdf";

        // Creating an index
        Index index = new Index(indexFolder);

        // Creating a document object
        InputStream stream = new FileInputStream(documentFilePath); // Opening a stream
        Document document = Document.createFromStream(documentFilePath, new Date(), ".pdf", stream);
        Document[] documents = new Document[] {
            document,
        };

        // Indexing document from the stream
        IndexingOptions options = new IndexingOptions();
        index.add(documents, options);

        // Closing the document stream after indexing is complete
        stream.close();

        // Searching in the index
        String query = "lorem";
        SearchResult result = index.search(query);

        Utils.traceResult(query, result);
    }

    public static void indexingFromStructure() throws IOException {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\IndexingFromDifferentSources\\IndexingFromStructure";
        String documentFilePath = Utils.DocumentsPath + "Lorem ipsum.txt";

        // Creating an index
        Index index = new Index(indexFolder);

        // Creating a document object
        String text = new String(Files.readAllBytes(Paths.get(documentFilePath)), StandardCharsets.UTF_8);
        DocumentField[] fields = new DocumentField[] {
            new DocumentField(CommonFieldNames.Content, text),
        };
        Document document = Document.createFromStructure("ExampleDocument", new Date(), fields);
        Document[] documents = new Document[] {
            document,
        };

        // Indexing document from the structure
        IndexingOptions options = new IndexingOptions();
        index.add(documents, options);

        // Searching in the index
        String query = "lorem";
        SearchResult result = index.search(query);

        Utils.traceResult(query, result);
    }

    public static void indexingFromUrl() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\IndexingFromDifferentSources\\IndexingFromUrl";
        String url = "http://unec.edu.az/application/uploads/2014/12/pdf-sample.pdf";

        // Creating an index
        IndexSettings settings = new IndexSettings();
        settings.setTextStorageSettings(new TextStorageSettings(Compression.High));
        Index index = new Index(indexFolder, settings, true);

        index.getEvents().ErrorOccurred.add(new EventHandler<IndexErrorEventArgs>() {
            @Override
            public void invoke(Object s, IndexErrorEventArgs a) {
                System.out.println(a.getMessage());
            }
        });

        // Creating a document object
        String documentKey = url;
        IDocumentLoader documentLoader = new DocumentLoaderFromUrl(documentKey, url, ".pdf");
        Document document = Document.createLazy(DocumentSourceKind.Stream, documentKey, documentLoader);
        Document[] documents = new Document[] {
            document,
        };

        // Indexing the lazy-loaded document
        IndexingOptions options = new IndexingOptions();
        options.setUseRawTextExtraction(false);
        index.add(documents, options);

        // Searching in the index
        String query = "files";
        SearchResult result = index.search(query);

        Utils.traceResult(query, result);
    }

    public static void indexingFromFtp() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\IndexingFromDifferentSources\\IndexingFromFtp";
        String url = "ftp://example.com/ExampleDocument.pdf";

        // Creating an index
        IndexSettings settings = new IndexSettings();
        settings.setTextStorageSettings(new TextStorageSettings(Compression.High));
        Index index = new Index(indexFolder, settings, true);

        index.getEvents().ErrorOccurred.add(new EventHandler<IndexErrorEventArgs>() {
            @Override
            public void invoke(Object s, IndexErrorEventArgs a) {
                System.out.println(a.getMessage());
            }
        });

        // Creating a document object
        String documentKey = url;
        IDocumentLoader documentLoader = new DocumentLoaderFromUrl(documentKey, url, ".pdf");
        Document document = Document.createLazy(DocumentSourceKind.Stream, documentKey, documentLoader);
        Document[] documents = new Document[] {
            document,
        };

        // Indexing the lazy-loaded document
        IndexingOptions options = new IndexingOptions();
        options.setUseRawTextExtraction(false);
        index.add(documents, options);

        // Searching in the index
        String query = "some";
        SearchResult result = index.search(query);

        Utils.traceResult(query, result);
    }

    public static void indexingFromAmazon() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\IndexingFromDifferentSources\\IndexingFromAmazon";
        String key = "example.pdf";

        // Creating an index
        Index index = new Index(indexFolder);

        index.getEvents().ErrorOccurred.add(new EventHandler<IndexErrorEventArgs>() {
            @Override
            public void invoke(Object s, IndexErrorEventArgs a) {
                System.out.println(a.getMessage());
            }
        });

        // Creating a document object
        String documentKey = "documentKey";
        IDocumentLoader documentLoader = new DocumentLoaderFromAmazon(documentKey, ".pdf", key);
        Document document = Document.createLazy(DocumentSourceKind.Stream, documentKey, documentLoader);
        Document[] documents = new Document[] {
            document,
        };

        // Indexing the lazy-loaded document
        IndexingOptions options = new IndexingOptions();
        index.add(documents, options);

        // Searching in the index
        String query = "some";
        SearchResult result = index.search(query);

        Utils.traceResult(query, result);
    }

    // Loading a document from Amazon S3 using the software.amazon.awssdk:s3 library
    // The credentials and region are taken from the default AWS provider chain (environment variables, ~/.aws/credentials, etc.)
    private static class DocumentLoaderFromAmazon implements IDocumentLoader {
        private final String documentKey;
        private final String extension;
        private final String storageKey;

        public DocumentLoaderFromAmazon(String documentKey, String extension, String storageKey) {
            this.documentKey = documentKey;
            this.extension = extension;
            this.storageKey = storageKey;
        }

        @Override
        public final Document loadDocument() {
            String bucketName = "my-bucket";
            software.amazon.awssdk.services.s3.S3Client client = software.amazon.awssdk.services.s3.S3Client.create();
            try {
                software.amazon.awssdk.services.s3.model.GetObjectRequest request = software.amazon.awssdk.services.s3.model.GetObjectRequest.builder()
                        .bucket(bucketName)
                        .key(storageKey)
                        .build();
                byte[] data = client.getObjectAsBytes(request).asByteArray();
                java.io.ByteArrayInputStream stream = new java.io.ByteArrayInputStream(data);
                Document document = Document.createFromStream(documentKey, new Date(), extension, stream);
                return document;
            } finally {
                client.close();
            }
        }

        @Override
        public final void closeDocument() {
        }
    }

    public static void indexingFromAzure() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\IndexingFromDifferentSources\\IndexingFromAzure";
        String blobName = "example.pdf";

        // Creating an index
        Index index = new Index(indexFolder);

        index.getEvents().ErrorOccurred.add(new EventHandler<IndexErrorEventArgs>() {
            @Override
            public void invoke(Object s, IndexErrorEventArgs a) {
                System.out.println(a.getMessage());
            }
        });

        // Creating a document object
        String documentKey = "documentKey";
        IDocumentLoader documentLoader = new DocumentLoaderFromAzure(documentKey, ".pdf", blobName);
        Document document = Document.createLazy(DocumentSourceKind.Stream, documentKey, documentLoader);
        Document[] documents = new Document[] {
            document,
        };

        // Indexing the lazy-loaded document
        IndexingOptions options = new IndexingOptions();
        index.add(documents, options);

        // Searching in the index
        String query = "some";
        SearchResult result = index.search(query);

        Utils.traceResult(query, result);
    }

    // Loading a document from Azure Blob Storage using the com.azure:azure-storage-blob library
    private static class DocumentLoaderFromAzure implements IDocumentLoader {
        private final String documentKey;
        private final String extension;
        private final String blobName;

        public DocumentLoaderFromAzure(String documentKey, String extension, String blobName) {
            this.documentKey = documentKey;
            this.extension = extension;
            this.blobName = blobName;
        }

        @Override
        public final Document loadDocument() {
            String accountName = "***";
            String accountKey = "***";
            String endpoint = "https://" + accountName + ".blob.core.windows.net/";
            String containerName = "***";
            com.azure.storage.common.StorageSharedKeyCredential credential =
                    new com.azure.storage.common.StorageSharedKeyCredential(accountName, accountKey);
            com.azure.storage.blob.BlobServiceClient blobServiceClient = new com.azure.storage.blob.BlobServiceClientBuilder()
                    .endpoint(endpoint)
                    .credential(credential)
                    .buildClient();
            com.azure.storage.blob.BlobContainerClient container = blobServiceClient.getBlobContainerClient(containerName);
            container.createIfNotExists();

            com.azure.storage.blob.BlobClient blob = container.getBlobClient(blobName);
            java.io.ByteArrayOutputStream outputStream = new java.io.ByteArrayOutputStream();
            blob.downloadStream(outputStream);
            java.io.ByteArrayInputStream stream = new java.io.ByteArrayInputStream(outputStream.toByteArray());

            Document document = Document.createFromStream(documentKey, new Date(), extension, stream);
            return document;
        }

        @Override
        public final void closeDocument() {
        }
    }

    private static class DocumentLoaderFromUrl implements IDocumentLoader {
        private final String documentKey;
        private final String url;
        private final String extension;

        public DocumentLoaderFromUrl(String documentKey, String url, String extension) {
            this.documentKey = documentKey;
            this.url = url;
            this.extension = extension;
        }

        @Override
        public final Document loadDocument() {
            try {
                java.net.URL urlInstance = new java.net.URL(url);
                java.io.InputStream stream = urlInstance.openStream();
                Document document = Document.createFromStream(documentKey, new Date(), extension, stream);
                return document;
            } catch (java.io.IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        @Override
        public final void closeDocument() {
        }
    }
}
