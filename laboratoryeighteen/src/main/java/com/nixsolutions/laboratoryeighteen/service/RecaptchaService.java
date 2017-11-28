package com.nixsolutions.laboratoryeighteen.service;

public interface RecaptchaService {

	 public boolean validateCaptcha(String remoteIp, String reCaptchaResponse);
}
