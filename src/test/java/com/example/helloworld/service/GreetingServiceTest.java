package com.example.helloworld.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class GreetingServiceTest {

    private final GreetingService service = new GreetingService();

    @ParameterizedTest
    @CsvSource({
            "alice,   Hello Alice",
            "Alice,   Hello Alice",
            "a,       Hello A",   // first letter of the first half
            "M,       Hello M",   // last letter of the first half
            "'  bob ', Hello Bob" // surrounding whitespace is trimmed
    })
    void greetsNamesStartingWithAToM(String name, String expected) {
        assertThat(service.greet(name)).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"nancy", "N", "z", "Zoe"})
    void rejectsNamesStartingWithNToZ(String name) {
        assertThatThrownBy(() -> service.greet(name)).isInstanceOf(InvalidNameException.class);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "1alice", "_alice", "Ángel"})
    void rejectsMissingBlankOrNonLetterNames(String name) {
        assertThatThrownBy(() -> service.greet(name)).isInstanceOf(InvalidNameException.class);
    }
}
