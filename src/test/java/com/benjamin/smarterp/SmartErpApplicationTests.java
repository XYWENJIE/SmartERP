package com.benjamin.smarterp;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockReset;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

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



}
