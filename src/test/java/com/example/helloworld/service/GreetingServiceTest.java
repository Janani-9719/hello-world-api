package com.example.helloworld.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class GreetingServiceTest {

    private final GreetingService service = new GreetingService();

    @ParameterizedTest(name = "\"{0}\" -> \"{1}\"")
    @CsvSource({
            "alice,      Hello Alice",
            "Alice,      Hello Alice",
            "a,          Hello A",        // first letter of the first half
            "M,          Hello M",        // last letter of the first half
            "mcDonald,   Hello McDonald", // only the first letter is changed
            "'  bob  ',  Hello Bob"       // surrounding whitespace is trimmed
    })
    void greetsNamesStartingWithAToM(String name, String expected) {
        assertThat(service.greet(name)).isEqualTo(expected);
    }

    @ParameterizedTest(name = "\"{0}\"")
    @ValueSource(strings = {"n", "N", "z", "Z", "nancy", "Zoe"})
    void rejectsNamesStartingWithNToZ(String name) {
        assertRejected(name);
    }

    @ParameterizedTest(name = "\"{0}\"")
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t"})
    void rejectsMissingOrBlankNames(String name) {
        assertRejected(name);
    }

    @ParameterizedTest(name = "\"{0}\"")
    @ValueSource(strings = {
            "1alice", // digit
            "_alice", // symbol
            "Ángel",  // accented letter
            "Алиса",  // non-Latin script
            "ıvan"    // Turkish dotless i, which upper-cases to 'I'
    })
    void rejectsNamesNotStartingWithAnEnglishLetter(String name) {
        assertRejected(name);
    }

    private void assertRejected(String name) {
        assertThatThrownBy(() -> service.greet(name)).isInstanceOf(InvalidNameException.class);
    }
}
