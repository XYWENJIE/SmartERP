package com.benjamin.smarterp.service;

import com.benjamin.smarterp.domain.entity.Personnel;
import com.benjamin.smarterp.domain.entity.Team;
import com.benjamin.smarterp.domain.entity.TemplateEntity;
import com.benjamin.smarterp.domain.entity.type.PersonnelRole;
import com.benjamin.smarterp.pdfbox.html2pdf.HtmlConverter;
import com.benjamin.smarterp.repository.jpa.PersonnelRepository;
import com.benjamin.smarterp.repository.jpa.TemplateEntityRepository;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonnelService {

    private static final Logger log = LoggerFactory.getLogger(PersonnelService.class);

    private final PersonnelRepository personnelRepository;
    private final TemplateEntityRepository templateEntityRepository;

    public PersonnelService(PersonnelRepository personnelRepository, TemplateEntityRepository templateEntityRepository) {
        this.personnelRepository = personnelRepository;
        this.templateEntityRepository = templateEntityRepository;
    }

    public List<PersonnelRecord> findPersonnelAll(){
        return this.personnelRepository.findAll().stream().map(personnel -> new PersonnelRecord(personnel.getEmail())).toList();
    }

    /**
     * 生成劳动合同
     * TODO 默认方法需要优化1.支持HTML的文字剧中和加粗，以及首页页面
     */
    public PDDocument generationLaborContract(Personnel personnel, Team team){
        Assert.notNull(personnel,"人员对象不能为空");
        Assert.notNull(team,"team 对象不能为空");
        Assert.isTrue(personnel.getPersonnelTeamRoles().stream().anyMatch(ptr -> ptr.getTeam().getId().equals(team.getId()) && ptr.getRole() == PersonnelRole.EMPLOYEE),"当前人员角色不是员工，要求角色是员工才能生成劳动合同");
        //TODO 在系统中的Template获取劳动合同模板，没有就系统默认内容。Template支持HTML和DOCx生成PDF，系统默认采用itext的API
        Optional<TemplateEntity> optionalTemplate = this.templateEntityRepository.findByTeamAndName(team,"LABOR_CONTRACT");
        if(optionalTemplate.isPresent()){
            //TODO 查询到TEMPLATE劳动合同模板
        }else{
            try{
                PDDocument document = new PDDocument();
                return HtmlConverter.convertToDocument(new ClassPathResource("contract/labor_contract_general.html").getFile(),document,null);
            }catch (Exception e){
                log.error(e.getMessage(),e);
            }


            try{
                PDDocument document = new PDDocument();
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
            }catch (Exception e){
                log.error(e.getMessage(),e);
            }
        }
        return null;
    }

    public record PersonnelRecord(@NotNull String email){}
}
