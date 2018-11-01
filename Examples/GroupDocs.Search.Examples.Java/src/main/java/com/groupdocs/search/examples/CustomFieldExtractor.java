package com.groupdocs.search.examples.java;

import com.groupdocs.search.FieldInfo;
import com.groupdocs.search.IFieldExtractor;

public class CustomFieldExtractor implements IFieldExtractor {
    public String[] getExtensions() {
        // Supported extensions
        return new String[]{".ext1", ".ext2"};
    }

    public FieldInfo[] getFields(String fileName) {
        // Resulting array contains 4 fields for an example. Actual number of fields depends on document type.
        FieldInfo[] result = new FieldInfo[4];
        result[0] = new FieldInfo("Content", "Hardcoded document content");
        result[1] = new FieldInfo("DocumentType", "MyDocumentType");
        result[2] = new FieldInfo("Author", "Hardcoded author");
        result[3] = new FieldInfo("CreationDate", "21.05.2004");
        return result;
    }
}