package de.neonique.persistence.source_loader;

import de.neonique.persistence.config_loader.ConfigLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestSourceLoaderFromFolder {

    SourceLoader srcLoader;
    ConfigLoader configLoader;
    @BeforeEach
    void init(){
        configLoader = null;
        srcLoader = null;
    }
    @Test
    @DisplayName("get default src path, when no custom path is configured")
    void test_01(){
        configLoader = mock(ConfigLoader.class);
        when(configLoader.getLocalSrcFolder()).thenReturn(null);
        srcLoader = new SourceLoaderFromFolder(configLoader);
        String expectedPath = Paths.get(System.getProperty("user.home") + "/Desktop/").toString();

        String srcPath = srcLoader.getPath();

        assertThat(expectedPath).isEqualTo(srcPath);
    }

    @Test
    @DisplayName("get custom path if it is configured")
    void test_02(){
        String expectedPath = "./src/test/resources/testSource/";
        configLoader = mock(ConfigLoader.class);
        when(configLoader.getLocalSrcFolder()).thenReturn(expectedPath);
        srcLoader = new SourceLoaderFromFolder(configLoader);

        String srcPath = srcLoader.getPath();

        assertThat(expectedPath).isEqualTo(srcPath);
    }

    @Test
    @DisplayName("get custom path target filenames, when there is no target")
    void test_03(){
        String path = "./src/test/resources/testSource/";
        configLoader = mock(ConfigLoader.class);
        when(configLoader.getLocalSrcFolder()).thenReturn(path);
        srcLoader = new SourceLoaderFromFolder(configLoader);

        List<String> targetFilenames = srcLoader.getTargetFilenames();

        assertThat(targetFilenames).isEmpty();
    }

    @Test
    @DisplayName("get custom path target filenames, when there is a target")
    void test_04(){
        String path = "./src/test/resources/testSource/targets/";
        configLoader = mock(ConfigLoader.class);
        when(configLoader.getLocalSrcFolder()).thenReturn(path);
        srcLoader = new SourceLoaderFromFolder(configLoader);

        List<String> targetFilenames = srcLoader.getTargetFilenames();

        assertThat(targetFilenames).containsExactly("_test_testData.txt");
    }
}