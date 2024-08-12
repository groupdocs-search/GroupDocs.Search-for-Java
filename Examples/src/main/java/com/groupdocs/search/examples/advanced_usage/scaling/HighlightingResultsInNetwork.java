package com.groupdocs.search.examples.advanced_usage.scaling;

import com.groupdocs.search.common.*;
import com.groupdocs.search.examples.Utils;
import com.groupdocs.search.highlighters.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.scaling.*;
import com.groupdocs.search.scaling.configuring.*;
import com.groupdocs.search.scaling.results.*;
import java.util.ArrayList;

public class HighlightingResultsInNetwork {
    public static void run() {
        String basePath = "./output/AdvancedUsage/Scaling/HighlightingResultsInNetwork/";
        // If an error occurs about using a busy network port, you need to change the value of the base port
        int basePort = 49116;

        Configuration configuration = ConfiguringSearchNetwork.configure(basePath, basePort);

        SearchNetworkNode[] nodes = SearchNetworkDeployment.deploy(basePath, basePort, configuration);
        SearchNetworkNode masterNode = nodes[0];

        SearchNetworkNodeEvents.subscibe(masterNode);

        IndexingDocuments.addDirectories(masterNode, Utils.DocumentsPath);

        ArrayList<NetworkFoundDocument> documents = TextSearchInNetwork.searchAll(masterNode, "ipsum", false);

        highlightInDocument(masterNode, documents.get(0), 3);

        for (SearchNetworkNode node : nodes) {
            node.close();
        }
    }

    public static void highlightInDocument(
        SearchNetworkNode node,
        NetworkFoundDocument document,
        int maxFragments) {
        Searcher searcher = node.getSearcher();

        FragmentHighlighter highlighter = new FragmentHighlighter(OutputFormat.PlainText);

        HighlightOptions options = new HighlightOptions();
        options.setTermsAfter(5);
        options.setTermsBefore(5);
        options.setTermsTotal(15);
        searcher.highlight(document, highlighter, options);

        FragmentContainer[] result = highlighter.getResult();
        System.out.println();
        for (FragmentContainer container : result) {
            if (container.getCount() == 0) continue;

            String[] fragments = container.getFragments();
            System.out.println(container.getFieldName());
            int count = Math.min(fragments.length, maxFragments);
            for (int j = 0; j < count; j++) {
                System.out.println("\t" + fragments[j]);
            }
        }
    }
}
