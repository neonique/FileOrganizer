package de.neonique.persistence.csv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;


public class TestLoadCsv {
    @Test
    @DisplayName("loading pair correctly from csv")
    void test_01(){
        HashMap<String, String> expectedPairs = new HashMap<>();
        expectedPairs.put("localSrcFolder", "D:/testFolder/testsrc");

        HashMap<String, String> pairs = LoadCsv.extractPairs("./src/test/resources/test_config/test_config.csv");

        assertThat(pairs).containsExactlyEntriesOf(expectedPairs);
    }

    @Test
    @DisplayName("throw exception, when there is only an id")
    void test_02(){
        HashMap<String, String> expectedPairs = new HashMap<>();
        expectedPairs.put("localSrcFolder", "D:/testFolder/testsrc");
        String expected = "lol";

        Exception e = assertThrows(IncorrectDataFormatException.class, () -> {
            HashMap<String, String> pairs = LoadCsv.extractPairs("./src/test/resources/test_config/test_faulty_config1.csv");

        });
    }

    @Test
    @DisplayName("throw exception, when data is blank")
    void test_03(){
        HashMap<String, String> expectedPairs = new HashMap<>();
        expectedPairs.put("localSrcFolder", "D:/testFolder/testsrc");
        String expected = "lol";

        Exception e = assertThrows(IncorrectDataFormatException.class, () -> {
            HashMap<String, String> pairs = LoadCsv.extractPairs("./src/test/resources/test_config/test_faulty_config2.csv");

        });
    }
    @Test
    @DisplayName("throw exception, when id is blank")
    void test_04(){
        HashMap<String, String> expectedPairs = new HashMap<>();
        expectedPairs.put("localSrcFolder", "D:/testFolder/testsrc");
        String expected = "lol";

        Exception e = assertThrows(IncorrectDataFormatException.class, () -> {
            HashMap<String, String> pairs = LoadCsv.extractPairs("./src/test/resources/test_config/test_faulty_config3.csv");

        });
    }

    @Test
    @DisplayName("throw exception, when id and data are blank")
    void test_05(){
        HashMap<String, String> expectedPairs = new HashMap<>();
        expectedPairs.put("localSrcFolder", "D:/testFolder/testsrc");
        String expected = "lol";

        Exception e = assertThrows(IncorrectDataFormatException.class, () -> {
            HashMap<String, String> pairs = LoadCsv.extractPairs("./src/test/resources/test_config/test_faulty_config4.csv");

        });
    }

    @Test
    @DisplayName("empty map for blank file")
    void test_06(){

        HashMap<String, String> pairs = LoadCsv.extractPairs("./src/test/resources/test_config/test_blank_config.csv");

        assertThat(pairs).isEmpty();
    }
}
