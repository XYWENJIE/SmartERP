package com.benjamin.smarterp.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultStatus {



    //0是成功500是失败有消息
    private Integer code;

    private String message;

    private String token;
}
