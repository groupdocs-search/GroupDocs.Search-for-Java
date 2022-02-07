package com.groupdocs.search.examples.highlight_in_html;

import java.util.List;

interface ISuperFinder
{
    public CharacterHolder getCharacterHolder();

    public boolean isCaseSensitive();

    public void add(IFinder finder);

    public void remove(IFinder finder);

    public void addFoundWord(FoundWord foundWord);

    public void removeFoundWords(List<FoundWord> foundWords);
}
