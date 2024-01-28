package de.neonique.persistence.identifier;

import de.neonique.persistence.csv.LoadCsv;
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

        return identifier;
    }


}
