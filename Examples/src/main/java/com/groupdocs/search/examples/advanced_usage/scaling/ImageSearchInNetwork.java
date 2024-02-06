package com.groupdocs.search.examples.advanced_usage.scaling;

import com.groupdocs.search.common.*;
import com.groupdocs.search.examples.Utils;
import com.groupdocs.search.options.*;
import com.groupdocs.search.scaling.*;
import com.groupdocs.search.scaling.configuring.*;
import com.groupdocs.search.scaling.results.*;

public class ImageSearchInNetwork {
    public static void run() {
        String basePath = "./output/AdvancedUsage/Scaling/ImageSearchInNetwork/";
        int basePort = 49100;

        Configuration configuration = ConfiguringSearchNetwork.configure(basePath, basePort);

        SearchNetworkNode[] nodes = SearchNetworkDeployment.deploy(basePath, basePort, configuration);
        SearchNetworkNode masterNode = nodes[0];

        SearchNetworkNodeEvents.subscibe(masterNode);

        IndexingDocuments.addDirectories(masterNode, Utils.ImagesPath);

        SearchImage searchImage = SearchImage.create(Utils.ImagesPath + "ic_arrow_back_black_18dp.png");
        imageSearch(masterNode, searchImage, 8);

        for (SearchNetworkNode node : nodes) {
            node.close();
        }
    }

    public static void imageSearch(
        SearchNetworkNode node,
        SearchImage searchImage,
        int hashDifferences) {
        System.out.println();
        System.out.println("First image search");

        Searcher searcher = node.getSearcher();
        ImageSearchOptions options = new ImageSearchOptions();
        options.setHashDifferences(hashDifferences);
        int total = 0;

        NetworkImageSearchResult result = searcher.searchFirst(searchImage, options);
        System.out.println("Images found (shard " + result.getShardIndex() + "): " + result.getImageCount());
        total += result.getImageCount();

        while (result.getNetworkImageSearchToken() != null) {
            System.out.println();
            System.out.println("Next image search");

            result = searcher.searchNext(result.getNetworkImageSearchToken());
            System.out.println("Images found (shard " + result.getShardIndex() + "): " + result.getImageCount());
            total += result.getImageCount();
        }

        System.out.println();
        System.out.println("Total images found (diffs = " + hashDifferences + "): " + total);
    }
}
