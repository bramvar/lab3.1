package exceptions;

public class ElementException extends Exception {
	
	public static final int ELEMENT_EXIST=1;
	public static final int NO_SUCH_ELEMENT=0;
	
	private int code;

	public ElementException(int code) {
		this.code=code;
	}
	
	public String getMessage() {
		String message=(code==ELEMENT_EXIST)?"Element exits already":"Element not found";
		return message;
	}
}
