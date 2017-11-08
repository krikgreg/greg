package com.nixsolutions.laboratoryseventeen.service;

public interface RecaptchaService {

//	boolean isResponseValid(String remoteIp, String response);
	 public boolean validateCaptcha(String remoteIp, String reCaptchaResponse);
}
