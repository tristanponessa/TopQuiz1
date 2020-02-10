package com.example.TopQuiz.model;

import android.content.Context;
import com.example.TopQuiz.R;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Profile.Section;
import org.ini4j.Wini;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class DialogueFactory {
    /**
     *      the goal of it being static
     *      imagine the game running activity being a live show
     *      the objects for the game are ready to be used question, choices, answers
     *      they come from the QuestionFactory object
     *      it must be created before the show is launched
     *      so it must auto create itself
     *
     *      1.loads form ini file to collection
     *      2.create queue list of objects
     *      3.shuffle
     *
     *      TO CHANGE
     *      the inner class diloague must be private
     *      dialogues/choices must be a collection read only, we init them with arraylists
     *
     *      problems
     *      need public to access in game activity
     *      need static to be used by other statics
     *
     */
    public static ArrayList<Dialogue> dialogues;
    //a dialogue is the action of one quest/aswer
    public static class Dialogue {

        public String            question;
        public ArrayList<String> choices;//find another collection , must be inmutable
        public String            answer;
    }

    static {

            dialogues = new ArrayList<>();
            Wini dialogue_stream = null;
            Context c = AppInfo.getContext();
            InputStream inputStream = c.getResources().openRawResource(R.raw.dialogue);

            try {
                //i call it a stream cause you gotta sort/convert/catch like intstream
                dialogue_stream = new Wini(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(-1);
            }

            //questions = question_shuffle(QuestionFactory.dialogue);
            for (Map.Entry<String, Section> entry : dialogue_stream.entrySet()) {
                String question = entry.getKey();
                Section s = entry.getValue();

                Dialogue d = new Dialogue();
                d.choices = new ArrayList<>();

                d.question = question;
                for (Map.Entry<String, String> sentry : s.entrySet()) {
                    String choice = sentry.getKey();
                    String is_answer = sentry.getValue();

                    if (is_answer.equals("Answer"))
                        d.answer = choice;

                    d.choices.add(choice);
                }
                dialogues.add(d);
            }
            Collections.shuffle(dialogues);
    }
}

