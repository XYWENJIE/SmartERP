package com.benjamin.smarterp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Request {

    public record CreateConversation(@JsonProperty("message") String message,@JsonProperty("contacts") List<String> contacts){}
}
