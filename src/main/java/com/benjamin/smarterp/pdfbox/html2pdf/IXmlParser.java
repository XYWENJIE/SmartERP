package com.benjamin.smarterp.pdfbox.html2pdf;

import java.io.IOException;
import java.io.InputStream;

import com.benjamin.smarterp.pdfbox.styledxmlparser.node.IDocumentNode;

public interface IXmlParser {

	IDocumentNode parse(String html);

	IDocumentNode parse(InputStream htmlStream, String charset) throws IOException;

}
