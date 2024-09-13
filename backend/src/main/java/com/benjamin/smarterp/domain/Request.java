package com.benjamin.smarterp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class Request {

    public record CreateConversation(@JsonProperty("message") @NotBlank String message, @JsonProperty("contacts") List<String> contacts){}
}
