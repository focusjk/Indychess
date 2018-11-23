package exception;

public class InputNotFilledException extends Exception {
	private int code;
	
	public InputNotFilledException(int code) {
		this.code = code;
		System.err.println("Please Fill Player Name");

	}

	public int getCode() {
		return code;
	}
}
