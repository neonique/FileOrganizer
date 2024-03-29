package de.neonique.persistence.identifier;

import de.neonique.exception.IncorrectFilePathException;
import de.neonique.persistence.csv.LoadCsv;
import de.neonique.service.interfaces.IdentifierManager;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class IdentifierManagerFromCSV implements IdentifierManager {
    private final String defaultIdentifierPath = "./src/main/resources/identifier.csv";
    private final String identifierPath;
    public IdentifierManagerFromCSV(){
        this.identifierPath = defaultIdentifierPath;
    }
    //For testing purposes
    IdentifierManagerFromCSV(String identifierPath){
        this.identifierPath = String.valueOf(identifierPath);   //for safety purposes
    }

    @Override
    public HashMap<String, String> loadIdentifier() {

        HashMap<String, String> identifier = new HashMap<>();
        identifier = LoadCsv.extractPairs(identifierPath);  //id-path-pairs

        validatePath(identifier);

        return identifier;
    }

    private void validatePath(HashMap<String, String> identifier) {
        for (String paths : identifier.values()) {
            if(!Files.exists(Path.of(paths))){
                throw new IncorrectFilePathException();
            }
        }
    }
}
