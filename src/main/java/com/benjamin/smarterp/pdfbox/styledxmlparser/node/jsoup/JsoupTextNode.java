package com.benjamin.smarterp.pdfbox.styledxmlparser.node.jsoup;

import org.jsoup.nodes.TextNode;

import com.benjamin.smarterp.pdfbox.styledxmlparser.node.ITextNode;

public class JsoupTextNode extends JsoupNode implements ITextNode{
	
	private TextNode textNode;
	
	public JsoupTextNode(TextNode textNode) {
		super(textNode);
		this.textNode = textNode;
	}
	
	@Override
	public String wholeText() {
		return textNode.getWholeText();
	}

}
