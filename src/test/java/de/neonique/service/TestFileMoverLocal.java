package de.neonique.service;

import de.neonique.persistence.identifier_loader.IdentifierLoader;
import de.neonique.persistence.source_loader.SourceLoader;
import de.neonique.service.file_mover.FileMover;
import de.neonique.service.file_mover.FileMoverLocal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class TestFileMoverLocal {
    FileMover fileMover;
    @BeforeEach
    void init(){
        IdentifierLoader idLoader = mock(IdentifierLoader.class);
        SourceLoader srcLoader = mock(SourceLoader.class);
        fileMover = new FileMoverLocal(idLoader, srcLoader);
    }
    @Test
    @DisplayName("moving a file")
    void test_01(){

    }
}
