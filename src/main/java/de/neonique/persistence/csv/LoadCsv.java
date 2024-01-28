package de.neonique.persistence.csv;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class LoadCsv {

    public static HashMap<String, String> extractPairs(String csvPath) {
        HashMap<String, String> pairs = new HashMap<>();

        try {
            FileReader filereader = new FileReader(csvPath);

            CSVReader csvReader = new CSVReader(filereader);
            String[] record;

            // we are going to read data line by line
            while ((record = csvReader.readNext()) != null) {
                String id = record[0];
                String path = record[1];
                pairs.put(id, path);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return pairs;
    }
}
