package com.nixsolutions.laboratoryseventeen.service;

public interface RecaptchaService {

	 public boolean validateCaptcha(String remoteIp, String reCaptchaResponse);
}
