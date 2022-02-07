package com.groupdocs.search.examples.highlight_in_html;

import java.util.ArrayList;
import java.util.List;

class PhraseFirstWordFinder extends WordFinder implements IFinder {
    public static final String AnyWordWildcard = "*";

    private final String[] phrase;

    public PhraseFirstWordFinder(
        ISuperFinder superFinder,
        String[] phrase) {
    	super(superFinder, phrase[0]);
	
        if (phrase.length < 2) {
            throw new IllegalArgumentException("The phrase must be at least 2 characters long.");
        }

        this.phrase = phrase;
    }

    @Override
    protected void handleWordFound() {
        final int nextWordIndex = 1;
        String nextWord = phrase[nextWordIndex];

        FoundWord foundWord = new FoundWord(getTextNode(), getTextNodeCharacterIndex(), getWord().length(), true);
        getSuperFinder().addFoundWord(foundWord);
        List<FoundWord> foundWords = new ArrayList<FoundWord>();
        foundWords.add(foundWord);

        IFinder finder;
        if (AnyWordWildcard.equals(nextWord)) {
            finder = new PhraseAnyWordFinder(getSuperFinder(), phrase, nextWordIndex, foundWords);
        } else {
            finder = new PhraseNextWordFinder(getSuperFinder(), phrase, nextWordIndex, foundWords);
        }
        getSuperFinder().add(finder);
    }
}
