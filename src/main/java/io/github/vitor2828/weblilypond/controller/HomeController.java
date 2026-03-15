package io.github.vitor2828.weblilypond.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.vitor2828.weblilypond.lilyAssets.LilyCleaner;
import io.github.vitor2828.weblilypond.lilyAssets.LilyRunner;



@Controller
public class HomeController {

    
    @GetMapping(value = "/code", produces = "text/plain")
    @ResponseBody
    public String getCode() {
        File code = new File(LilyCleaner.lilyFilePath);

        String finalCode = "";
        
        try (Scanner reader = new Scanner(code)) {
            while(reader.hasNextLine()) {
                String data = reader.nextLine() + "\n";
                finalCode += data;
            }

            return finalCode;
            
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found");
            e.printStackTrace();
            return "Type your code here...";
        }   
    }

    @GetMapping("/pdf")
    public ResponseEntity<FileSystemResource> getPdf() { // response entity is essential in this case. Pdf files require a customized HTTP request
        Path pdfPath;
        FileSystemResource pdfFile = null;

        pdfPath = Paths.get(LilyCleaner.lilyPdfPath);

        if (Files.exists(pdfPath)) {
            pdfFile = new FileSystemResource(pdfPath);
            HttpHeaders headers = new HttpHeaders(); // creates headers for HTTTP request
            headers.add("Content-Disposition", "inline; filename=partitura.pdf"); // adding headers settings
            headers.setContentType(MediaType.APPLICATION_PDF); // sets content type for PDF configuration
            return new ResponseEntity<>(pdfFile, headers, HttpStatus.OK); // returns responsy entity with file, headers and HTTP code
        }

        else {
            System.out.println("PDF could not be found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/midi")
    public ResponseEntity<FileSystemResource> getMidi() {
        Path midiPath = Paths.get(LilyCleaner.lilyMidiPath);
        FileSystemResource midiFile = null;

        if (Files.exists(midiPath)) {
            midiFile = new FileSystemResource(midiPath);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=musica.midi");
            headers.setContentType(MediaType.parseMediaType("audio/midi"));
            return new ResponseEntity<>(midiFile, headers, HttpStatus.OK);
        }

        else {
            System.out.println("MIDI could not be found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping("/home")
    public String home() {
        LilyCleaner.clean();
        getCode();
        return "home";
    }

    @PostMapping("/compile")
    public String compile(@RequestParam String code) {
        try {
            FileWriter Writer = new FileWriter(LilyCleaner.lilyFilePath);

            Writer.write(code);
            Writer.close();

            System.out.println("The code has been saved");
        }

        catch (IOException e) {
            System.out.println("The code could not be saved");
            e.printStackTrace();
        }
        if (LilyRunner.run() == 1) {
            LilyCleaner.clean();
            return "compileError";
        }
        getPdf();
        getMidi();
        getCode();
        return "compileCopy";
    }

}
