package homeworks.hw13.repo;



import homeworks.hw13.data.*;

import java.util.stream.Stream;

public class SignInDataRep {

    public static Stream<SignInData> uaUsers() {
        return Stream.of(
                new SignInData("samplestest@greencity.com", "weyt3$Guew^", "З поверненням!", "Будь ласка, внесiть свої дані для входу.", "Електронна пошта", "Svitlana"),
                new SignInData("newuser1@greencity.com", "NewPass1!", "З поверненням!", "Будь ласка, внесiть свої дані для входу.", "Електронна пошта", "Newuser1"),
                new SignInData("testuser2025@greencity.com", "Test2025#", "З поверненням!", "Будь ласка, внесiть свої дані для входу.", "Електронна пошта", "Testuser2025"),
                new SignInData("greenfan3@greencity.com", "GreenFan3$", "З поверненням!", "Будь ласка, внесiть свої дані для входу.", "Електронна пошта", "Greenfan3")
        );
    }
    public static Stream<SignInData> enUsers() {
        return Stream.of(
                new SignInData("samplestest@greencity.com", "weyt3$Guew^", "Welcome back!", "Please enter your details to sign in.", "Email", "Svitlana"),
                new SignInData("newuser1@greencity.com", "NewPass1!", "Welcome back!", "Please enter your details to sign in.", "Email", "Newuser1"),
                new SignInData("testuser2025@greencity.com", "Test2025#", "Welcome back!", "Please enter your details to sign in.", "Email", "Testuser2025"),
                new SignInData("greenfan3@greencity.com", "GreenFan3$", "Welcome back!", "Please enter your details to sign in.", "Email", "Greenfan3")
        );
    }
    public static Stream<SignInDataInvalidMail> invalidEmailUa() {
        return Stream.of(
                new SignInDataInvalidMail("samplestesgreencity.com", "somepassword", "Перевірте, чи правильно вказано вашу адресу електронної пошти"),
                new SignInDataInvalidMail("testuser2025greencity.com", "somepassword", "Перевірте, чи правильно вказано вашу адресу електронної пошти"),
                new SignInDataInvalidMail("newuser1greencity.com", "somepassword", "Перевірте, чи правильно вказано вашу адресу електронної пошти"),
                new SignInDataInvalidMail("greenfan3@greencitycom", "somepassword", "Перевірте, чи правильно вказано вашу адресу електронної пошти")
        );
    }

    public static Stream<SignInDataInvalidMail> invalidEmailsEn() {
        return Stream.of(
                new SignInDataInvalidMail("samplestesgreencity.com", "somepassword", "Please check that your e-mail address is indicated correctly"),
                new SignInDataInvalidMail("testuser2025greencity.com", "somepassword", "Please check that your e-mail address is indicated correctly"),
                new SignInDataInvalidMail("newuser1greencity.com", "somepassword", "Please check that your e-mail address is indicated correctly"),
                new SignInDataInvalidMail("greenfan3@greencitycom", "somepassword", "Please check that your e-mail address is indicated correctly")
        );
    }

    public static Stream<SignInInvalidPassword> invalidPasswordUa() {
        return Stream.of(
                new SignInInvalidPassword("samplestest@greencity.com", "wrongpass123!", "Введено невірний email або пароль"),
                new SignInInvalidPassword("testuser2025@greencity.com", "wroszxtj123!", "Введено невірний email або пароль"),
                new SignInInvalidPassword("newuser1@greencity.com", "wssjtrjass123!", "Введено невірний email або пароль"),
                new SignInInvalidPassword("greenfan3@greencity.com", "wrojstrjs123!", "Введено невірний email або пароль")
        );
    }

    public static Stream<SignInInvalidPassword> invalidPasswordEn() {
        return Stream.of(
                new SignInInvalidPassword("samplestest@greencity.com", "wrongpass123!", "Bad email or password"),
                new SignInInvalidPassword("testuser2025@greencity.com", "wrongpass123!", "Bad email or password"),
                new SignInInvalidPassword("newuser1@greencity.com", "wrongpass123!", "Bad email or password"),
                new SignInInvalidPassword("greenfan3@greencity.com", "wrongpass123!", "Bad email or password")
        );
    }

    public static Stream<SignInEmptyEmail> emptyEmailUa() {
        return Stream.of(
                new SignInEmptyEmail("", "somepassword", "Введіть пошту"),
                new SignInEmptyEmail("", "GreenFan3$", "Введіть пошту"),
                new SignInEmptyEmail("", "weyt3$Guew^", "Введіть пошту"),
                new SignInEmptyEmail("", "NewPass1!", "Введіть пошту")
        );
    }

    public static Stream<SignInEmptyEmail> emptyEmailEn() {
        return Stream.of(
                new SignInEmptyEmail("", "somepassword", "Email is required"),
                new SignInEmptyEmail("", "GreenFan3$", "Email is required"),
                new SignInEmptyEmail("", "weyt3$Guew^", "Email is required"),
                new SignInEmptyEmail("", "NewPass1!", "Email is required")
        );
    }

    public static Stream<EmptyPassword> emptyPasswordUa() {
        return Stream.of(
                new EmptyPassword("samplestest@greencity.com", "", "Введіть пароль"),
                new EmptyPassword("testuser2025@greencity.com", "", "Введіть пароль"),
                new EmptyPassword("newuser1@greencity.com", "", "Введіть пароль"),
                new EmptyPassword("greenfan3@greencity.com", "", "Введіть пароль")
        );
    }

    public static Stream<EmptyPassword> emptyPasswordEn() {
        return Stream.of(
                new EmptyPassword("samplestest@greencity.com", "", "Password is required"),
                new EmptyPassword("testuser2025@greencity.com", "", "Password is required"),
                new EmptyPassword("newuser1@greencity.com", "", "Password is required"),
                new EmptyPassword("greenfan3@greencity.com", "", "Password is required")
        );
    }
}
