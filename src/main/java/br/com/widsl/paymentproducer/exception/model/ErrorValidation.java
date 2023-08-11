package br.com.widsl.paymentproducer.exception.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorValidation implements Serializable {

    @Serial
    private static final long serialVersionUID = 4990835441678162388L;

    private String description;

    public ErrorValidation(String description) {
        this.description = description;
    }

    public ErrorValidation() {
    }

    public static ErrorValidationBuilder builder() {
        return new ErrorValidationBuilder();
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ErrorValidation that = (ErrorValidation) o;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ErrorValidation;
    }

    public String toString() {
        return "ErrorValidation(description=" + this.getDescription() + ")";
    }

    public static class ErrorValidationBuilder {
        private String description;

        ErrorValidationBuilder() {
        }

        public ErrorValidationBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ErrorValidation build() {
            return new ErrorValidation(this.description);
        }

        public String toString() {
            return "ErrorValidation.ErrorValidationBuilder(description=" + this.description + ")";
        }
    }
}
