package homeworks.hw13.data;

public class SignInInvalidPassword {
    public final String email;
    public final String password;
    public final String expectedErrorPass;

    public SignInInvalidPassword(String email, String password, String expectedErrorPass) {
        this.email = email;
        this.password = password;
        this.expectedErrorPass = expectedErrorPass;
    }
}