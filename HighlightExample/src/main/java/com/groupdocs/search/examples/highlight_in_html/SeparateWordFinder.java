package com.groupdocs.search.examples.highlight_in_html;

class SeparateWordFinder extends WordFinder implements IFinder {
    public SeparateWordFinder(
        ISuperFinder superFinder,
        String word) {
    	super(superFinder, word);
    }

    @Override
    protected void handleWordFound() {
        FoundWord word = new FoundWord(getTextNode(), getTextNodeCharacterIndex(), getWord().length(), true);
        getSuperFinder().addFoundWord(word);
    }
}
