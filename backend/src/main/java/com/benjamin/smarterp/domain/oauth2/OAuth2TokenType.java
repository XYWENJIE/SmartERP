package com.benjamin.smarterp.domain.oauth2;

import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Objects;

public class OAuth2TokenType implements Serializable {

    public static final OAuth2TokenType ACCESS_TOKEN = new OAuth2TokenType("access_token");
    public static final OAuth2TokenType REFRESH_TOKEN = new OAuth2TokenType("refresh_token");

    private final String value;

    public OAuth2TokenType(String value){
        Assert.hasText(value,"value cannot be empty");
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OAuth2TokenType that = (OAuth2TokenType) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
