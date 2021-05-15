package com.gri.alex;


import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileManagerTest {

    @Test
    void run() {
        // Given
        String dir = "data/";
        String text = "and";
        FileManager fileManager = new FileManager();

        // When
        List<String> files = fileManager.run(dir, text);

        // Then
        assertNotNull(files);
        assertEquals(8, files.size());
    }

    @Test
    void listFilesUsingFilesList() {
        // Given
        String dir = "data/";
        FileManager fileManager = new FileManager();

        // When
        List<File> filesList = fileManager.listFilesUsingFilesList(dir);

        // Then
        assertEquals(15, filesList.size());
    }

    @Test
    void getResource() {
        // Given
        String dir = "data/";
        FileManager fileManager = new FileManager();

        // When
        Optional<File> optResource = fileManager.getResource(dir);

        // Then
        assertTrue(optResource.isPresent());
    }
}
