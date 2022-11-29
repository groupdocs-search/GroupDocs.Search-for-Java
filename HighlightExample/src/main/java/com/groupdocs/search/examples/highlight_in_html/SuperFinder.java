package com.groupdocs.search.examples.highlight_in_html;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class SuperFinder implements ISuperFinder {
    private final CharacterHolder characterHolder;
    private final boolean isCaseSensitive;
    private final List<IFinder> finders = new ArrayList<IFinder>();
    private final List<IFinder> toRemove = new ArrayList<IFinder>();

    private final LinkedList<FoundWord> foundWords = new LinkedList<FoundWord>();

    public SuperFinder(
        CharacterHolder characterHolder,
        boolean isCaseSensitive,
        String[] terms,
        String[][] phrases) {
        this.characterHolder = characterHolder;
        this.isCaseSensitive = isCaseSensitive;

        if (terms != null) {
            for (String term : terms) {
                SeparateWordFinder finder = new SeparateWordFinder(this, term);
                finders.add(finder);
            }
        }
        if (phrases != null) {
            for (String[] phrase : phrases) {
                PhraseFirstWordFinder finder = new PhraseFirstWordFinder(this, phrase);
                finders.add(finder);
            }
        }
    }

    @Override
    public CharacterHolder getCharacterHolder() {
        return characterHolder;
    }

    @Override
    public boolean isCaseSensitive() {
        return isCaseSensitive;
    }

    public final void handleCharacter() {
        if (!toRemove.isEmpty()) {
            for (int i = 0; i < toRemove.size(); i++) {
                finders.remove(toRemove.get(i));
            }
            toRemove.clear();
        }

        for (int i = 0; i < finders.size(); i++) {
            IFinder finder = finders.get(i);
            finder.handleCharacter();
        }
    }

    public void flush() {
        for (int i = 0; i < finders.size(); i++) {
            IFinder finder = finders.get(i);
            finder.flush();
        }
    }

    @Override
    public final void add(IFinder finder) {
        finders.add(finder);
    }

    @Override
    public final void remove(IFinder finder) {
        toRemove.add(finder);
    }

    @Override
    public final void addFoundWord(FoundWord foundWord) {
        foundWords.addFirst(foundWord);
    }

    @Override
    public final void removeFoundWords(List<FoundWord> words) {
        for (int i = 0; i < words.size(); i++) {
            FoundWord word = words.get(i);
            foundWords.remove(word);
        }
    }

    public final void highlightFoundWords() {
        while (!foundWords.isEmpty()) {
            FoundWord foundWord = foundWords.getFirst();
            foundWords.removeFirst();
            foundWord.highlight();
        }
    }
}
