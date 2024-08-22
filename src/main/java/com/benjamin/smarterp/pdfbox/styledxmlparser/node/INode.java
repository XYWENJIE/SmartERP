package com.benjamin.smarterp.pdfbox.styledxmlparser.node;

import java.util.List;

public interface INode {
	
	List<INode> childNodes();
	
	void addChild(INode node);
	
	INode parentNode();

}
