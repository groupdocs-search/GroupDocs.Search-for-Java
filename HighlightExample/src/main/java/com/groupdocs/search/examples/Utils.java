package com.groupdocs.search.examples;

import com.groupdocs.search.*;
import com.groupdocs.search.common.*;
import com.groupdocs.search.dictionaries.*;
import com.groupdocs.search.events.*;
import com.groupdocs.search.highlighters.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.Date;

public class Utils {
    public static final String LicensePath = "C:\\Licenses\\GroupDocs.Search.lic";

    public static final String DocumentsPath = System.getProperty("user.dir") + "\\Resources\\SampleFiles\\Documents\\";
    public static final String DocumentsPath2 = System.getProperty("user.dir") + "\\Resources\\SampleFiles\\Documents2\\";
    public static final String DocumentsPath3 = System.getProperty("user.dir") + "\\Resources\\SampleFiles\\Documents3\\";
    public static final String DocumentsPath4 = System.getProperty("user.dir") + "\\Resources\\SampleFiles\\Documents4\\";
    public static final String ImagesPath = System.getProperty("user.dir") + "\\Resources\\SampleFiles\\Images\\";
    public static final String DocumentsPNG = System.getProperty("user.dir") + "\\Resources\\SampleFiles\\DocumentsPNG\\";
    public static final String PasswordProtectedDocumentsPath = System.getProperty("user.dir") + "\\Resources\\SampleFiles\\PasswordProtectedDocuments\\";
    public static final String LogPath = System.getProperty("user.dir") + "\\Resources\\SampleFiles\\Log\\";
    public static final String DocumentsUtf32Path = System.getProperty("user.dir") + "\\Resources\\SampleFiles\\DocumentsUtf32\\";
    public static final String ArchivesPath = System.getProperty("user.dir") + "\\Resources\\SampleFiles\\Archives\\";

    public static final String OldIndexPath = System.getProperty("user.dir") + "\\Resources\\SampleFiles\\Index_19_4\\";

    public static void traceResult(String query, SearchResult result) {
        System.out.println();
        System.out.println("Query: " + query);
        System.out.println("Documents: " + result.getDocumentCount());
        System.out.println("Occurrences: " + result.getOccurrenceCount());
    }

    public static void traceIndexedDocuments(Index index) {
        System.out.println();
        System.out.println("Indexed documents:");
        DocumentInfo[] documents = index.getIndexedDocuments();
        for (int i = 0; i < documents.length; i++) {
            System.out.println("\t" + documents[i].getFilePath());
        }
    }

    public static void highlight(Index index, FoundDocument document, String filePath) {
        if (document == null) return;

        FileOutputAdapter outputAdapter = new FileOutputAdapter(OutputFormat.Html, filePath);
        DocumentHighlighter highlighter = new DocumentHighlighter(outputAdapter);
        index.highlight(document, highlighter);
    }

    public static void cleanDirectory(String path) {
        try {
            if (Files.isDirectory(Paths.get(path))) {
                delete(new File(path));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        try {
            Files.createDirectories(Paths.get(path));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void copyFiles(String sourcePath, String destinationPath) {
        File sourceFolder = new File(sourcePath);
        File destinationFolder = new File(destinationPath);
        try {
            copyFolder(sourceFolder, destinationFolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Date createDate(int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        Date date = calendar.getTime();
        return date;
    }

    private static void copyFolder(File sourceFolder, File destinationFolder) throws IOException {
        // Check if sourceFolder is a directory or file
        // If sourceFolder is file; then copy the file directly to new location
        if (sourceFolder.isDirectory()) {
            // Verify if destinationFolder is already present; If not then create it
            if (!destinationFolder.exists()) {
                destinationFolder.mkdir();
                System.out.println("Directory created: " + destinationFolder);
            }

            // Get all files from source directory
            String files[] = sourceFolder.list();

            // Iterate over all files and copy them to destinationFolder one by one
            for (String file : files) {
                File srcFile = new File(sourceFolder, file);
                File destFile = new File(destinationFolder, file);

                // Recursive function call
                copyFolder(srcFile, destFile);
            }
        } else {
            // Copy the file content from one place to another
            Files.copy(sourceFolder.toPath(), destinationFolder.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied: " + destinationFolder);
        }
    }

    private static void delete(File file) throws IOException {
        if (file.isDirectory()) {
            for (File c : file.listFiles()) {
                delete(c);
            }
        }
        if (!file.delete()) {
            throw new FileNotFoundException("Failed to delete file: " + file);
        }
    }
}
