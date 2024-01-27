package de.neonique.persistence.source_loader;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SourceLoaderFromFolder implements SourceLoader {
    private Path folderPath;
    private Path configPath;
    private File folder;

    public SourceLoaderFromFolder(){
        this.configPath = Paths.get("./src/main/resources/config.csv");
        //Erst checken ob config einen Pfad hat, ansonsten standard dir speichern
        this.folderPath = Paths.get(System.getProperty("user.home") + "/Desktop/"); //Default Pfad in dem der SRCLoader suchen soll;
        this.folder = new File(folderPath.toString());
    }



    private List<String> getFilenames() {
        List<String> fileNames = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
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
        return folderPath;
    }
}
