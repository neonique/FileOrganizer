package de.neonique.persistence.identifier_loader;

import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class IdentifierLoaderFromCSV implements IdentifierLoader {

    private final String identifierPath;
    public IdentifierLoaderFromCSV(){
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
