package com.groupdocs.search.examples.java;


import com.groupdocs.search.License;
import com.groupdocs.search.Metered;


public class Utilities {
    public static final String indexPath = System.getProperty("user.dir") + "\\Data\\Documents Indexes\\";
    public static final String indexPath2 = System.getProperty("user.dir") + "Data\\Documents Indexes2\\";
    public static final String documentsPath = System.getProperty("user.dir") + "\\Data\\Documents\\";
    public static final String documentsPath2 = System.getProperty("user.dir") + "Data\\Documents2\\";
    public static final String documentsPath3 = System.getProperty("user.dir") + "Data\\Documents3\\";
    public static final String synonymFilePath = System.getProperty("user.dir") + "Data\\Dictionaries\\synonyms.txt";
    public static final String synonymNewFilePath = System.getProperty("user.dir") + "Data\\Dictionaries\\Newsynonyms.txt";
    public static String licensePath = "D:/GroupDocs.Total.lic";
    public static final String pathToPstFile = "D:\\MyOutlookDataFile.pst";
    public static final String spellingDictionaryFilePath = System.getProperty("user.dir") + "Data\\Dictionaries\\MySpellingDictionary.txt";
    public static final String exportedSpellingDictionaryFilePath = System.getProperty("user.dir") + "Data\\Dictionaries\\MyExportedSpellingDictionary.txt";
    public static final String oldIndexFolderPath = System.getProperty("user.dir") + "\\Data\\Old index";
    public static String publicKey = "[Your Dynabic.Metered public key]";
    public static String privateKey = "[Your Dynabic.Metered private key]";
    public static final String documentTextPath = System.getProperty("user.dir") + "\\Data\\Output\\DocumentText.html";

    // Apply license
    public static void applyLicense() {
        // initialize License
        License lic = new License();
        // Set license
        lic.setLicense(licensePath);
    }

    // Apply Dynabic Metered license
    public static void useDynabicMeteredAccount() throws Exception {
        // initialize Metered API and set-up credentials
        new Metered().setMeteredKey(publicKey, privateKey);

    }
}