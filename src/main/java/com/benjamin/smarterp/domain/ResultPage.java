package com.benjamin.smarterp.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 与ResultStatus一同返回，主要用户Spring Data等Page解析并输出JSON格式给前端客户端
 * @param <T>
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultPage<T> {

    private final List<T> content;

    public ResultPage(Page<T> page){
        this.content = page.getContent();
    }
}
