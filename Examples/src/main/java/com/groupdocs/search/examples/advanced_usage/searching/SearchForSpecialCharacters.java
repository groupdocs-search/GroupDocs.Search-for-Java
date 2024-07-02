package com.groupdocs.search.examples.advanced_usage.searching;

import com.groupdocs.search.*;
import com.groupdocs.search.dictionaries.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class SearchForSpecialCharacters {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\Searching\\SearchForSpecialCharacters";
        String documentsFolder = Utils.DocumentsPath;

        // Creating an index in the specified folder
        Index index = new Index(indexFolder);

        // Setting character types
        index.getDictionaries().getAlphabet().setRange(new char[] { '&' }, CharacterType.Letter);
        index.getDictionaries().getAlphabet().setRange(new char[] { '-' }, CharacterType.Separator);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Defining a search query
        String word = "rock&roll-music";

        // Replacing separators with the space characters
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char character = word.charAt(i);
            CharacterType characterType = index.getDictionaries().getAlphabet().getCharacterType(character);
            if (characterType == CharacterType.Separator) {
                result.append(' ');
            } else {
                result.append(character);
            }
        }

        // Escaping special characters
        String specialCharacters = "():\"&|!^~*?\\";
        for (int i = result.length() - 1; i >= 0; i--) {
            char c = result.charAt(i);
            if (specialCharacters.indexOf(c) != -1) {
                result.insert(i, '\\');
            }
        }

        String query = result.toString();
        if (query.contains(" ")) {
            query = "\"" + query + "\"";
        }

        // Searching
        SearchResult searchResult = index.search(query);

        Utils.traceResult(query, searchResult);
    }
}
