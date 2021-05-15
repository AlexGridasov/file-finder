package com.gri.alex;


import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    void listFilesUsingFilesList() throws IOException {
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
        File resource = fileManager.getResource(dir);

        // Then
        assertNotNull(resource);
    }
}
