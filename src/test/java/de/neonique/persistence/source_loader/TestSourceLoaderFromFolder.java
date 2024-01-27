package de.neonique.persistence.source_loader;

import de.neonique.persistence.config_loader.ConfigLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class TestSourceLoaderFromFolder {

    SourceLoader srcLoader;
    @BeforeEach
    void init(){
        srcLoader = new SourceLoaderFromFolder(mock(ConfigLoader.class));
    }
    @Test()
    @DisplayName("get default src path, when no custom path is configured")
    void test_01(){
        String expectedPath = Paths.get(System.getProperty("user.home") + "/Desktop/").toString();

        String srcPath = srcLoader.getPath();

        assertThat(expectedPath).isEqualTo(srcPath);
    }
/*
    @Test()
    @DisplayName("get custom path if it is configured")
    void test_02(){
        String expectedPath = Paths.get(System.getProperty("user.home") + "/Desktop/").toString();

    }
*/
}