package de.neonique.service;

/*Ein Programm um Dateien vom Desktop mit einer vorangeschrieben ID automatisch in Ordner zu sortieren.
Bsp.: _dnd_charsheet.pdf -> D:/Fun/DND/charsheet.pdf
* */

import de.neonique.persistence.config.ConfigManager;
import de.neonique.service.interfaces.IdentifierManager;
import de.neonique.persistence.identifier.IdentifierManagerFromCSV;
import de.neonique.service.interfaces.SourceManager;
import de.neonique.persistence.source.SourceManagerFromFolder;
import de.neonique.service.interfaces.FileMover;
import de.neonique.service.file_mover.FileMoverLocal;
import de.neonique.stereotypes.Root;

@Root
public class FileOrganizer {

    public static void main(String[] args) {
        //Enthält Konfigurationswerte wie Source Path
        ConfigManager configManager = new ConfigManager();

        //Identifier mit Destination Path werden geladen
        //Wird später durch UI eingefügt
        IdentifierManager idLoader = new IdentifierManagerFromCSV();
        //HashMap<String, String> identifier = idLoader.getIdentifier();

        //Source Path und possible IDs werden geladen
        //Wird später durch UI eingefügt
        SourceManager srcLoader = new SourceManagerFromFolder(configManager);
        //List<String> targetFiles = srcLoader.getTargetFilenames();


        //Bundling existing IDs and Moving them
        FileMover fileMover = new FileMoverLocal(idLoader, srcLoader);
        fileMover.moveAll();
    }
}