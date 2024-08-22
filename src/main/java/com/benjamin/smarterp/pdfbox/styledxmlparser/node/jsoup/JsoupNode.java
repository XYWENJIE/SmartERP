package com.benjamin.smarterp.pdfbox.styledxmlparser.node.jsoup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jsoup.nodes.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.benjamin.smarterp.pdfbox.styledxmlparser.node.INode;

public class JsoupNode implements INode{
	
	private Node node;
	
	private List<INode> childNodes = new ArrayList<>();
	
	INode parentNode;

	public JsoupNode(Node node) {
		this.node = node;
	}

	@Override
	public List<INode> childNodes() {
		return Collections.unmodifiableList(childNodes);
	}

	@Override
	public void addChild(INode node) {
		if(node instanceof JsoupNode) {
			childNodes.add(node);
			((JsoupNode)node).parentNode = this;
		}else {
			Logger logger = LoggerFactory.getLogger(JsoupNode.class);
			logger.error("");
		}
	}

	@Override
	public INode parentNode() {
		return parentNode;
	}

}
