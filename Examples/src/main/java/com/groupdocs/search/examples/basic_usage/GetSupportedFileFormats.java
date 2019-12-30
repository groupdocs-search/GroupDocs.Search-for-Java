package com.groupdocs.search.examples.basic_usage;

import com.groupdocs.search.results.FileType;

import java.util.Iterator;

public class GetSupportedFileFormats {
    public static void run() {
        Iterable<FileType> supportedFileTypes = FileType.getSupportedFileTypes();
        Iterator iterator = supportedFileTypes.iterator();
        while (iterator.hasNext()) {
            FileType fileType = (FileType)iterator.next();
            System.out.println(fileType.getExtension() + " - " + fileType.getDescription());
        }
    }
}
