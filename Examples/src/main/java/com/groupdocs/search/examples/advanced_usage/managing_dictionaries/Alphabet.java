package com.groupdocs.search.examples.advanced_usage.managing_dictionaries;

import com.groupdocs.search.*;
import com.groupdocs.search.common.*;
import com.groupdocs.search.dictionaries.*;
import com.groupdocs.search.events.*;
import com.groupdocs.search.highlighters.*;
import com.groupdocs.search.options.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class Alphabet {
    public static void run() {
        String indexFolder = ".\\output\\AdvancedUsage\\ManagingDictionaries\\Alphabet\\Index";
        String documentsFolder = Utils.DocumentsPath;

        // Creating or opening an index from the specified folder
        Index index = new Index(indexFolder);

        // Export the alphabet to a file
        String fileName = ".\\output\\AdvancedUsage\\ManagingDictionaries\\Alphabet\\Alphabet.dat";
        index.getDictionaries().getAlphabet().exportDictionary(fileName);

        if (index.getDictionaries().getAlphabet().getCount() > 0) {
            // Setting a type of all characters to Separator
            index.getDictionaries().getAlphabet().clear();
        }

        // Import the alphabet from a file
        index.getDictionaries().getAlphabet().importDictionary(fileName);

        if (index.getDictionaries().getAlphabet().getCharacterType('-') != CharacterType.Blended) {
            // Setting a type of hyphen character to Blended
            index.getDictionaries().getAlphabet().setRange(new char[] { '-' }, CharacterType.Blended);
        }

        // Indexing documents from the specified folder
        index.add(documentsFolder);

        // Search in the index
        String query = "Elliot-Murray-Kynynmound";
        SearchResult result = index.search(query);

        Utils.traceResult(query, result);
    }
}
