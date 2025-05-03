package homeworks.hw13.data;

public class SignInData {

    public final String email;
    public final String password;
    public final String expectedWelcome;
    public final String expectedDetails;
    public final String expectedLabel;
    public final String expectedName;

    public SignInData(String email, String password, String expectedWelcome, String expectedDetails, String expectedLabel, String expectedName) {
        this.email = email;
        this.password = password;
        this.expectedWelcome = expectedWelcome;
        this.expectedDetails = expectedDetails;
        this.expectedLabel = expectedLabel;
        this.expectedName = expectedName;
    }

}
