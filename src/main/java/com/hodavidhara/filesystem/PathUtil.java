package com.hodavidhara.filesystem;



import org.springframework.util.StringUtils;

import javax.annotation.Nullable;

/**
 *
 */
public class PathUtil {

    @Nullable
    public static String getParent(@Nullable String path) {
        if (path == null || path.isEmpty() || "/".equals(path)) {
            return null;
        }

        path = StringUtils.trimTrailingCharacter(path, '/');
        return path.substring(0, path.lastIndexOf("/") + 1);
    }

    @Nullable
    public static String getFilename(@Nullable String path) {
        if (path == null || path.isEmpty() || "/".equals(path)) {
            return null;
        }

        path = StringUtils.trimTrailingCharacter(path, '/');
        return path.substring(path.lastIndexOf("/") + 1);
    }
}
