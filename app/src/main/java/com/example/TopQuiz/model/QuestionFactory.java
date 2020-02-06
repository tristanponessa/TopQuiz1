package com.example.TopQuiz.model;

import android.content.Context;
import com.example.TopQuiz.R;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

import java.io.IOException;
import java.io.InputStream;

public class QuestionFactory {

    public static Wini dialogue;

    static {

            Context c = AppInfo.getContext();
            InputStream inputStream = c.getResources().openRawResource(R.raw.dialogue);
            try {
                dialogue = new Wini(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}

