package com.groupdocs.search.examples.basic_usage;

import com.groupdocs.search.results.FileType;

public class GetSupportedFileFormats {
    public static void run() {
        Iterable<FileType> supportedFileTypes = FileType.getSupportedFileTypes();
        for (FileType fileType : supportedFileTypes) {
            System.out.println(fileType.getExtension() + " - " + fileType.getDescription());
        }
    }
}
