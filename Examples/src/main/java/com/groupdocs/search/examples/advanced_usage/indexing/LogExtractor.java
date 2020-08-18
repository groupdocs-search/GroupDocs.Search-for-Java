package com.groupdocs.search.examples.advanced_usage.indexing;

import com.groupdocs.search.common.DocumentField;
import com.groupdocs.search.common.IFieldExtractor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LogExtractor implements IFieldExtractor {
    private final String[] extensions = new String[] { ".log" };

    @Override
    public String[] getExtensions() {
        return extensions;
    }

    @Override
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

    @Override
    public DocumentField[] getFields(InputStream in) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
