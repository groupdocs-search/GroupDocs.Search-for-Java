package com.groupdocs.search.examples.advanced_usage.scaling;

import com.groupdocs.search.examples.Utils;
import com.groupdocs.search.options.*;
import com.groupdocs.search.scaling.*;
import com.groupdocs.search.scaling.configuring.*;

public class OptimizingShards {
    public static void run() {
        String basePath = "./output/AdvancedUsage/Scaling/OptimizingShards/";
        int basePort = 49100;

        Configuration configuration = ConfiguringSearchNetwork.configure(basePath, basePort);

        SearchNetworkNode[] nodes = SearchNetworkDeployment.deploy(basePath, basePort, configuration);
        SearchNetworkNode masterNode = nodes[0];

        SearchNetworkNodeEvents.subscibe(masterNode);

        IndexingDocuments.addDirectories(masterNode, Utils.DocumentsPath);
        IndexingDocuments.addDirectories(masterNode, Utils.DocumentsPath2);

        TextSearchInNetwork.searchAll(masterNode, "ligula", false);

        optimizeShards(masterNode);

        TextSearchInNetwork.searchAll(masterNode, "ligula", false);

        for (SearchNetworkNode node : nodes) {
            node.close();
        }
    }

    public static void optimizeShards(SearchNetworkNode node) {
        System.out.println();
        System.out.println("Optimizing shards");
        Indexer indexer = node.getIndexer();
        OptimizeOptions options = new OptimizeOptions();
        indexer.optimize(options);
    }
}
