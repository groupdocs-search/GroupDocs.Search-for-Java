package com.groupdocs.search.examples.advanced_usage.scaling;

import com.groupdocs.search.options.*;
import com.groupdocs.search.scaling.configuring.*;

public class ConfiguringSearchNetwork {
    public static void run() {
        String basePath = "./output/AdvancedUsage/Scaling/ConfiguringSearchNetwork/";
        // If an error occurs about using a busy network port, you need to change the value of the base port
        int basePort = 49100;

        Configuration configuration = configure(basePath, basePort);
    }

    /**
     * One or more nodes can be created on one server.
     * Only one node accepts the configuration,
     * which is automatically propagated to all nodes when the network is started.
     */
    public static Configuration configure(String basePath, int basePort) {
        String address = "127.0.0.1";
        Configuration configuration = new Configurator()
            .setIndexSettings()
                .setUseStopWords(false)
                .setUseCharacterReplacements(false)
                .setTextStorageSettings(true, Compression.High)
                .setIndexType(IndexType.NormalIndex)
                .setSearchThreads(NumberOfThreads.Default)
                .completeIndexSettings()
            .addNode(0)
                .setTcpEndpoint(address, basePort)
                .addLogSink()
                .addIndexer(basePath + "Indexer0")
                .addSearcher(basePath + "Searcher0")
                .completeNode()
            .addNode(1)
                .setTcpEndpoint(address, basePort + 1)
                .addShard(basePath + "Shard1")
                .addExtractor(basePath + "Extractor1")
                .completeNode()
            .addNode(2)
                .setTcpEndpoint(address, basePort + 2)
                .addShard(basePath + "Shard2")
                .addExtractor(basePath + "Extractor2")
                .completeNode()
            .addNode(3)
                .setTcpEndpoint(address, basePort + 3)
                .addShard(basePath + "Shard3")
                .addExtractor(basePath + "Extractor3")
                .completeNode()
            .completeConfiguration();
        return configuration;
    }
}
