package com.gri.alex;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileManager {

    public List<String> run(String path, String text) {
        List<String> filesWithText = new ArrayList<>();

        try {
            List<File> filesList = listFilesUsingFilesList(path);
            System.out.println("Found " + filesList.size() + " files in '" + path + "'");

            for (File file : filesList) {
                Stream<String> lines = Files.lines(file.toPath());
                String data = lines.collect(Collectors.joining("\n"));

                if (data.contains(text)) {
                    filesWithText.add(file.getName());
                    System.out.println("Found file: " + file);
                }
                lines.close();

            }
            System.out.println("Found " + filesWithText.size() + " files with text '" + text + "'");

        } catch (IOException e) {
            System.out.println("Unable to find files in " + path);
        }

        return filesWithText;
    }

    public List<File> listFilesUsingFilesList(String dir) throws IOException {
        File resource = getResource(dir);

        try (Stream<Path> stream = Files.walk(Paths.get(resource.getAbsolutePath()))) {
            return stream
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        }
    }

    public File getResource(String name) {
        ClassLoader classLoader = getClass().getClassLoader();

        return new File(Objects.requireNonNull(classLoader.getResource(name)).getFile());
    }

    public static void main(String[] args) {
        String dir = "data/";
        FileManager fileManager = new FileManager();

        fileManager.run(dir, "and");
    }
}
