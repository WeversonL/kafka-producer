package br.com.widsl.paymentproducer.exception.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("ApiErrorResponseBuilder Test")
class ApiErrorResponseBuilderTest {

    @Test
    @DisplayName("Should correctly build ApiErrorResponse with ApiErrorResponseBuilder")
    void buildApiErrorResponse() {

        List<ErrorValidation> errors = new ArrayList<>();

        ApiErrorResponse expected = new ApiErrorResponse(500, "message", "description", errors);

        ApiErrorResponse.ApiErrorResponseBuilder builder = new ApiErrorResponse.ApiErrorResponseBuilder();

        ApiErrorResponse dto = builder
                .code(500)
                .message("message")
                .description("description")
                .errors(errors)
                .build();

        assertEquals(expected, dto);
    }

    @Test
    @DisplayName("Should correctly convert ApiErrorResponseBuilder to string")
    void toStringInApiErrorResponseBuilder() {
        ApiErrorResponse.ApiErrorResponseBuilder apiErrorResponseBuilder = new ApiErrorResponse.ApiErrorResponseBuilder();
        apiErrorResponseBuilder.code(500);

        String expected = "ApiErrorResponse.ApiErrorResponseBuilder(code=500, message=null, description=null, errors=null)";
        assertEquals(expected, apiErrorResponseBuilder.toString());
    }

    @Test
    @DisplayName("Should set the errors correctly in the ApiErrorResponseBuilder")
    void setRealizedInApiErrorResponseBuilder() {
        List<ErrorValidation> errors = new ArrayList<>();
        ApiErrorResponse.ApiErrorResponseBuilder apiErrorResponseBuilder = new ApiErrorResponse.ApiErrorResponseBuilder();

        ApiErrorResponse.ApiErrorResponseBuilder result = apiErrorResponseBuilder.errors(errors);

        assertEquals(errors, result.build().getErrors());
    }

    @Test
    @DisplayName("Should set the description correctly in the ApiErrorResponseBuilder")
    void setPriorityInApiErrorResponseBuilder() {
        String description = "description";
        ApiErrorResponse.ApiErrorResponseBuilder apiErrorResponseBuilder = new ApiErrorResponse.ApiErrorResponseBuilder();

        ApiErrorResponse.ApiErrorResponseBuilder result = apiErrorResponseBuilder.description(description);

        assertEquals(description, result.build().getDescription());
    }

    @Test
    @DisplayName("Should set the message correctly in the builder")
    void setDescriptionInBuilder() {
        String message = "message";
        ApiErrorResponse.ApiErrorResponseBuilder apiErrorResponseBuilder = new ApiErrorResponse.ApiErrorResponseBuilder();

        ApiErrorResponse.ApiErrorResponseBuilder result = apiErrorResponseBuilder.message(message);

        assertEquals(message, result.build().getMessage());
    }

    @Test
    @DisplayName("Should set the code correctly in the builder")
    void setNameInBuilder() {
        Integer code = 500;
        ApiErrorResponse.ApiErrorResponseBuilder apiErrorResponseBuilder = new ApiErrorResponse.ApiErrorResponseBuilder();

        ApiErrorResponse.ApiErrorResponseBuilder result = apiErrorResponseBuilder.code(code);

        assertEquals(code, result.build().getCode());
    }

}
