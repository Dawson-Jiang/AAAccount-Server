package com.dawson.aaaccount.entity;

public class OperateResult<T> {
	private int result;
	private int errorCode;
	private String errorMsg;
	private String addInfo;
	private T content;
	
	public OperateResult() {}
	
	public OperateResult(T ct) {
		content=ct;
		result=ErrorCode.SUCCESS;
	}
	public OperateResult(int ec,String msg) {
		errorCode=ec;
		errorMsg=msg;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getAddInfo() {
		return addInfo;
	}
	public void setAddInfo(String addInfo) {
		this.addInfo = addInfo;
	}
	public T getContent() {
		return content;
	}
	public void setContent(T content) {
		this.content = content;
	}

	
}
