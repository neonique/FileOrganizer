package de.neonique.persistence.source_loader;

import de.neonique.persistence.config_loader.ConfigLoader;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SourceLoaderFromFolder implements SourceLoader {

    private final String defaultSrcPath = Paths.get(System.getProperty("user.home") + "/Desktop/").toString();
    private ConfigLoader configLoader;
    private String srcPath;
    private File srcFolder;

    public SourceLoaderFromFolder(ConfigLoader configLoader){
        this.configLoader = configLoader;
        this.srcPath = getSrcPath();
        this.srcFolder = new File(srcPath);
    }

    private String getSrcPath() {

        //Laden eines Custom Paths aus config Datei
        String srcPath = configLoader.getLocalSrcFolder();
        if(srcPath != null){
            return srcPath;
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
