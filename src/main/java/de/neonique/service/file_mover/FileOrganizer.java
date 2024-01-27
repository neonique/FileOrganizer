package de.neonique.service.file_mover;

/*Ein Programm um Dateien vom Desktop mit einer vorangeschrieben ID automatisch in Ordner zu sortieren.
Bsp.: _dnd_charsheet.pdf -> D:/Fun/DND/charsheet.pdf
* */

import de.neonique.service.file_mover.FileMover;
import de.neonique.service.file_mover.FileMoverLocal;
import de.neonique.persistence.identifier_loader.IdentifierLoader;
import de.neonique.persistence.identifier_loader.IdentifierLoaderFromCSV;
import de.neonique.persistence.source_loader.SourceLoader;
import de.neonique.persistence.source_loader.SourceLoaderFromFolder;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileOrganizer {

    public static void main(String[] args) {

        //Identifier mit Destination Path werden geladen
        //Wird später durch UI eingefügt
        Path csvFilePath = Paths.get("./src/main/resources/identifier.csv"); //Pfad der Identifier
        IdentifierLoader idLoader = new IdentifierLoaderFromCSV(csvFilePath);
        //HashMap<String, String> identifier = idLoader.getIdentifier();

        //Source Path und possible IDs werden geladen
        //Wird später durch UI eingefügt
        Path desktopPath = Paths.get(System.getProperty("user.home") + "/Desktop/"); //Pfad in dem der SRCLoader suchen soll
        SourceLoader srcLoader = new SourceLoaderFromFolder(desktopPath);
        //List<String> targetFiles = srcLoader.getTargetFilenames();


        //Bundling existing IDs and Moving them
        FileMover fileMover = new FileMoverLocal(idLoader, srcLoader);
        fileMover.moveAll();
    }
}