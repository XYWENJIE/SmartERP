package com.benjamin.smarterp.pdfbox.styledxmlparser.node;

import java.util.Map;

public interface IStylesContainer {
	
	void setStyles(Map<String,String> stringStringMap);
	
	Map<String,String> getStyles();

}
