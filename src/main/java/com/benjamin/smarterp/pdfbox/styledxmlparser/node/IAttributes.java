package com.benjamin.smarterp.pdfbox.styledxmlparser.node;

public interface IAttributes extends Iterable<IAttribute>{
	
	String getAttribute(String key);
	
	void setAttribute(String key,String value);
	
	int size();

}
