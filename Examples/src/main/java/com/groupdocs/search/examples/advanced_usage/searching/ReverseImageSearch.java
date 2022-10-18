package com.groupdocs.search.examples.advanced_usage.searching;

import com.groupdocs.search.*;
import com.groupdocs.search.common.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class ReverseImageSearch {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\ReverseImageSearch";
        String documentsFolder = Utils.ImagesPath;

        // Creating an index
        Index index = new com.groupdocs.search.Index(indexFolder);

        // Setting the image indexing options
        IndexingOptions indexingOptions = new IndexingOptions();
        indexingOptions.getImageIndexingOptions().setEnabledForContainerItemImages(true);
        indexingOptions.getImageIndexingOptions().setEnabledForEmbeddedImages(true);
        indexingOptions.getImageIndexingOptions().setEnabledForSeparateImages(true);

        // Indexing documents in a document folder
        index.add(documentsFolder, indexingOptions);

        // Setting the image search options
        ImageSearchOptions imageSearchOptions = new ImageSearchOptions();
        imageSearchOptions.setHashDifferences(10);
        imageSearchOptions.setMaxResultCount(10000);
        imageSearchOptions.setSearchDocumentFilter(SearchDocumentFilter.createFileExtension(".zip", ".png", ".jpg"));

        // Creating a reference image for search
        SearchImage searchImage = SearchImage.create(Utils.ImagesPath + "ic_arrow_downward_black_18dp.png");

        // Searching in the index
        ImageSearchResult result = index.search(searchImage, imageSearchOptions);

        System.out.println("Images found: " + result.getImageCount());
        for (int i = 0; i < result.getImageCount(); i++) {
            FoundImageFrame image = result.getFoundImage(i);
            System.out.println(image.getDocumentInfo().toString());
        }
    }
}
