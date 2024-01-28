package de.neonique.service.file_mover;

import de.neonique.service.interfaces.IdentifierManager;
import de.neonique.service.interfaces.SourceManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
//Using Static Methods and moving files in this class is bad practice
//FileMover should use a separate class in the persistence layer with interface implementation
//needs heavier refactoring
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

        HashMap<String,String> identifierTable = idLoader.loadIdentifier();
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
        StringBuilder trimmed = new StringBuilder();
        String [] splitted = targetFile.split("_");
        for(int i = 2; i < splitted.length; i++){
            trimmed.append(splitted[i]);
            if(i < splitted.length - 1){
                trimmed.append("_");
            }
        }
        return trimmed.toString();
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
