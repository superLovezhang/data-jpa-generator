package com.superflower.generator.util;

import java.io.File;
import java.io.IOException;

public class FileUtils {

    public static boolean mkdirs(String packageDir) {
        String dirUrl = SqlParams.projectUrl.replaceAll("\\.", "/");
        if (packageDir != null) {
            dirUrl += packageDir;
        }
        File file = new File(SqlParams.rootUrl, dirUrl);
        file.mkdirs();
        return true;
    }

}
