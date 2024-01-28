package de.neonique.service;

import de.neonique.persistence.config.ConfigManager;
import de.neonique.persistence.identifier.IdentifierManagerFromCSV;
import de.neonique.persistence.source.SourceManagerFromFolder;
import de.neonique.service.file_mover.FileMoverLocal;
import de.neonique.service.interfaces.FileMover;
import de.neonique.service.interfaces.IdentifierManager;
import de.neonique.service.interfaces.SourceManager;
import de.neonique.stereotypes.Root;

//This class looks superfluous, but will be used by controller/ui layer
@Root
public class ApplicationService {

    FileMover fileMover;
    public ApplicationService(){

    }

    public void moveAll(){

        //Enthält Konfigurationswerte wie Source Path
        ConfigManager configManager;
        configManager = new ConfigManager();

        //Identifier mit Destination Path werden geladen
        //Wird später durch UI eingefügt
        IdentifierManager idLoader;
        idLoader = new IdentifierManagerFromCSV();

        //Source Path und possible IDs werden geladen
        //Wird später durch UI eingefügt
        SourceManager srcLoader;
        srcLoader = new SourceManagerFromFolder(configManager);

        //Bundling existing IDs and Moving them
        FileMover fileMover;
        fileMover = new FileMoverLocal(idLoader, srcLoader);

        fileMover.moveAll();
    }
}
