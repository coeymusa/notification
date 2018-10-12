package corey.hue.notficiations.client;

public class HttpClientException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HttpClientException(String errorMessage, Throwable err){
		super(errorMessage,err);
	}
}
