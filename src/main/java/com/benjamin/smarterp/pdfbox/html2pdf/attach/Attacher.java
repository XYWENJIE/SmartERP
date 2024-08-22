package com.benjamin.smarterp.pdfbox.html2pdf.attach;

import org.apache.pdfbox.pdmodel.PDDocument;

import com.benjamin.smarterp.pdfbox.html2pdf.ConverterProperties;
import com.benjamin.smarterp.pdfbox.html2pdf.attach.impl.DefaultHtmlProcessor;
import com.benjamin.smarterp.pdfbox.styledxmlparser.node.IDocumentNode;

public class Attacher {
	
	public static PDDocument attach(IDocumentNode documentNode,PDDocument document,ConverterProperties converterProperties) {
		IHtmlProcessor processor = new DefaultHtmlProcessor(converterProperties);
		return processor.processDocument(documentNode,document);
	}

}
