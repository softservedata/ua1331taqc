package homeworks.hw13.data;

public class EmptyPassword {
    public final String email;
    public final String password;
    public final String expectedErrorPass;

    public EmptyPassword(String email, String password, String expectedErrorPass) {
        this.email = email;
        this.password = password;
        this.expectedErrorPass = expectedErrorPass;
    }
}