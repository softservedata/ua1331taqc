package com.softserve.edu07fwk.tests;

import com.softserve.edu07fwk.data.UserRepository;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class TestParameters {

    private static Stream<Arguments> provideUsers() {
        return Stream.of(
                Arguments.of(UserRepository.getValid())
        );
    }

}

