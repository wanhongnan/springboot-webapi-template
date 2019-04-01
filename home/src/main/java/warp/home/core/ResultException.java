package warp.home.core;

public class ResultException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int code;
	String msg;
	Object data;
	
	public ResultException(int code,String msg, Object data ) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public int getCode() {return this.code;}
	public String getMsg() {return this.msg;}
	public Object getData() {return this.data;}
}
