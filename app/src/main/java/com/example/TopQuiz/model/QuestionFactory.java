package com.example.TopQuiz.model;

import android.content.Context;

import com.example.TopQuiz.R;

import org.ini4j.Wini;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;


public class QuestionFactory {

    public static Wini dialogue;

    static {
        //https://developer.android.com/training/data-storage/app-specific
        //https://developer.android.com/training/data-storage
        //https://www.dev2qa.com/android-get-application-context-from-anywhere-example/
        //String fpath = "C:/Users/Nanosense-dev02/Desktop/App1/app/src/main/res/raw/dialogue.ini";
        //AppInfo a = new AppInfo();
        //a.onCreate();
        //Context c = AppInfo.getContext();//contains all info to all activities, int data

        try {

            Context c = AppInfo.getContext();
            //File   f =  new File(context.getFilesDir(), "raw/dialogue.ini");
            //File   f = new File(fpath);

            //getResources().openRawResource(R.raw.dialogue)

            InputStream inputStream = c.getResources().openRawResource(R.raw.dialogue);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String sCurrentLine;
            while ((sCurrentLine = bufferedReader.readLine()) != null)
            {

                System.out.println(sCurrentLine);
            }
            //String[] words;
            //while (eachline != null) {
                // `the words in the file are separated by space`, so to get each words
                //String[] words = eachline.split(" ");
                //eachline = bufferedReader.readLine();

            //}



            try {
                dialogue = null;//new Wini(f);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

