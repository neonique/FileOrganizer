package de.neonique.persistence.source_loader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SourceLoaderFromFolder implements SourceLoader {

    private final Path configPath;
    private Path srcPath;
    private File srcFolder;

    public SourceLoaderFromFolder(){
        this.configPath = Paths.get("./src/main/resources/config.csv");
        this.srcPath = getSrcPath();
        this.srcFolder = new File(srcPath.toString());
    }

    private Path getSrcPath() {

        //Hier kann das Laden eines Custom Paths aus einer config Datei eingefügt werden

        return Paths.get(System.getProperty("user.home") + "/Desktop/"); //Default Pfad in dem der SRCLoader suchen soll;;
    }


    private List<String> getFilenames() {
        List<String> fileNames = new ArrayList<>();
        for (final File fileEntry : srcFolder.listFiles()) {
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
    public Path getPath() {
        return srcPath;
    }
}
