package com.groupdocs.search.examples.advanced_usage.scaling;

import com.groupdocs.search.examples.Utils;
import com.groupdocs.search.options.*;
import com.groupdocs.search.scaling.*;
import com.groupdocs.search.scaling.configuring.*;

public class DeletingDocuments {
    public static void run() {
        String basePath = "./output/AdvancedUsage/Scaling/DeletingDocuments/";
        int basePort = 49100;

        Configuration configuration = ConfiguringSearchNetwork.configure(basePath, basePort);

        SearchNetworkNode[] nodes = SearchNetworkDeployment.deploy(basePath, basePort, configuration);
        SearchNetworkNode masterNode = nodes[0];

        SearchNetworkNodeEvents.subscibe(masterNode);

        IndexingDocuments.addDirectories(masterNode, Utils.DocumentsPath);

        TextSearchInNetwork.searchAll(masterNode, "nulla", false);

        deleteDocuments(masterNode, "Lorem ipsum.pdf", "Lorem ipsum.docx");

        TextSearchInNetwork.searchAll(masterNode, "nulla", false);

        for (SearchNetworkNode node : nodes) {
            node.close();
        }
    }

    public static void deleteDocuments(SearchNetworkNode node, String... filePaths) {
        System.out.println();
        System.out.println("Deleting documents");

        String[] fileNames = new String[filePaths.length];
        for (int i = 0; i < filePaths.length; i++) {
            String filePath = filePaths[i];
            fileNames[i] = filePath;
        }

        Indexer indexer = node.getIndexer();

        DeleteOptions options = new DeleteOptions();
        indexer.delete(fileNames, options);
    }
}
