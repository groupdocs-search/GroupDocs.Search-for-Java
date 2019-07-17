package com.groupdocs.search.examples;

import com.groupdocs.search.*;

public class Indexing {
	/**
	 * Creates Index in memory
	 */
	public static void createIndexInMemory() {
		// Create index in memory
		Index index = new Index();
		// Create index in memory using index repository
		IndexRepository repository = new IndexRepository();
		Index index2 = repository.create();
	}

	/**
	 * Creates Index on disk
	 */
	public static void createIndexOnDisk() {
		// Create index on disk
		Index index = new Index(Utilities.INDEX_PATH);
		// Create index on disk using index repository
		IndexRepository repository = new IndexRepository();
		Index index2 = repository.create(Utilities.INDEX_PATH_2);
	}

	/**
	 * Creates Index in memory with required index settings
	 */
	public static void createIndexInMemoryWithIndexSettings() {
		IndexingSettings settings = new IndexingSettings();
		settings.setIndexType(IndexType.Normal);

		// Create index in memory with specified settings
		Index index = new Index(settings);

		// Create index in memory with specified settings using index repository
		IndexRepository repository = new IndexRepository();
		Index index2 = repository.create(settings);
	}

	/**
	 * Creates Index on disk with required index settings
	 */
	public static void createIndexOnDiskWithIndexSettings() {
		IndexingSettings settings = new IndexingSettings();
		settings.setIndexType(IndexType.Normal);

		// Create index on disk with specified settings
		Index index = new Index(Utilities.INDEX_PATH, settings);

		// Create index in memory with specified settings using index repository
		IndexRepository repository = new IndexRepository();
		Index index2 = repository.create(Utilities.INDEX_PATH_2, settings);
	}

	/**
	 * Creates index with overwriting existing one
	 */
	public static void createWithOverwritingExistedIndex() {
		// Create index in memory with overwriting if index already exists in
		// specified folder
		Index index = new Index(Utilities.INDEX_PATH, true);
	}

	/**
	 * Loads Index
	 */
	public static void loadIndex() {
		// Load index from disk
		Index index = new Index(Utilities.INDEX_PATH);
	}

	/**
	 * Loads Index to Index Repository
	 */
	public static void loadIndexToIndexRepository() {
		// Load index from disk
		Index index = new Index(Utilities.INDEX_PATH);

		// Add loaded index to repository
		IndexRepository repository = new IndexRepository();
		repository.addToRepository(index);
	}

	/**
	 * Adds Document to Index
	 */
	public static void addDocumentToIndex() {
		// Create index
		Index index = new Index(Utilities.INDEX_PATH);
		// Add all files from folder and its subfolders to the index
		index.addToIndex(Utilities.DOCUMENTS_PATH);
	}

	/**
	 * Adds Document to Index Async
	 */
	public static void addDocumentToIndexAsync() {
		// Create index
		Index index = new Index(Utilities.INDEX_PATH);
		// Add all files from folder and its subfolders to the index
		// asynchronously
		index.addToIndexAsync(Utilities.DOCUMENTS_PATH);
	}

	/**
	 * Subscribes to events
	 */
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
	/**
	 * Event that notifies about search phase is finished and provides intermediate results.
     * Feature is supported in version 19.3 of the API
	 */
	public static void finshedSearchNotificationEvent()
    {
        //ExStart:FinshedSearchNotification_19.3
        // Creating index
        Index index = new Index(Utilities.INDEX_PATH);

        // Adding synonyms
        index.getDictionaries().getSynonymDictionary().addRange(new String[][] { new String[] { "big", "large" } });
         
        // Adding documents to index
        index.addToIndex(Utilities.DOCUMENTS_PATH);
         
        // Subscribing to the event
        index.SearchPhaseCompleted.add(new EventHandler<SearchPhaseEventArgs>() {
            public void invoke(Object sender, SearchPhaseEventArgs args) {
                System.out.println("Phase " + args.getSearchPhase() + ": " + args.getWords().length);
            }
        });
         
        // Creating search parameters
        SearchParameters parameters = new SearchParameters();
        parameters.setUseCaseSensitiveSearch(false);
        parameters.getKeyboardLayoutCorrector().setEnabled(true);
        parameters.getSpellingCorrector().setEnabled(true);
        parameters.getSpellingCorrector().setMaxMistakeCount(1);
        parameters.setUseHomophoneSearch(true);
        parameters.setUseSynonymSearch(true);
        parameters.setUseWordFormsSearch(true);
        parameters.getFuzzySearch().setEnabled(true);
        parameters.getFuzzySearch().setFuzzyAlgorithm(new TableDiscreteFunction(1));
         
        // Searching for word 'big'.
        // Note that enabling many of search options at a time may give many results and take a long time.
        SearchResults results = index.search("big", parameters);

        //ExEnd:FinshedSearchNotification_19.3

    }

	/**
	 * Updates Index
	 */
	public static void updateIndex() {
		// Load index
		Index index = new Index(Utilities.INDEX_PATH);

		// Update index
		index.update();
	}

	/**
	 * Updates Indexes in Index Repository
	 */
	public static void updateIndexInRepository() {
		// Create index repository
		IndexRepository repository = new IndexRepository();

		// Add indexes to created repository
		repository.addToRepository(Utilities.INDEX_PATH);
		repository.addToRepository(Utilities.INDEX_PATH_2);

		// Update all indexes in repository
		repository.update();
	}

	/**
	 * Updates Index asynchronously
	 */
	public static void updateIndexAsync() {
		// Load index
		Index index = new Index(Utilities.INDEX_PATH);

		// Update index asynchronously
		index.updateAsync();
	}

	/**
	 * Update Index in repo asynchronously
	 */
	public static void updateIndexInRepoAsync() {
		// Create index repository
		IndexRepository repository = new IndexRepository();

		// Add indexes to created repository
		repository.addToRepository(Utilities.INDEX_PATH);
		repository.addToRepository(Utilities.INDEX_PATH_2);

		// Update all indexes in repository asynchronously
		repository.updateAsync();
	}

	/**
	 * Updates Index version
	 */
	public static void updateIndexVersion() {
		// Load index of one of previous versions
		Index index = new Index(Utilities.OLD_INDEX_FOLDER_PATH);

		// Add documents to index. Index will be updated to actual version
		// before adding new documents.
		index.addToIndex(Utilities.DOCUMENTS_PATH, true);
	}

	/**
	 * Supports for a new document format
	 */
	public static void customExtractor() {
		// Create index
		Index index = new Index();

		// Add instance of custom extractor to created index
		index.getCustomExtractors().addItem(new CustomFieldExtractor());
	}

	/**
	 * Extracts Document Text from Index to File
	 */
	public static void extractDocumentTextToFileFromIndex() {

		// Creating index from existing folder
		Index index = new Index(Utilities.INDEX_PATH);

		// Getting list of indexed documents
		DocumentInfo[] documents = index.getIndexedDocuments();

		// Extracting HTML formatted document text to a file
		index.extractDocumentText(Utilities.DOCUMENT_TEXT_PATH, documents[0], null);
	}

	/**
	 * Extracts document text from index
	 */
	public static void extractDocumentTextFromIndex() {
		// Creating index from existing folder
		Index index = new Index(Utilities.INDEX_PATH);

		// Getting list of indexed documents
		DocumentInfo[] documents = index.getIndexedDocuments();

		// Extracting HTML formatted document text
		String htmlText = index.extractDocumentText(documents[0], null);
	}

	/**
	 * Get a list of indexed documents from an index
	 */
	public static void getListOfIndexedDocuments() {
		// Creating index from existing folder
		Index index = new Index(Utilities.INDEX_PATH);

		// Getting list of indexed documents
		DocumentInfo[] documents = index.getIndexedDocuments();

		// Getting items of container document
		DocumentInfo[] items = index.getIndexedDocumentItems(documents[0]);
	}

	/**
	 * Breaks Index Repository
	 */
	public static void breakIndexRepository() {
		IndexRepository repository = new IndexRepository();
		Index index = repository.create(Utilities.INDEX_PATH);
		index.addToIndexAsync(Utilities.DOCUMENTS_PATH);

		// Breaking all processes in all indexes of index repository
		repository.break_();
	}

	/**
	 * Breaks Index Repository using Cancellation Object
	 */
	public static void breakIndexRepositoryUsingCancellationObject() {
		IndexRepository repository = new IndexRepository();
		Index index = repository.create();
		index.addToIndex(Utilities.DOCUMENTS_PATH);

		Cancellation cancellation = new Cancellation();

		// Updating all indexes in repository
		repository.updateAsync(cancellation);

		// Cancelling all operations in index repository
		cancellation.cancel();
	}

	/**
	 * Breaks indexing manually
	 */
	public static void breakIndexing() {
		// Creating index
		Index index = new Index(Utilities.INDEX_PATH);
		// Indexing selected folder asynchronously
		index.addToIndexAsync(Utilities.DOCUMENTS_PATH);
		// Break Index
		index.break_();

	}

	/**
	 * Breaks indexing using cancellation object
	 */
	public static void breakIndexingWithCancellationObject() {
		// Creating cancellation object
		Cancellation cancellation = new Cancellation();

		// Creating index
		Index index = new Index(Utilities.INDEX_PATH);

		// Indexing
		index.addToIndexAsync(Utilities.DOCUMENTS_PATH, cancellation);

		cancellation.cancel();

	}

	/**
	 * Breaks indexing with time limitation
	 */
	public static void breakIndexingWithTimeLimitation() {
		// Creating cancellation object
		Cancellation cancellation = new Cancellation();
		// Cancelling after 1 second of searching
		cancellation.cancelAfter(1000);

		// Creating index
		Index index = new Index(Utilities.INDEX_PATH);

		// Indexing
		index.addToIndex(Utilities.DOCUMENTS_PATH, cancellation);

	}

	/**
	 * Breaks Merging Manually
	 */
	public static void breakMergingManually() {
		// Creating cancellation object
		Cancellation cancellation = new Cancellation();
		cancellation.cancelAfter(5000);

		// Load index
		Index index = new Index(Utilities.INDEX_PATH);
		index.merge(cancellation);
	}

	/**
	 * Breaks update operation with cancellation object
	 */
	public static void breakUpdatingUsingCancellationObject() {
		// Creating cancellation object
		Cancellation cancellation = new Cancellation();

		// Load index
		Index index = new Index(Utilities.INDEX_PATH);

		// Updating
		index.updateAsync(cancellation);

		// Breaking
		cancellation.cancel();

	}

	/**
	 * Breaks Update operation manually
	 */
	public static void breakUpdatingManually() {
		// Load index
		Index index = new Index(Utilities.INDEX_PATH);

		// Updating index
		index.updateAsync();

		// Breaking updating
		index.break_();
	}

	/**
	 * Implements multi threaded indexing asynchronously
	 */
	public static void multiThreadedIndexingAsync() {
		// Creating index
		Index index = new Index(Utilities.INDEX_PATH);

		// Indexing in 2 threads
		index.addToIndexAsync(Utilities.DOCUMENTS_PATH, 2);

		// User can perform a search after the completion of the indexing
		// operation
	}

	/**
	 * Implements multi threaded indexing
	 */
	public static void multiThreadedIndexing() {
		// Creating index
		Index index = new Index(Utilities.INDEX_PATH);

		// Indexing in 2 threads
		index.addToIndex(Utilities.DOCUMENTS_PATH, 2);

	}

	/**
	 * Implements Compact Indexing
	 */
	public static void compactIndexing() {
		// Creating indexing settings object
		IndexingSettings indexingSettings = new IndexingSettings();
		// Setting compact index type
		indexingSettings.setIndexType(IndexType.CompactIndex);

		// Creating index
		Index index = new Index(Utilities.INDEX_PATH, indexingSettings);

		// Indexing
		index.addToIndex(Utilities.DOCUMENTS_PATH);

		// Searching
		SearchResults result = index.search("Einstein");
	}

	/**
	 * Merges index with delta indexes
	 */
	public static void mergingIndexWithDeltaIndexes() {
		// Creating index
		Index index = new Index(Utilities.INDEX_PATH, true);

		// Adding documents to index
		index.addToIndex(Utilities.DOCUMENTS_PATH);

		// Adding one more folder to index. Delta index will be created.
		index.addToIndex(Utilities.DOCUMENTS_PATH_2);

		// Run merging
		index.merge();
	}

	/**
	 * Merges several indexes
	 */
	public static void mergingMultipleIndexes() {
		// Creating/loading first index
		Index index1 = new Index(Utilities.INDEX_PATH);
		index1.addToIndex(Utilities.DOCUMENTS_PATH);

		// Creating/loading second index
		Index index2 = new Index(Utilities.INDEX_PATH_2);
		index2.addToIndex(Utilities.DOCUMENTS_PATH);

		index1.merge(index2); // Merging data from index2 to index1. The index2
								// remains unchanged.
	}

	/**
	 * Merges current index with index repository
	 */
	public static void mergingCurrentIndexWithIndexRepository() {
		IndexRepository indexRepository = new IndexRepository();
		Index index1 = indexRepository.create(Utilities.MERGE_INDEX_PATH_1);
		index1.addToIndex(Utilities.DOCUMENTS_PATH);

		Index index2 = indexRepository.create(Utilities.MERGE_INDEX_PATH_2);
		index2.addToIndex(Utilities.DOCUMENTS_PATH_2);

		Index mainIndex = new Index(Utilities.MAIN_MERGED_INDEX_PATH);
		mainIndex.addToIndex(Utilities.DOCUMENTS_PATH_3);

		// Merge data from indexes in repository to main index. After merge
		// index repository stays unmodified.
		mainIndex.merge(indexRepository);
	}

	/**
	 * Merge index with delta indexes asynchronously
	 */
	public static void mergingIndexWithDeltaIndexesAsync() {
		// Creating index
		Index index = new Index(Utilities.INDEX_PATH, true);

		// Adding documents to index
		index.addToIndex(Utilities.DOCUMENTS_PATH);

		// Adding one more folder to index. Delta index will be created.
		index.addToIndex(Utilities.DOCUMENTS_PATH_2);

		// Run merging asynchronously
		index.mergeAsync();
	}

	/**
	 * Merges several indexes asynchronously
	 */
	public static void mergingMultipleIndexesAsync() {
		// Creating/loading first index
		Index index1 = new Index(Utilities.INDEX_PATH);
		index1.addToIndex(Utilities.DOCUMENTS_PATH);

		// Creating/loading second index
		Index index2 = new Index(Utilities.INDEX_PATH_2);
		index2.addToIndex(Utilities.DOCUMENTS_PATH_2);

		index1.mergeAsync(index2); // Merging data from index2 to index1. The
									// index2 remains unchanged.
	}

	/**
	 * Merges current index with index repository asynchronously
	 */
	public static void mergingCurrentIndexWithIndexRepositoryAsync() {
		IndexRepository indexRepository = new IndexRepository();
		Index index1 = indexRepository.create(Utilities.MERGE_INDEX_PATH_1);
		index1.addToIndex(Utilities.DOCUMENTS_PATH);

		Index index2 = indexRepository.create(Utilities.MERGE_INDEX_PATH_2);
		index2.addToIndex(Utilities.DOCUMENTS_PATH_2);

		Index mainIndex = new Index(Utilities.MAIN_MERGED_INDEX_PATH);
		mainIndex.addToIndex(Utilities.DOCUMENTS_PATH_3);

		// Merge data from indexes in repository to main index. After merge
		// index repository stays unmodified.
		mainIndex.mergeAsync(indexRepository);
	}

	/**
	 * Checks if index should be reloaded
	 */
	public static void reloadIndex() {
		// Creating index
		Index index = new Index(Utilities.INDEX_PATH);

		// Indexing
		index.addToIndex(Utilities.DOCUMENTS_PATH);

		// Checking the need to reload
		if (index.getIndexStatus() == IndexStatus.Failed) {
			// Reloading index
			index = new Index(Utilities.INDEX_PATH);
		}
	}

	/**
	 * Implements caching text of indexed documents in index
	 */
	public static void cacheTextOfIndexedDocuments() {
		// Creating indexing settings object
		IndexingSettings settings = new IndexingSettings();
		// Enabling source document text caching with normal compression level
		// So in order to cache document's text with high compression level use
		// "Compression.High"
		settings.setTextStorageSettings(new TextStorageSettings(Compression.Normal));

		// Creating index
		Index index = new Index(Utilities.INDEX_PATH, settings);

		// Indexing
		index.addToIndex(Utilities.DOCUMENTS_PATH);
	}
	/*
    * Set log file path while indexing
    * Feature is supported in version 19.5 of the API
    */
    public static void setLogFileConfigs()
    {
        //ExStart:SetLogFileConfigs_19.5
    	
    	// Creating index
    	Index index = new Index(Utilities.INDEX_PATH);
    	 
    	// Setting the log file name. The log file name can be relative or absolute.
    	index.getLogSettings().setFileName("Log\\Log.txt");
    	 
    	// Setting the maximum size of the log file in megabytes. This value must be in the range from 0.1 to 1000.
    	index.getLogSettings().setMaxSize(2.0);
    	 
    	// Adding documents to index
    	index.addToIndex(Utilities.DOCUMENTS_PATH);
    	 
    	// Searching
    	SearchResults results = index.search("sample");

        //ExEnd:SetLogFileConfigs_19.5
    }
    /*
    * Optimization of index storage format
    * Feature is supported in version 19.5 of the API
    */
    public static void optimizeIndex()
    {
        //ExStart:OptimizeIndex_19.5
    	// Creating updater instance
    	IndexVersionUpdater updater = new IndexVersionUpdater();
    	 
    	// Updating index version
    	if (updater.canUpdate(Utilities.OLD_INDEX_PATH)) {
    	    int updateResult = updater.update(Utilities.OLD_INDEX_PATH, Utilities.NEW_INDEX_PATH);
    	    System.out.println("Updated: " + (updateResult == VersionUpdateResult.Updated));
    	}
    	 
    	// Loading updated index
    	Index index = new Index(Utilities.NEW_INDEX_PATH);
    	 
    	// Searching
    	SearchResults results = index.search("hobbit");
                 
        //ExEnd:OptimizeIndex_19.5
    }

    /*
    * Attach arbitrary additional fields to a document 
    * Feature is supported in version 19.5 of the API
    */
    public static void attachAbritraryFields()
    {
        //ExStart:AttachAbritraryFields_19.5
    	// Creating index
    	Index index = new Index(Utilities.INDEX_PATH);
    	 
    	// Subscribing to event
    	index.FileIndexing.add(new EventHandler<FileIndexingEventArgs>() {
    	    @Override
    	    public void invoke(Object sender, FileIndexingEventArgs args) {
    	        com.groupdocs.search.FieldInfo[] additionalFields = new com.groupdocs.search.FieldInfo[] {
    	            new com.groupdocs.search.FieldInfo("Tags", "arbitrary additional fields"),
    	        };
    	        args.setAdditionalFields(additionalFields);
    	    }
    	});
    	 
    	// Adding documents to index
    	index.addToIndex(Utilities.DOCUMENTS_PATH);
    	 
    	// Searching
    	SearchResults results = index.search("arbitrary");

        //ExEnd:AttachAbritraryFields_19.5
    }

}
