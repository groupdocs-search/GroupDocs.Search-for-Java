package com.groupdocs.search.examples.advanced_usage.managing_dictionaries;

import com.groupdocs.search.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;
import java.io.File;

public class DocumentPasswords {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\ManagingDictionaries\\DocumentPasswords\\Index";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index from in specified folder
        Index index = new Index(indexFolder);

        if (index.getDictionaries().getDocumentPasswords().getCount() > 0) {
            // Removing all passwords from the dictionary
            index.getDictionaries().getDocumentPasswords().clear();
        }

        String path = new File(Utils.PasswordProtectedDocumentsPath + "English.docx").getAbsolutePath();
        index.getDictionaries().getDocumentPasswords().add(path, "123456");

        if (index.getDictionaries().getDocumentPasswords().contains(path)) {
            // Getting a password for a document
            String password = index.getDictionaries().getDocumentPasswords().getPassword(path);
            System.out.println(path);
            System.out.println("\tPassword: " + password);

            // Deleting the password from the dictionary
            index.getDictionaries().getDocumentPasswords().remove(path);
        }

        // Adding document passwords to the dictionary
        index.getDictionaries().getDocumentPasswords().add(Utils.PasswordProtectedDocumentsPath + "English.docx", "123456");
        index.getDictionaries().getDocumentPasswords().add(Utils.PasswordProtectedDocumentsPath + "Lorem ipsum.docx", "123456");

        // Indexing documents from the specified folder
        // Passwords will be automatically retrieved from the dictionary when necessary
        index.add(documentsFolder);

        // Searching in the index
        String query = "ipsum OR increasing";
        SearchResult result = index.search(query);

        Utils.traceResult(query, result);
    }
}
