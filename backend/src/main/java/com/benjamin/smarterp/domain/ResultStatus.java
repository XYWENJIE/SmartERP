package com.benjamin.smarterp.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultStatus<T> {

    public ResultStatus(Integer code, String message, String token, T result) {
		super();
		this.code = code;
		this.message = message;
		this.token = token;
		this.result = result;
	}

	//0是成功500是失败，失败会有错误消息
    private Integer code;

    //消息
    private String message;

    //Token是登录用户的Token值，用于前端接收保存，目前主要是JWT的认证
    private String token;

    //返回前端主要数据
    private T result;

    public static <R> ResultStatus<R> success(R result){
        return new ResultStatus<>(0,null,null,result);
    }
    
    public static <R> ResultStatus<R> successToken(String token){
    	return new ResultStatus<R>(0, null, token, null);
    }

    /**
     * 创建包含错误状态的ResultStatus实例
     * @param message 错误信息
     * @return
     * @param <R>
     */
    public static <R> ResultStatus<R> error(String message){
        return new ResultStatus<>(-1,message,null,null);
    }

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
    
    
}
