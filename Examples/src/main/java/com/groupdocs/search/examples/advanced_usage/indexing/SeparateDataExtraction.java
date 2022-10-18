package com.groupdocs.search.examples.advanced_usage.indexing;

import com.groupdocs.search.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class SeparateDataExtraction {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\SeparateDataExtraction";
        String documentPath = Utils.DocumentsPath + "Lorem ipsum.pdf";

        // Extracting data from a document
        Extractor extractor = new Extractor();
        Document document = Document.createFromFile(documentPath);
        ExtractionOptions extractionOptions = new ExtractionOptions();
        extractionOptions.setUseRawTextExtraction(false);
        ExtractedData extractedData = extractor.extract(document, extractionOptions);

        // Serializing the data
        byte[] array = extractedData.serialize();

        // Deserializing the data
        ExtractedData deserializedData = ExtractedData.deserialize(array);

        // Creating an index
        com.groupdocs.search.Index index = new com.groupdocs.search.Index(indexFolder);

        // Indexing the data
        ExtractedData[] data = new ExtractedData[] {
            deserializedData
        };
        index.add(data, new IndexingOptions());

        // Searching in the index
        String query = "ipsum";
        SearchResult result = index.search(query);

        Utils.traceResult(query, result);
    }
}
