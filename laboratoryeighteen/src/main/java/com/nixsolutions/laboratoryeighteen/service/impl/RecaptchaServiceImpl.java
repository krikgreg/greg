package com.nixsolutions.laboratoryeighteen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.nixsolutions.laboratoryeighteen.exception.RestException;
import com.nixsolutions.laboratoryeighteen.model.RecaptchaResponse;
import com.nixsolutions.laboratoryeighteen.service.RecaptchaService;

@Service
@PropertySource({ "classpath:application.properties" })
public class RecaptchaServiceImpl implements RecaptchaService {
	
	@Value("${recaptcha.url}")
    private String recaptchaUrl;

    @Value("${recaptcha.secret-key}")
    private String recaptchaSecretKey;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public boolean validateCaptcha(String remoteIp, String reCaptchaResponse) {
        RecaptchaResponse recaptchaResponse;
        
        try {
            recaptchaResponse = restTemplate
                    .postForEntity(recaptchaUrl, createBody(recaptchaSecretKey, remoteIp, reCaptchaResponse), RecaptchaResponse.class)
                    .getBody();
            
        } catch (RestClientException e) {
            throw new RestException(403, "Recaptcha API not available due to exception");
        }
        
        return recaptchaResponse.isSuccess();
    }

    private MultiValueMap<String, String> createBody(String secret, String remoteIp, String response) {
        
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("secret", secret);
        form.add("remoteip", remoteIp);
        form.add("response", response);
        return form;
    }
}