package com.softserve.edu.pr_task13_14;

import java.util.stream.Stream;

public class SignInPositiveRepository {
    public static Stream<SignInPositiveData> getData() {
        return Stream.of(
                new SignInPositiveData("gabim67170@npo2.com", "Gabim67170!"),
                new SignInPositiveData("cptt31km3v@knmcadibav.com", "Cptt31km3v@"),
                new SignInPositiveData("eqoyutv@mailto.plus", "eqoyutv@T3")
        );
    }
}