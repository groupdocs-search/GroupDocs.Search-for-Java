package com.groupdocs.search.examples.java;
import com.groupdocs.search.*;
import java.util.ArrayList;

public class Searching {
    // Creates index, adds documents to index and search string in index
    public static void simpleSearch(String searchString) {
        // Create index
        Index index = new Index(Utilities.indexPath);

        // Add documents to index
        index.addToIndex(Utilities.documentsPath);

        // Search in index
        SearchResults searchResults = index.search(searchString);

        // Print results in the console
        for (DocumentResultInfo result : searchResults) {
            System.out.println(result.getHitCount() + " hits are in " + result.getFileName());
        }
    }

    // Creates index, adds documents to index and do boolean search
    public static void booleanSearch(String firstTerm, String secondTerm) {

        // Create index
        Index index = new Index(Utilities.indexPath);

        // Add documents to index
        index.addToIndex(Utilities.documentsPath);

        // Search in index
        SearchResults searchResults = index.search(firstTerm + "OR" + secondTerm);

        // Print results in the console
        for (DocumentResultInfo result : searchResults) {
            System.out.println(result.getHitCount() + " hits are in " + result.getFileName());
        }

    }

    // Creates index, adds documents to index and do regex search
    public static void regexSearch() {
        // Create index
        Index index = new Index(Utilities.indexPath);

        // Add documents to index
        index.addToIndex(Utilities.documentsPath);

        // Search for documents where at least one word contains 'DEF'
        SearchResults searchResults1 = index.search("^.*DEF.*$");

        // Print found files in the Console
        for (DocumentResultInfo documentResultInfo : searchResults1) {
            System.out.println(documentResultInfo.getFileName());
        }

    }

    //Search results from misspelled search query
    public static void fuzzySearch() {
        // Create index
        Index index = new Index(Utilities.indexPath);

        // Add documents to index
        index.addToIndex(Utilities.documentsPath);

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

    //Show results based on similarity
    public static void fuzzySearchSimilarity() {
        // Create index
        Index index = new Index(Utilities.indexPath);

        // Add documents to index
        index.addToIndex(Utilities.documentsPath);

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

    //Fuzzy search that uses table of word length values mapped to max mistake count values
    public static void fuzzySearchOnlyBestResultsRange() {
        // Create index
        Index index = new Index(Utilities.indexPath);

        // Add documents to index
        index.addToIndex(Utilities.documentsPath);

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

    //Fuzzy search that uses table of word length values mapped to max mistake count values
    public static void fuzzySearchTableDiscrete() {
        // Create index
        Index index = new Index(Utilities.indexPath);

        // Add documents to index
        index.addToIndex(Utilities.documentsPath);

        TableDiscreteFunction adaptiveDiscreteFunction = new TableDiscreteFunction(0, new Step(4, 1), new Step(5, 2), new Step(6, 3));
        // Function returns 0 mistakes for words of less than 4 characters,
        // 1 mistake for words of 4 characters,
        // 2 mistakes for words of 5 characters,
        // and 3 mistakes for words of 6 and more characters
        SearchParameters adaptiveSearchParameters = new SearchParameters();
        adaptiveSearchParameters.getFuzzySearch().setEnabled(true);
        adaptiveSearchParameters.getFuzzySearch().setFuzzyAlgorithm(adaptiveDiscreteFunction);
        // Fuzzy search will allow 1 mistake for word 'user', 2 mistakes for word 'query' and 3 mistakes for word 'search'
        SearchResults adaptiveResults = index.search("user search query", adaptiveSearchParameters);

        TableDiscreteFunction constanDiscreteFunction = new TableDiscreteFunction(2);
        // Function returns 2 mistakes for word of any length
        SearchParameters constantSearchParameters = new SearchParameters();
        constantSearchParameters.getFuzzySearch().setEnabled(true);
        constantSearchParameters.getFuzzySearch().setFuzzyAlgorithm(constanDiscreteFunction);
        // Fuzzy search will allow 2 mistakes for all three words in query
        SearchResults constantResults = index.search("user search query", constantSearchParameters);
    }

    //Search for specific fields of document
    public static void facetedSearch() {
        // Create index
        Index index = new Index(Utilities.indexPath);

        // Add documents to index
        index.addToIndex(Utilities.documentsPath);

        String searchString = "query";
        // Search for documents in index that contain word 'query' in file content
        SearchResults searchResults = index.search("Content:" + searchString);

        // Print results to standard output
        for (DocumentResultInfo documentResultInfo : searchResults) {
            System.out.println(documentResultInfo.getFileName() + "\n");
        }
    }

    public static void facetedSearchWithBooleanSearch() {

        // Create index
        Index index = new Index(Utilities.indexPath);

        // Add documents to index
        index.addToIndex(Utilities.documentsPath);

        String firstTerm = "term1";
        String secondTerm = "term2";
        // Search for documents in index that contain word 'term1' or word 'term2' or both in file content
        SearchResults searchResults = index.search("Content:" + firstTerm + " OR Content:" + secondTerm);

        // Print results to standard output
        for (DocumentResultInfo documentResultInfo : searchResults) {
            System.out.println(documentResultInfo.getFileName() + "\n");
        }
    }

    //Search for documents with Synonyms
    public static void synonymSearch() {
        // Create index
        Index index = new Index(Utilities.indexPath);
        // Import synonyms from file. Existing synonyms are staying.
        index.getDictionaries().getSynonymDictionary().importDictionary(Utilities.synonymFilePath);
        // Add documents to the index
        index.addToIndex(Utilities.documentsPath);

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

    //Manages Synonyms search
    public static void manageSynonyms() {
        // Create or load index
        Index index = new Index(Utilities.indexPath);

        // Add documents to the index
        index.addToIndex(Utilities.documentsPath);

        // Clear synonym dictionary
        index.getDictionaries().getSynonymDictionary().clear();

        // Add synonyms
        String[] synonymGroup1 = new String[]{"big", "huge", "colossal", "massive"};
        String[] synonymGroup2 = new String[]{"fast", "agile", "quick", "rapid", "swift"};

        ArrayList<String[]> synonymGroups = new ArrayList<>();
        synonymGroups.add(synonymGroup1);
        synonymGroups.add(synonymGroup2);
        index.getDictionaries().getSynonymDictionary().addRange(synonymGroups);

        // Import synonyms from file. Existing synonyms are staying.
        index.getDictionaries().getSynonymDictionary().importDictionary(Utilities.synonymFilePath);
        // Export synonyms to file
        index.getDictionaries().getSynonymDictionary().importDictionary(Utilities.synonymNewFilePath);

        // Enable synonym search in parameters
        SearchParameters parameters = new SearchParameters();
        parameters.setUseSynonymSearch(true);

        // Search for word 'big' and its synonyms
        String searchQuery = "big";
        SearchResults results = index.search(searchQuery, parameters);
    }

    //Perform numeric range search
    public static void numericRangeSearch() {
        Index index = new Index(Utilities.indexPath);
        index.addToIndex(Utilities.documentsPath);

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

    //Perform date range search
    public static void dateRangeSearch() {
        // Create index
        Index index = new Index(Utilities.indexPath);

        // Add documents to the index
        index.addToIndex(Utilities.documentsPath);

        // Search for dates between 1 Jan 2015 and 31 Dec 2018
        SearchResults searchResults = index.search("daterange(2015-01-01~~2018-12-31)");

        // Print found documents in the console
        for (DocumentResultInfo documentResultInfo : searchResults) {
            System.out.println(documentResultInfo.getFileName());
        }
    }

    //Manage date range formats
    public static void manageDateRangeFormats() {
        // Create index
        Index index = new Index(Utilities.indexPath);

        // Add documents to the index
        index.addToIndex(Utilities.documentsPath);

        // Create search parameters object
        SearchParameters searchParameters = new SearchParameters();

        // Delete default formats
        searchParameters.getDateFormats().clear();

        // Add format 'MM/dd/yyyy'
        DateFormatElement[] formatElements1 = new DateFormatElement[]
                {
                        DateFormatElement.getMonthTwoDigits(),
                        DateFormatElement.getDateSeparator(),
                        DateFormatElement.getDayOfMonthTwoDigits(),
                        DateFormatElement.getDateSeparator(),
                        DateFormatElement.getYearFourDigits(),
                };
        DateFormat format1 = new DateFormat(formatElements1, "/");
        searchParameters.getDateFormats().addItem(format1);

        // Add format 'dd.MM.yyyy'
        DateFormatElement[] formatElements2 = new DateFormatElement[]
                {
                        DateFormatElement.getDayOfMonthTwoDigits(),
                        DateFormatElement.getDateSeparator(),
                        DateFormatElement.getMonthTwoDigits(),
                        DateFormatElement.getDateSeparator(),
                        DateFormatElement.getYearFourDigits(),
                };
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

    //Search using Spelling Corrector
    public static void spellingCorrectorUsage() {
        // Create or load index
        Index index = new Index(Utilities.indexPath);
        // Add documents to the index
        index.addToIndex(Utilities.documentsPath);

        SearchParameters parameters = new SearchParameters();
        // Enable spelling corrector
        parameters.getSpellingCorrector().setEnabled(true);
        // Set max mistake count value to 1. The default value is 2.
        parameters.getSpellingCorrector().setMaxMistakeCount(1);

        // Search for misspelled term 'structure'
        String searchQuery = "stucture";
        SearchResults results = index.search(searchQuery, parameters);
    }

    //Manage spelling corrector
    public static void spellingCorrectorManagement() {
        Index index = new Index(Utilities.indexPath);
        // Add documents to index
        index.addToIndex(Utilities.documentsPath);

        // Remove all words from spelling corrector dictionary
        index.getDictionaries().getSpellingCorrector().clear();
        // Import spelling dictionary from file. Existing words are staying.
        index.getDictionaries().getSpellingCorrector().importDictionary(Utilities.spellingDictionaryFilePath);
        ArrayList<String> words = new ArrayList<>();
        words.add("structure");
        words.add("building");
        words.add("rail");
        words.add("house");

        // Add word array to the dictionary. Words are case insensitive.
        index.getDictionaries().getSpellingCorrector().addRange(words);
        // Export spelling dictionary to file.
        index.getDictionaries().getSpellingCorrector().exportDictionary(Utilities.exportedSpellingDictionaryFilePath);

        SearchParameters parameters = new SearchParameters();
        // Enable spelling corrector
        parameters.getSpellingCorrector().setEnabled(true);
        // The default value for maximum mistake count is 2
        parameters.getSpellingCorrector().setMaxMistakeCount(1);

        // Search for misspelled term 'structure'
        String searchQuery = "stucture";
        SearchResults results = index.search(searchQuery, parameters);
    }

    //Search only best results
    public static void spellingCorrectorBestResults() {
        // Create index
        Index index = new Index(Utilities.indexPath);

        // Add documents to the index
        index.addToIndex(Utilities.documentsPath);

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

    //Search using Keyboard Layout Corrector
    public static void keyboardLayoutCorrectorUsage() {
        String searchQuery = "\u03c0\u03b1\u03b8\u03c3\u03b5";
        // Word 'pause' typed on Greek keyboard layout ('παθσε')
        // Characters are presented as their Unicode numbers because during compilation process on different computers String literals may be transformed to some other characters

        Index index = new Index(Utilities.indexPath);
        index.addToIndex(Utilities.documentsPath);

        // Enable keyboard layout correction in parameters
        SearchParameters parameters = new SearchParameters();
        parameters.getKeyboardLayoutCorrector().setEnabled(true);

        // Search for word 'pause'
        SearchResults results = index.search(searchQuery, parameters);
    }

    //Search documents wih exact phrase
    public static void exactPhraseSearch() {
        // Create index
        Index index = new Index(Utilities.indexPath);

        SearchResults results = index.search("\"exact phrase\"");
        for (DocumentResultInfo result : results) {
            System.out.println(result.getHitCount() + " hits are in " + result.getFileName());
        }
    }

    //Specify number of thread for searching
    public static void specifyNumberOfThreads() {

        IndexingSettings settings = new IndexingSettings();
        // Specify number of threads for searching
        settings.setSearchingThreads(NumberOfThreads.Four);

        Index index = new Index(Utilities.indexPath, true, settings);
        index.addToIndex(Utilities.documentsPath);

        // Search with the specified number of threads
        SearchResults result = index.search("query");
    }

    //Cancel Search Operation
    public static void cancelSearchOperation() {
        // Creating index
        Index index = new Index(Utilities.indexPath);

        // Indexing
        index.addToIndex(Utilities.documentsPath);

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

    //Cancel Search Operation with time limitation
    public static void cancelSearchOperationWithTimeLimitation() {
        // Creating index
        Index index = new Index(Utilities.indexPath);

        // Indexing
        index.addToIndex(Utilities.documentsPath);

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

    //Searching by parts(Chunks)
    public static void searchingByParts() {
        // Create index
        Index index = new Index(Utilities.indexPath, true);

        // Add documents to the index
        index.addToIndex(Utilities.documentsPath);
        index.addToIndex(Utilities.documentsPath2);
        index.addToIndex(Utilities.documentsPath3);
        int chankCount = 1;
        SearchParameters sp = new SearchParameters();
        // Turn on search by parts
        sp.setChunkSearch(true);

        // Search for the first part of all results
        String searchString = "query";
        SearchResults result = index.search(searchString, sp);
        System.out.println("Document count " + chankCount + " ('" + searchString + "'): " + result.getCount());
        System.out.println("Occurrence count " + chankCount + " ('" + searchString + "'): " + result.getTotalHitCount());


        while (result.getNextChunkSearchToken() != null) {
            // Search for the next part of all results
            result = index.search(result.getNextChunkSearchToken());
            System.out.println("Document count " + chankCount + " ('" + searchString + "'): " + result.getCount());
            System.out.println("Occurrence count " + chankCount + " ('" + searchString + "'): " + result.getTotalHitCount());
            chankCount++;
        }
    }

    //Get search report
    public static void getSearchReport() {

        Index index = new Index(Utilities.indexPath);
        index.addToIndex(Utilities.documentsPath);
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

    //Limit Search Report
    public static void limitSearchReport() {
        Index index = new Index(Utilities.indexPath);

        // Setting the maximum count of search reports
        index.getIndexingSettings().setMaxSearchingReportCount(3);

        // Indexing
        index.addToIndex(Utilities.documentsPath);

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

    //Highlight search results
    public static void generateHighlightedTextSearchResults() {
        // Creating index
        Index index = new Index(Utilities.indexPath);

        // Indexing
        index.addToIndex(Utilities.documentsPath);

        // Searching
        SearchResults results = index.search("some");

        // Generating HTML-formatted text for the first document in search results
        String text = index.highlightInText(results.get_Item(0));
    }

    public static void generateHighlightedTextResultsToFile() {
        // Creating index
        Index index = new Index(Utilities.indexPath);

        // Indexing
        index.addToIndex(Utilities.documentsPath);

        // Searching
        SearchResults results = index.search("some");

        // Generating HTML-formatted text for the first document directly to the file 'HighlightedResults.html'
        index.highlightInText("HighlightedResults.html", results.get_Item(0));
    }

    //Highlight search results to HTML File
    public static void generateHighlightedTextResultsToHTMLFile() {
        // Creating index
        Index index = new Index(Utilities.indexPath);

        // Indexing documents
        index.addToIndex(Utilities.documentsPath);

        // Searching for phrase 'cumulative distribution function'
        SearchResults results = index.search("\"cumulative distribution function\"");

        // Generating HTML-formatted text for the first document directly to the file 'HighlightedResults.html'
        index.highlightInText("HighlightedResults.html", results.get_Item(0));
    }

    //Search with Query as a parameter
    public static void searchWithQuery() {
        // Creating index
        Index index = new Index(Utilities.indexPath);

        // Indexing
        index.addToIndex(Utilities.documentsPath);

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

    //Search for any document in index
    public static void searchFileName() {
        // Create index
        Index index = new Index(Utilities.indexPath);

        // Add documents to index
        index.addToIndex(Utilities.documentsPath);

        // Searching for any document in index that contain search string in file name
        SearchResults searchResults = index.search("FileName:" + "getting");
    }

    // Shows how to implement own custom extractor for outlook document for the extension .ost and .pst files
    public static void ownExtractorOst() {
        // Create or load index
        Index index = new Index(Utilities.indexPath);

        index.getCustomExtractors().addItem(new CustomOstPstExtractor()); // Adding new custom extractor for container document

        index.addToIndex(Utilities.documentsPath); // Documents with "ost" and "pst" extension will be indexed using MyCustomContainerExtractor

        SearchResults searchResults = index.search("pause");
    }

    //Generate HTML formatted text with highlighted found words
    public static void savingEncodingAutomatically() {
        // Creating index
        Index index = new Index(Utilities.indexPath);

        // Subscribing to file indexing event
        index.FileIndexing.add(new EventHandler<FileIndexingEventArgs>() {
            public void invoke(Object sender, FileIndexingEventArgs args) {
                // Setting encoding for each text file during indexing
                args.setEncoding(Encodings.windows_1251);
            }
        });

        // Adding text documents encoded in windows-1251 to index
        index.addToIndex(Utilities.documentsPath);

        // Searching for word 'человеческий'
        SearchResults results = index.search("человеческий");

        // Generating HTML formatted text with highlighted found words
        // There is no need to provide the encoding again - it is saved in the index
        String htmlText = index.highlightInText(results.get_Item(0));
    }

    //Searching using morphological word forms
    public static void searchUsingMorphologicalWordForm() {
        Index index = new Index(Utilities.indexPath); // Creating index in c:\MyIndex\ folder
        index.addToIndex(Utilities.documentsPath); // Indexing folder with documents

        SearchParameters parameters = new SearchParameters();
        parameters.setUseWordFormsSearch(true); // Enabling word forms search

        SearchResults results1 = index.search("simplest", parameters); // Searching for words "simplest", "simple", and "simpler"
        SearchResults results2 = index.search("swimming", parameters); // Searching for words "swim", "swims", "swimming", "swam", "swum"
    }

    //Get searching time
    public static void getSearchingTime() {
        Index index = new Index(Utilities.indexPath, true);
        index.addToIndex(Utilities.documentsPath);
        SearchResults result = index.search("pause");

        System.out.println("Searching starts: " + result.getStartTime() + "Searching Ends: " + result.getEndTime() + "Searching Total Time: " + result.getSearchingTime());
    }

    //Search with wildcards using query
    public static void wildCardSearch() {
        // Creating index
        Index index = new Index(Utilities.indexPath);

        // Indexing
        index.addToIndex(Utilities.documentsPath);

        // Searching for 'First law of thermodynamics'
        // Note that wildcard is used instead of 'of' because it is not indexed as a stop word
        SearchResults result1 = index.search("\"First law *1 thermodynamics\"");

        // Searching for 'Frodo spoke to Pippin' and 'Frodo stripped the blankets from Pippin'
        SearchResults result2 = index.search("\"Frodo *1~~5 Pippin\"");
    }
}