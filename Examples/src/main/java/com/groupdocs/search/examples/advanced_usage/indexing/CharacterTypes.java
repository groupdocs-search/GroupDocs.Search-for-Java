package com.groupdocs.search.examples.advanced_usage.indexing;

import com.groupdocs.search.*;
import com.groupdocs.search.dictionaries.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class CharacterTypes {
    public static void regularCharacters() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\CharacterTypes\\RegularCharacters";
        String documentFolder = Utils.DocumentsPath;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Configuring the alphabet
        // Setting the separator type for all characters in the alphabet
        index.getDictionaries().getAlphabet().clear();
        // Creating a list of letter characters
        StringBuilder sb = new StringBuilder();
        for (char i = (char)0x0030; i <= 0x0039; i++) {
            sb.append(i); // Digits
        }
        for (char i = (char)0x0041; i <= 0x005A; i++) {
            sb.append(i); // Latin capital letters
        }
        sb.append((char)0x005F); // Low line
        for (char i = (char)0x0061; i <= 0x007A; i++) {
            sb.append(i); // Latin small letters
        }
        // Setting the type of characters in the alphabet
        char[] characters = new char[sb.length()];
        sb.getChars(0, sb.length() - 1, characters, 0);
        index.getDictionaries().getAlphabet().setRange(characters, CharacterType.Letter);

        // Indexing documents from the specified folder
        index.add(documentFolder);

        // Searching in the index
        String query = "travelling";
        SearchResult result = index.search(query);

        Utils.traceResult(query, result);
    }

    public static void blendedCharacters() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\CharacterTypes\\BlendedCharacters";
        String documentFolder = Utils.DocumentsPath;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Setting hyphen character type to blended
        index.getDictionaries().getAlphabet().setRange(new char[] { '-' }, CharacterType.Blended);

        // Indexing documents from the specified folder
        index.add(documentFolder);

        // Searching in the index
        String query1 = "Elliot-Murray-Kynynmound";
        SearchResult result1 = index.search(query1);
        String query2 = "Elliot";
        SearchResult result2 = index.search(query2);
        String query3 = "Murray";
        SearchResult result3 = index.search(query3);
        String query4 = "Kynynmound";
        SearchResult result4 = index.search(query4);

        Utils.traceResult(query1, result1);
        Utils.traceResult(query2, result2);
        Utils.traceResult(query3, result3);
        Utils.traceResult(query4, result4);
    }
}
