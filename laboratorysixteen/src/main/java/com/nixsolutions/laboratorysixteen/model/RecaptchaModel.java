package com.nixsolutions.laboratorysixteen.model;

public abstract class RecaptchaModel {
	
    private String recaptchaResponse;

    public void setRecaptchaResponse(String response) {
        this.recaptchaResponse = response;
    }

    public String getRecaptchaResponse() {
        return recaptchaResponse;
    }

    @Override
    public String toString() {
        return "RecaptchaForm{" +
                "recaptchaResponse='" + recaptchaResponse + '\'' +
                '}';
    }

}
