package grafi.exception;

public class VerticeNonEsisteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VerticeNonEsisteException() { }

	public VerticeNonEsisteException(String arg0) {
		super(arg0);
	}

	public VerticeNonEsisteException(Throwable arg0) {
		super(arg0);
	}

	public VerticeNonEsisteException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
