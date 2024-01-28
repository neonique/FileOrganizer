package de.neonique.persistence.identifier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class TestIdentifierManagerFromCSV {
    IdentifierManager identifierManager;
    @BeforeEach
    void init(){
        identifierManager = null;
    }

    @Test
    @DisplayName("get identifier from csv with correct format")
    void test_01(){
        identifierManager = new IdentifierManagerFromCSV("./src/test/resources/test_identifier.csv");
        HashMap<String,String> expected = new HashMap<>();
        expected.put("test1", "./src/test/resources/testSource/");
        expected.put("test2", "./src/test/resources/testSource/targets/");


        HashMap<String, String> identifier = identifierManager.loadIdentifier();

        assertThat(identifier).containsExactlyEntriesOf(expected);
    }

}
