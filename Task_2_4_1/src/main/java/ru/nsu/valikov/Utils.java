package ru.nsu.valikov;

import java.io.File;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Utils {

    void deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        directoryToBeDeleted.delete();
    }
}
