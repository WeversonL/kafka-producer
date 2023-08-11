package br.com.widsl.paymentproducer.exception.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ApiErrorResponse Test")
class ApiErrorResponseTest {

    @Test
    @DisplayName("Should set the errors when the errors list is not null")
    void setErrorsWhenErrorsListIsNotNull() {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        ErrorValidation error1 = new ErrorValidation("Error 1");
        ErrorValidation error2 = new ErrorValidation("Error 2");
        List<ErrorValidation> errors = new ArrayList<>();
        errors.add(error1);
        errors.add(error2);

        apiErrorResponse.setErrors(errors);

        assertEquals(errors, apiErrorResponse.getErrors());
    }

    @Test
    @DisplayName("Should set the errors to null when the errors list is null")
    void setErrorsWhenErrorsListIsNull() {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setErrors(null);

        assertTrue(apiErrorResponse.getErrors().isEmpty());
    }

    @Test
    @DisplayName("Checks if ApiErrorResponse is building correctly")
    void testApiErrorResponse() {

        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .code(500)
                .message("Internal Server Error")
                .description("An unexpected error occurred")
                .build();

        assertEquals(500, errorResponse.getCode());
        assertEquals("Internal Server Error", errorResponse.getMessage());
        assertEquals("An unexpected error occurred", errorResponse.getDescription());
    }

    @Test
    @DisplayName("Test Equals ApiErrorResponse")
    void testEquals() {
        ApiErrorResponse response1 = ApiErrorResponse.builder()
                .code(500)
                .message("Internal Server Error")
                .description("An unexpected error occurred")
                .build();

        ApiErrorResponse response2 = ApiErrorResponse.builder()
                .code(500)
                .message("Internal Server Error")
                .description("An unexpected error occurred")
                .build();

        ApiErrorResponse response3 = ApiErrorResponse.builder()
                .code(400)
                .message("Bad Request")
                .description("Bad Request")
                .build();

        assertEquals(response1, response2);

        assertNotEquals(response1, response3);
    }

    @Test
    @DisplayName("Should return false when compared with null")
    void equalsWhenComparedWithNull() {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(400, "Bad Request", "Invalid request", null);

        boolean result = apiErrorResponse.equals(null);

        assertFalse(result);
    }

    @Test
    @DisplayName("Should return false when compared with different class object")
    void equalsWhenComparedWithDifferentClassObject() {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(400, "Bad Request", "Invalid request",
                new ArrayList<>());
        String otherObject = "Some string";

        boolean result = apiErrorResponse.equals(otherObject);

        assertFalse(result);
    }

    @Test
    @DisplayName("Should return true when compared with the same object")
    void equalsWhenComparedWithSameObject() {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(200, "Success", "Request processed successfully",
                null);

        boolean result = apiErrorResponse.equals(apiErrorResponse);

        assertTrue(result);
    }

    @Test
    @DisplayName("Should return true when compared with object having same properties")
    void equalsWhenComparedWithObjectHavingSameProperties() {
        ApiErrorResponse errorResponse1 = new ApiErrorResponse(400, "Bad Request", "Invalid input", null);
        ApiErrorResponse errorResponse2 = new ApiErrorResponse(400, "Bad Request", "Invalid input", null);

        boolean result = errorResponse1.equals(errorResponse2);

        assertTrue(result);
    }

    @Test
    @DisplayName("Should return false when compared with object having different properties")
    void equalsWhenComparedWithObjectHavingDifferentProperties() {
        ApiErrorResponse errorResponse1 = new ApiErrorResponse(400, "Bad Request", "Invalid input", null);
        ApiErrorResponse errorResponse2 = new ApiErrorResponse(400, "Bad Request", "Invalid input2", null);

        boolean result = errorResponse1.equals(errorResponse2);

        assertFalse(result);
    }

    @Test
    @DisplayName("Test HashCode ApiErrorResponse")
    void testHashCode() {
        ApiErrorResponse response1 = ApiErrorResponse.builder()
                .code(500)
                .message("Internal Server Error")
                .description("An unexpected error occurred")
                .build();

        ApiErrorResponse response2 = ApiErrorResponse.builder()
                .code(500)
                .message("Internal Server Error")
                .description("An unexpected error occurred")
                .build();

        ApiErrorResponse response3 = ApiErrorResponse.builder()
                .code(400)
                .message("Bad Request")
                .description("Bad Request")
                .build();

        assertEquals(response1.hashCode(), response2.hashCode());

        assertNotEquals(response1.hashCode(), response3.hashCode());
    }

    @Test
    @DisplayName("Test ToString ApiErrorResponse")
    void testToString() {
        ApiErrorResponse response = ApiErrorResponse.builder()
                .code(500)
                .message("Internal Server Error")
                .description("An unexpected error occurred")
                .build();

        assertEquals(
                "ApiErrorResponse(code=500, message=Internal Server Error, description=An unexpected error occurred, errors=[])",
                response.toString());
    }

    @Test
    @DisplayName("Test Setters ApiErrorResponse")
    void testSetters() {
        ApiErrorResponse response = new ApiErrorResponse();
        response.setCode(500);
        response.setMessage("Internal Server Error");
        response.setDescription("An unexpected error occurred");
        assertEquals(500, response.getCode());
        assertEquals("Internal Server Error", response.getMessage());
        assertEquals("An unexpected error occurred", response.getDescription());

    }

    @Test
    @DisplayName("Test CanEqual ApiErrorResponse")
    void testCanEqual() {
        ApiErrorResponse response = new ApiErrorResponse();
        assertTrue(response.canEqual(new ApiErrorResponse()));
        assertFalse(response.canEqual(new ErrorValidation("x")));
    }

    @Test
    @DisplayName("Test Default Constructor ApiErrorResponse")
    void testDefaultConstructor() {
        ApiErrorResponse response = new ApiErrorResponse();
        assertNull(response.getCode());
        assertNull(response.getMessage());
        assertNull(response.getDescription());
    }
}