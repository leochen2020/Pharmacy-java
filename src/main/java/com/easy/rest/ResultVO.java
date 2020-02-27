package com.easy.rest;

public class ResultVO<T> {
    // code请自己定义，例如  10成功，20需要登陆  30无权限等等 40错误
    private Integer code;
    private String msg;
    private T data;
    
    public ResultVO() {
    }
    
    public ResultVO(Integer code, String msg) {
    	this.code = code;
    	this.msg = msg;
    }
    
    public ResultVO(Integer code, String msg, T data) {
    	this.code = code;
    	this.msg = msg;
    	this.data = data;
    }
    
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
    
    
}
