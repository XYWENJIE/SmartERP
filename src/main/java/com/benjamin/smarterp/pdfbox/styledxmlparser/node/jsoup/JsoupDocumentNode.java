package com.benjamin.smarterp.pdfbox.styledxmlparser.node.jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.benjamin.smarterp.pdfbox.styledxmlparser.node.IDocumentNode;

public class JsoupDocumentNode extends JsoupElementNode implements IDocumentNode {
	
	private Document document;

	public JsoupDocumentNode(Document document) {
		super(document);
		this.document = document;
	}
	
	public Document getDocument() {
		return document;
	}

}
