package de.neonique.persistence.source_loader;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SourceLoaderFromFolder implements SourceLoader {
    private Path folderPath;
    private File folder;

    public SourceLoaderFromFolder(Path folderPath){
        this.folderPath = folderPath;
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
