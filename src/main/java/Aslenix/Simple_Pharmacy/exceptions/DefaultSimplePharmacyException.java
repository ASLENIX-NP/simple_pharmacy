package Aslenix.Simple_Pharmacy.exceptions;

public class DefaultSimplePharmacyException extends RuntimeException {
    private static final String DEFAULT_MESSAGE =
            "An unexpected error occurred.";

    public DefaultSimplePharmacyException(String message) {
        super(message == null || message.isBlank()
                ? DEFAULT_MESSAGE
                : message);
    }
}
