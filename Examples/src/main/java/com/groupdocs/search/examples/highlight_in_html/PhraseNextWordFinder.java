package com.groupdocs.search.examples.highlight_in_html;

import com.aspose.html.dom.Text;
import java.util.List;
import java.util.Locale;

class PhraseNextWordFinder implements IFinder {
    private final String word;
    private final ISuperFinder superFinder;
    private final CharacterHolder characterHolder;
    private final String[] phrase;
    private final int wordIndex;
    private final List<FoundWord> foundWords;

    private final boolean isCaseSensitive;
    private final String originalWord;
    private final String upperCaseWord;
    private int characterIndex;

    private boolean passNonSeparator;
    private Text textNode;
    private int textNodeCharacterIndex;

    private boolean previousIsSeparator = true;

    public PhraseNextWordFinder(
        ISuperFinder superFinder,
        String[] phrase,
        int wordIndex,
        List<FoundWord> foundWords) {
        word = phrase[wordIndex];
        if (PhraseFirstWordFinder.AnyWordWildcard.equals(word)) {
            throw new IllegalArgumentException("The current word of the phrase cannot be the wildcard.");
        }

        this.superFinder = superFinder;
        characterHolder = superFinder.getCharacterHolder();
        this.phrase = phrase;
        this.wordIndex = wordIndex;
        this.foundWords = foundWords;

        isCaseSensitive = superFinder.isCaseSensitive();
        originalWord = word;
        upperCaseWord = word.toUpperCase(Locale.ROOT);
    }

    @Override
    public final void handleCharacter() {
        char character = characterHolder.Character;
        char upperCaseCharacter = characterHolder.UpperCaseCharacter;
        boolean isSeparator = characterHolder.IsSeparator;

        passNonSeparator |= !isSeparator;

        if (characterIndex < originalWord.length()) {
            boolean isMatch = isCaseSensitive ?
                character == originalWord.charAt(characterIndex) :
                upperCaseCharacter == upperCaseWord.charAt(characterIndex);
            if (isMatch) {
                if (characterIndex == 0) {
                    if (previousIsSeparator || characterHolder.NewNode) {
                        characterIndex++;
                        textNode = characterHolder.TextNode;
                        textNodeCharacterIndex = characterHolder.TextNodeCharacterIndex;
                    }
                } else { // characterIndex > 0
                    characterIndex++;
                }
            } else {
                if (passNonSeparator) {
                    handleWordNotFound();
                } else {
                    characterIndex = 0;
                }
            }
        } else {
            if (isSeparator || characterHolder.NewNode) {
                handleWordFound();
            }
            characterIndex = 0;
        }

        previousIsSeparator = isSeparator;
    }

    @Override
    public void flush()
    {
        superFinder.removeFoundWords(foundWords);
    }

    private void handleWordNotFound() {
        superFinder.remove(this);

        superFinder.removeFoundWords(foundWords);
    }

    private void handleWordFound() {
        superFinder.remove(this);

        FoundWord foundWord = new FoundWord(textNode, textNodeCharacterIndex, word.length(), false);
        superFinder.addFoundWord(foundWord);
        foundWords.add(foundWord);

        int nextWordIndex = wordIndex + 1;
        if (nextWordIndex >= phrase.length) {
            // Do nothing
        } else {
            String nextWord = phrase[nextWordIndex];
            IFinder finder;
            if (PhraseFirstWordFinder.AnyWordWildcard.equals(nextWord)) {
                finder = new PhraseAnyWordFinder(superFinder, phrase, nextWordIndex, foundWords);
            } else {
                finder = new PhraseNextWordFinder(superFinder, phrase, nextWordIndex, foundWords);
            }
            superFinder.add(finder);
        }
    }
}
