package com.benjamin.smarterp.pdfbox.html2pdf;

import org.apache.pdfbox.pdmodel.font.FontProvider;

public class ConverterProperties {
	
	private FontProvider fontProvider;
	
	public FontProvider getFontProvider() {
		return fontProvider;
	}
	
	public ConverterProperties setFontProvider(FontProvider fontProvider) {
		this.fontProvider = fontProvider;
		return this;
	}
	
//	public IMetaInfo getEventMetaInfo() {
//		return metaInfo == null ? HtmlConverter.createPdf2HtmlMetaInfo() : metaInfo;
//	}
}
