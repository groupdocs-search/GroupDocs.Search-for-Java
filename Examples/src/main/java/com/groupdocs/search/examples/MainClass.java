package com.groupdocs.search.examples;

public class MainClass {
	public static void main(String[] args) throws Throwable {

		// region Licensing
		// Uncomment to apply license
		// Utilities.applyLicense();

		// Use Dynabic.Metered account
		// Utilities.useDynabicMeteredAccount();
		// endregion

		// region Searching

		// Simple search, search a word
		// Searching.simpleSearch();

		// Search term1 and term2 or term3 but not term4
		// Searching.booleanSearch();

		// Search for documents that contain a relevant word and term1, an email
		// address or term2
		// Searching.regexSearch();

		// Search results from misspelled search query
		// Searching.fuzzySearch();

		// Show results based on similarity
		// Searching.fuzzySearchSimilarity();

		// Show only best results range from a fuzzy search
		// Searching.fuzzySearchOnlyBestResultsRange();

		// Fuzzy search that uses table of word length values mapped to max
		// mistake count values
		// Searching.fuzzySearchTableDiscrete();

		// Search for specific fields of document
		// Searching.facetedSearch();

		// Faceted search combine with boolean search
		// Searching.facetedSearchWithBooleanSearch();

		// Search for documents with Synonyms
		// Searching.synonymSearch();

		// Manages Synonyms search
		// Searching.manageSynonyms();

		// Perform numeric range search
		// Searching.numericRangeSearch();

		// Perform date range search
		// Searching.dateRangeSearch();

		// Manage date range formats
		// Searching.manageDateRangeFormats();

		// Search using Spelling Corrector
		// Searching.spellingCorrectorUsage();
		// Manage spelling corrector
		// Searching.spellingCorrectorManagement();
		// Search only best results
		// Searching.spellingCorrectorBestResults();

		// Search using Keyboard Layout Corrector
		// Searching.keyboardLayoutCorrectorUsage();

		// Search documents wih exact phrase
		// Searching.exactPhraseSearch();

		// Specify number of thread for searching
		// Searching.specifyNumberOfThreads();

		// Cancel Search Operation
		// Searching.cancelSearchOperation();

		// Cancel Search Operation with time limitation
		// Searching.cancelSearchOperationWithTimeLimitation();

		// Searching by parts(Chunks)
		// Searching.searchingByParts();

		// Get search report
		// Searching.getSearchReport();

		// Limit Search Report
		// Searching.limitSearchReport();

		// Highlight search results
		// Searching.generateHighlightedTextSearchResults();

		// Highlight search results to file
		// Searching.generateHighlightedTextResultsToFile();

		// Highlight search results to HTML File
		// Searching.generateHighlightedTextResultsToHTMLFile();

		// Search with Query as a parameter
		// Searching.searchWithQuery();

		// Search for any document in index
		// Searching.searchFileName();

		// Shows how to implement own custom extractor for outlook document for
		// the extension .ost and .pst files
		// Searching.ownExtractorOst();

		// Search with wildcards using query
		// Searching.wildCardSearch();

		// Get searching time
		// Searching.getSearchingTime();

		// Searching using morphological word forms
		// Searching.searchUsingMorphologicalWordForm();

		// Generate HTML formatted text with highlighted found words
		// Searching.savingEncodingAutomatically();

		// Search password protected document
		// Searching.searchPasswordProtectedDocument();

		// Search in Outlook Document
		// Searching.outlookEmailMessageResultInfo();

		// Custom Ost Extractor
		// Searching.customOstExtractor();

		// Perform a case sensitive search
		// Searching.caseSensitiveSearch();

		// Manages Stop Word dictionary
		// Searching.manageStopWords();

		// Searches while disabling use of stop words
		// Searching.disableStopWords();

		// use alias dictionary
		// Searching.usingAliasToDictionary();

		// Use homophone search
		// Searching.useHomophoneSearch();

		// Manage homephone dictionary
		// Searching.manageHomophoneDictionary();

		// endregion

		// region Indexing

		// Create Index in memory
		// Indexing.createIndexInMemory();

		// Create Index on disk
		// Indexing.createIndexOnDisk();

		// Create Index in memory with required index settings
		// Indexing.createIndexInMemoryWithIndexSettings();

		// Create Index on disk with required index settings
		// Indexing.createIndexOnDiskWithIndexSettings();

		// Create index with overwriting existing one
		// Indexing.createWithOverwritingExistedIndex();

		// Load Index
		// Indexing.loadIndex();

		// Load Index to Index Repository
		// Indexing.loadIndexToIndexRepository();

		// Add Document to Index
		// Indexing.addDocumentToIndex();

		// Add Document to Index Async
		// Indexing.addDocumentToIndexAsync();

		// Subscribe to events
		// Indexing.subscriptionToEvents();

		// Update Index
		// Indexing.updateIndex();

		// Update Indexes in Index Repository
		// Indexing.updateIndexInRepository();

		// Update Index asynchronously
		// Indexing.updateIndexAsync();

		// Update Index in repo asynchronously
		// Indexing.updateIndexInRepoAsync();

		// Update Index version
		// Indexing.updateIndexVersion();

		// Support for new document format
		// Indexing.customExtractor();

		// Compact Indexing
		// Indexing.compactIndexing();

		// Multi threaded indexing
		// Indexing.multiThreadedIndexing();

		// Multi threaded indexing async
		// Indexing.multiThreadedIndexingAsync();

		// Break Update operation manually
		// Indexing.breakUpdatingManually();

		// Break Updating with cancellation object
		// Indexing.breakUpdatingUsingCancellationObject();

		// Break Merging Manually
		// Indexing.breakMergingManually();

		// Break indexing
		// Indexing.breakIndexing();

		// Break indexing with cancellation object
		// Indexing.breakIndexingWithCancellationObject();

		// Break indexing with time limitation
		// Indexing.breakIndexingWithTimeLimitation();

		// Break Index Repository
		// Indexing.breakIndexRepository();

		// Break Index Repository using Cancellation Object
		// Indexing.breakIndexRepositoryUsingCancellationObject();

		// Get a list of indexed documents from an index
		// Indexing.getListOfIndexedDocuments();

		// Extract Document Text from Index
		// Indexing.extractDocumentTextFromIndex();

		// Extract Document Text from Index to File
		// Indexing.extractDocumentTextToFileFromIndex();

		// Merge Index with Delta Indexes
		// Indexing.mergingIndexWithDeltaIndexes();

		// Merge multiple Indexes
		// Indexing.mergingMultipleIndexes();

		// Merge current index with IndexRepository
		// Indexing.mergingCurrentIndexWithIndexRepository();

		// Merge Index with Delta Indexes Async
		// Indexing.mergingIndexWithDeltaIndexesAsync();

		// Merge multiple Indexes Async
		// Indexing.mergingMultipleIndexesAsync();

		// Merge current index with IndexRepository Async
		// Indexing.mergingCurrentIndexWithIndexRepositoryAsync();

		// Reload Index
		// Indexing.reloadIndex();

		// Cache Text of Indexed Documents
		// Indexing.cacheTextOfIndexedDocuments();

		// endregion

		// region Business Cases

        // Create new books index, add documents and search
		// BusinessCases.searchBooks();

        // Search in existing books index
		// BusinessCases.searchBooksInExistingIndex();

        // Add documents in index
		// BusinessCases.addDocumentsInBooksIndex();

        // Update books index
		// BusinessCases.updateBooksIndex();

        // Search in several indexes
        // BusinessCases.searchInSeveralIndexes();

        // endregion

		System.out.println("Operation completed...");
	}
}