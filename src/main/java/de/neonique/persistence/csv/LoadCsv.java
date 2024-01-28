package de.neonique.persistence.csv;

import com.opencsv.CSVReader;
import de.neonique.exception.IncorrectDataFormatException;
import de.neonique.exception.IncorrectFilePathException;

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

            while ((record = csvReader.readNext()) != null) {

                String id = record[0];
                String path = record[1];
                if(id.isBlank() || path.isBlank()){
                    throw new IncorrectDataFormatException();
                }
                pairs.put(id, path);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IncorrectDataFormatException(e);
        } catch (IOException e) {
            throw new IncorrectFilePathException(e);
        }
        return pairs;
    }
}
