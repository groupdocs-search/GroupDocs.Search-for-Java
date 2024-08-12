package com.groupdocs.search.examples.advanced_usage.scaling;

import com.groupdocs.search.SearchQuery;
import com.groupdocs.search.examples.Utils;
import com.groupdocs.search.options.*;
import com.groupdocs.search.scaling.*;
import com.groupdocs.search.scaling.configuring.*;
import com.groupdocs.search.scaling.results.*;
import java.util.ArrayList;

public class TextSearchInNetwork {
    public static void run() {
        String basePath = "./output/AdvancedUsage/Scaling/TextSearchInNetwork/";
        // If an error occurs about using a busy network port, you need to change the value of the base port
        int basePort = 49148;

        Configuration configuration = ConfiguringSearchNetwork.configure(basePath, basePort);

        SearchNetworkNode[] nodes = SearchNetworkDeployment.deploy(basePath, basePort, configuration);
        SearchNetworkNode masterNode = nodes[0];

        SearchNetworkNodeEvents.subscibe(masterNode);

        IndexingDocuments.addDirectories(masterNode, Utils.DocumentsPath);

        searchAll(masterNode, "tempor", false, false);

        for (SearchNetworkNode node : nodes) {
            node.close();
        }
    }

    public static ArrayList<NetworkFoundDocument> searchAll(
        SearchNetworkNode node,
        String word,
        boolean useSynonymSearch) {
        return searchAll(node, word, useSynonymSearch, true);
    }

    public static ArrayList<NetworkFoundDocument> searchAll(
        SearchNetworkNode node,
        String word,
        boolean useSynonymSearch,
        boolean isObjectForm) {
        Searcher searcher = node.getSearcher();

        SearchOptions options = new SearchOptions();
        options.setChunkSearch(true);
        options.setUseSynonymSearch(useSynonymSearch);
        int totalOccurrences = 0;
        ArrayList<NetworkFoundDocument> documents = new ArrayList<NetworkFoundDocument>();

        NetworkSearchResult result;
        if (isObjectForm) {
            SearchQuery query = SearchQuery.createWordQuery(word);
            System.out.println();
            System.out.println("First time search in object form for: " + query);
            result = searcher.searchFirst(query, options);
        } else {
            String query = word;
            System.out.println();
            System.out.println("First time search in text form for: " + query);
            result = searcher.searchFirst(query, options);
        }

        addDocsFromResult(documents, result);
        totalOccurrences += result.getOccurrenceCount();
        traceResult(result);

        while (result.getNextChunkSearchToken() != null) {
            System.out.println();
            System.out.println("Next time search for: " + word);

            result = searcher.searchNext(result.getNextChunkSearchToken());

            addDocsFromResult(documents, result);
            totalOccurrences += result.getOccurrenceCount();
            traceResult(result);
        }

        System.out.println();
        System.out.println("Total occurrences: " + totalOccurrences);

        return documents;
    }

    private static void addDocsFromResult(ArrayList<NetworkFoundDocument> documents, NetworkSearchResult result) {
        for (int i = 0; i < result.getDocumentCount(); i++) {
            documents.add(result.getFoundDocument(i));
        }
    }

    private static void traceResult(NetworkSearchResult result) {
        System.out.println("Search in node " + result.getNodeIndex() + " gave occurrences: " + result.getOccurrenceCount());
    }
}
