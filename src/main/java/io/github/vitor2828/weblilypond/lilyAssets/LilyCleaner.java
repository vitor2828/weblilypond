package io.github.vitor2828.weblilypond.lilyAssets;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LilyCleaner {
    private static void deleteFile(String file) {
        Path lilyFilePath;
        try { // cleans codes previously used
            lilyFilePath = Paths.get("file");

            if (Files.exists(lilyFilePath)) {
                Files.delete(lilyFilePath);
                System.out.println("File deleted successfully");
            }

            else {
                System.out.println("File could not be found");
            }   
        }

        catch (IOException e) {
            System.out.println("Error while cleaning: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void clean() {
        deleteFile("src/main/java/io/github/vitor2828/weblilypond/lilyAssets/lilyCode.ly");
        deleteFile("target/classes/static/output/lilyCode.midi");
        deleteFile("target/classes/static/output/lilyCode.pdf");
    }
}
