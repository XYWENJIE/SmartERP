package com.benjamin.smarterp.pdfbox.html2pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.benjamin.smarterp.pdfbox.html2pdf.attach.Attacher;
import com.benjamin.smarterp.pdfbox.styledxmlparser.node.IDocumentNode;

import java.io.File;
import java.io.IOException;

public class HtmlConverter {

    private final static Logger log = LoggerFactory.getLogger(HtmlConverter.class);


    public static void convertToPdf(File htmlFile,File pdfFile) throws IOException {
        convertToPdf(htmlFile,pdfFile,null);
    }

    public static void convertToPdf(File htmlFile,File pdfFile,ConverterProperties converterProperties) throws  IOException {

    }

    public static PDDocument convertToDocument(File htmlFile,PDDocument document,ConverterProperties converterProperties) throws IOException{
    	converterProperties = setDefaultFontProviderForPdfA(document, converterProperties);
    	IXmlParser parser = new JsoupHtmlParser();
    	IDocumentNode doc = parser.parse("");
        return Attacher.attach(doc,document,converterProperties);
    }
    
    private static ConverterProperties setDefaultFontProviderForPdfA(PDDocument document,ConverterProperties converterProperties) {
    	if(converterProperties == null) {
    		converterProperties = new ConverterProperties();
    	}
    	if(converterProperties.getFontProvider() == null) {
    		converterProperties.setFontProvider(null);
    	}
    	return converterProperties;
    }

}
