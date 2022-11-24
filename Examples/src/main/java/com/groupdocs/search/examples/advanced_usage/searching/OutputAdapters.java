package com.groupdocs.search.examples.advanced_usage.searching;

import com.groupdocs.search.*;
import com.groupdocs.search.common.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

import java.io.ByteArrayOutputStream;

public class OutputAdapters {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\OutputAdapters\\Index";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index settings instance
        IndexSettings settings = new IndexSettings();
        settings.setTextStorageSettings(new TextStorageSettings(Compression.High)); // Enabling the storage of extracted text in the index

        // Creating an index in the specified folder
        Index index = new Index(indexFolder, settings);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Getting list of indexed documents
        DocumentInfo[] documents = index.getIndexedDocuments();

        // Getting a document text
        if (documents.length > 0) {
            DocumentInfo document = documents[0];

            // Output to a file
            FileOutputAdapter fileOutputAdapter = new FileOutputAdapter(OutputFormat.Html, ".\\output\\AdvancedUsage\\Searching\\OutputAdapters\\Text.html");
            index.getDocumentText(document, fileOutputAdapter);

            // Output to a stream
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            StreamOutputAdapter streamOutputAdapter = new StreamOutputAdapter(OutputFormat.Html, stream);
            index.getDocumentText(document, streamOutputAdapter);

            // Output to a string
            StringOutputAdapter stringOutputAdapter = new StringOutputAdapter(OutputFormat.Html);
            index.getDocumentText(document, stringOutputAdapter);
            String result = stringOutputAdapter.getResult();
            //System.out.println(result);

            // Output to a structure
            StructureOutputAdapter structureOutputAdapter = new StructureOutputAdapter(OutputFormat.PlainText);
            index.getDocumentText(document, structureOutputAdapter);
            DocumentField[] fields = structureOutputAdapter.getResult();
            System.out.println(document.toString());
            for (DocumentField field : fields) {
                System.out.println("\t" + field.getName());
            }
        }
    }
}
