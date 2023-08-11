package br.com.widsl.paymentproducer.exception.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ErrorValidation Test")
class ErrorValidationTest {

    @Test
    @DisplayName("Checks if ErrorValidation is building correctly")
    void testErrorValidation() {

        ErrorValidation errorResponse = ErrorValidation.builder()
                .description("Internal Server Error")
                .build();
        assertEquals("Internal Server Error", errorResponse.getDescription());
    }

    @Test
    @DisplayName("Test Equals ErrorValidation")
    void testEquals() {

        ErrorValidation response1 = ErrorValidation.builder()
                .description("Internal Server Error")
                .build();

        ErrorValidation response2 = ErrorValidation.builder()
                .description("Internal Server Error")
                .build();

        ErrorValidation response3 = ErrorValidation.builder()
                .description("Error")
                .build();

        assertEquals(response1, response2);

        assertNotEquals(response1, response3);
    }

    @Test
    @DisplayName("Should return true when compared with the same object")
    void equalsWhenComparedWithSameObject() {
        ErrorValidation errorValidation = new ErrorValidation("Test Description");
        boolean result = errorValidation.equals(errorValidation);
        assertTrue(result);
    }

    @Test
    @DisplayName("Should return false when compared with different class object")
    void equalsWhenComparedWithDifferentClassObject() {
        ErrorValidation errorValidation = new ErrorValidation("Test Description");
        String otherObject = "Test String";

        boolean result = errorValidation.equals(otherObject);

        assertFalse(result);
    }

    @Test
    @DisplayName("Should return true when compared with same class object having same description")
    void equalsWhenComparedWithSameClassObjectHavingSameDescription() {
        ErrorValidation errorValidation1 = ErrorValidation.builder()
                .description("Error 1")
                .build();

        ErrorValidation errorValidation2 = ErrorValidation.builder()
                .description("Error 1")
                .build();

        boolean result = errorValidation1.equals(errorValidation2);
        assertTrue(result);
    }

    @Test
    @DisplayName("Should return false when compared with same class object having different description")
    void equalsWhenComparedWithSameClassObjectHavingDifferentDescription() {
        ErrorValidation errorValidation1 = ErrorValidation.builder()
                .description("Error 1")
                .build();

        ErrorValidation errorValidation2 = ErrorValidation.builder()
                .description("Error 2")
                .build();

        boolean result = errorValidation1.equals(errorValidation2);

        assertFalse(result);
    }

    @Test
    @DisplayName("Should return false when compared with null")
    void equalsWhenComparedWithNull() {
        ErrorValidation errorValidation = new ErrorValidation("Test Description");
        ErrorValidation nullErrorValidation = null;

        boolean result = errorValidation.equals(nullErrorValidation);

        assertFalse(result);
    }

    @Test
    @DisplayName("Test HashCode ErrorValidation")
    void testHashCode() {

        ErrorValidation response1 = ErrorValidation.builder()
                .description("An unexpected error occurred")
                .build();

        ErrorValidation response2 = ErrorValidation.builder()
                .description("An unexpected error occurred")
                .build();

        ErrorValidation response3 = ErrorValidation.builder()
                .description("Bad Request")
                .build();

        assertEquals(response1.hashCode(), response2.hashCode());

        assertNotEquals(response1.hashCode(), response3.hashCode());
    }

    @Test
    @DisplayName("Test ToString ErrorValidation")
    void testToString() {

        ErrorValidation response = ErrorValidation.builder()
                .description("An unexpected error occurred")
                .build();

        assertEquals(
                "ErrorValidation(description=An unexpected error occurred)",
                response.toString());
    }

    @Test
    @DisplayName("Test Setters ErrorValidation")
    void testSetters() {
        ErrorValidation response = new ErrorValidation();
        response.setDescription("An unexpected error occurred");
        assertEquals("An unexpected error occurred", response.getDescription());

    }

    @Test
    @DisplayName("Test CanEqual ErrorValidation")
    void testCanEqual() {
        ErrorValidation response = new ErrorValidation();
        assertTrue(response.canEqual(new ErrorValidation()));
        assertFalse(response.canEqual(new ApiErrorResponse()));
    }

    @Test
    @DisplayName("Test Default Constructor ErrorValidation")
    void testDefaultConstructor() {
        ErrorValidation response = new ErrorValidation();
        assertNull(response.getDescription());
    }
}