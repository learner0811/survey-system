package fis.ftu.util;

public class AppException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorMsg;
	private String code;
	
	public AppException() {
		
	}
	
	public AppException(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public AppException(String errorMsg, String code) {
		this.errorMsg = errorMsg;
		this.code = code;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}		
}
