package com.benjamin.smarterp.pdfbox.html2pdf;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

public class HtmlConverter {

    public static void convertToPdf(File htmlFile,File pdfFile) throws IOException {
        convertToPdf(htmlFile,pdfFile,null);
    }

    public static void convertToPdf(File htmlFile,File pdfFile,ConverterProperties converterProperties) throws  IOException {

    }

    public static PDDocument convertToDocument(File htmlFile,PDDocument document,ConverterProperties converterProperties) throws IOException{
        return null;
    }

}
