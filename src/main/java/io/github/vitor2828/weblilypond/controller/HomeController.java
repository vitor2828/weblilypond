package io.github.vitor2828.weblilypond.controller;

import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.vitor2828.weblilypond.lilyAssets.LilyCleaner;
import io.github.vitor2828.weblilypond.lilyAssets.LilyRunner;


@Controller
public class HomeController {
    
    @RequestMapping("/home")
    public String home() {
        LilyCleaner.clean();
        return "home";
    }

    @PostMapping("/compile")
    public String compile(@RequestParam String code) {
        try {
            FileWriter Writer = new FileWriter("src/main/java/io/github/vitor2828/weblilypond/lilyAssets/lilyCode.ly");

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
        return "compile";
    }
}
