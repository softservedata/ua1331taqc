package homeworks.hw13.data;

public class SignInEmptyEmail {
    public final String email;
    public final String password;
    public final String expectedErrorEmail;

    public SignInEmptyEmail(String email, String password, String expectedErrorEmail) {
        this.email = email;
        this.password = password;
        this.expectedErrorEmail = expectedErrorEmail;
    }
}