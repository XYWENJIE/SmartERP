package com.benjamin.smarterp.pdfbox.html2pdf.attach;

import com.benjamin.smarterp.pdfbox.html2pdf.ConverterProperties;

public class ProcessorContext {
	
	public ProcessorContext(ConverterProperties converterProperties) {
		if(converterProperties == null) {
			converterProperties = new ConverterProperties();
		}
	}

}
