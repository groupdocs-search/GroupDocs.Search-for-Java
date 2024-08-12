package com.groupdocs.search.examples.advanced_usage.scaling;

import com.groupdocs.search.events.*;
import com.groupdocs.search.examples.advanced_usage.indexing.ConsoleLogger;
import com.groupdocs.search.scaling.*;
import com.groupdocs.search.scaling.configuring.*;

public class SearchNetworkDeployment {
    public static void run() {
        String basePath = "./output/AdvancedUsage/Scaling/SearchNetworkDeployment/";
        // If an error occurs about using a busy network port, you need to change the value of the base port
        int basePort = 49136;

        Configuration configuration = ConfiguringSearchNetwork.configure(basePath, basePort);

        SearchNetworkNode[] nodes = deploy(basePath, basePort, configuration);

        for (SearchNetworkNode node : nodes) {
            node.close();
        }
    }

    /*
     * One or more nodes can be created on one server.
     * Only one node accepts the configuration,
     * which is automatically propagated to all nodes when the network is started.
     */
    public static SearchNetworkNode[] deploy(String basePath, int basePort, Configuration configuration) {
        int sendTimeout = 3000;
        int receiveTimeout = 3000;

        // Each of the following 3 nodes can run on a separate server or in conjunction with others
        SearchNetworkNode node1 = new SearchNetworkNode(
            1,
            basePath + "Node1",
            new TcpSettings(basePort + 1, sendTimeout, receiveTimeout));
        node1.start();
        SearchNetworkNode node2 = new SearchNetworkNode(
            2,
            basePath + "Node2",
            new TcpSettings(basePort + 2, sendTimeout, receiveTimeout));
        node2.start();
        SearchNetworkNode node3 = new SearchNetworkNode(
            3,
            basePath + "Node3",
            new TcpSettings(basePort + 3, sendTimeout, receiveTimeout));
        node3.start();

        SearchNetworkNode node0 = new SearchNetworkNode(
            0,
            basePath + "Node0",
            new TcpSettings(basePort, sendTimeout, receiveTimeout),
            new ConsoleLogger(),
            configuration);

        node0.getEvents().ConfigurationCompleted.add(new EventHandler() {
            @Override
            public void invoke(Object s, EventArgs e) {
                System.out.println("Configuration complete");
            }
        });

        System.out.println();
        System.out.println("Configuring the search network");
        node0.configureAllNodes();

        System.out.println("Launching the search network");
        node0.start();

        SearchNetworkNode[] nodes = new SearchNetworkNode[] {
            node0, node1, node2, node3,
        };
        return nodes;
    }
}
