package de.neonique.persistence.source_loader;

import de.neonique.persistence.config_loader.ConfigLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestSourceLoaderFromFolder {

    SourceLoader srcLoader;
    ConfigLoader configLoader;
    @BeforeEach
    void init(){
        configLoader = mock(ConfigLoader.class);
        srcLoader = new SourceLoaderFromFolder(configLoader);
    }
    @Test
    @DisplayName("get default src path, when no custom path is configured")
    void test_01(){
        String expectedPath = Paths.get(System.getProperty("user.home") + "/Desktop/").toString();
        when(configLoader.getLocalSrcFolder()).thenReturn(null);

        String srcPath = srcLoader.getPath();

        assertThat(expectedPath).isEqualTo(srcPath);
    }
/*
    @Test
    @DisplayName("get custom path if it is configured")
    void test_02(){
        String expectedPath = "./src/test/resources/testSource/";
        when(configLoader.getLocalSrcFolder()).thenReturn(expectedPath);

        String srcPath = srcLoader.getPath();

        assertThat(expectedPath).isNotEqualTo(srcPath);
    }
*/
}