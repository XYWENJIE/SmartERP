package com.benjamin.smarterp.pdfbox.styledxmlparser.node;

import java.util.List;
import java.util.Map;

public interface IElementNode extends INode,IStylesContainer{
	
	String name();
	
	IAttributes getAttributes();
	
	String getAttribute(String key);
	
	List<Map<String,String>> getAdditionalHtmlStyles();
	
	void addAdditionalHtmlStyles(Map<String,String> styles);
	
	String getLang();

}
