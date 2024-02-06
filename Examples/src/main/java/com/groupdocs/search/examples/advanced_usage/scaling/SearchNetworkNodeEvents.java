package com.groupdocs.search.examples.advanced_usage.scaling;

import com.groupdocs.search.events.*;
import com.groupdocs.search.scaling.*;
import com.groupdocs.search.scaling.events.*;
import com.groupdocs.search.scaling.configuring.*;

public class SearchNetworkNodeEvents {
    public static void run() {
        String basePath = "./output/AdvancedUsage/Scaling/SearchNetworkNodeEvents/";
        int basePort = 49100;

        Configuration configuration = ConfiguringSearchNetwork.configure(basePath, basePort);

        SearchNetworkNode[] nodes = SearchNetworkDeployment.deploy(basePath, basePort, configuration);
        SearchNetworkNode masterNode = nodes[0];

        subscibe(masterNode);

        for (SearchNetworkNode node : nodes) {
            node.close();
        }
    }

    public static void subscibe(SearchNetworkNode node) {
        node.getEvents().IndexingCompleted.add(new EventHandler() {
            @Override
            public void invoke(Object s, EventArgs e) {
                System.out.println("Indexing complete");
            }
        });
        node.getEvents().DeletionCompleted.add(new EventHandler() {
            @Override
            public void invoke(Object s, EventArgs e) {
                System.out.println("Deleting complete");
            }
        });
        node.getEvents().OptimizationCompleted.add(new EventHandler() {
            @Override
            public void invoke(Object s, EventArgs e) {
                System.out.println("Optimization complete");
            }
        });
        node.getEvents().SynchronizationCompleted.add(new EventHandler() {
            @Override
            public void invoke(Object s, EventArgs e) {
                System.out.println("Synchronization complete");
            }
        });
        node.getEvents().AttributeChangesCompleted.add(new EventHandler() {
            @Override
            public void invoke(Object s, EventArgs e) {
                System.out.println("Attribute changes complete");
            }
        });
        node.getEvents().StatusChanged.add(new EventHandler<StatusChangedEventArgs>() {
            @Override
            public void invoke(Object s, StatusChangedEventArgs e) {
                System.out.println("Status changed: " + e.getOldStatus().name() + " -> " + e.getNewStatus().name());
            }
        });
        node.getEvents().DataExtracted.add(new EventHandler<DataExtractedEventArgs>() {
            @Override
            public void invoke(Object s, DataExtractedEventArgs e) {
                System.out.println("Data extracted (" + e.getExtractorIndex() + "): " + e.getDocumentKey());
            }
        });
        node.getEvents().DocumentIndexed.add(new EventHandler<DocumentIndexedEventArgs>() {
            @Override
            public void invoke(Object s, DocumentIndexedEventArgs e) {
                System.out.println("Document indexed (" + e.getShardIndex() + "): " + e.getDocumentKey());
            }
        });
        node.getEvents().DocumentDeleted.add(new EventHandler<DocumentDeletedEventArgs>() {
            @Override
            public void invoke(Object s, DocumentDeletedEventArgs e) {
                System.out.println("Document deleted (" + e.getShardIndex() + "): " + e.getDocumentKey());
            }
        });
        node.getEvents().ErrorOccurred.add(new EventHandler<ErrorOccurredEventArgs>() {
            @Override
            public void invoke(Object s, ErrorOccurredEventArgs e) {
                System.out.println("Error occurred (" + e.getNodeIndex() + ", " + e.getServiceIndex() + "): " + e.getMessage());
            }
        });
        node.getEvents().IndexingProgressChanged.add(new EventHandler<NetworkIndexingProgressEventArgs>() {
            @Override
            public void invoke(Object s, NetworkIndexingProgressEventArgs e) {
                System.out.println("Indexing progress changed (" + e.getNodeIndex() + ", " + e.getServiceIndex() + "): " + e.getProgressPercentage());
            }
        });
        node.getEvents().OptimizationProgressChanged.add(new EventHandler<NetworkOptimizationProgressEventArgs>() {
            @Override
            public void invoke(Object s, NetworkOptimizationProgressEventArgs e) {
                System.out.println("Optimization progress changed (" + e.getNodeIndex() + ", " + e.getServiceIndex() + "): " + e.getProgressPercentage());
            }
        });
    }
}
