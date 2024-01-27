package de.neonique.service.file_mover;

/*Ein Programm um Dateien vom Desktop mit einer vorangeschrieben ID automatisch in Ordner zu sortieren.
Bsp.: _dnd_charsheet.pdf -> D:/Fun/DND/charsheet.pdf
* */

import de.neonique.persistence.config_loader.ConfigLoader;
import de.neonique.persistence.identifier_loader.IdentifierLoader;
import de.neonique.persistence.identifier_loader.IdentifierLoaderFromCSV;
import de.neonique.persistence.source_loader.SourceLoader;
import de.neonique.persistence.source_loader.SourceLoaderFromFolder;

public class FileOrganizer {

    public static void main(String[] args) {
        //Enthält Konfigurationswerte wie Source Path
        ConfigLoader configLoader = new ConfigLoader();

        //Identifier mit Destination Path werden geladen
        //Wird später durch UI eingefügt
        IdentifierLoader idLoader = new IdentifierLoaderFromCSV();
        //HashMap<String, String> identifier = idLoader.getIdentifier();

        //Source Path und possible IDs werden geladen
        //Wird später durch UI eingefügt
        SourceLoader srcLoader = new SourceLoaderFromFolder(configLoader);
        //List<String> targetFiles = srcLoader.getTargetFilenames();


        //Bundling existing IDs and Moving them
        FileMover fileMover = new FileMoverLocal(idLoader, srcLoader);
        fileMover.moveAll();
    }
}