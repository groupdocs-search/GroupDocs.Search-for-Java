package com.groupdocs.search.examples.advanced_usage.scaling;

import com.groupdocs.search.examples.Utils;
import com.groupdocs.search.scaling.*;
import com.groupdocs.search.scaling.configuring.*;
import com.groupdocs.search.scaling.results.*;

public class GettingDocumentsInNetwork {
    public static void run() {
        String basePath = "./output/AdvancedUsage/Scaling/GettingDocumentsInNetwork/";
        int basePort = 49100;

        Configuration configuration = ConfiguringSearchNetwork.configure(basePath, basePort);

        SearchNetworkNode[] nodes = SearchNetworkDeployment.deploy(basePath, basePort, configuration);
        SearchNetworkNode masterNode = nodes[0];

        SearchNetworkNodeEvents.subscibe(masterNode);

        IndexingDocuments.addDirectories(masterNode, Utils.DocumentsPath);

        getIndexedDocuments(masterNode);

        for (SearchNetworkNode node : nodes) {
            node.close();
        }
    }

    public static void getIndexedDocuments(SearchNetworkNode node) {
        Searcher searcher = node.getSearcher();
        Indexer indexer = node.getIndexer();

        int[] shardIndices = node.getShardIndices();
        System.out.println();
        for (int i = 0; i < shardIndices.length; i++) {
            int shardIndex = shardIndices[i];
            NetworkDocumentInfo[] infos = searcher.getIndexedDocuments(shardIndex);
            for (NetworkDocumentInfo info : infos) {
                int nodeIndex = node.getNodeIndex(info.getShardIndex());
                System.out.println(nodeIndex + ": " + info.getShardIndex() + ": " + info.getDocumentInfo().getFilePath());
                String[] attributes = indexer.getAttributes(info.getDocumentInfo().getFilePath());
                for (String attribute : attributes) {
                    System.out.println("\t\t" + attribute);
                }

                NetworkDocumentInfo[] items = searcher.getIndexedDocumentItems(info);
                for (NetworkDocumentInfo item : items) {
                    System.out.println("\t" + nodeIndex + ": " + item.getShardIndex() + ": " + item.getDocumentInfo().toString());
                }
            }
        }
    }
}
