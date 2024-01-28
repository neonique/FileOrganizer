package de.neonique.persistence.source;

import de.neonique.persistence.config.ConfigManager;
import de.neonique.service.interfaces.SourceManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestSourceManagerFromFolder {

    SourceManager srcLoader;
    ConfigManager configManager;
    @BeforeEach
    void init(){
        configManager = null;
        srcLoader = null;
    }
    @Test
    @DisplayName("get default src path, when no custom path is configured")
    void test_01(){
        configManager = mock(ConfigManager.class);
        when(configManager.getLocalSrcFolder()).thenReturn(null);
        srcLoader = new SourceManagerFromFolder(configManager);
        String expectedPath = Paths.get(System.getProperty("user.home") + "/Desktop/").toString();

        String srcPath = srcLoader.getPath();

        assertThat(expectedPath).isEqualTo(srcPath);
    }

    @Test
    @DisplayName("get custom path if it is configured")
    void test_02(){
        String expectedPath = "./src/test/resources/test_source/";
        configManager = mock(ConfigManager.class);
        when(configManager.getLocalSrcFolder()).thenReturn(expectedPath);
        srcLoader = new SourceManagerFromFolder(configManager);

        String srcPath = srcLoader.getPath();

        assertThat(expectedPath).isEqualTo(srcPath);
    }

    @Test
    @DisplayName("get custom path target filenames, when there is no target")
    void test_03(){
        String path = "./src/test/resources/test_source/";
        configManager = mock(ConfigManager.class);
        when(configManager.getLocalSrcFolder()).thenReturn(path);
        srcLoader = new SourceManagerFromFolder(configManager);

        List<String> targetFilenames = srcLoader.getTargetFilenames();

        assertThat(targetFilenames).isEmpty();
    }

    @Test
    @DisplayName("get custom path target filenames, when there is a target")
    void test_04(){
        String path = "./src/test/resources/test_source/targets/";
        configManager = mock(ConfigManager.class);
        when(configManager.getLocalSrcFolder()).thenReturn(path);
        srcLoader = new SourceManagerFromFolder(configManager);

        List<String> targetFilenames = srcLoader.getTargetFilenames();

        assertThat(targetFilenames).containsExactly("_test_testData.txt");
    }

    @Test
    @DisplayName("when the path is invalid the default path is used")
    void test_05(){
        String targetPath = "./src/test/resources/test_source/notreal/";
        configManager = mock(ConfigManager.class);
        when(configManager.getLocalSrcFolder()).thenReturn(targetPath);
        srcLoader = new SourceManagerFromFolder(configManager);
        String defaultPath = Paths.get(System.getProperty("user.home") + "/Desktop/").toString();

        String srcPath = srcLoader.getPath();

        assertThat(defaultPath).isEqualTo(srcPath);

    }

}