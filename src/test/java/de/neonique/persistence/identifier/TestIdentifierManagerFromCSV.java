package de.neonique.persistence.identifier;

import de.neonique.exception.IncorrectDataFormatException;
import de.neonique.exception.IncorrectFilePathException;
import de.neonique.persistence.csv.LoadCsv;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        identifierManager = new IdentifierManagerFromCSV("./src/test/resources/test_identifier/test_identifier.csv");
        HashMap<String,String> expected = new HashMap<>();
        expected.put("test1", "./src/test/resources/test_source/");
        expected.put("test2", "./src/test/resources/test_source/targets/");


        HashMap<String, String> identifier = identifierManager.loadIdentifier();

        assertThat(identifier).containsExactlyEntriesOf(expected);
    }

    @Test
    @DisplayName("throws exception in case of invalid path from identifier.csv")
    void test_02(){
        identifierManager = new IdentifierManagerFromCSV("./src/test/resources/test_identifier/test_faulty_identifier1.csv");

        Exception e = assertThrows(IncorrectFilePathException.class, () -> {
            HashMap<String, String> identifier = identifierManager.loadIdentifier();
        });
    }

}
