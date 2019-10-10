package com.nickb.spots.Utils;

import java.io.File;
import java.util.ArrayList;

public class FileSearch {

    public static ArrayList<String> getFilePaths(String directory) {
        ArrayList<String> fileArray = new ArrayList<>();
        File file = new File(directory);
        File[] listFiles = file.listFiles();
        for (int i = 0; i < listFiles.length; i++) {
            if (listFiles[i].isFile()) {
                fileArray.add(listFiles[i].getAbsolutePath());
            }
        }

        return fileArray;
    }
}
