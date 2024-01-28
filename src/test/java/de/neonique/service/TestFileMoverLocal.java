package de.neonique.service;

import de.neonique.service.interfaces.IdentifierManager;
import de.neonique.service.interfaces.SourceManager;
import de.neonique.service.file_mover.FileMover;
import de.neonique.service.file_mover.FileMoverLocal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

//Paths and ids are already verified, but it is important to test for external io exceptions
public class TestFileMoverLocal {
    FileMover fileMover;
    @BeforeEach
    void init(){
        IdentifierManager idLoader = mock(IdentifierManager.class);
        SourceManager srcLoader = mock(SourceManager.class);
        fileMover = new FileMoverLocal(idLoader, srcLoader);
    }
    @Test
    @DisplayName("test filename trimming")
    void test_01(){

    }
}
