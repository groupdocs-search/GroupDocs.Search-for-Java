package com.groupdocs.search.examples.advanced_usage.indexing;

import com.groupdocs.search.common.DocumentField;
import com.groupdocs.search.common.IFieldExtractor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LogExtractor implements IFieldExtractor {
    private final String[] extensions = new String[] { ".log" };

    public String[] getExtensions() {
        return extensions;
    }

    public DocumentField[] getFields(String filePath) {
        File file = new File(filePath);
        DocumentField[] fields = new DocumentField[] {
                new DocumentField("FileName", file.getAbsolutePath()),
                new DocumentField("Content", extractContent(filePath)),
        };
        return fields;
    }

    private String extractContent(String filePath) {
        try {
            String contents = new String(Files.readAllBytes(Paths.get(filePath)));
            return contents;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
