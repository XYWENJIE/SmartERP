package com.benjamin.smarterp.pdfbox.html2pdf;

import java.io.IOException;
import java.io.InputStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.benjamin.smarterp.pdfbox.styledxmlparser.node.IDocumentNode;
import com.benjamin.smarterp.pdfbox.styledxmlparser.node.INode;
import com.benjamin.smarterp.pdfbox.styledxmlparser.node.jsoup.JsoupDataNode;
import com.benjamin.smarterp.pdfbox.styledxmlparser.node.jsoup.JsoupDocumentNode;
import com.benjamin.smarterp.pdfbox.styledxmlparser.node.jsoup.JsoupDocumentTypeNode;
import com.benjamin.smarterp.pdfbox.styledxmlparser.node.jsoup.JsoupElementNode;
import com.benjamin.smarterp.pdfbox.styledxmlparser.node.jsoup.JsoupTextNode;

public class JsoupHtmlParser implements IXmlParser {
	
	private static Logger logger = LoggerFactory.getLogger(JsoupHtmlParser.class);
	
	@Override
	public IDocumentNode parse(InputStream htmlStream,String charset) throws IOException{
		String baseUri = "";
		Document document = Jsoup.parse(htmlStream,charset,baseUri);
		INode result = wrapJsoupHierarchy(document);
		if(result instanceof IDocumentNode) {
			return (IDocumentNode) result;
		}else {
			throw new IllegalStateException();
		}
	}
	
	@Override
	public IDocumentNode parse(String html) {
		Document doc = Jsoup.parse(html);
		INode result = wrapJsoupHierarchy(doc);
		if(result instanceof IDocumentNode) {
			return (IDocumentNode)result;
		}else {
			throw new IllegalStateException();
		}
	}
	
	private INode wrapJsoupHierarchy(Node jsoupNode) {
		INode resultNode = null;
		if(jsoupNode instanceof Document) {
			resultNode = new JsoupDocumentNode((Document) jsoupNode);
		}else if(jsoupNode instanceof TextNode) {
			resultNode = new JsoupTextNode((TextNode) jsoupNode);
		}else if(jsoupNode instanceof Element) {
			resultNode = new JsoupElementNode((Element) jsoupNode);
		}else if(jsoupNode instanceof DataNode) {
			resultNode = new JsoupDataNode((DataNode) jsoupNode);
		}else if(jsoupNode instanceof DocumentType) {
			resultNode = new JsoupDocumentTypeNode((DocumentType) jsoupNode);
		}else if(jsoupNode instanceof Comment) {
			
		}else {
			logger.error("Could not map node type:{}",jsoupNode.getClass());
		}
		
		for(Node node: jsoupNode.childNodes()) {
			INode childNode = wrapJsoupHierarchy(node);
			if(childNode != null) {
				resultNode.addChild(childNode);
			}
		}
		return resultNode;
		
	}

}
