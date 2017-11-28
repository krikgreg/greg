package com.nixsolutions.laboratoryeighteen.exception;

public class RestException extends RuntimeException {

	private static final long serialVersionUID = 2012998234269312011L;

	private int status;

	private String message;

	public RestException() {
		super();
	}

	public RestException(int status, String message) {
		super();
		this.setStatus(status);
		this.setMessage(message);
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
