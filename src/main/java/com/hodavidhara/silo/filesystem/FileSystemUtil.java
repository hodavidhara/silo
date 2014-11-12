package com.hodavidhara.silo.filesystem;

import javax.annotation.Nullable;
import java.io.*;
import java.nio.file.Paths;

/**
 *
 */
public class FileSystemUtil {

    public static void createDocument(String path, InputStream inputStream) {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(path);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            close(bufferedInputStream);
            close(fileOutputStream);
        }
    }

    public static void createDirectory(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public static void deleteFile(String path) {

        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }

    private static void close(@Nullable Closeable closable) {
        if (closable != null) {
            try {
                closable.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String buildFullPath(String path) {
        if (path.startsWith("/")) {

            // Already a full path, just return.
            return path;
        } else {

            // Relative path, prepend the current directory.
            return Paths.get(System.getProperty("user.dir"), path).toString();
        }
    }
}
