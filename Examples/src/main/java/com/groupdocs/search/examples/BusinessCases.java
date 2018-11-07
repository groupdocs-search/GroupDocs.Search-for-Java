package com.groupdocs.search.examples;

import com.groupdocs.search.DocumentResultInfo;
import com.groupdocs.search.Index;
import com.groupdocs.search.IndexRepository;
import com.groupdocs.search.SearchResults;

public class BusinessCases {
    /**
     * Creates a new books index, add documents in it and performs a search operation in it
     */
    public static void searchBooks() {
        // Creating index folder
        Index index = new Index(Utilities.BOOKS_INDEX);


        // Indexing documents in folder
        index.addToIndex(Utilities.BOOKS);


        // When indexing is finished user can search in it
        SearchResults searchResults = index.search("Gregor Samsa");


        // List of found files
        for (DocumentResultInfo result : searchResults) {
            System.out.println(result.getFileName());
        }
    }
    /**
     * Performs a search operation in existing books index
     */
    public static void searchBooksInExistingIndex() {
        // Loading index
        Index index = new Index(Utilities.BOOKS_INDEX);

        SearchResults searchResults = index.search("Gregor Samsa");

        // List of found files
        for (DocumentResultInfo result : searchResults) {
            System.out.println(result.getFileName());
        }
    }
    /**
     * Adds documents in books index
     */
    public static void addDocumentsInBooksIndex() {
        // Loading index
        Index index = new Index(Utilities.BOOKS_INDEX);

        // Adding to index folder
        index.addToIndex(Utilities.DOCUMENTS_PATH);
    }
    /**
     * Updates books index
     */
    public static void updateBooksIndex() {
        // Loading index
        Index index = new Index(Utilities.BOOKS_INDEX);

        // Updating index
        index.update();

        SearchResults searchResults = index.search("Gregor Samsa");

        // List of found files
        for (DocumentResultInfo result : searchResults) {
            System.out.println(result.getFileName());
        }
    }
    /**
     * Performs a search operation in several indexes using IndexRepository
     */
    public static void searchInSeveralIndexes() {
        // Creating Index Repository
        IndexRepository repository = new IndexRepository();

        // Adding already created indexes fo repository
        repository.addToRepository(Utilities.BOOKS_INDEX);
        repository.addToRepository(Utilities.INDEX_PATH);
        repository.addToRepository(Utilities.INDEX_PATH_2);

        // Searching in all indexes in repository
        SearchResults searchResults = repository.search("Gregor Samsa");

        // List of found files
        for (DocumentResultInfo result : searchResults) {
            System.out.println(result.getFileName());
        }
    }

}
