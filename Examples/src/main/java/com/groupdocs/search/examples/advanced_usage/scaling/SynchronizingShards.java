package com.groupdocs.search.examples.advanced_usage.scaling;

import com.groupdocs.search.examples.Utils;
import com.groupdocs.search.options.*;
import com.groupdocs.search.scaling.*;
import com.groupdocs.search.scaling.configuring.*;

public class SynchronizingShards {
    public static void run() {
        String basePath = "./output/AdvancedUsage/Scaling/SynchronizingShards/";
        int basePort = 49100;

        Configuration configuration = ConfiguringSearchNetwork.configure(basePath, basePort);

        SearchNetworkNode[] nodes = SearchNetworkDeployment.deploy(basePath, basePort, configuration);
        SearchNetworkNode masterNode = nodes[0];

        SearchNetworkNodeEvents.subscibe(masterNode);

        IndexingDocuments.addDirectories(masterNode, Utils.DocumentsPath);

        synchronizeShards(masterNode);

        for (SearchNetworkNode node : nodes) {
            node.close();
        }
    }

    public static void synchronizeShards(SearchNetworkNode node) {
        System.out.println();
        System.out.println("Synchronizing shards");
        Indexer indexer = node.getIndexer();
        SynchronizeOptions options = new SynchronizeOptions();
        indexer.synchronize(options);
    }
}
