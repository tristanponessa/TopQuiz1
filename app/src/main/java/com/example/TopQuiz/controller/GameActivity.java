package com.example.TopQuiz.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
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
import java.util.List;
import java.util.Map;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout        parent_layout;
    private ArrayList<Button>   choice_bts;
    private TextView            t;
    private AlertDialog.Builder alert;

    private UserInfo player1;

    private DialogueFactory.Dialogue current_dialogue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        this.layout_setup();

        this.current_dialogue = DialogueFactory.dialogues.get(0);
        this.choice_bts = new ArrayList<>();
        this.create_the_dynamic_textview(this.current_dialogue.question);
        for (String choice : this.current_dialogue.choices) {
            Button b = this.create_dynamic_button(choice);
            this.choice_bts.add(b);
        }

        this.display_widgets();
    }

    //need to access current dialogue by "globals" class vars
    @Override
    public void onClick(View v) {
        String choice_selected = (String)v.getTag();

        if (choice_selected == this.current_dialogue.answer) {
            // Good answer
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            this.delete_widgets();
            this.do_sleep(1000);
            player1.fluctuateScore('+', 1);

            this.dynamic_dialogue_box("y");


            //finish();

            //disactivate touch
            //mScore++;
        } else {
            // Wrong answer
            Toast.makeText(this, "Wrong answer!", Toast.LENGTH_SHORT).show();
        }
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

    private void dynamic_dialogue_box(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Well done!")
                .setMessage("Your score is " + "9999")
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

    private void do_sleep(final int ms) {

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(ms);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void retreive_data_from_MainActivity() {

        Intent main_intent = getIntent();
        Bundle extras = main_intent.getExtras();
        player1 = (UserInfo) extras.get(Ids.BUNDLE_EXTRA_USER);

        System.out.println("RE from main" + this.player1.getName());
        System.out.println("RE from main" + this.player1.getScore());
    }

    private void send_data_to_MainActivity() {

        Intent game_intent = new Intent();
        game_intent.putExtra(Ids.BUNDLE_EXTRA_USER, player1);
        setResult(RESULT_OK, game_intent);

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
