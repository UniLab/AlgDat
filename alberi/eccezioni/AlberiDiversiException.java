package alberi.eccezioni;

public class AlberiDiversiException extends RuntimeException {

	public AlberiDiversiException() { }

	public AlberiDiversiException(String arg0) { super(arg0); }

	public AlberiDiversiException(Throwable arg0) { super(arg0); }

	public AlberiDiversiException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public AlberiDiversiException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
