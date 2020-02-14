package com.example.TopQuiz.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.TopQuiz.R;
import com.example.TopQuiz.model.DialogueFactory;
import com.example.TopQuiz.model.UserInfo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout        parent_layout;
    private ArrayList<Button>   choice_bts;
    private TextView            t;
    private boolean             mEnableTouch;

    private UserInfo player1;

    //iterator does not holld current
    private Iterator dialogue_it;
    private DialogueFactory.Dialogue current_dialogue;
    private int wrong_answer_count;

    //name it to determined toast pauses app while alive
    static class SingleToast {

        private static Toast mToast;//why static got from int

        public static void show(Context context, String text, int duration) {
            cancel();
            System.out.println("TOAST CANCEL");
            System.out.println(mToast);

            mToast = Toast.makeText(context, text, duration);
            mToast.show();
            System.out.println("TOAST created");
            System.out.println(mToast);
        }

        public static void cancel() {
            if (mToast != null) {
                mToast.cancel(); //destory wrong answer to right answer
                mToast = null;
            }
        }
    }


    /*protected void onSaveInstanceState(Bundle outState) {
        outState.put(Ids.BUNDLE_STATE_USER, player1);
        super.onSaveInstanceState(outState);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        this.layout_setup();
        this.mEnableTouch = true;

        this.dialogue_it = DialogueFactory.dialogues.iterator();

        run_dialogue();
    }

    private void run_dialogue() {

        this.mEnableTouch = true;
        if (this.dialogue_it.hasNext()) {

            this.current_dialogue = (DialogueFactory.Dialogue)this.dialogue_it.next();
            this.choice_bts = new ArrayList<>();
            this.create_the_dynamic_textview(this.current_dialogue.question);
            for (String choice : this.current_dialogue.choices) {
                Button b = this.create_dynamic_button(choice);
                this.choice_bts.add(b);
            }
            this.display_widgets();
        }
        else
            run_endgame_dialogue_box();
    }

    private boolean check_proposal(View v) {

        String choice_selected = (String)v.getTag();

        if (choice_selected == this.current_dialogue.answer) {

            wrong_answer_count = 0;
            SingleToast.show(GameActivity.this, "Correct...", Toast.LENGTH_SHORT);
            player1.fluctuateScore('+', 1);
            return true;

        } else {
            wrong_answer_count++;
            SingleToast.show(this, "Wrong answer!!!", Toast.LENGTH_SHORT);
            return false;
        }
    }

    //need to access current dialogue by "globals" class vars
    @Override
    public void onClick(View v) {

        //this.mEnableTouch = false;
        /*if (wrong_answer_count == 3) {
            wrong_answer_count = 0;
            mEnableTouch = false;
            SingleToast.show(this, "<cheater> wait 3 seconds", Toast.LENGTH_SHORT);

        }*/

        boolean x = check_proposal(v);
        if (x) {

            ArrayList<Runnable> ps = new ArrayList<>();
            ps.add(() -> delete_widgets());
            ps.add(() -> run_dialogue());
            run_processes(ps, 2100);
        }
        //this.mEnableTouch = true;
    }

    //controls button press
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnableTouch && super.dispatchTouchEvent(ev);
    }

    private void layout_setup() {

        this.parent_layout = findViewById(R.id.dynamicContainer);
        this.parent_layout.setOrientation(LinearLayout.VERTICAL);
        int h = LinearLayout.LayoutParams.FILL_PARENT;
        int w = LinearLayout.LayoutParams.FILL_PARENT;
        ViewGroup.LayoutParams zl = new LinearLayout.LayoutParams(h, w);
        this.parent_layout.setLayoutParams(zl);
    }

    //stock all widgets in a list

    private void display_widgets() {

        this.parent_layout.addView(t);
        for (Button b : this.choice_bts) {
            parent_layout.addView(b);
        }
        setContentView(this.parent_layout);
    }

    private void create_the_dynamic_textview(String txt_msg) {

        this.t = new TextView(this);

        LinearLayout.LayoutParams params = init_layout_params();
        params.setMargins(0,100,0,200);
        this.t.setLayoutParams(params);
        this.t.setGravity(Gravity.CENTER);
        this.t.setTextColor(Color.GREEN);
        this.t.setText(txt_msg);
    }

    private Button create_dynamic_button(String txt_msg) {

        Button b = new Button(this);

        LinearLayout.LayoutParams params = init_layout_params();
        b.setLayoutParams(params);
        b.setGravity(Gravity.CENTER);//corresponds ot text
        b.setTag(txt_msg);
        b.setText(txt_msg);
        b.setOnClickListener(this);

        return b;
    }

    private void delete_widgets() {
        this.parent_layout.removeAllViews();
    }

    private void run_endgame_dialogue_box() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Well done! " + player1.getName())
                .setMessage("Your score is " + player1.getScore())
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        send_data_to_MainActivity();
                        finish();
                    }
                })
                .create()
                .show();
    }

    private LinearLayout.LayoutParams init_layout_params() {

        int h = ViewGroup.LayoutParams.WRAP_CONTENT;
        int w = ViewGroup.LayoutParams.MATCH_PARENT;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w, h);
        return params;
    }

    private void run_processes(ArrayList<Runnable> ps, int ms) {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            for (Runnable p : ps)
                                p.run();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }, ms);
    }

    private void retreive_data_from_MainActivity() {

        Intent main_intent = getIntent();//returns intent that started this activity
        Bundle extras = main_intent.getExtras();
        player1 = (UserInfo) extras.get(Ids.BUNDLE_EXTRA_USER);

        System.out.println("RE from main" + this.player1.getName());
        System.out.println("RE from main" + this.player1.getScore());
    }

    private void send_data_to_MainActivity() {

        Intent data_intent = new Intent(GameActivity.this, MainActivity.class);
        data_intent.putExtra(Ids.BUNDLE_EXTRA_USER, player1);
        setResult(RESULT_OK, data_intent);

        System.out.println("SE from game" + this.player1.getName());
        System.out.println("SE from game" + this.player1.getScore());
    }

    @Override
    protected void onStart() {
        super.onStart();
        retreive_data_from_MainActivity();
        System.out.println("GameActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("GameActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.out.println("GameActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("GameActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("GameActivity::onDestroy()");
    }
}
