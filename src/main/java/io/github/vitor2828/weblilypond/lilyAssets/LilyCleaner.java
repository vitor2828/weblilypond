package io.github.vitor2828.weblilypond.lilyAssets;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



public class LilyCleaner {

    public static String tmpdir = System.getProperty("java.io.tmpdir") + "/weblilypond";
    
    public static String lilyFilePath = tmpdir + "/lilyCode.ly";
    public static String lilyPdfPath = tmpdir + "/lilyCode.pdf";
    public static String lilyMidiPath = tmpdir + "/lilyCode.midi";

    private static void deleteFile(String path) {
        Path lilyPath;
        try { // cleans codes previously used
            lilyPath = Paths.get(path);
            
            if (Files.exists(lilyPath)) {
                Files.delete(lilyPath);
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
        deleteFile(lilyFilePath);
        deleteFile(lilyPdfPath);
        deleteFile(lilyMidiPath);
    }
}
