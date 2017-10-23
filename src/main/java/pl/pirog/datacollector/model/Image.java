package pl.pirog.datacollector.model;

import com.google.common.io.Files;
import lombok.Getter;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Image {
    private final File file;
    @Getter
    private final String originalFileName;

    public Image(File file, String fileNameFromUrl) {
        this.file = file;
        this.originalFileName = fileNameFromUrl;
    }

    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(Files.toByteArray(this.file));
    }
}