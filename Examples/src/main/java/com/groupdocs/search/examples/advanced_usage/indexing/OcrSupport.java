package com.groupdocs.search.examples.advanced_usage.indexing;

import com.groupdocs.search.Index;
import com.groupdocs.search.examples.Utils;
import com.groupdocs.search.options.IOcrConnector;
import com.groupdocs.search.options.IndexingOptions;
import com.groupdocs.search.options.OcrContext;
import com.groupdocs.search.results.SearchResult;

public class OcrSupport {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\OcrSupport";
        String documentsFolder = Utils.DocumentsPNG;
        String query = "water";

        // Creating an index
        Index index = new Index(indexFolder);

        // Setting the OCR indexing options
        IndexingOptions options = new IndexingOptions();
        options.getOcrIndexingOptions().setEnabledForSeparateImages(true);
        options.getOcrIndexingOptions().setEnabledForEmbeddedImages(true);
        options.getOcrIndexingOptions().setOcrConnector(new OcrConnector());

        // Indexing documents in a document folder
        index.add(documentsFolder, options);

        // Searching in the index
        SearchResult result = index.search(query);

        Utils.traceResult(query, result);
    }

    // Implementing the OCR connector that uses com.aspose.ocr library
    public static class OcrConnector implements IOcrConnector {
        public OcrConnector() {
        }

        @Override
        public final String recognize(OcrContext context) {
            if (null == context.getImageLocation()) {
                throw new RuntimeException("The image type is not supported: " + context.getImageLocation());
            } else {
                switch (context.getImageLocation()) {
                case Separate:
                case Embedded:
                case ContainerItem:
                    return recognizePrivate(context);
                default:
                    throw new RuntimeException("The image type is not supported: " + context.getImageLocation());
                }
            }
        }

        private String recognizePrivate(OcrContext context) {
            try {
                java.awt.image.BufferedImage image = javax.imageio.ImageIO.read(context.getImageStream());
                com.aspose.ocr.AsposeOCR asposeOcr = new com.aspose.ocr.AsposeOCR();
                String result = asposeOcr.RecognizePage(image);
                return result;
            } catch (java.io.IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
