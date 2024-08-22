package com.benjamin.smarterp.pdfbox.styledxmlparser.node.jsoup;

import org.jsoup.nodes.Attribute;

import com.benjamin.smarterp.pdfbox.styledxmlparser.node.IAttribute;

public class JsoupAttribute implements IAttribute {
	
	private Attribute attribute;

	public JsoupAttribute(Attribute attribute) {
		super();
		this.attribute = attribute;
	}

	@Override
	public String getKey() {
		return attribute.getKey();
	}

	@Override
	public String getValue() {
		return attribute.getValue();
	}

}
