package homeworks.hw13.data;

public class SignInDataInvalidMail {
    public final String email;
    public final String password;
    public final String expectedError;

    public SignInDataInvalidMail(String email, String password, String expectedError) {
        this.email = email;
        this.password = password;
        this.expectedError = expectedError;
    }
}