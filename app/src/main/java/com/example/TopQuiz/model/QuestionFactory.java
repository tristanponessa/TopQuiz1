package com.example.TopQuiz.model;

import org.ini4j.Wini;
import java.io.File;


public class QuestionFactory {

    public static Wini dialogue;

    static {

        String fpath = "C:/Users/Nanosense-dev02/Desktop/eclipse_workspace1/test/src/test/dialogue.ini";
        File   f = new File(fpath);

        try {
            dialogue = new Wini(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}