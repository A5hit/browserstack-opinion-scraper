package org.browserstack.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileDownloader {
    public static void download(String urlStr, String outputPath) throws IOException {
        URL url = new URL(urlStr);
        try (InputStream in = url.openStream()) {
            Path output = Path.of(outputPath);
            Files.createDirectories(output.getParent());
            Files.copy(in, output);
        }
    }
}
