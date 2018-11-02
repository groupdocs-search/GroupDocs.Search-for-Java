package com.groupdocs.search.examples;


import com.groupdocs.search.License;
import com.groupdocs.search.Metered;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class Utilities {
    public static final String INDEX_PATH = (System.getProperty("user.dir") + "\\Data\\Documents Indexes\\");
    public static final String INDEX_PATH_2 = System.getProperty("user.dir") + "\\Data\\Documents Indexes2\\";
    public static final String MERGE_INDEX_PATH_1 = System.getProperty("user.dir") + "\\Data\\Index Merging\\Index1\\";
    public static final String MERGE_INDEX_PATH_2 = System.getProperty("user.dir") + "\\Data\\Index Merging\\Index2\\";
    public static final String MAIN_MERGED_INDEX_PATH = System.getProperty("user.dir") + "\\Data\\Index Merging\\Main Merged Indexes\\";
    public static final String DOCUMENTS_PATH = System.getProperty("user.dir") + "\\Data\\Documents\\";
    public static final String PASSWORD_PROTECTED_DOCUMENT = System.getProperty("user.dir") + "\\Data\\Documents\\Password Protected Document.docx";
    public static final String DOCUMENTS_PATH_2 = System.getProperty("user.dir") + "\\Data\\Documents2\\";
    public static final String DOCUMENTS_PATH_3 = System.getProperty("user.dir") + "\\Data\\Documents3\\";
    public static final String SYNONYM_FILE_PATH = System.getProperty("user.dir") + "\\Data\\Dictionaries\\synonyms.txt";
    public static final String SYNONYM_NEW_FILE_PATH = System.getProperty("user.dir") + "\\Data\\Dictionaries\\Newsynonyms.txt";
    public static final String PATH_TO_PST_FILE = System.getProperty("user.dir") + "\\Data\\MyOutlookDataFile.pst";
    public static final String SPELLING_DICTIONARY_FILE_PATH = System.getProperty("user.dir") + "\\Data\\Dictionaries\\MySpellingDictionary.txt";
    public static final String EXPORTED_SPELLING_DICTIONARY_FILE_PATH = System.getProperty("user.dir") + "\\Data\\Dictionaries\\MyExportedSpellingDictionary.txt";
    public static final String OLD_INDEX_FOLDER_PATH = System.getProperty("user.dir") + "\\Data\\Old index";
    public static final String STOP_WORDS_FILE_PATH = System.getProperty("user.dir") + "Data\\Dictionaries\\MyStopWords.txt";
    public static final String EXPORTED_STOP_WORDS_FILE_PATH = System.getProperty("user.dir") + "\\Data\\Dictionaries\\MyExportedStopWords.txt";
    public static final String ALIAS_FILE_PATH = System.getProperty("user.dir") + "\\Data\\Dictionaries\\MyAliases.txt";
    public static final String EXPORTED_ALIAS_FILE_PATH = System.getProperty("user.dir") + "\\Data\\Dictionaries\\MyExportedAliases.txt";
    public static final String EXPORTED_HOMOPHONES_FILE_PATH = System.getProperty("user.dir") + "\\Data\\Dictionaries\\MyExportedHomophones.txt";
    public static final String HOMOPHONES_FILE_PATH = System.getProperty("user.dir") + "\\Data\\Dictionaries\\MyHomophones.txt";
    public static String publicKey = "[Your Dynabic.Metered public key]";
    public static String privateKey = "[Your Dynabic.Metered private key]";
    public static final String DOCUMENT_TEXT_PATH = System.getProperty("user.dir") + "\\Data\\Output\\DocumentText.html";
    public static final String ALPHABET_FILE_PATH = System.getProperty("user.dir") + "\\Data\\Dictionaries\\MyAlphabet.txt";
    public static final String EXPORTED_ALPHABET_TXT = System.getProperty("user.dir") + "\\Data\\Dictionaries\\MyExportedAlphabet.txt";
    public static String LICENSE_PATH = "D:/GroupDocs.Total.Java.lic";

    /**
     * This method applies license
     *
     * @throws IOException
     */
    public static void applyLicense() throws IOException {
        try (InputStream stream = new FileInputStream(LICENSE_PATH)) {
            License lic = new License();
            lic.setLicense(stream);
        }
        catch(Exception ex)
        {
        	System.out.println(ex.getMessage());
        }
    }

    /**
     * This method applies Dynabic Metered license
     *
     * @throws Exception
     */
    public static void useDynabicMeteredAccount() throws Exception {
        // initialize Metered API and set-up credentials
        new Metered().setMeteredKey(publicKey, privateKey);
    }
}