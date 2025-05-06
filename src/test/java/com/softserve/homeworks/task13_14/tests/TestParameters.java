package com.softserve.homeworks.task13_14.tests;

import com.softserve.homeworks.task13_14.data.UserRepository;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class TestParameters {

    //expect no error
    private static Stream<Arguments> provideUsersValid() {
        return Stream.of(
                Arguments.of(UserRepository.getUserValid())
        );
    }

    //expect general error
    private static Stream<Arguments> provideUsersWrongCredentials() {
        return Stream.of(
                Arguments.of(UserRepository.getUserWrongCredentials())
        );
    }

    private static Stream<Arguments> provideUsersEmptyCredentials() {
        return Stream.of(
                Arguments.of(UserRepository.getUserEmptyCredentials())
        );
    }

    //expect specified error
    private static Stream<Arguments> provideUsersInvalidEmail() {
        return Stream.of(
                Arguments.of(UserRepository.getUserInvalidEmail())
        );
    }

    private static Stream<Arguments> provideUsersInvalidPassword() {
        return Stream.of(
                Arguments.of(UserRepository.getUserInvalidPassword())
        );
    }

    private static Stream<Arguments> provideUsersEmptyEmail() {
        return Stream.of(
                Arguments.of(UserRepository.getUserEmptyEmail())
        );
    }

    private static Stream<Arguments> provideUsersEmptyPassword() {
        return Stream.of(
                Arguments.of(UserRepository.getUserEmptyPassword())
        );
    }

}