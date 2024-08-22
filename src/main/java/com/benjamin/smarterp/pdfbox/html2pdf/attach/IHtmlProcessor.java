package com.benjamin.smarterp.pdfbox.html2pdf.attach;

import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;

import com.benjamin.smarterp.pdfbox.styledxmlparser.node.IDocumentNode;
import com.benjamin.smarterp.pdfbox.styledxmlparser.node.INode;

public interface IHtmlProcessor {
	
	PDDocument processDocument(INode documentNode,PDDocument pdDocument);
	

}
