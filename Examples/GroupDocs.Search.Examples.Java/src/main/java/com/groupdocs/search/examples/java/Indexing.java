package com.groupdocs.search.examples.java;

import com.groupdocs.search.*;

public class Indexing {
    //Create Index in memory
    public static void createIndexInMemory() {
        // Create index in memory
        Index index = new Index();
        // Create index in memory using index repository
        IndexRepository repository = new IndexRepository();
        Index index2 = repository.create();
    }

    //Create Index on disk
    public static void createIndexOnDisk() {
        // Create index on disk
        Index index = new Index(Utilities.indexPath);
        // Create index on disk using index repository
        IndexRepository repository = new IndexRepository();
        Index index2 = repository.create(Utilities.indexPath2);
    }

    //Create Index in memory with required index settings
    public static void createIndexInMemoryWithIndexSettings() {
        IndexingSettings settings = new IndexingSettings();
        settings.setIndexType(IndexType.Normal);

        // Create index in memory with specified settings
        Index index = new Index(settings);

        // Create index in memory with specified settings using index repository
        IndexRepository repository = new IndexRepository();
        Index index2 = repository.create(settings);
    }

    //Create Index on disk with required index settings
    public static void createIndexOnDiskWithIndexSettings() {
        IndexingSettings settings = new IndexingSettings();
        settings.setIndexType(IndexType.Normal);

        // Create index on disk  with specified settings
        Index index = new Index(Utilities.indexPath, settings);

        // Create index in memory with specified settings using index repository
        IndexRepository repository = new IndexRepository();
        Index index2 = repository.create(Utilities.indexPath2, settings);
    }

    //Create index with overwriting existing one
    public static void createWithOverwritingExistedIndex() {
        // Create index in memory with overwriting if index already exists in specified folder
        Index index = new Index(Utilities.indexPath, true);
    }

    //Load Index
    public static void loadIndex() {
        // Load index from disk
        Index index = new Index(Utilities.indexPath);
    }

    //Load Index to Index Repository
    public static void loadIndexToIndexRepository() {
        // Load index from disk
        Index index = new Index(Utilities.indexPath);

        // Add loaded index to repository
        IndexRepository repository = new IndexRepository();
        repository.addToRepository(index);
    }

    //Add Document to Index
    public static void addDocumentToIndex() {
        // Create index
        Index index = new Index(Utilities.indexPath);
        // Add all files from folder and its subfolders to the index
        index.addToIndex(Utilities.documentsPath);
    }

    //Add Document to Index Async
    public static void addDocumentToIndexAsync() {
        // Create index
        Index index = new Index(Utilities.indexPath);
        // Add all files from folder and its subfolders to the index asynchronously
        index.addToIndexAsync(Utilities.documentsPath);
    }

    //Subscribe to events
    public static void subscriptionToEvents() {
        // Create index
        Index index = new Index();

        // Subscribe to event
        index.ErrorHappened.add(new EventHandler<BaseIndexEventArgs>() {
            public void invoke(Object sender, BaseIndexEventArgs args) {
                // Do something here
            }
        });
    }

    //Update Index
    public static void updateIndex() {
        // Load index
        Index index = new Index(Utilities.indexPath);

        // Update index
        index.update();
    }

    //Update Indexes in Index Repository
    public static void updateIndexInRepository() {
        // Create index repository
        IndexRepository repository = new IndexRepository();

        // Add indexes to created repository
        repository.addToRepository(Utilities.indexPath);
        repository.addToRepository(Utilities.indexPath2);

        // Update all indexes in repository
        repository.update();
    }

    //Update Index asynchronously
    public static void updateIndexAsync() {
        // Load index
        Index index = new Index(Utilities.indexPath);

        // Update index asynchronously
        index.updateAsync();
    }

    //Update Index in repo asynchronously
    public static void updateIndexInRepoAsync() {
        // Create index repository
        IndexRepository repository = new IndexRepository();

        // Add indexes to created repository
        repository.addToRepository(Utilities.indexPath);
        repository.addToRepository(Utilities.indexPath2);

        // Update all indexes in repository asynchronously
        repository.updateAsync();
    }

    //Update Index version
    public static void updateIndexVersion() {
        // Load index of one of previous versions
        Index index = new Index(Utilities.oldIndexFolderPath);

        // Add documents to index. Index will be updated to actual version before adding new documents.
        index.addToIndex(Utilities.documentsPath, true);
    }

    //Support for new document format
    public static void customExtractor() {
        // Create index
        Index index = new Index();

        // Add instance of custom extractor to created index
        index.getCustomExtractors().addItem(new CustomFieldExtractor());
    }

    //Extract Document Text from Index to File
    public static void extractDocumentTextToFileFromIndex() {

        // Creating index from existing folder
        Index index = new Index(Utilities.indexPath);

        // Getting list of indexed documents
        DocumentInfo[] documents = index.getIndexedDocuments();

        // Extracting HTML formatted document text to a file
        index.extractDocumentText("c:\\DocumentText.html", documents[0], null);
    }

    public static void extractDocumentTextFromIndex() {
        // Creating index from existing folder
        Index index = new Index(Utilities.indexPath);

        // Getting list of indexed documents
        DocumentInfo[] documents = index.getIndexedDocuments();

        // Extracting HTML formatted document text
        String htmlText = index.extractDocumentText(documents[0], null);
    }

    //Get a list of indexed documents from an index
    public static void getListOfIndexedDocuments() {
        // Creating index from existing folder
        Index index = new Index(Utilities.indexPath);

        // Getting list of indexed documents
        DocumentInfo[] documents = index.getIndexedDocuments();

        // Getting items of container document
        DocumentInfo[] items = index.getIndexedDocumentItems(documents[0]);
    }

    //Break Index Repository
    public static void breakIndexRepository() {
        IndexRepository repository = new IndexRepository();
        Index index = repository.create(Utilities.indexPath);
        index.addToIndexAsync(Utilities.documentsPath);

        // Breaking all processes in all indexes of index repository
        repository.break_();
    }

    //Break Index Repository using Cancellation Object
    public static void breakIndexRepositoryUsingCancellationObject() {
        IndexRepository repository = new IndexRepository();
        Index index = repository.create();
        index.addToIndex(Utilities.documentsPath);

        Cancellation cancellation = new Cancellation();

        // Updating all indexes in repository
        repository.updateAsync(cancellation);

        // Cancelling all operations in index repository
        cancellation.cancel();
    }

    //Break indexing with cancellation object
    public static void breakIndexingWithCancellationObject() {
        // Creating cancellation object
        Cancellation cancellation = new Cancellation();

        // Creating index
        Index index = new Index(Utilities.indexPath);

        // Indexing
        index.addToIndexAsync(Utilities.documentsPath, cancellation);

        cancellation.cancel();

    }

    //Break indexing with time limitation
    public static void breakIndexingWithTimeLimitation() {
        // Creating cancellation object
        Cancellation cancellation = new Cancellation();
        // Cancelling after 1 second of searching
        cancellation.cancelAfter(1000);

        // Creating index
        Index index = new Index(Utilities.indexPath);

        // Indexing
        index.addToIndex(Utilities.documentsPath, cancellation);

    }

    //Break Merging Manually
    public static void breakMergingManually() {
        // Creating cancellation object
        com.groupdocs.search.Cancellation cancellation = new com.groupdocs.search.Cancellation();
        cancellation.cancelAfter(5000);

        // Load index
        Index index = new Index(Utilities.indexPath);
        index.merge(cancellation);
    }

    //Break Updating with cancellation object
    public static void breakUpdatingUsingCancellationObject() {
        // Creating cancellation object
        Cancellation cancellation = new Cancellation();

        // Load index
        Index index = new Index(Utilities.indexPath);

        // Updating
        index.updateAsync(cancellation);

        // Breaking
        cancellation.cancel();
    }

    //Break Update operation manually
    public static void breakUpdatingManually() {
        // Load index
        Index index = new Index(Utilities.indexPath);

        // Updating index
        index.updateAsync();

        // Breaking updating
        index.break_();
    }

    //Multi threaded indexing async
    public static void multiThreadedIndexingAsync() {
        // Creating index
        Index index = new Index(Utilities.indexPath);

        // Indexing in 2 threads
        index.addToIndexAsync(Utilities.documentsPath, 2);

        // User can perform a search after the completion of the indexing operation
    }

    //Multi threaded indexing
    public static void multiThreadedIndexing() {
        // Creating index
        Index index = new Index(Utilities.indexPath);

        // Indexing in 2 threads
        index.addToIndex(Utilities.documentsPath, 2);

    }

    //Compact Indexing
    public static void compactIndexing() {
        // Creating indexing settings object
        IndexingSettings indexingSettings = new IndexingSettings();
        // Setting compact index type
        indexingSettings.setIndexType(IndexType.CompactIndex);

        // Creating index
        Index index = new Index(Utilities.indexPath, indexingSettings);

        // Indexing
        index.addToIndex(Utilities.documentsPath);

        // Searching
        SearchResults result = index.search("Einstein");
    }
}
