package com.benjamin.smarterp.service;

import com.benjamin.smarterp.domain.entity.Employee;
import com.benjamin.smarterp.domain.entity.LaborContracts;
import com.benjamin.smarterp.domain.entity.Personnel;
import com.benjamin.smarterp.domain.entity.Team;
import com.benjamin.smarterp.domain.entity.TemplateEntity;
import com.benjamin.smarterp.domain.entity.type.PersonnelRole;
import com.benjamin.smarterp.repository.jpa.PersonnelRepository;
import com.benjamin.smarterp.repository.jpa.TemplateEntityRepository;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.Assert;
import org.springframework.web.reactive.result.view.freemarker.FreeMarkerConfigurer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

@Service
public class PersonnelService {

    private static final Logger log = LoggerFactory.getLogger(PersonnelService.class);

    private final PersonnelRepository personnelRepository;
    private final TemplateEntityRepository templateEntityRepository;
    private final FreeMarkerConfigurer freeMarkerConfigurer;

    public PersonnelService(PersonnelRepository personnelRepository, TemplateEntityRepository templateEntityRepository, FreeMarkerConfigurer freeMarkerConfigurer) {
        this.personnelRepository = personnelRepository;
        this.templateEntityRepository = templateEntityRepository;
        this.freeMarkerConfigurer = freeMarkerConfigurer;
    }

    public List<PersonnelRecord> findPersonnelAll(){
        return this.personnelRepository.findAll().stream().map(personnel -> new PersonnelRecord(personnel.getEmail())).toList();
    }

    /**
     * 生成劳动合同
     * TODO 默认方法需要优化1.支持HTML的文字剧中和加粗，以及首页页面
     */
    public OutputStream generationLaborContract(LaborContracts laborContracts) throws IOException, TemplateException {

        Assert.notNull(laborContracts,"人员对象不能为空");
        Assert.isTrue(laborContracts.getPersonnel().getPersonnelTeamRoles().stream().anyMatch(ptr -> ptr.getTeam().getId().equals(laborContracts.getTeam().getId()) && ptr.getRole() == PersonnelRole.EMPLOYEE),"当前人员角色不是员工，要求角色是员工才能生成劳动合同");
        //TODO 在系统中的Template获取劳动合同模板，没有就系统默认内容。Template支持HTML和DOCx生成PDF，系统默认采用itext的API
        Optional<TemplateEntity> optionalTemplate = this.templateEntityRepository.findByTeamAndName(laborContracts.getTeam(),"LABOR_CONTRACT");
        log.debug("查询劳动合同模版，对象是否存在：{}",optionalTemplate.isPresent());
        if(optionalTemplate.isPresent()){
            //TODO 查询到TEMPLATE劳动合同模板
        }else{
            log.debug("没有设置劳动合同模版就按照系统默认劳动模版生产");
            OutputStream outputStream = new ByteArrayOutputStream();
            ConverterProperties converterProperties = new ConverterProperties();
            converterProperties.setFontProvider(new DefaultFontProvider(true,true,true));
            PdfWriter pdfWriter = new PdfWriter(outputStream);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.setDefaultPageSize(PageSize.A4);
            log.debug("完成定义PDF为A4规格");
            Template template = this.freeMarkerConfigurer.getConfiguration().getTemplate("contract/labor_contract_general.ftlh");
            log.debug("读取FreeMarker模版");
            String pdfhtml = FreeMarkerTemplateUtils.processTemplateIntoString(template,laborContracts);
            log.debug("基于模版生产合同内容");
            HtmlConverter.convertToPdf(pdfhtml,pdfDocument,converterProperties);
            log.debug("基于默认劳动合同内容HTML生产PDF文件");
            return outputStream;
        }
        return null;
    }

    /**
     * 创建员工
     */
    public void createEmployee(Employee employee){
        //TODO 添加员工信息
    }

    public record PersonnelRecord(@NotNull String email){}
}
