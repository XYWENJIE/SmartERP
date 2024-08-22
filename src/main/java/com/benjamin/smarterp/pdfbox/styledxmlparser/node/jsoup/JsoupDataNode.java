package com.benjamin.smarterp.pdfbox.styledxmlparser.node.jsoup;

import org.jsoup.nodes.DataNode;
import com.benjamin.smarterp.pdfbox.styledxmlparser.node.IDataNode;

public class JsoupDataNode extends JsoupNode implements IDataNode {
	
	private DataNode dataNode;

	public JsoupDataNode(DataNode dataNode) {
		super(dataNode);
		this.dataNode = dataNode;
	}
	
	@Override
	public String getWholeData() {
		return dataNode.getWholeData();
	}

}
