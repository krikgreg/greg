package com.nixsolutions.laboratorysixteen.service;

public interface RecaptchaService {
	
	 boolean isResponseValid(String remoteIp, String response);
}
