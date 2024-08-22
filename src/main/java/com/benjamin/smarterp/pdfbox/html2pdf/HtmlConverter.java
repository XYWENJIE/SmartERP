package com.benjamin.smarterp.pdfbox.html2pdf;

import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HtmlConverter {

    private final static Logger log = LoggerFactory.getLogger(HtmlConverter.class);


    public static void convertToPdf(File htmlFile,File pdfFile) throws IOException {
        convertToPdf(htmlFile,pdfFile,null);
    }

    public static void convertToPdf(File htmlFile,File pdfFile,ConverterProperties converterProperties) throws  IOException {

    }

    public static PDDocument convertToDocument(File htmlFile,PDDocument document,ConverterProperties converterProperties) throws IOException{
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document,page);
        PDType0Font font = PDType0Font.load(document, new File("C:/Windows/fonts/simfang.ttf"));
        Document htmlDocument = Jsoup.parse(new ClassPathResource("contract/labor_contract_general.html").getFile());
        Elements elements = htmlDocument.body().children();
        float cmToPoint = 28.34567f;
        float marginTop = 2.54f * cmToPoint;
        float marginLeft = 2.7f * cmToPoint;
        float pageHeight = 2.7f * cmToPoint;
        float startX = marginLeft;
        float startY = page.getMediaBox().getUpperRightY() - marginTop;
        float fontSize = 14;
        for(Element element : elements){
            Attribute attribute = element.attribute("style");
            log.info("{}",attribute);
            String text = element.text();
            int lineLimit = (int)((page.getMediaBox().getUpperRightX() - (marginLeft * 2)) / fontSize);
            List<String> lines = new ArrayList<>();
            for(int i = 0;i < text.length();i += lineLimit){
                String chunk = StringUtils.substring(text,i,Math.min(i+lineLimit,text.length()));
                lines.add(chunk);
            }
            for(String line : lines){
                contentStream.beginText();
                contentStream.setFont(font,fontSize);
                contentStream.newLineAtOffset(startX,startY);
                contentStream.showText(line);
                contentStream.endText();
                startY = startY - 15;
                if(startY < pageHeight){
                    page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                    contentStream.close();
                    contentStream = new PDPageContentStream(document,page);
                    startY = page.getMediaBox().getUpperRightY() - marginTop;
                }
            }
        }
        contentStream.close();
        return document;
    }

}
