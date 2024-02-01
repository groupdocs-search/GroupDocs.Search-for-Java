package com.groupdocs.search.examples.advanced_usage.indexing;

import com.groupdocs.search.*;
import com.groupdocs.search.dictionaries.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class CharacterReplacementDuringIndexing {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\Indexing\\CharacterReplacementDuringIndexing";
        String documentFolder = Utils.DocumentsPath;

        // Enabling character replacements in the index settings
        IndexSettings settings = new IndexSettings();
        settings.setUseCharacterReplacements(true);

        // Creating an index in the specified folder
        Index index = new Index(indexFolder, settings);

        // Configuring character replacements
        // Deleting all existing character replacements from the dictionary
        index.getDictionaries().getCharacterReplacements().clear();
        // Creating new character replacements
        CharacterReplacementPair[] characterReplacements = new CharacterReplacementPair[Character.MAX_VALUE + 1];
        for (int i = 0; i < characterReplacements.length; i++) {
            char character = (char)i;
            char replacement = Character.toLowerCase(character);
            characterReplacements[i] = new CharacterReplacementPair(character, replacement);
        }
        // Adding character replacements to the dictionary
        index.getDictionaries().getCharacterReplacements().addRange(characterReplacements);

        // Indexing documents from the specified folder
        index.add(documentFolder);

        // Searching in the index
        // Case-sensitive search is no longer possible for this index, since all characters are lowercase
        // By default, case-insensitive search is performed
        String query = "Promotion";
        SearchOptions options = new SearchOptions();
        options.setUseCaseSensitiveSearch(true);
        SearchResult result = index.search(query, options);

        Utils.traceResult(query, result);
    }
}
