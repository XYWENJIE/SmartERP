package com.benjamin.smarterp.pdfbox.html2pdf.attach.impl;

import org.apache.pdfbox.pdmodel.PDDocument;

import com.benjamin.smarterp.pdfbox.html2pdf.ConverterProperties;
import com.benjamin.smarterp.pdfbox.html2pdf.ProcessorContextCreator;
import com.benjamin.smarterp.pdfbox.html2pdf.attach.IHtmlProcessor;
import com.benjamin.smarterp.pdfbox.html2pdf.attach.ProcessorContext;
import com.benjamin.smarterp.pdfbox.styledxmlparser.node.INode;

public class DefaultHtmlProcessor implements IHtmlProcessor{
	
	private ProcessorContext context;
	
	public DefaultHtmlProcessor(ConverterProperties converterProperties) {
		this.context = ProcessorContextCreator.createProcessorContext(converterProperties);
	}

	@Override
	public PDDocument processDocument(INode root, PDDocument pdDocument) {
		//this.context.reset(pdDocument);
		return null;
	}

}
