package com.benjamin.smarterp;

import com.benjamin.smarterp.domain.Request;
import com.benjamin.smarterp.domain.ResultStatus;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class SmartErpApplicationTests {

	private final MockMvc mockMvc;

	private final ObjectMapper objectMapper;

	private final Jwt jwt = Jwt.withTokenValue("token").header("alg","none").claim("sub","huangrenjia").build();

	@Autowired
	public SmartErpApplicationTests(MockMvc mockMvc, ObjectMapper objectMapper) {
		this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

	@Test
	void contextLoads() {
	}

	@Test
	void userLogin() throws Exception {
		this.mockMvc.perform(post("/login"));
	}

	@Test
	void chatContacts() throws Exception {
		log.info("获取聊天联系人");
		MvcResult mvcResult = this.mockMvc.perform(get("/chat/contacts").with(jwt().jwt(jwt))).andExpect(status().isOk()).andReturn();
		String body = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
		log.info("测试返回对象是{}",body);
	}

    @Test
	void createConversation() throws Exception{
		log.info("创建谈话会话");
		Request.CreateConversation createConversation = new Request.CreateConversation("你好", List.of(2));
		MvcResult mvcResult =this.mockMvc.perform(post("/chat/conversation")
						.contentType(MediaType.APPLICATION_JSON)
						.content(this.objectMapper.writeValueAsString(createConversation)).with(jwt().jwt(jwt))).andExpect(status().isOk()).andReturn();
		String body = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
		JavaType javaType = this.objectMapper.getTypeFactory().constructParametricType(ResultStatus.class,String.class);
		ResultStatus<String> resultStatus = this.objectMapper.readValue(body, javaType);
		log.info(resultStatus.toString());

		log.info("获取谈话内容{}",resultStatus.getResult());
		this.openMessage(resultStatus.getResult());
	}

	private void openMessage(String conversationId) throws Exception{
		MvcResult mvcResult = this.mockMvc.perform(get("/chat/conversation/"+conversationId).with(jwt().jwt(jwt))).andExpect(status().isOk()).andReturn();
		String body = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
		log.info(body);
	}



}
