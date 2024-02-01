package com.groupdocs.search.examples.advanced_usage.managing_dictionaries;

import com.groupdocs.search.dictionaries.*;

import java.util.ArrayList;

public class SimpleWordFormsProvider implements IWordFormsProvider {
    @Override
    public final String[] getWordForms(String word)     {
        ArrayList<String> result = new ArrayList<String>();

        // Assume that the input word is in the plural, then we add the singular
        if (word.length() > 2 && word.toLowerCase().endsWith("es")) {
            result.add(word.substring(0, word.length() - 2));
        }
        if (word.length() > 1 && word.toLowerCase().endsWith("s")) {
            result.add(word.substring(0, word.length() - 1));
        }

        // Then assume that the input word is in the singular, we add the plural
        if (word.length() > 1 && word.toLowerCase().endsWith("y")) {
            result.add(word.substring(0, word.length() - 1).concat("is"));
        }
        result.add(word.concat("s"));
        result.add(word.concat("es"));
        // All rules are implemented in the EnglishWordFormsProvider class

        return result.toArray(new String[0]);
    }
}
