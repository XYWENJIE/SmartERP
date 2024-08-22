package com.benjamin.smarterp.pdfbox.styledxmlparser.node.jsoup;

import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Node;

import com.benjamin.smarterp.pdfbox.styledxmlparser.node.IDocumentTypeNode;

public class JsoupDocumentTypeNode extends JsoupNode implements IDocumentTypeNode {

	public JsoupDocumentTypeNode(DocumentType node) {
		super(node);
	}

}
