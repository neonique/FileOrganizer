package de.neonique.persistence.source_loader;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SourceLoaderFromFolder implements SourceLoader {

    private final String configPath;
    private final String configId = "localSrcFolder";
    private final String defaultSrcPath = Paths.get(System.getProperty("user.home") + "/Desktop/").toString();
    private String srcPath;
    private File srcFolder;

    public SourceLoaderFromFolder(){
        this.configPath = "./src/main/resources/config.csv";
        this.srcPath = getSrcPath();
        this.srcFolder = new File(srcPath);
    }

    private String getSrcPath() {

        //Laden eines Custom Paths aus config Datei
        try {
            FileReader filereader = new FileReader(configPath);

            CSVReader csvReader = new CSVReader(filereader);
            String[] record;

            while ((record = csvReader.readNext()) != null) {
                if(record[0].equals(configId)){
                    return record[1];
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return defaultSrcPath;
    }


    private List<String> getFilenames() {
        List<String> fileNames = new ArrayList<>();

        File[] files = srcFolder.listFiles();

        for (final File fileEntry : files) {
            fileNames.add(fileEntry.getName());
        }
        return fileNames;
    }

    @Override
    public List<String> getTargetFilenames() {
        return getFilenames()
                .stream()
                .filter(s -> s.matches("_.*_.*"))
                .toList();
    }

    @Override
    public String getPath() {
        return srcPath;
    }
}
