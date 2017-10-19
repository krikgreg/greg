package com.nixsolutions.laboratorysixteen.exception;

public class RecaptchaServiceException extends RuntimeException {
	
	private static final long serialVersionUID = 2347260041631123822L;

	public RecaptchaServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
