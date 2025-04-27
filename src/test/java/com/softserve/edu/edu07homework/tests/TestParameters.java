package com.softserve.edu.edu07homework.tests;

import com.softserve.edu.edu07homework.data.UserRepository;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class TestParameters {

    private static Stream<Arguments> provideUsers() {
        return Stream.of(
                Arguments.of(UserRepository.getValid())
        );
    }

    private static Stream<Arguments> provideUserNonvalid() {
        return Stream.of(
                Arguments.of(UserRepository.getNonvalidUser())
        );
    }

    private static Stream<Arguments> provideUserNonvalidEmail() {
        return Stream.of(
                Arguments.of(UserRepository.getNonvalidEmail())
        );
    }
    private static Stream<Arguments> provideUserNonvalidPassword() {
        return Stream.of(
                Arguments.of(UserRepository.getNonvalidPassword())
        );
    }

}

