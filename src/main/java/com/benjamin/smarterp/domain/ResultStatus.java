package com.benjamin.smarterp.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultStatus<T> {

    //0是成功500是失败有消息
    private Integer code;

    private String message;

    private String token;

    private T result;

    public static <R> ResultStatus<R> success(R result){
        return new ResultStatus<>(0,null,null,result);
    }
}
