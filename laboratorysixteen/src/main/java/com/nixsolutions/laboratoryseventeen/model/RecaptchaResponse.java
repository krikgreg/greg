package com.nixsolutions.laboratoryseventeen.model;

import java.io.Serializable;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecaptchaResponse implements Serializable {
	 private static final long serialVersionUID = 4083373293221420031L;

	    @JsonProperty("success")
	    private boolean success;

	    @JsonProperty("error-codes")
	    private Collection<String> errorCodes;

		public boolean isSuccess() {
			return success;
		}

		public void setSuccess(boolean success) {
			this.success = success;
		}

		public Collection<String> getErrorCodes() {
			return errorCodes;
		}

		public void setErrorCodes(Collection<String> errorCodes) {
			this.errorCodes = errorCodes;
		}
	}

