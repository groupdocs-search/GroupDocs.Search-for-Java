package com.groupdocs.search.examples.advanced_usage.scaling;

import com.groupdocs.search.common.*;
import com.groupdocs.search.examples.Utils;
import com.groupdocs.search.options.*;
import com.groupdocs.search.scaling.*;
import com.groupdocs.search.scaling.configuring.*;

public class WorkingWithAttributes {
    public static void run() {
        String basePath = "./output/AdvancedUsage/Scaling/WorkingWithAttributes/";
        int basePort = 49100;

        Configuration configuration = ConfiguringSearchNetwork.configure(basePath, basePort);

        SearchNetworkNode[] nodes = SearchNetworkDeployment.deploy(basePath, basePort, configuration);
        SearchNetworkNode masterNode = nodes[0];

        SearchNetworkNodeEvents.subscibe(masterNode);

        IndexingDocuments.addDirectories(masterNode, Utils.DocumentsPath);

        addAttribute(masterNode, "Lorem ipsum.pdf", "First");

        addAttribute(masterNode, "English.docx", "Second");

        GettingDocumentsInNetwork.getIndexedDocuments(masterNode);

        for (SearchNetworkNode node : nodes) {
            node.close();
        }
    }

    public static void addAttribute(SearchNetworkNode node, String documentKey, String attribute) {
        System.out.println();
        System.out.println("Adding attribute: " + attribute);

        Indexer indexer = node.getIndexer();

        AttributeChangeBatch batch = new AttributeChangeBatch();
        batch.add(documentKey, attribute);
        ChangeAttributesOptions options = new ChangeAttributesOptions();
        indexer.changeAttributes(batch, options);
    }
}
