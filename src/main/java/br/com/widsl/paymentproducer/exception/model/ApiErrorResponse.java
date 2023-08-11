package br.com.widsl.paymentproducer.exception.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApiErrorResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1350375822517352971L;

    private Integer code;
    private String message;
    private String description;

    @JsonProperty("errors")
    private List<ErrorValidation> errors;

    public ApiErrorResponse(Integer code, String message, String description, List<ErrorValidation> errors) {
        this.code = code;
        this.message = message;
        this.description = description;
        this.errors = errors;
    }

    public ApiErrorResponse() {
    }

    public static ApiErrorResponseBuilder builder() {
        return new ApiErrorResponseBuilder();
    }

    public List<ErrorValidation> getErrors() {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        return errors;
    }

    public void addError(ErrorValidation errorValidation) {
        getErrors().add(errorValidation);
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getDescription() {
        return this.description;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("errors")
    public void setErrors(List<ErrorValidation> errors) {
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ApiErrorResponse that = (ApiErrorResponse) o;
        return Objects.equals(code, that.code) && Objects.equals(message, that.message)
                && Objects.equals(description, that.description) && Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message, description, errors);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ApiErrorResponse;
    }

    public String toString() {
        return "ApiErrorResponse(code=" + this.getCode() + ", message=" + this.getMessage() + ", description="
                + this.getDescription() + ", errors=" + this.getErrors() + ")";
    }

    public static class ApiErrorResponseBuilder {
        private Integer code;
        private String message;
        private String description;
        private List<ErrorValidation> errors;

        ApiErrorResponseBuilder() {
        }

        public ApiErrorResponseBuilder code(Integer code) {
            this.code = code;
            return this;
        }

        public ApiErrorResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ApiErrorResponseBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ApiErrorResponseBuilder errors(List<ErrorValidation> errors) {
            this.errors = errors;
            return this;
        }

        public ApiErrorResponse build() {
            return new ApiErrorResponse(this.code, this.message, this.description, this.errors);
        }

        public String toString() {
            return "ApiErrorResponse.ApiErrorResponseBuilder(code=" + this.code + ", message=" + this.message
                    + ", description=" + this.description + ", errors=" + this.errors + ")";
        }
    }
}
