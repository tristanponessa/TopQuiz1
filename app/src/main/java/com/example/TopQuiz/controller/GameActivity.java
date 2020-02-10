package com.example.TopQuiz.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.graphics.Color;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameActivity extends AppCompatActivity {

    private int score;//retreive from userinfo class
    private int name;

    private LinearLayout        parent_layout;
    private ArrayList<Button>   choice_bts;
    private TextView            t;
    private AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //init for all
        this.layout_setup();
        /*
        for (DialogueFactory.Dialogue d : DialogueFactory.dialogues) {

            this.create_the_dynamic_textview(d.question);

            for (String choice : d.choices) {

            }
        }*/

        DialogueFactory.Dialogue d = DialogueFactory.dialogues.get(0);
        this.choice_bts = new ArrayList<>();
        this.create_the_dynamic_textview(d.question);
        for (String choice : d.choices) {
            Button b = this.create_dynamic_button(choice);
            this.choice_bts.add(b);
        }


        //used fatsest queue possibkle to simply take out first
        /*this.choice_bts = new ArrayList<>();
        this.choice_bts.add(b1);
        this.choice_bts.add(b2);*/

        this.display_widgets();


        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GameActivity.this, "This button is created dynamically",
                        Toast.LENGTH_SHORT).show();
            }
        });*/

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
        b.setText(txt_msg);

        return b;
    }

    private LinearLayout.LayoutParams init_layout_params() {

        int h = ViewGroup.LayoutParams.WRAP_CONTENT;
        int w = ViewGroup.LayoutParams.MATCH_PARENT;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w, h);
        return params;
    }
}
