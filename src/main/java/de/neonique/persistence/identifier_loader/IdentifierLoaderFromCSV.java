package de.neonique.persistence.identifier_loader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class IdentifierLoaderFromCSV implements IdentifierLoader {

    private Path filePath;
    public IdentifierLoaderFromCSV(){
        this.filePath = Paths.get("./src/main/resources/identifier.csv");
    }

    @Override
    public HashMap<String, String> getIdentifier() {
        List<String> lines;
        try {
            lines = Files.readAllLines(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        HashMap<String, String> identifier = new HashMap<>();

        try {
            for (String line : lines) {
                String id = line.substring(0, line.indexOf(","));
                String path = line.substring(line.indexOf(",") + 1);
                identifier.put(id, path);
            }
        } catch (StringIndexOutOfBoundsException e){
            throw new RuntimeException(e);
        }
        return identifier;
    }
}
