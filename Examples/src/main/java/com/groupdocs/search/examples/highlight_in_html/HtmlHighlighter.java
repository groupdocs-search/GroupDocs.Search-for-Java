package com.groupdocs.search.examples.highlight_in_html;

import com.aspose.html.HTMLDocument;
import com.groupdocs.search.dictionaries.Alphabet;
import com.groupdocs.search.dictionaries.CharacterType;

class HtmlHighlighter
{
    public static String handle(
        String pageData,
        boolean isCaseSensitive,
        Alphabet alphabet,
        String[] terms,
        String[][] phrases) {
        int characterCount = 1 << 16;
        boolean[] isSeparator = new boolean[characterCount];
        for (int i = 0; i < isSeparator.length; i++) {
            char character = (char)i;
            CharacterType type = alphabet.getCharacterType(character);
            isSeparator[i] = type == CharacterType.Separator || type == CharacterType.Blended;
        }

        HTMLDocument document = new HTMLDocument(pageData, "");

        CharacterHolder characterHolder = new CharacterHolder();
        TextSource textSource = new TextSource(characterHolder, isSeparator, document);
        SuperFinder superFinder = new SuperFinder(characterHolder, isCaseSensitive, terms, phrases);

        while (true) {
            boolean success = textSource.readCharacter();
            if (!success) {
                break;
            }

            superFinder.handleCharacter();
        }
        superFinder.flush();

        superFinder.highlightFoundWords();

        String outerHtml = document.getDocumentElement().getOuterHTML();
        
        document.dispose();

        return outerHtml;
    }
}
