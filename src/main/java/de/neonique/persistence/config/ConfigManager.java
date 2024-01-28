package de.neonique.persistence.config;

import com.opencsv.CSVReader;
import de.neonique.persistence.csv.LoadCsv;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ConfigManager {
    private final String defaultConfigPath = "./src/main/resources/config.csv";
    private final String configPath;
    private final HashMap<String,String> config;

    public ConfigManager(){
        this.configPath = defaultConfigPath;
        this.config = loadConfig();
    }

    public String getLocalSrcFolder(){
        String configId = "localSrcFolder";
        String src = config.get(configId);
        return src != null ? String.valueOf(src): null;
    }
    private HashMap<String,String> loadConfig(){

        HashMap<String,String> config = new HashMap<>();

        config = LoadCsv.extractPairs(configPath);

        return config;
    }
}
