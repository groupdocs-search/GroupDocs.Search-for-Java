package com.groupdocs.search.examples.advanced_usage.scaling;

import com.groupdocs.search.Document;
import com.groupdocs.search.examples.Utils;
import com.groupdocs.search.options.*;
import com.groupdocs.search.scaling.*;
import com.groupdocs.search.scaling.configuring.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import org.apache.commons.io.FilenameUtils;

public class IndexingDocuments {
    public static void run() {
        String basePath = "./output/AdvancedUsage/Scaling/IndexingDocuments/";
        // If an error occurs about using a busy network port, you need to change the value of the base port
        int basePort = 49124;

        Configuration configuration = ConfiguringSearchNetwork.configure(basePath, basePort);

        SearchNetworkNode[] nodes = SearchNetworkDeployment.deploy(basePath, basePort, configuration);
        SearchNetworkNode masterNode = nodes[0];

        SearchNetworkNodeEvents.subscibe(masterNode);

        addDirectories(masterNode, Utils.DocumentsPath);

        for (SearchNetworkNode node : nodes) {
            node.close();
        }
    }

    public static void addDirectories(SearchNetworkNode node, String... directoryPaths) {
        ArrayList<String> files = new ArrayList<>();
        for (String directoryPath : directoryPaths) {
            final File folder = new File(directoryPath);
            listFiles(folder, files);
        }
        addFiles(node, files.toArray(new String[0]));
    }

    public static void addFiles(SearchNetworkNode node, String... filePaths) {
        System.out.println();
        System.out.println("Adding documents to the search network");

        try {
            InputStream[] streams = new InputStream[filePaths.length];
            Document[] documents = new Document[filePaths.length];
            String[] passwords = new String[filePaths.length];
            for (int i = 0; i < filePaths.length; i++) {
                String filePath = filePaths[i];
                String fileName = FilenameUtils.getName(filePath);
                String extension = "." + FilenameUtils.getExtension(filePath);
                InputStream stream = new FileInputStream(filePath);
                streams[i] = stream;
                Document document = Document.createFromStream(
                    fileName,
                    new Date(),
                    extension,
                    stream);
                documents[i] = document;
            }

            Indexer indexer = node.getIndexer();
            IndexingOptions options = new IndexingOptions();
            options.setUseRawTextExtraction(false);
            options.getImageIndexingOptions().setEnabledForSeparateImages(true);
            options.getImageIndexingOptions().setEnabledForEmbeddedImages(true);
            options.getImageIndexingOptions().setEnabledForContainerItemImages(true);
            options.getOcrIndexingOptions().setEnabledForSeparateImages(true);
            options.getOcrIndexingOptions().setEnabledForEmbeddedImages(true);
            options.getOcrIndexingOptions().setEnabledForContainerItemImages(true);
            indexer.add(documents, passwords, options);

            for (InputStream stream : streams) {
                stream.close();
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
    }

    private static void listFiles(final File folder, ArrayList<String> list) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFiles(fileEntry, list);
            } else {
                list.add(fileEntry.getPath());
            }
        }
    }
}
