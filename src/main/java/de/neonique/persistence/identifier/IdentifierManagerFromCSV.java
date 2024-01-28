package de.neonique.persistence.identifier;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class IdentifierManagerFromCSV implements IdentifierManager {

    private final String identifierPath;
    public IdentifierManagerFromCSV(){
        this.identifierPath = "./src/main/resources/identifier.csv";
    }

    @Override
    public HashMap<String, String> getIdentifier() {

        HashMap<String, String> identifier = new HashMap<>();

        try {
            FileReader filereader = new FileReader(identifierPath);

            CSVReader csvReader = new CSVReader(filereader);
            String[] record;

            // we are going to read data line by line
            while ((record = csvReader.readNext()) != null) {
                String id = record[0];
                String path = record[1];
                identifier.put(id, path);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return identifier;
    }
}
