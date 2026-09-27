package com.groupdocs.search.examples.advanced_usage.indexing;

import com.groupdocs.search.Index;
import com.groupdocs.search.examples.Utils;
import com.groupdocs.search.options.IOcrConnector;
import com.groupdocs.search.options.IndexingOptions;
import com.groupdocs.search.options.OcrContext;
import com.groupdocs.search.results.SearchResult;

public class OcrSupport {
    public static void useAsposeOcrConnector() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\OcrSupport\\UseAsposeOcrConnector";
        String documentsFolder = Utils.DocumentsPNG;
        String query = "water";

        // Creating an index
        Index index = new Index(indexFolder, true);

        // Setting the OCR indexing options
        IndexingOptions options = new IndexingOptions();
        options.getOcrIndexingOptions().setEnabledForSeparateImages(true);
        options.getOcrIndexingOptions().setEnabledForEmbeddedImages(true);
        options.getOcrIndexingOptions().setOcrConnector(new AsposeOcrConnector());

        // Indexing documents in a document folder
        index.add(documentsFolder, options);

        // Searching in the index
        SearchResult result = index.search(query);

        Utils.traceResult(query, result);
    }

    public static void useTesseractOcrConnector() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\OcrSupport\\UseTesseractOcrConnector";
        String documentsFolder = Utils.DocumentsPNG;
        String query = "water";

        // Creating an index
        Index index = new Index(indexFolder, true);

        // Setting the OCR indexing options
        IndexingOptions options = new IndexingOptions();
        options.getOcrIndexingOptions().setEnabledForSeparateImages(true);
        options.getOcrIndexingOptions().setEnabledForEmbeddedImages(true);
        options.getOcrIndexingOptions().setOcrConnector(new TesseractOcrConnector());

        // Indexing documents in a document folder
        index.add(documentsFolder, options);

        // Searching in the index
        SearchResult result = index.search(query);

        Utils.traceResult(query, result);
    }

    // Implementing the OCR connector that uses com.aspose.ocr library
    public static class AsposeOcrConnector implements IOcrConnector {
        public AsposeOcrConnector() {
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
                com.aspose.ocr.OcrInput input = new com.aspose.ocr.OcrInput(com.aspose.ocr.InputType.SingleImage);
                input.add(image);
                com.aspose.ocr.AsposeOCR asposeOcr = new com.aspose.ocr.AsposeOCR();
                com.aspose.ocr.OcrOutput output = asposeOcr.Recognize(input);
                StringBuilder result = new StringBuilder();
                for (com.aspose.ocr.RecognitionResult page : output) {
                    result.append(page.recognitionText);
                }
                return result.toString();
            } catch (java.io.IOException | com.aspose.ocr.AsposeOCRException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    // Implementing the OCR connector that uses Tesseract through the net.sourceforge.tess4j library
    // The tess4j package already contains the native Tesseract library and the English language data
    public static class TesseractOcrConnector implements IOcrConnector {
        private final net.sourceforge.tess4j.Tesseract tesseract;

        public TesseractOcrConnector() {
            // Extracting the bundled language data to a temporary folder
            java.io.File tessDataFolder = net.sourceforge.tess4j.util.LoadLibs.extractTessResources("tessdata");

            tesseract = new net.sourceforge.tess4j.Tesseract();
            tesseract.setDatapath(tessDataFolder.getAbsolutePath());
            tesseract.setLanguage("eng");
        }

        @Override
        public final String recognize(OcrContext context) {
            try {
                java.awt.image.BufferedImage image = javax.imageio.ImageIO.read(context.getImageStream());
                if (image == null) {
                    return null; // The image format is not supported by ImageIO
                }
                String recognizedText = tesseract.doOCR(image);
                return recognizedText;
            } catch (java.io.IOException | net.sourceforge.tess4j.TesseractException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
