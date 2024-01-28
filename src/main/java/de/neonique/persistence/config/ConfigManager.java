package de.neonique.persistence.config;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ConfigManager {
    private final String configPath;
    private final HashMap<String,String> config;

    public ConfigManager(){
        this.configPath = "./src/main/resources/config.csv";
        this.config = loadConfig();
    }

    public String getLocalSrcFolder(){
        String configId = "localSrcFolder";
        String src = config.get(configId);
        return src != null ? String.valueOf(src): null;
    }
    private HashMap<String,String> loadConfig(){

        HashMap<String,String> config = new HashMap<>();

        try {
            FileReader filereader = new FileReader(configPath);

            CSVReader csvReader = new CSVReader(filereader);
            String[] record;

            while ((record = csvReader.readNext()) != null) {
                String id = record[0];
                String setting = record[1];
                config.put(id,setting);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return config;
    }
}
