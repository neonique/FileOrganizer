package de.neonique.persistence.source;

import de.neonique.persistence.config.ConfigManager;
import de.neonique.service.interfaces.SourceManager;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SourceManagerFromFolder implements SourceManager {

    private final String defaultSrcPath = Paths.get(System.getProperty("user.home") + "/Desktop/").toString();
    private ConfigManager configManager;
    private String srcPath;
    private File srcFolder;

    public SourceManagerFromFolder(ConfigManager configManager){
        this.configManager = configManager;
        this.srcPath = getSrcPath();
        this.srcFolder = new File(srcPath);
    }

    private String getSrcPath() {

        //Laden eines Custom Paths aus config Datei
        String srcPath = configManager.getLocalSrcFolder();
        //Abgefragter Path muss existieren sonst default Path
        //Hier kann erstellung des vorgegebenen Pfades falls abwesend eingebunden werden
        if(srcPath != null && Files.exists(Paths.get(srcPath))){
            return srcPath;
        }
        return defaultSrcPath;
    }


    private List<String> getFilenames() {
        List<String> fileNames = new ArrayList<>();

        File[] files = srcFolder.listFiles();
        if(files == null){
            return Collections.emptyList();
        }

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
