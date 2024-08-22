package com.benjamin.smarterp.pdfbox.html2pdf;

import com.benjamin.smarterp.pdfbox.html2pdf.attach.ProcessorContext;

public class ProcessorContextCreator {
	
	public static ProcessorContext createProcessorContext(ConverterProperties converterProperties) {
		if(converterProperties == null) {
			converterProperties = new ConverterProperties();
		}
		ProcessorContext processorContext = new ProcessorContext(converterProperties);
		//processorContext.setMetaInfo(converterProperties.getEventMetaInfo());
		return processorContext;
	}

}
