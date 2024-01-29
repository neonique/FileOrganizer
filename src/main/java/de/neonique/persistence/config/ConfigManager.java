package de.neonique.persistence.config;

import de.neonique.persistence.csv.LoadCsv;

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

        HashMap<String,String> config;

        config = LoadCsv.extractPairs(configPath);

        return config;
    }
}
