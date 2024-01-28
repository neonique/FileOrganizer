package de.neonique.service.file_mover;

import de.neonique.persistence.identifier.IdentifierManager;
import de.neonique.persistence.source.SourceManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
//Kann später durch FileMover ausgetauscht werden, der Lokales, sowie Cloud movement unterstützt
public class FileMoverLocal implements FileMover {
    private final IdentifierManager idLoader;
    private final SourceManager srcLoader;

    public FileMoverLocal(IdentifierManager idLoader, SourceManager srcLoader) {

        this.idLoader = idLoader;
        this.srcLoader = srcLoader;
    }

    @Override
    public void moveAll() {

        List<String> files = srcLoader.getTargetFilenames();
        files.forEach(this::move);
    }
    private void move(String file) {

        HashMap<String,String> identifierTable = idLoader.getIdentifier();
        String id = getIdentifierFromFilename(file);

        if(isIdentifierDefined(identifierTable, id)){
            String srcPath = srcLoader.getPath() + "/" + file;
            String destinationPath = identifierTable.get(id) + trimFilename(file);

            moveFile(srcPath, destinationPath);
        }
    }

    private boolean isIdentifierDefined(HashMap<String, String> identifier, String id) {
        return identifier.containsKey(id);
    }


    private static String trimFilename(String targetFile) {
        String trimmed = "";
        String [] splitted = targetFile.split("_");
        for(int i = 2; i < splitted.length; i++){
            trimmed += splitted[i];
            if(i < splitted.length - 1){
                trimmed += "_";
            }
        }
        return trimmed;
    }

    private static String getIdentifierFromFilename(String targetFile) {
        return targetFile.split("_")[1];
    }

    private static void moveFile(String src, String dest ) {
        Path result = null;

        System.out.println("Moving " + src + " to " + dest + " ...");

        try {
            result = Files.move(Paths.get(src), Paths.get(dest));
        } catch (IOException e) {
            System.out.println("Exception while moving file: " + e.getMessage());
        }
        if(result != null) {
            System.out.println("File moved successfully.");
        }else{
            System.out.println("File movement failed.");
        }
    }
}
