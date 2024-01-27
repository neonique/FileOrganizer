package de.neonique.source_loader;

import de.neonique.persistence.source_loader.SourceLoader;
import de.neonique.persistence.source_loader.SourceLoaderFromFolder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.mockito.Mockito.mock;

public class TestSourceLoaderFromFolder {

    static SourceLoader srcLoader;
    @BeforeAll
    static void init(){
        Path srcPath = mock(Path.class);
        srcLoader = new SourceLoaderFromFolder(srcPath);
    }
    @Test()
    @DisplayName("Test")
    void test_01(){

    }

}