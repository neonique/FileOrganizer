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
        IdentifierLoader idLoader = new IdentifierLoaderFromCSV();
        //HashMap<String, String> identifier = idLoader.getIdentifier();

        //Source Path und possible IDs werden geladen
        //Wird später durch UI eingefügt
        SourceLoader srcLoader = new SourceLoaderFromFolder();
        //List<String> targetFiles = srcLoader.getTargetFilenames();


        //Bundling existing IDs and Moving them
        FileMover fileMover = new FileMoverLocal(idLoader, srcLoader);
        fileMover.moveAll();
    }
}