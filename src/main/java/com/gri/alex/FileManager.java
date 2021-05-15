package com.gri.alex;


import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
public class FileManager {

    public List<String> run(String path, String text) {
        log.info("Search started...");
        List<String> filesWithText = new ArrayList<>();

        try {
            List<File> filesList = listFilesUsingFilesList(path);
            log.info("Found {} files in '{}'", filesList.size(), path);

            for (File file : filesList) {
                Stream<String> lines = Files.lines(file.toPath());
                String data = lines.collect(Collectors.joining("\n"));

                if (data.contains(text)) {
                    filesWithText.add(file.getName());
                    log.info("Found file: {}", file);
                }
                lines.close();

            }
            log.info("Found {} files with text '{}'", filesWithText.size(), text);

        } catch (IOException e) {
            log.error("Unable to find files in {}", path);
        }

        return filesWithText;
    }

    public List<File> listFilesUsingFilesList(String dir) {
        Optional<File> optResource = getResource(dir);
        List<File> files = new ArrayList<>();

        if (optResource.isPresent()) {
            try (Stream<Path> stream =
                         Files.walk(
                                 Paths.get(optResource.get().getAbsolutePath()))) {

                files = stream
                        .filter(Files::isRegularFile)
                        .map(Path::toFile)
                        .collect(Collectors.toList());

            } catch (IOException e) {
                log.error("Unable to read files in {}", dir);
            }
        }
        return files;
    }

    public Optional<File> getResource(String name) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(name);

        if (resource != null) {
            return Optional.of(new File(resource.getFile()));
        } else {
            return Optional.empty();
        }
    }

    public static void main(String[] args) {
        String dir = "data/";
        FileManager fileManager = new FileManager();

        fileManager.run(dir, "and");
    }
}
