package com.groupdocs.search.examples.highlight_in_html;

import com.aspose.html.dom.Element;
import com.aspose.html.dom.Text;

class FoundWord {
    private static final String HighlightedClassName = "highlighted-term";
    private static final String CountedClassName = "counted-term";
    private static final String CountedAndHighlighted = CountedClassName + " " + HighlightedClassName;

    private final Text textNode;
    private final int termStart;
    private final int termLength;
    private final boolean isCounted;

    public FoundWord(
        Text textNode,
        int termStart,
        int termLength,
        boolean isCounted) {
        this.textNode = textNode;
        this.termStart = termStart;
        this.termLength = termLength;
        this.isCounted = isCounted;
    }

    public final void highlight() {
        Text node = textNode;
        int startIndex = termStart;
        int length = termLength;
        int textContentLength = node.getTextContent().length();
        if (startIndex >= textContentLength) {
            return;
        }

        int newLength = textContentLength - startIndex;
        if (length >= newLength) {
            length = newLength;
        }

        Text termNode = node.splitText(startIndex);
        Text lastTextNode = termNode.splitText(length);

        Element span = termNode.getOwnerDocument().createElement("span");
        span.setClassName(isCounted ? CountedAndHighlighted : HighlightedClassName);

        termNode.getParentNode().replaceChild(span, termNode);
        span.appendChild(termNode);
    }
}
