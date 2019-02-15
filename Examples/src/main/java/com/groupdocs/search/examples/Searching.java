package com.groupdocs.search.examples;

import com.groupdocs.search.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Searching {
	/**
	 * performs simple search
	 */
	public static void simpleSearch() {
		// Create index
		Index index = new Index(Utilities.INDEX_PATH);

		// Add documents to index
		index.addToIndex(Utilities.DOCUMENTS_PATH);

		// Search in index
		SearchResults searchResults = index.search("one");

		// Print results in the console
		for (DocumentResultInfo result : searchResults) {
			System.out.println(result.getHitCount() + " hits are in " + result.getFileName());
		}
	}

	/**
	 * performs boolean search
	 */
	public static void booleanSearch() {

		// Create index
		Index index = new Index(Utilities.INDEX_PATH);

		// Add documents to index
		index.addToIndex(Utilities.DOCUMENTS_PATH);

		// Search in index
		SearchResults searchResults = index.search("(Pause AND Return)" + "OR" + "(hydra NOT omega)");

		// Print results in the console
		for (DocumentResultInfo result : searchResults) {
			System.out.println(result.getHitCount() + " hits are in " + result.getFileName());
		}

	}

	/**
	 * Performs regex search
	 */
	public static void regexSearch() {
		// Create index
		Index index = new Index(Utilities.INDEX_PATH);

		// Add documents to index
		index.addToIndex(Utilities.DOCUMENTS_PATH);

		// Search for documents where at least one word contains 'DEF'
		SearchResults searchResults1 = index.search("^.*DEF.*$");

		// Print found files in the Console
		for (DocumentResultInfo documentResultInfo : searchResults1) {
			System.out.println(documentResultInfo.getFileName());
		}

	}

	/**
	 * Performs fuzzy search
	 */
	public static void fuzzySearch() {
		// Create index
		Index index = new Index(Utilities.INDEX_PATH);

		// Add documents to index
		index.addToIndex(Utilities.DOCUMENTS_PATH);

		SearchParameters parameters = new SearchParameters();
		// Turn on fuzzy search feature
		parameters.getFuzzySearch().setEnabled(true);

		// Perform search
		String searchString = "query";
		SearchResults results = index.search(searchString, parameters);

		// Print results to the console
		for (DocumentResultInfo resultsDoc : results) {
			System.out.println(resultsDoc.getFileName());
		}
	}

	/**
	 * Performs fuzzy search based on similarity
	 */
	public static void fuzzySearchSimilarity() {
		// Create index
		Index index = new Index(Utilities.INDEX_PATH);

		// Add documents to index
		index.addToIndex(Utilities.DOCUMENTS_PATH);

		SearchParameters parameters = new SearchParameters();
		// Turn on fuzzy search feature
		parameters.getFuzzySearch().setEnabled(true);
		// Create linear fuzzy algorithm with coefficient of 0.8
		parameters.getFuzzySearch().setFuzzyAlgorithm(new SimilarityLevel(0.8));

		// Perform search
		String searchString = "query";
		SearchResults results = index.search(searchString, parameters);

		// Print results to the console
		for (DocumentResultInfo resultsDoc : results) {
			System.out.println(resultsDoc.getFileName());
		}
	}

	/**
	 * Performs fuzzy search and gets only best results
	 */
	public static void fuzzySearchOnlyBestResultsRange() {
		// Create index
		Index index = new Index(Utilities.INDEX_PATH);

		// Add documents to index
		index.addToIndex(Utilities.DOCUMENTS_PATH);

		SearchParameters searchParameters = new SearchParameters();
		// Enable fuzzy search
		searchParameters.getFuzzySearch().setEnabled(true);
		// Set maximum mistake count to 5
		searchParameters.getFuzzySearch().setFuzzyAlgorithm(new TableDiscreteFunction(5));
		// Set best results range to 2
		searchParameters.getFuzzySearch().setOnlyBestResultsRange((byte) 2);
		// Enable only best results option
		searchParameters.getFuzzySearch().setOnlyBestResults(true);

		// Perform search
		SearchResults searchResults = index.search("some", searchParameters);

		// Print results to the console
		for (DocumentResultInfo resultsDoc : searchResults) {
			System.out.println(resultsDoc.getFileName());
		}
	}

	/**
	 * Performs fuzzy search using table discrete
	 */
	public static void fuzzySearchTableDiscrete() {
		// Create index
		Index index = new Index(Utilities.INDEX_PATH);

		// Add documents to index
		index.addToIndex(Utilities.DOCUMENTS_PATH);

		TableDiscreteFunction adaptiveDiscreteFunction = new TableDiscreteFunction(0, new Step(4, 1), new Step(5, 2),
				new Step(6, 3));
		// Function returns 0 mistakes for words of less than 4 characters,
		// 1 mistake for words of 4 characters,
		// 2 mistakes for words of 5 characters,
		// and 3 mistakes for words of 6 and more characters
		SearchParameters adaptiveSearchParameters = new SearchParameters();
		adaptiveSearchParameters.getFuzzySearch().setEnabled(true);
		adaptiveSearchParameters.getFuzzySearch().setFuzzyAlgorithm(adaptiveDiscreteFunction);
		// Fuzzy search will allow 1 mistake for word 'user', 2 mistakes for
		// word 'query' and 3 mistakes for word 'search'
		SearchResults adaptiveResults = index.search("user search query", adaptiveSearchParameters);

		TableDiscreteFunction constanDiscreteFunction = new TableDiscreteFunction(2);
		// Function returns 2 mistakes for word of any length
		SearchParameters constantSearchParameters = new SearchParameters();
		constantSearchParameters.getFuzzySearch().setEnabled(true);
		constantSearchParameters.getFuzzySearch().setFuzzyAlgorithm(constanDiscreteFunction);
		// Fuzzy search will allow 2 mistakes for all three words in query
		SearchResults constantResults = index.search("user search query", constantSearchParameters);
	}

	/**
	 * Performs faceted search
	 */
	public static void facetedSearch() {
		// Create index
		Index index = new Index(Utilities.INDEX_PATH);

		// Add documents to index
		index.addToIndex(Utilities.DOCUMENTS_PATH);

		String searchString = "query";
		// Search for documents in index that contain word 'query' in file
		// content
		SearchResults searchResults = index.search("Content:" + searchString);

		// Print results to standard output
		for (DocumentResultInfo documentResultInfo : searchResults) {
			System.out.println(documentResultInfo.getFileName() + "\n");
		}
	}

	/**
	 * Performs faceted search with boolean search
	 */
	public static void facetedSearchWithBooleanSearch() {

		// Create index
		Index index = new Index(Utilities.INDEX_PATH);

		// Add documents to index
		index.addToIndex(Utilities.DOCUMENTS_PATH);

		String firstTerm = "term1";
		String secondTerm = "term2";
		// Search for documents in index that contain word 'term1' or word
		// 'term2' or both in file content
		SearchResults searchResults = index.search("Content:" + firstTerm + " OR Content:" + secondTerm);

		// Print results to standard output
		for (DocumentResultInfo documentResultInfo : searchResults) {
			System.out.println(documentResultInfo.getFileName() + "\n");
		}
	}

	/**
	 * Performs synonym search
	 */
	public static void synonymSearch() {
		// Create index
		Index index = new Index(Utilities.INDEX_PATH);
		// Import synonyms from file. Existing synonyms are staying.
		index.getDictionaries().getSynonymDictionary().importDictionary(Utilities.SYNONYM_FILE_PATH);
		// Add documents to the index
		index.addToIndex(Utilities.DOCUMENTS_PATH);

		// Turn on synonym search feature
		SearchParameters parameters = new SearchParameters();
		parameters.setUseSynonymSearch(true);

		// Search for one of words in synonyms.txt file
		String searchString = "virtual";
		SearchResults searchResults = index.search(searchString, parameters);

		// Print found results in the console
		for (DocumentResultInfo documentResultInfo : searchResults) {
			System.out.println(documentResultInfo.getFileName() + "\n");
		}
	}

	/**
	 * Manages synonym search
	 */
	public static void manageSynonyms() {
		// Create or load index
		Index index = new Index(Utilities.INDEX_PATH);

		// Add documents to the index
		index.addToIndex(Utilities.DOCUMENTS_PATH);

		// Clear synonym dictionary
		index.getDictionaries().getSynonymDictionary().clear();

		// Add synonyms
		String[] synonymGroup1 = new String[] { "big", "huge", "colossal", "massive" };
		String[] synonymGroup2 = new String[] { "fast", "agile", "quick", "rapid", "swift" };

		ArrayList<String[]> synonymGroups = new ArrayList<>();
		synonymGroups.add(synonymGroup1);
		synonymGroups.add(synonymGroup2);
		index.getDictionaries().getSynonymDictionary().addRange(synonymGroups);

		// Import synonyms from file. Existing synonyms are staying.
		index.getDictionaries().getSynonymDictionary().importDictionary(Utilities.SYNONYM_FILE_PATH);
		// Export synonyms to file
		index.getDictionaries().getSynonymDictionary().exportDictionary(Utilities.SYNONYM_NEW_FILE_PATH);

		// Enable synonym search in parameters
		SearchParameters parameters = new SearchParameters();
		parameters.setUseSynonymSearch(true);

		// Search for word 'big' and its synonyms
		String searchQuery = "big";
		SearchResults results = index.search(searchQuery, parameters);
	}

	/**
	 * Performs numeric range search
	 */
	public static void numericRangeSearch() {
		Index index = new Index(Utilities.INDEX_PATH);
		index.addToIndex(Utilities.DOCUMENTS_PATH);

		// Search for numbers
		String searchQuery = "150~~300";
		SearchResults searchResults = index.search(searchQuery);
		if (searchResults.getCount() > 0) {
			// Print list of found files in the console
			for (DocumentResultInfo documentResultInfo : searchResults) {
				System.out.println("Document path: " + documentResultInfo.getFileName());
				System.out.println("Hit count: " + documentResultInfo.getHitCount());
			}
		} else {
			System.out.println("No results found");
		}
	}

	/**
	 * Performs data range search
	 */
	public static void dateRangeSearch() {
		// Create index
		Index index = new Index(Utilities.INDEX_PATH);

		// Add documents to the index
		index.addToIndex(Utilities.DOCUMENTS_PATH);

		// Search for dates between 1 Jan 2015 and 31 Dec 2018
		SearchResults searchResults = index.search("daterange(2015-01-01~~2018-12-31)");

		// Print found documents in the console
		for (DocumentResultInfo documentResultInfo : searchResults) {
			System.out.println(documentResultInfo.getFileName());
		}
	}

	/**
	 * Manages date range format
	 */
	public static void manageDateRangeFormats() {
		// Create index
		Index index = new Index(Utilities.INDEX_PATH);

		// Add documents to the index
		index.addToIndex(Utilities.DOCUMENTS_PATH);

		// Create search parameters object
		SearchParameters searchParameters = new SearchParameters();

		// Delete default formats
		searchParameters.getDateFormats().clear();

		// Add format 'MM/dd/yyyy'
		DateFormatElement[] formatElements1 = new DateFormatElement[] { DateFormatElement.getMonthTwoDigits(),
				DateFormatElement.getDateSeparator(), DateFormatElement.getDayOfMonthTwoDigits(),
				DateFormatElement.getDateSeparator(), DateFormatElement.getYearFourDigits(), };
		DateFormat format1 = new DateFormat(formatElements1, "/");
		searchParameters.getDateFormats().addItem(format1);

		// Add format 'dd.MM.yyyy'
		DateFormatElement[] formatElements2 = new DateFormatElement[] { DateFormatElement.getDayOfMonthTwoDigits(),
				DateFormatElement.getDateSeparator(), DateFormatElement.getMonthTwoDigits(),
				DateFormatElement.getDateSeparator(), DateFormatElement.getYearFourDigits(), };
		DateFormat format2 = new DateFormat(formatElements2, ".");
		searchParameters.getDateFormats().addItem(format2);

		// Search for dates
		String searchQuery = "content:daterange(2015-01-01~~2018-12-31)";
		SearchResults searchResults = index.search(searchQuery, searchParameters);
		if (searchResults.getCount() > 0) {
			// Print found documents in the console
			for (DocumentResultInfo documentResultInfo : searchResults) {
				System.out.println("Document: " + documentResultInfo.getFileName());
				System.out.println("Hit count: " + documentResultInfo.getHitCount());
			}
		} else {
			System.out.println("No results found");
		}
	}

	/**
	 * Performs search using spelling corrector
	 */
	public static void spellingCorrectorUsage() {
		// Create or load index
		Index index = new Index(Utilities.INDEX_PATH);
		// Add documents to the index
		index.addToIndex(Utilities.DOCUMENTS_PATH);

		SearchParameters parameters = new SearchParameters();
		// Enable spelling corrector
		parameters.getSpellingCorrector().setEnabled(true);
		// Set max mistake count value to 1. The default value is 2.
		parameters.getSpellingCorrector().setMaxMistakeCount(1);

		// Search for misspelled term 'structure'
		String searchQuery = "stucture";
		SearchResults results = index.search(searchQuery, parameters);
	}

	/**
	 * Manages spelling corrector
	 */
	public static void spellingCorrectorManagement() {
		Index index = new Index(Utilities.INDEX_PATH);
		// Add documents to index
		index.addToIndex(Utilities.DOCUMENTS_PATH);

		// Remove all words from spelling corrector dictionary
		index.getDictionaries().getSpellingCorrector().clear();
		// Import spelling dictionary from file. Existing words are staying.
		index.getDictionaries().getSpellingCorrector().importDictionary(Utilities.SPELLING_DICTIONARY_FILE_PATH);
		ArrayList<String> words = new ArrayList<>();
		words.add("structure");
		words.add("building");
		words.add("rail");
		words.add("house");

		// Add word array to the dictionary. Words are case insensitive.
		index.getDictionaries().getSpellingCorrector().addRange(words);
		// Export spelling dictionary to file.
		index.getDictionaries().getSpellingCorrector()
				.exportDictionary(Utilities.EXPORTED_SPELLING_DICTIONARY_FILE_PATH);

		SearchParameters parameters = new SearchParameters();
		// Enable spelling corrector
		parameters.getSpellingCorrector().setEnabled(true);
		// The default value for maximum mistake count is 2
		parameters.getSpellingCorrector().setMaxMistakeCount(1);

		// Search for misspelled term 'structure'
		String searchQuery = "stucture";
		SearchResults results = index.search(searchQuery, parameters);
	}

	/**
	 * performs search with only best matched spellings
	 */
	public static void spellingCorrectorBestResults() {
		// Create index
		Index index = new Index(Utilities.INDEX_PATH);

		// Add documents to the index
		index.addToIndex(Utilities.DOCUMENTS_PATH);

		SearchParameters searchParameters = new SearchParameters();
		// Enable spelling correction
		searchParameters.getSpellingCorrector().setEnabled(true);
		// Set maximum mistake count value to 5
		searchParameters.getSpellingCorrector().setMaxMistakeCount(5);
		// Enable OnlyBestResults option
		searchParameters.getSpellingCorrector().setOnlyBestResults(true);

		// Perform search
		String searchQuery = "query";
		SearchResults searchResults = index.search(searchQuery, searchParameters);
	}

	/**
	 * Performs search using a different keyboard layout
	 */
	public static void keyboardLayoutCorrectorUsage() {
		String searchQuery = "\u03c0\u03b1\u03b8\u03c3\u03b5";
		// Word 'pause' typed on Greek keyboard layout ('Ï€Î±Î¸ÏƒÎµ')
		// Characters are presented as their Unicode numbers because during
		// compilation process on different computers String literals may be
		// transformed to some other characters

		Index index = new Index(Utilities.INDEX_PATH);
		index.addToIndex(Utilities.DOCUMENTS_PATH);

		// Enable keyboard layout correction in parameters
		SearchParameters parameters = new SearchParameters();
		parameters.getKeyboardLayoutCorrector().setEnabled(true);

		// Search for word 'pause'
		SearchResults results = index.search(searchQuery, parameters);
	}

	/**
	 * Performs search using exact phrase
	 */
	public static void exactPhraseSearch() {
		// Create index
		Index index = new Index(Utilities.INDEX_PATH);

		SearchResults results = index.search("\"exact phrase\"");
		for (DocumentResultInfo result : results) {
			System.out.println(result.getHitCount() + " hits are in " + result.getFileName());
		}
	}

	/**
	 * Performs search by specifying number of searching thread
	 */
	public static void specifyNumberOfThreads() {

		IndexingSettings settings = new IndexingSettings();
		// Specify number of threads for searching
		settings.setSearchingThreads(NumberOfThreads.Four);

		Index index = new Index(Utilities.INDEX_PATH, true, settings);
		index.addToIndex(Utilities.DOCUMENTS_PATH);

		// Search with the specified number of threads
		SearchResults result = index.search("query");
	}

	/**
	 * Cancels search operation
	 */
	public static void cancelSearchOperation() {
		// Creating index
		Index index = new Index(Utilities.INDEX_PATH);

		// Indexing
		index.addToIndex(Utilities.DOCUMENTS_PATH);

		// Creating cancellation object
		final Cancellation cancellation = new Cancellation();
		// Imitating cancelling by request
		Thread thread = new Thread(new Runnable() {
			public void run() {
				// Cancelling after 1 second of searching
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				cancellation.cancel();
			}
		});
		thread.start();

		// Creating search parameters object
		SearchParameters searchParameters = new SearchParameters();
		searchParameters.getFuzzySearch().setEnabled(true);
		searchParameters.getFuzzySearch().setFuzzyAlgorithm(new TableDiscreteFunction(3));

		// Searching
		SearchResults result = index.search("\"information technology\"", searchParameters, cancellation);
	}

	/**
	 * Cancels search operation with time limitation
	 */
	public static void cancelSearchOperationWithTimeLimitation() {
		// Creating index
		Index index = new Index(Utilities.INDEX_PATH);

		// Indexing
		index.addToIndex(Utilities.DOCUMENTS_PATH);

		// Creating cancellation object
		Cancellation cancellation = new Cancellation();
		// Cancelling after 1 second of searching
		cancellation.cancelAfter(1000);

		// Creating search parameters object
		SearchParameters searchParameters = new SearchParameters();
		searchParameters.getFuzzySearch().setEnabled(true);
		searchParameters.getFuzzySearch().setFuzzyAlgorithm(new TableDiscreteFunction(3));

		// Searching
		SearchResults result = index.search("\"information technology\"", searchParameters, cancellation);
	}

	/**
	 * Performs searching by parts or chunks
	 */
	public static void searchingByParts() {
		// Create index
		Index index = new Index(Utilities.INDEX_PATH, true);

		// Add documents to the index
		index.addToIndex(Utilities.DOCUMENTS_PATH);
		index.addToIndex(Utilities.DOCUMENTS_PATH_2);
		index.addToIndex(Utilities.DOCUMENTS_PATH_3);
		int chankCount = 1;
		SearchParameters sp = new SearchParameters();
		// Turn on search by parts
		sp.setChunkSearch(true);

		// Search for the first part of all results
		String searchString = "query";
		SearchResults result = index.search(searchString, sp);
		System.out.println("Document count " + chankCount + " ('" + searchString + "'): " + result.getCount());
		System.out
				.println("Occurrence count " + chankCount + " ('" + searchString + "'): " + result.getTotalHitCount());

		while (result.getNextChunkSearchToken() != null) {
			// Search for the next part of all results
			result = index.search(result.getNextChunkSearchToken());
			System.out.println("Document count " + chankCount + " ('" + searchString + "'): " + result.getCount());
			System.out.println(
					"Occurrence count " + chankCount + " ('" + searchString + "'): " + result.getTotalHitCount());
			chankCount++;
		}
	}

	/**
	 * Generates searching report
	 */
	public static void getSearchReport() {

		Index index = new Index(Utilities.INDEX_PATH);
		index.addToIndex(Utilities.DOCUMENTS_PATH);
		String query1 = "sample";
		SearchParameters param1 = new SearchParameters();
		String query2 = "pause";
		SearchParameters param2 = new SearchParameters();
		param2.setUseHomophoneSearch(true);
		String query3 = "Sample";
		SearchParameters param3 = new SearchParameters();
		param3.setUseCaseSensitiveSearch(true);
		SearchResults results1 = index.search(query1, param1);
		SearchResults results2 = index.search(query2, param2);
		SearchResults results3 = index.search(query3, param3);

		// Get searching report
		SearchingReport[] report = index.getSearchingReport();

		for (SearchingReport record : report) {
			System.out.println(record.getResultCount() + " results was found.");
		}
	}

	/**
	 * Limits search reports
	 */
	public static void limitSearchReport() {
		Index index = new Index(Utilities.INDEX_PATH);

		// Setting the maximum count of search reports
		index.getIndexingSettings().setMaxSearchingReportCount(3);

		// Indexing
		index.addToIndex(Utilities.DOCUMENTS_PATH);

		// Running 100 of searches
		for (int i = 0; i < 100; i++) {
			index.search("Query");
		}

		// Getting search report. Array contains only 3 last records.
		SearchingReport[] report = index.getSearchingReport();

		// This code writes to console information about 3 last searches only
		for (SearchingReport record : report) {
			System.out.println(record.getResultCount() + "results was found.");
		}
	}

	/**
	 * Highlights search results
	 */
	public static void generateHighlightedTextSearchResults() {
		// Creating index
		Index index = new Index(Utilities.INDEX_PATH);

		// Indexing
		index.addToIndex(Utilities.DOCUMENTS_PATH);

		// Searching
		SearchResults results = index.search("some");

		// Generating HTML-formatted text for the first document in search
		// results
		String text = index.highlightInText(results.get_Item(0));
	}

	/**
	 * Saves highlighted text results to a file
	 */
	public static void generateHighlightedTextResultsToFile() {
		// Creating index
		Index index = new Index(Utilities.INDEX_PATH);

		// Indexing
		index.addToIndex(Utilities.DOCUMENTS_PATH);

		// Searching
		SearchResults results = index.search("some");

		// Generating HTML-formatted text for the first document directly to the
		// file 'HighlightedResults.html'
		index.highlightInText(Utilities.DOCUMENT_TEXT_PATH, results.get_Item(0));
	}

	/**
	 * Saves highlighted text to HTML file
	 */
	public static void generateHighlightedTextResultsToHTMLFile() {
		// Creating index
		Index index = new Index(Utilities.INDEX_PATH);

		// Indexing documents
		index.addToIndex(Utilities.DOCUMENTS_PATH);

		// Searching for phrase 'cumulative distribution function'
		SearchResults results = index.search("\"cumulative distribution function\"");

		// Generating HTML-formatted text for the first document directly to the
		// file 'HighlightedResults.html'
		index.highlightInText(Utilities.DOCUMENT_TEXT_PATH, results.get_Item(0));
	}

	/**
	 * Performs search using a query
	 */
	public static void searchWithQuery() {
		// Creating index
		Index index = new Index(Utilities.INDEX_PATH);

		// Indexing
		index.addToIndex(Utilities.DOCUMENTS_PATH);

		// Creating subquery 1
		SearchQuery subquery1 = SearchQuery.createWordQuery("is");
		subquery1.getSearchParameters().getFuzzySearch().setEnabled(true);
		subquery1.getSearchParameters().getFuzzySearch().setConsiderTranspositions(true);
		subquery1.getSearchParameters().getFuzzySearch().setFuzzyAlgorithm(new TableDiscreteFunction(3));

		// Creating subquery 2
		SearchQuery subquery2 = SearchQuery.createNumericRangeQuery(1, 1000000);

		// Creating subquery 3
		SearchQuery subquery3 = SearchQuery.createRegexQuery("(.)\1");

		// Combining subqueries into one query
		SearchQuery query = SearchQuery.createPhraseSearchQuery(subquery1, subquery2, subquery3);

		// Creating search parameters object with increased capacity of results
		SearchParameters searchParameters = new SearchParameters();
		searchParameters.setMaxHitCountPerTerm(1000000);
		searchParameters.setMaxTotalHitCount(10000000);

		// Searching
		SearchResults searchResults = index.search(query, searchParameters);
	}

	/**
	 * Performs file name search
	 */
	public static void searchFileName() {
		// Create index
		Index index = new Index(Utilities.INDEX_PATH);

		// Add documents to index
		index.addToIndex(Utilities.DOCUMENTS_PATH);

		// Searching for any document in index that contain search string in
		// file name
		SearchResults searchResults = index.search("FileName:" + "getting");
	}

	/**
	 * Shows how to implement own custom extractor for outlook document for the
	 * extension .ost and .pst files
	 */
	public static void ownExtractorOst() {
		// Create or load index
		Index index = new Index(Utilities.INDEX_PATH);

		index.getCustomExtractors().addItem(new CustomOstPstExtractor()); // Adding
																			// new
																			// custom
																			// extractor
																			// for
																			// container
																			// document

		index.addToIndex(Utilities.DOCUMENTS_PATH); // Documents with "ost" and
													// "pst" extension will be
													// indexed using
													// MyCustomContainerExtractor

		SearchResults searchResults = index.search("pause");
	}

	/**
	 * Generates HTML formatted text with highlighted found words
	 */
	public static void savingEncodingAutomatically() {
		// Creating index
		Index index = new Index(Utilities.INDEX_PATH);

		// Subscribing to file indexing event
		index.FileIndexing.add(new EventHandler<FileIndexingEventArgs>() {
			public void invoke(Object sender, FileIndexingEventArgs args) {
				// Setting encoding for each text file during indexing
				args.setEncoding(Encodings.windows_1251);
			}
		});

		// Adding text documents encoded in windows-1251 to index
		index.addToIndex(Utilities.DOCUMENTS_PATH);

		// Searching for word 'Ñ‡ÐµÐ»Ð¾Ð²ÐµÑ‡ÐµÑ�ÐºÐ¸Ð¹'
		SearchResults results = index.search("Ñ‡ÐµÐ»Ð¾Ð²ÐµÑ‡ÐµÑ�ÐºÐ¸Ð¹");

		// Generating HTML formatted text with highlighted found words
		// There is no need to provide the encoding again - it is saved in the
		// index
		String htmlText = index.highlightInText(results.get_Item(0));
	}

	/**
	 * Performs search using morphological word forms
	 */
	public static void searchUsingMorphologicalWordForm() {
		Index index = new Index(Utilities.INDEX_PATH); // Creating index in
														// c:\MyIndex\ folder
		index.addToIndex(Utilities.DOCUMENTS_PATH); // Indexing folder with
													// documents

		SearchParameters parameters = new SearchParameters();
		parameters.setUseWordFormsSearch(true); // Enabling word forms search

		SearchResults results1 = index.search("simplest", parameters); // Searching
																		// for
																		// words
																		// "simplest",
																		// "simple",
																		// and
																		// "simpler"
		SearchResults results2 = index.search("swimming", parameters); // Searching
																		// for
																		// words
																		// "swim",
																		// "swims",
																		// "swimming",
																		// "swam",
																		// "swum"
	}

	/**
	 * Gets searching time
	 */
	public static void getSearchingTime() {
		Index index = new Index(Utilities.INDEX_PATH, true);
		index.addToIndex(Utilities.DOCUMENTS_PATH);
		SearchResults result = index.search("pause");

		System.out.println("Searching starts: " + result.getStartTime() + "Searching Ends: " + result.getEndTime()
				+ "Searching Total Time: " + result.getSearchingTime());
	}

	/**
	 * Performs search with wildcards using query
	 */
	public static void wildCardSearchInPhraseSearch() {
		// Creating index
		Index index = new Index(Utilities.INDEX_PATH);

		index.addToIndex(Utilities.DOCUMENTS_PATH);

		// Searching for 'First law of thermodynamics'
		// Note that wildcard is used instead of 'of' because it is not indexed
		// as a stop word
		SearchResults result1 = index.search("\"First law *1 thermodynamics\"");

		// Searching for 'Frodo spoke to Pippin' and 'Frodo stripped the
		// blankets from Pippin'
		SearchResults result2 = index.search("\"Frodo *1~~5 Pippin\"");
	}

	/**
	 * Performs search in password protected document
	 */
	public static void searchPasswordProtectedDocument() {
		// Creating index
		Index index = new Index(Utilities.INDEX_PATH);
		// Sets passwords for documents
		index.getDictionaries().getDocumentPasswords().add(Utilities.PASSWORD_PROTECTED_DOCUMENT, "test");
		// Indexing
		index.addToIndex(Utilities.DOCUMENTS_PATH, true);
		// Searching
		SearchResults results = index.search("sample");
		// Print results in the console
		for (DocumentResultInfo result : results) {
			System.out.println(result.getHitCount() + " hits are in " + result.getFileName());
		}
	}

	/**
	 * Performs search operation in outlook email message
	 */
	public static void outlookEmailMessageResultInfo() {
		// Create or load index
		Index index = new Index(Utilities.INDEX_PATH);
		index.addToIndex(Utilities.DOCUMENTS_PATH, true);
		// Query
		String searchString = "pause";

		SearchResults results = index.search(searchString);
		for (DocumentResultInfo resultInfo : results) {
			if (resultInfo.getDocumentType() == DocumentType.OutlookEmailMessage) {
				// for email message result info user should cast resultInfo as
				// OutlookEmailMessageResultInfo for acessing EntryIdString
				// property
				OutlookEmailMessageResultInfo emailResultInfo = (OutlookEmailMessageResultInfo) resultInfo;

				System.out.println("Query " + searchString + " has " + emailResultInfo.getHitCount()
						+ " hit count in message " + emailResultInfo.getEntryIdString() + " in file "
						+ emailResultInfo.getFileName() + "");
			}
		}
	}

	/**
	 * Shows how to implement own custom extractor for outlook document for the
	 * extension .ost and .pst files
	 */
	public static void customOstExtractor() {
		Index index = new Index(Utilities.INDEX_PATH);

		index.getCustomExtractors().addItem(new CustomOstPstExtractor()); // Adding
																			// new
																			// custom
																			// extractor
																			// for
																			// container
																			// document

		index.addToIndex(Utilities.DOCUMENTS_PATH); // Documents with "ost" and
													// "pst" extension will be
													// indexed using
													// MyCustomContainerExtractor

		SearchResults searchResults = index.search("pause");
	}

	/**
	 * Performs a case sensitive search
	 */
	public static void caseSensitiveSearch() {
		IndexingSettings settings = new IndexingSettings();

		// Create or load index
		Index index = new Index(Utilities.INDEX_PATH, settings);

		index.addToIndex(Utilities.DOCUMENTS_PATH, true);

		SearchParameters parameters = new SearchParameters();
		parameters.setUseCaseSensitiveSearch(true);// using case sensitive
													// search feature

		SearchResults searchResults = index.search("SAMPLE", parameters);

		// Print results in the console
		for (DocumentResultInfo result : searchResults) {
			System.out.println(result.getHitCount() + " hits are in " + result.getFileName());
		}
	}

	/**
	 * Manages stop words
	 */
	public static void manageStopWords() {
		// create or load index
		Index index = new Index(Utilities.INDEX_PATH);
		int stopWordsCount = index.getDictionaries().getStopWordDictionary().getCount(); // Get
																							// count
																							// of
																							// stop
																							// words
		index.getDictionaries().getStopWordDictionary().clear(); // Clear
																	// dictionary
																	// of stop
																	// words

		index.getDictionaries().getStopWordDictionary().addRange(Arrays.asList("one", "two", "three")); // Add
																										// several
																										// stop
																										// words
																										// to
																										// dictionary.
																										// Words
																										// are
																										// case
																										// insensitive.
		index.getDictionaries().getStopWordDictionary().removeRange(Arrays.asList("one", "two")); // Remove
																									// stop
																									// words
																									// from
																									// dictionary.
																									// Words
																									// which
																									// are
																									// absent
																									// will
																									// be
																									// ignored.

		index.addToIndex(Utilities.DOCUMENTS_PATH, true);

		index.getDictionaries().getStopWordDictionary().importDictionary(Utilities.STOP_WORDS_FILE_PATH); // Import
																											// stop
																											// words
																											// from
																											// file.
																											// Existing
																											// stop
																											// words
																											// are
																											// staying.
		index.getDictionaries().getStopWordDictionary().exportDictionary(Utilities.EXPORTED_STOP_WORDS_FILE_PATH); // Export
																													// stop
																													// words
																													// to
																													// file

		SearchResults results = index.search("three");

		// Print results in the console
		for (DocumentResultInfo result : results) {
			System.out.println(result.getHitCount() + " hits are in " + result.getFileName());
		}

	}

	/**
	 * Disables stop words
	 */
	public static void disableStopWords() {
		Index index = new Index(Utilities.INDEX_PATH);

		index.getIndexingSettings().setUseStopWords(true); // This line disables
															// using stop words
															// and all of the
															// words in
															// documents will be
															// indexed

		index.addToIndex(Utilities.DOCUMENTS_PATH);
		SearchResults results = index.search("one");

		// Print results in the console
		for (DocumentResultInfo result : results) {
			System.out.println(result.getHitCount() + " hits are in " + result.getFileName());
		}

	}

	/**
	 * Clears, adds, removes, imports and exports alias to the dictionary
	 */
	public static void usingAliasToDictionary() {
		// Create or load index
		Index index = new Index(Utilities.INDEX_PATH);
		// Add documents to index
		index.addToIndex(Utilities.DOCUMENTS_PATH, true);

		// Add alias 's' to the dictionary
		index.getDictionaries().getAliasDictionary().add("s", "structure");

		// Clear dictionary of aliases
		index.getDictionaries().getAliasDictionary().clear();
		// Add alias 's' to the dictionary. Alias and aliased text are case
		// insensitive.
		index.getDictionaries().getAliasDictionary().add("s", "structure");
		// Remove alias 'x' from the dictionary. Words which are absent will be
		// ignored.
		index.getDictionaries().getAliasDictionary().remove("x");
		// Import aliases from file. Existing aliases are staying.
		index.getDictionaries().getAliasDictionary().importDictionary(Utilities.ALIAS_FILE_PATH);
		// Export aliases to file
		index.getDictionaries().getAliasDictionary().exportDictionary(Utilities.EXPORTED_ALIAS_FILE_PATH);

		// Search for term 'structure'
		SearchResults results = index.search("@s");

		// Print results in the console
		for (DocumentResultInfo result : results) {
			System.out.println(result.getHitCount() + " hits are in " + result.getFileName());
		}
	}

	/**
	 * Performs homophone search
	 */
	public static void useHomophoneSearch() {
		// Create or load index
		Index index = new Index(Utilities.INDEX_PATH);
		// Add documents to index
		index.addToIndex(Utilities.DOCUMENTS_PATH);

		SearchParameters parameters = new SearchParameters();
		// Enable homophone search in parameters
		parameters.setUseHomophoneSearch(true);

		// Search for "pause", "paws", "pores", "pours"
		SearchResults results = index.search("pause", parameters);

		// Print results in the console
		for (DocumentResultInfo result : results) {
			System.out.println(result.getHitCount() + " hits are in " + result.getFileName());
		}
	}

	/**
	 * Manages homophone dictionary
	 */
	public static void manageHomophoneDictionary() {
		// Create or load index
		Index index = new Index(Utilities.INDEX_PATH);
		// Add documents to index
		index.addToIndex(Utilities.DOCUMENTS_PATH, true);

		// Clearing homophone dictionary
		index.getDictionaries().getHomophoneDictionary().clear();

		// Adding homophones
		String[] homophoneGroup1 = new String[] { "braise", "brays", "braze" };
		String[] homophoneGroup2 = new String[] { "pause", "paws", "pores", "pours" };
		ArrayList<String[]> homophoneGroups = new ArrayList<>();
		homophoneGroups.add(homophoneGroup1);
		homophoneGroups.add(homophoneGroup2);
		index.getDictionaries().getHomophoneDictionary().addRange(homophoneGroups);

		// Import homophones from file. Existing homophones are staying.
		index.getDictionaries().getHomophoneDictionary().importDictionary(Utilities.HOMOPHONES_FILE_PATH);
		// Export homophones to file
		index.getDictionaries().getHomophoneDictionary().exportDictionary(Utilities.EXPORTED_HOMOPHONES_FILE_PATH);

		SearchParameters parameters = new SearchParameters();
		// Enable homophone search in parameters
		parameters.setUseHomophoneSearch(true);

		// Search for "pause", "paws", "pores", "pours"
		SearchResults results = index.search("pause", parameters);

		// Print results in the console
		for (DocumentResultInfo result : results) {
			System.out.println(result.getHitCount() + " hits are in " + result.getFileName());
		}
	}

	/**
	 * Performs blended characters search
	 * This method is supported by version 18.12 or greater
	 */
	public static void searchBlendedCharacters() {

		// Creating index
		Index index = new Index(Utilities.INDEX_PATH,true);

		// Marking hyphen as blended character
		index.getDictionaries().getAlphabet().setRange(new char[] { '-' }, CharacterType.Blended);

		// Adding documents to index
		index.addToIndex(Utilities.DOCUMENTS_PATH,true);

		// Searching for word 'silver-gray'
		SearchResults results = index.search("silver-gray");
		// Print results in the console
		for (DocumentResultInfo result : results) {
			System.out.println(result.getHitCount() + " hits are in " + result.getFileName());
		}
	}

	/**
	 * Performs wild card search
	 * This method is supported by version 18.12 or greater
	 */
	public static void wildCardSearch() {
		// Creating index
		Index index = new Index(Utilities.INDEX_PATH,true);

		// Adding documents to index
		index.addToIndex(Utilities.DOCUMENTS_PATH,true);

		// Searching for words 'affect' or 'effect' in a one document with 'principal', 'principle', 'principles', or 'principally'
		SearchResults results1 = index.search("?ffect & princip?(2~4)");

		// Searching with a single query for phrases 'assure equal opportunities', 'ensure equal opportunities', and 'sure equal opportunities'
		SearchResults results2 = index.search("\"?(0~2)sure equal opportunities\"");
	}

	/**
	 * Performs wild card search using object
	 * This method is supported by version 18.12 or greater
	 */
	public static void wildCardSearchUsingObject() {
		// Creating index
		Index index = new Index(Utilities.INDEX_PATH,true);

		// Adding documents to index
		index.addToIndex(Utilities.DOCUMENTS_PATH, true);

		// Constructing query 1
		// Word 1 in the query is a pattern '?ffect' for wildcard search
		WordPattern pattert11 = new WordPattern();
		pattert11.appendOneCharacterWildcard();
		pattert11.appendString("ffect");
		SearchQuery subquery11 = SearchQuery.createWordPatternQuery(pattert11);

		// Word 2 in the query is a pattern 'princip?(2~4)' for wildcard search
		WordPattern pattert12 = new WordPattern();
		pattert12.appendString("princip");
		pattert12.appendWildcard(2, 4);
		SearchQuery subquery12 = SearchQuery.createWordPatternQuery(pattert12);

		// Creating boolean search query
		SearchQuery query1 = SearchQuery.createAndQuery(subquery11, subquery12);

		// Searching with query 1
		SearchResults results1 = index.search(query1, new SearchParameters());

		// Constructing query 2
		// Word 1 in the phrase is a pattern '?(0~2)sure' for wildcard search
		WordPattern pattert21 = new WordPattern();
		pattert21.appendWildcard(0, 2);
		pattert21.appendString("sure");
		SearchQuery subquery21 = SearchQuery.createWordPatternQuery(pattert21);

		// Word 2 in the phrase is searched with different word forms ('equal', 'equals', 'equally', etc.)
		SearchQuery subquery22 = SearchQuery.createWordQuery("equal");
		subquery22.setSearchParameters(new SearchParameters());
		subquery22.getSearchParameters().setUseWordFormsSearch(true);

		// Word 3 in the phrase is searched with maximum 2 differences of fuzzy search
		SearchQuery subquery23 = SearchQuery.createWordQuery("opportunities");
		subquery23.setSearchParameters(new SearchParameters());
		subquery23.getSearchParameters().getFuzzySearch().setEnabled(true);
		subquery23.getSearchParameters().getFuzzySearch().setFuzzyAlgorithm(new TableDiscreteFunction(2));

		// Creating phrase search query
		SearchQuery query2 = SearchQuery.createPhraseSearchQuery(subquery21, subquery22, subquery23);

		// Searching with query 2
		SearchResults results2 = index.search(query2, new SearchParameters());
	}
	/**
	 * Escaping special characters in search queries
	 * Feature is supported in version 19.2 of the API
	 */
	public static void escapingSpecialCharacterInSearch()
    {
        //ExStart:EscapingSpecialCharacterInSearch
        // Creating index
        Index index = new Index(Utilities.INDEX_PATH);

        // Marking character '&' as a valid letter, not a separator
        index.getDictionaries().getAlphabet().setRange(new char[] { '&' }, CharacterType.Letter);

        // Indexing
        index.addToIndex(Utilities.DOCUMENTS_PATH);
        
        // Searching for word 'R&B'
        SearchResults results0 = index.search("R\\&B");

        // Searching for word 'R&B'
        SearchResults results1 = index.search("R\\u0026B");

        //ExEnd:EscapingSpecialCharacterInSearch

    }
	/**
	 * Escaping special characters in search queries
	 * Feature is supported in version 19.2 of the API
	 */
	 public static void searchZipArchives()
     {
         //ExStart:SearchZipArchives
         // Creating index
         Index index = new Index(Utilities.INDEX_PATH);

         // Before indexing, please make sure that Zip archives exist in the documents directory
         index.addToIndex(Utilities.DOCUMENTS_PATH);

         // Searching for Zips
         SearchResults results = index.search("Zip");

         // Display the counts of Zips
         System.out.println("Zips Found: "+results.getCount());

         //ExEnd:SearchZipArchives


     }
}