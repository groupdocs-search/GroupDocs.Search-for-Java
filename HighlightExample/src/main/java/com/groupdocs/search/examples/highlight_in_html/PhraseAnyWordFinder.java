package com.groupdocs.search.examples.highlight_in_html;

import com.aspose.html.dom.Text;
import java.util.List;

class PhraseAnyWordFinder implements IFinder {
    private final ISuperFinder superFinder;
    private final CharacterHolder characterHolder;
    private final String[] phrase;
    private final int wordIndex;
    private final List<FoundWord> foundWords;

    private int characterIndex;

    private Text textNode;
    private int textNodeCharacterIndex;

    public PhraseAnyWordFinder(
        ISuperFinder superFinder,
        String[] phrase,
        int wordIndex,
        List<FoundWord> foundWords) {
        String word = phrase[wordIndex];
        if (!PhraseFirstWordFinder.AnyWordWildcard.equals(word)) {
            throw new IllegalArgumentException("The current word of the phrase must be the wildcard.");
        }

        this.superFinder = superFinder;
        characterHolder = superFinder.getCharacterHolder();
        this.phrase = phrase;
        this.wordIndex = wordIndex;
        this.foundWords = foundWords;
    }

    @Override
    public final void handleCharacter() {
        boolean isSeparator = characterHolder.IsSeparator;

        if (characterIndex == 0) {
            if (!isSeparator) {
                textNode = characterHolder.TextNode;
                textNodeCharacterIndex = characterHolder.TextNodeCharacterIndex;
                characterIndex++;
            }
        } else {
            if (isSeparator || characterHolder.NewNode) {
                handleWordFound();
            } else {
                characterIndex++;
            }
        }
    }

    @Override
    public void flush()
    {
        superFinder.removeFoundWords(foundWords);
    }

    private void handleWordFound() {
        superFinder.remove(this);

        FoundWord foundWord = new FoundWord(textNode, textNodeCharacterIndex, characterIndex, false);
        superFinder.addFoundWord(foundWord);
        foundWords.add(foundWord);

        int nextWordIndex = wordIndex + 1;
        if (nextWordIndex >= phrase.length) {
            throw new IllegalStateException("The wildcard cannot be at the end of a phrase.");
        }
        else
        {
            String nextWord = phrase[nextWordIndex];
            IFinder finder;
            if (PhraseFirstWordFinder.AnyWordWildcard.equals(nextWord))
            {
                finder = new PhraseAnyWordFinder(superFinder, phrase, nextWordIndex, foundWords);
            }
            else
            {
                finder = new PhraseNextWordFinder(superFinder, phrase, nextWordIndex, foundWords);
            }
            superFinder.add(finder);
        }
    }
}
