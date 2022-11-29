package com.groupdocs.search.examples.highlight_in_html;

import com.aspose.html.HTMLDocument;
import com.aspose.html.dom.Element;
import com.aspose.html.dom.Node;
import com.aspose.html.dom.Text;
import java.util.ArrayList;
import java.util.List;

class TextSource {
    private final CharacterHolder characterHolder;
    private final boolean[] isSeparator;
    private final HTMLDocument document;
    private final List<Text> textNodes = new ArrayList<Text>();
    private int nodeIndex;
    private int charIndex = -1;

    public TextSource(
        CharacterHolder characterHolder,
        boolean[] isSeparator,
        HTMLDocument document) {
        this.characterHolder = characterHolder;
        this.isSeparator = isSeparator;
        this.document = document;

        init();
    }

    public final boolean readCharacter() {
        if (nodeIndex >= textNodes.size()) {
            return false;
        }

        int oldNodeIndex = nodeIndex;
        charIndex++;
        Text currentNode = textNodes.get(nodeIndex);
        String currentNodeText = currentNode.getTextContent();
        if (charIndex >= currentNodeText.length()) {
            charIndex = 0;
            nodeIndex++;
            if (nodeIndex >= textNodes.size()) {
                return false;
            } else {
                currentNode = textNodes.get(nodeIndex);
                currentNodeText = currentNode.getTextContent();
            }
        }

        char character = currentNodeText.charAt(charIndex);
        characterHolder.TextNode = currentNode;
        characterHolder.NewNode = oldNodeIndex != nodeIndex;
        characterHolder.TextNodeCharacterIndex = charIndex;
        characterHolder.Character = character;
        characterHolder.UpperCaseCharacter = Character.toUpperCase(character);
        characterHolder.IsSeparator = isSeparator[character];
        return true;
    }

    private void init() {
        for (Element child : document.getChildren()) {
            find(child);
        }
    }

    private void find(Node node) {
        if ("STYLE".equalsIgnoreCase(node.getNodeName()) ||
            "TITLE".equalsIgnoreCase(node.getNodeName()) ||
            "HEAD".equalsIgnoreCase(node.getNodeName()) ||
            "SCRIPT".equalsIgnoreCase(node.getNodeName())) {
            return;
        }

        if (node.getNodeType() == 3) {
            String text = node.getTextContent();
            if (text != null && text.length() > 0) {
                textNodes.add((Text)node);
            }
        } else {
            for (Node child : node.getChildNodes()) {
                find(child);
            }
        }
    }
}
