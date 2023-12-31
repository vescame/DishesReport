package org.example.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

abstract class DefaultFileReader {

    public final Scanner loadResource(String resourceName) {
        try {
            final File resource = getResourceFile(resourceName);
            return new Scanner(resource);
        } catch (FileNotFoundException | NullPointerException e) {
            throw new RuntimeException("could not read file " + resourceName);
        }
    }

    public final Collection<File> listFiles(String resourceName) {
        final File resourceDirectory = getResourceFile(resourceName);
        final File[] files = resourceDirectory.listFiles();
        if (resourceDirectory.isDirectory() && files != null) {
            return List.of(files);
        }
        return List.of();
    }

    private File getResourceFile(String resourceName) {
        try {
            URL resource = getClass().getClassLoader().getResource(resourceName);
            assert resource != null;
            return new File(resource.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
