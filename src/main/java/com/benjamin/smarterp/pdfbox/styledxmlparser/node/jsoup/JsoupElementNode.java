package com.benjamin.smarterp.pdfbox.styledxmlparser.node.jsoup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import com.benjamin.smarterp.pdfbox.styledxmlparser.CommonAttributeConstants;
import com.benjamin.smarterp.pdfbox.styledxmlparser.node.IAttributes;
import com.benjamin.smarterp.pdfbox.styledxmlparser.node.IElementNode;
import com.benjamin.smarterp.pdfbox.styledxmlparser.node.INode;

public class JsoupElementNode extends JsoupNode implements IElementNode {
	
	private Element element;
	
	private IAttributes attributes;
	
	private Map<String, String> elementResolvedStyles;
	
	private List<Map<String,String>> customDefaultStyles;
	
	private String lang = null;

	public JsoupElementNode(Element element) {
		super(element);
		this.element = element;
		this.attributes = new JsoupAttributes(element.attributes());
		this.lang = getAttribute(CommonAttributeConstants.LANG);
	}

	@Override
	public String name() {
		return element.nodeName();
	}
	
	public IAttributes getAttributes() {
		return attributes;
	}
	
	@Override
	public String getAttribute(String key) {
		return attributes.getAttribute(key);
	}
	
	@Override
	public void setStyles(Map<String, String> elementResolvedStyles) {
		this.elementResolvedStyles = elementResolvedStyles;
	}
	
	@Override
	public Map<String, String> getStyles() {
		return this.elementResolvedStyles;
	}

	@Override
	public List<Map<String, String>> getAdditionalHtmlStyles() {
		return customDefaultStyles;
	}

	@Override
	public void addAdditionalHtmlStyles(Map<String, String> styles) {
		if(customDefaultStyles == null) {
			customDefaultStyles = new ArrayList<Map<String,String>>();
		}
		customDefaultStyles.add(styles);
	}

	@Override
	public String getLang() {
		if(lang != null) {
			return lang;
		}else {
			INode parent = parentNode;
			lang = parent instanceof IElementNode ? ((IElementNode)parent).getLang() : null;
			if(lang == null) {
				lang = "";
			}
			return lang;
		}
	}
	
	public String text(){
		return element.text();
	}
	
	
	
}
