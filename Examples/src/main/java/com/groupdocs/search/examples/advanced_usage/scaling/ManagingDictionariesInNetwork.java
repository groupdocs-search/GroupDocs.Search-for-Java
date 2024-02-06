package com.groupdocs.search.examples.advanced_usage.scaling;

import com.groupdocs.search.dictionaries.*;
import com.groupdocs.search.examples.Utils;
import com.groupdocs.search.scaling.*;
import com.groupdocs.search.scaling.configuring.*;

public class ManagingDictionariesInNetwork {
    public static void run() {
        String basePath = "./output/AdvancedUsage/Scaling/ManagingDictionaries/";
        int basePort = 49100;

        Configuration configuration = ConfiguringSearchNetwork.configure(basePath, basePort);

        SearchNetworkNode[] nodes = SearchNetworkDeployment.deploy(basePath, basePort, configuration);
        SearchNetworkNode masterNode = nodes[0];

        SearchNetworkNodeEvents.subscibe(masterNode);

        addSynonyms(masterNode, new String[] { "efficitur", "tristique", "venenatis" }, true);

        IndexingDocuments.addDirectories(masterNode, Utils.DocumentsPath);

        TextSearchInNetwork.searchAll(masterNode, "tristique", false);
        TextSearchInNetwork.searchAll(masterNode, "tristique", true);

        for (SearchNetworkNode node : nodes) {
            node.close();
        }
    }

    public static void addSynonyms(SearchNetworkNode node, String[] group, boolean clearBeforeAdding) {
        System.out.println();
        System.out.println("Adding synonyms");

        Indexer indexer = node.getIndexer();

        int[] indices = node.getShardIndices();
        SynonymDictionary dictionary = indexer.getSynonymDictionary(indices[0]);

        if (clearBeforeAdding) {
            dictionary.clear();
        }
        dictionary.addRange(new String[][] { group });

        indexer.setDictionary(dictionary);
    }
}
