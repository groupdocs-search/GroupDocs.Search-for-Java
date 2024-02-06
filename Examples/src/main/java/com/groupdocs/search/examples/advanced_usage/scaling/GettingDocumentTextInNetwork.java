package com.groupdocs.search.examples.advanced_usage.scaling;

import com.groupdocs.search.common.*;
import com.groupdocs.search.examples.Utils;
import com.groupdocs.search.options.*;
import com.groupdocs.search.scaling.*;
import com.groupdocs.search.scaling.configuring.*;
import com.groupdocs.search.scaling.results.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GettingDocumentTextInNetwork {
    public static void run() {
        String basePath = "./output/AdvancedUsage/Scaling/GettingDocumentText/";
        int basePort = 49100;

        Configuration configuration = ConfiguringSearchNetwork.configure(basePath, basePort);

        SearchNetworkNode[] nodes = SearchNetworkDeployment.deploy(basePath, basePort, configuration);
        SearchNetworkNode masterNode = nodes[0];

        SearchNetworkNodeEvents.subscibe(masterNode);

        IndexingDocuments.addDirectories(masterNode, Utils.DocumentsPath);

        getDocumentText(masterNode, "English.txt");

        for (SearchNetworkNode node : nodes) {
            node.close();
        }
    }

    public static void getDocumentText(SearchNetworkNode node, String containsInPath) {
        Searcher searcher = node.getSearcher();

        ArrayList<NetworkDocumentInfo> documents = new ArrayList<NetworkDocumentInfo>();
        int[] shardIndices = node.getShardIndices();
        for (int i = 0; i < shardIndices.length; i++) {
            int shardIndex = shardIndices[i];
            NetworkDocumentInfo[] infos = searcher.getIndexedDocuments(shardIndex);
            documents.addAll(Arrays.asList(infos));
            for (NetworkDocumentInfo info : infos) {
                NetworkDocumentInfo[] items = searcher.getIndexedDocumentItems(info);
                documents.addAll(Arrays.asList(items));
            }
        }

        for (int i = 0; i < documents.size(); i++) {
            NetworkDocumentInfo document = documents.get(i);
            if (document.getDocumentInfo().toString().contains(containsInPath)) {
                System.out.println();
                System.out.println(document.getDocumentInfo().toString());

                StringOutputAdapter outputAdapter = new StringOutputAdapter(OutputFormat.PlainText);
                searcher.getDocumentText(document, outputAdapter);

                System.out.println(outputAdapter.getResult());
                break;
            }
        }
    }
}
