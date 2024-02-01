package com.groupdocs.search.examples.advanced_usage.managing_dictionaries;

import com.groupdocs.search.*;
import com.groupdocs.search.dictionaries.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class CharacterReplacements {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\ManagingDictionaries\\CharacterReplacements\\Index";
        String documentsFolder = Utils.DocumentsPath;

        // Enabling character replacements in the index settings
        IndexSettings settings = new IndexSettings();
        settings.setUseCharacterReplacements(true);

        // Creating an index from in specified folder
        Index index = new Index(indexFolder, settings);

        if (index.getDictionaries().getCharacterReplacements().getCount() > 0) {
            // Deleting all character replacements from the dictionary
            index.getDictionaries().getCharacterReplacements().clear();
        }

        // Adding character replacement
        index.getDictionaries().getCharacterReplacements().addRange(new CharacterReplacementPair[] { new CharacterReplacementPair('-', '~') });

        if (index.getDictionaries().getCharacterReplacements().contains('-')) {
            char replacement = index.getDictionaries().getCharacterReplacements().getReplacement('-');
            System.out.println("The replacement for hyphen is " + replacement);

            // Deleting the hyphen character replacement from the dictionary
            index.getDictionaries().getCharacterReplacements().removeRange(new char[] { '-' });
        }

        // Creating new character replacements
        CharacterReplacementPair[] characterReplacements = new CharacterReplacementPair[Character.MAX_VALUE + 1];
        for (int i = 0; i < characterReplacements.length; i++) {
            char character = (char)i;
            char replacement = Character.toLowerCase(character);
            characterReplacements[i] = new CharacterReplacementPair(character, replacement);
        }
        // Adding character replacements to the dictionary
        index.getDictionaries().getCharacterReplacements().addRange(characterReplacements);

        // Export character replacements to a file
        String fileName = ".\\output\\AdvancedUsage\\ManagingDictionaries\\CharacterReplacements\\CharacterReplacements.dat";
        index.getDictionaries().getCharacterReplacements().exportDictionary(fileName);

        // Import character replacements from a file
        index.getDictionaries().getCharacterReplacements().importDictionary(fileName);

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Search in the index
        String query = "Elliot";
        SearchOptions options = new SearchOptions();
        options.setUseCaseSensitiveSearch(true);
        SearchResult result = index.search(query, options);

        Utils.traceResult(query, result);
    }
}
