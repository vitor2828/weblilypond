package io.github.vitor2828.weblilypond.lilyAssets;

import java.io.IOException; // handles io errors
import java.io.BufferedReader; // reads text efficiently
import java.io.InputStreamReader; // converts bytes into characters
import java.io.File; // represents system filepaths
import java.util.List; // useful for imutable lists of arguments

public class LilyRunner {

    public static void run() {
        List<String> cmd = List.of("lilypond", 
        "-o", "target/classes/static/output/lilyCode",
        "src/main/java/io/github/vitor2828/weblilypond/lilyAssets/lilyCode.ly"); // list that contais the command;
        
        ProcessBuilder lilypond = new ProcessBuilder(cmd); // creates a new process builder instance;
        lilypond.directory(new File(".")); // determines this folder as the working directory

        try {
            Process process = lilypond.start(); // executes the process itself

            try (BufferedReader output = new BufferedReader (new InputStreamReader(process.getInputStream()));
                 BufferedReader error = new BufferedReader (new InputStreamReader(process.getErrorStream()))) {

                // getInputStream() -> returns process' standard output in bytes
                // InputStreamReader() -> converts those bytes in chars, making it possible to read;
                // BufferedReader() -> adds a buffer and read it line per line

                String line;
                while ((line = output.readLine()) != null) { // prints all the lines in the buffer until it is over
                    System.out.println(line);
                }

                while ((line = error.readLine()) != null) { // does the same but for the error stream
                    System.out.println(line);
                }
            }

            int exitCode = process.waitFor(); // stops Java until the process end. Returns an exit code, useful for debugging

            if (exitCode == 0) System.out.println("The process has been finalized correctly!");
            else System.out.println("The process has been finalized with errors. Code: " + exitCode);


        } catch (IOException e) { // catches io exceptions and treats them
            System.err.println("The process could not be initialized");
            e.printStackTrace();

        } catch (InterruptedException e) { // catches interruptions and treats them 
            Thread.currentThread().interrupt();
            System.err.println("The execution has been interrupted");
        }
    } 
    
}
