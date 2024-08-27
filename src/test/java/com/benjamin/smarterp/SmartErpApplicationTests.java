package com.benjamin.smarterp;

import com.benjamin.smarterp.domain.Request;
import com.benjamin.smarterp.domain.ResultStatus;
import com.benjamin.smarterp.domain.entity.Personnel;
import com.benjamin.smarterp.domain.entity.PersonnelTeamRole;
import com.benjamin.smarterp.domain.entity.Team;
import com.benjamin.smarterp.domain.entity.type.PersonnelRole;
import com.benjamin.smarterp.service.PersonnelService;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.*;
import java.util.List;

@SpringBootTest
@AutoConfigureWebTestClient
class SmartErpApplicationTests {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private WebTestClient webTestClient;

	private final ObjectMapper objectMapper;

	private final PersonnelService personnelService;

	private final Jwt jwt = Jwt.withTokenValue("token").header("alg","none").claim("sub","huangrenjia").build();

	@Autowired
	public SmartErpApplicationTests(WebTestClient webTestClient, ObjectMapper objectMapper, PersonnelService personnelService) {
		this.webTestClient = webTestClient;
        this.objectMapper = objectMapper;
        this.personnelService = personnelService;
    }

	@Test
	void contextLoads() {
	}

	@Test
	void userLogin() throws Exception {
		//this.webTestClient.post().uri("/login").exchange().expectStatus().isOk();
	}

	@Test
	void chatContacts() throws Exception {
		log.info("获取聊天联系人");
		String body = this.webTestClient.get().uri("/chat/contacts").header("Authorization","Bearer "+jwt.getTokenValue()).exchange().expectStatus().isOk().expectBody(String.class).returnResult().getResponseBody();
//		MvcResult mvcResult = this.mockMvc.perform(get("/chat/contacts").with(jwt().jwt(jwt))).andExpect(status().isOk()).andReturn();
//		String body = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
		log.info("测试返回对象是{}",body);
	}

    @Test
	void createConversation() throws Exception{
		log.info("创建谈话会话");
		Request.CreateConversation createConversation = new Request.CreateConversation("你好", List.of("2"));
		String body = this.webTestClient.post().uri("/chat/conversation").contentType(MediaType.APPLICATION_JSON).bodyValue(createConversation).header("Authorization","Bearer "+ jwt.getTokenValue()).exchange().expectStatus().isOk().expectBody(String.class).returnResult().getResponseBody();
		JavaType javaType = this.objectMapper.getTypeFactory().constructParametricType(ResultStatus.class,String.class);
		ResultStatus<String> resultStatus = this.objectMapper.readValue(body, javaType);
		log.info(resultStatus.toString());

		log.info("获取谈话内容{}",resultStatus.getResult());
		this.openMessage(resultStatus.getResult());
	}

	private void openMessage(String conversationId) throws Exception{
		String body = this.webTestClient.get().uri("/chat/conversation/"+conversationId).headers(httpHeaders -> httpHeaders.setBearerAuth(jwt.getTokenValue())).exchange().expectStatus().isOk().expectBody(String.class).returnResult().getResponseBody();
		log.info(body);
	}

	@Test
	public void personnelList() throws Exception {
		log.info("获取人员列表");
		this.webTestClient.get().uri("/personnel/list").headers(httpHeaders -> httpHeaders.setBearerAuth(jwt.getTokenValue())).exchange().expectStatus().isOk();
	}


	/**
	 * 创建生产PDF文件，采用Apache PDFBox
	 * @throws IOException 异常通知
	 */
	@Test
	public void printPDF() throws IOException {
		Team team = new Team();
		team.setId(1);
		team.setTeamName("壹家播商");
		Personnel personnel = new Personnel();
		PersonnelTeamRole personnelTeamRole = new PersonnelTeamRole();
		personnelTeamRole.setRole(PersonnelRole.EMPLOYEE);
		personnelTeamRole.setTeam(team);
		personnel.getPersonnelTeamRoles().add(personnelTeamRole);
		OutputStream outputStream = this.personnelService.generationLaborContract(personnel,team);
		log.info("任务执行完成");
		Assertions.assertNotNull(outputStream);
		FileOutputStream fileOutputStream = new FileOutputStream("D:\\test.pdf");
		((ByteArrayOutputStream)outputStream).writeTo(fileOutputStream);
		fileOutputStream.close();
	}


}
