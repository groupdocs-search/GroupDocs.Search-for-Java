package com.groupdocs.search.examples.highlight_in_html;

import com.aspose.html.dom.Text;
import java.util.Locale;

abstract class WordFinder implements IFinder {
    private final ISuperFinder superFinder;
    private final CharacterHolder characterHolder;
    private final boolean isCaseSensitive;
    private final String originalWord;
    private final String upperCaseWord;
    private int characterIndex;

    private Text textNode;
    private int textNodeCharacterIndex;

    private boolean previousIsSeparator = true;

    public WordFinder(
        ISuperFinder superFinder,
        String word) {
        if (word.length() == 0) {
            throw new IllegalArgumentException("The word cannot be empty.");
        }

        this.superFinder = superFinder;
        characterHolder = superFinder.getCharacterHolder();
        isCaseSensitive = superFinder.isCaseSensitive();
        originalWord = word;
        upperCaseWord = word.toUpperCase(Locale.ROOT);
    }

    public ISuperFinder getSuperFinder() {
        return superFinder;
    }

    public String getWord() {
        return originalWord;
    }

    public Text getTextNode() {
        return textNode;
    }

    public int getTextNodeCharacterIndex() {
        return textNodeCharacterIndex;
    }

    @Override
    public final void handleCharacter() {
        char character = characterHolder.Character;
        char upperCaseCharacter = characterHolder.UpperCaseCharacter;
        boolean isSeparator = characterHolder.IsSeparator;

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
                characterIndex = 0;
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
    public void flush() {
    }

    protected abstract void handleWordFound();
}
