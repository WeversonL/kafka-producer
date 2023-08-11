package br.com.widsl.paymentproducer.exception.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("ErrorValidationBuilder Test")
class ErrorValidationBuilderTest {

    @Test
    @DisplayName("Should correctly build ErrorValidation with ErrorValidationBuilder")
    void buildErrorValidation() {

        ErrorValidation expected = new ErrorValidation("description");

        ErrorValidation.ErrorValidationBuilder builder = new ErrorValidation.ErrorValidationBuilder();

        ErrorValidation dto = builder
                .description("description")
                .build();

        assertEquals(expected, dto);
    }

    @Test
    @DisplayName("Should correctly convert ErrorValidationBuilder to string")
    void toStringInErrorValidationBuilder() {

        ErrorValidation.ErrorValidationBuilder apiErrorResponseBuilder = new ErrorValidation.ErrorValidationBuilder();
        apiErrorResponseBuilder.description("description");

        String expected = "ErrorValidation.ErrorValidationBuilder(description=description)";
        assertEquals(expected, apiErrorResponseBuilder.toString());
    }

    @Test
    @DisplayName("Should set the description correctly in the ErrorValidationBuilder")
    void setPriorityInErrorValidationBuilder() {
        String description = "description";
        ErrorValidation.ErrorValidationBuilder apiErrorResponseBuilder = new ErrorValidation.ErrorValidationBuilder();

        ErrorValidation.ErrorValidationBuilder result = apiErrorResponseBuilder.description(description);

        assertEquals(description, result.build().getDescription());
    }

}
