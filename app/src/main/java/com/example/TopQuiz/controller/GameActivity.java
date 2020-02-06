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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameActivity extends AppCompatActivity {

    private int score;//retreive from userinfo class
    private int name;

    private ArrayList<Button> buttons;
    private Map<String, TextView> labels;
    private AlertDialog.Builder alert;

    private Button mPlayButton1, mPlayButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //mPlayButton1 = (Button) findViewById(R.id.activity_main_play_btn);
        //mPlayButton2 = (Button) findViewById(R.id.activity_main_play_btn);
        /*mPlayButton1 = new Button(this);

        mPlayButton1.setText("Push Me");

        LinearLayout ll = (LinearLayout)findViewById(R.id.layout);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ll.addView(mPlayButton1, lp);*/
        LinearLayout relativeLayout = findViewById(R.id.dynamicContainer);

        TextView t = create_dynamic_textview("The Question?");

        int nb_questions = 3;
       // Button button = create_dynamic_button("dynamic", t.getId());
        //Button button2 = create_dynamic_button("dynamic2");

        //int tid = t.getId();
        ArrayList<Button> bs = new ArrayList<Button>();

        String btag = "00";
        for (int i = 0; i < 3; i++) {
            bs.add(create_dynamic_button("dynamic" + i, i));

        }

        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GameActivity.this, "This button is created dynamically",
                        Toast.LENGTH_SHORT).show();
            }
        });*/
        if (relativeLayout != null){

            relativeLayout.addView(t);
            for (Button b : bs)
                relativeLayout.addView(b);
        }

    }

    private TextView create_dynamic_textview(String bt_msg) {
        TextView t = new TextView(this);

        int h = ViewGroup.LayoutParams.WRAP_CONTENT;
        int w = ViewGroup.LayoutParams.MATCH_PARENT;

        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(w, h);
        params1.setMargins(0,50,0,200);
        //params1.addRule(LinearLayout.ALIGN_PARENT_LEFT, LinearLayout.TRUE);
        int sec_id = ViewCompat.generateViewId();
        t.setId(sec_id);
        //t.setTag(sec_id, "zquest");
        t.setLayoutParams(params1);
        t.setGravity(Gravity.CENTER);
        t.setTextColor(Color.GREEN);
        t.setText(bt_msg);

        //t.setTextColor("gold");

        return t;
    }
//https://abhiandroid.com/ui/dynamic-relativelayout-params-programmatically.html
    private Button create_dynamic_button(String bt_msg, int last_bt_id) {
        Button button = new Button(this);
        //button.setTag(last_bt_id);

        //LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)button.getLayoutParams();
        /*layoutParams.addRule(LinearLayout.BELOW, button.getId());
        layoutParams.addRule(LinearLayout.ALIGN_LEFT, button.getId());
        button.setLayoutParams(layoutParams);*/

        int h = ViewGroup.LayoutParams.WRAP_CONTENT;
        int w = ViewGroup.LayoutParams.MATCH_PARENT;

        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(w, h);

        //TextView = this.findViewWithTag("zquest");
        //params1.addRule(LinearLayout.BELOW, last_bt_id);
        button.setLayoutParams(params1);
        button.setGravity(Gravity.CENTER);//corresponds ot text
        button.setText(bt_msg);


        return button;
    }
/*
    public void test() {
        Button button;
        TextView textView;
        LinearLayout relativeLayout;

        relativeLayout = findViewById(R.id.a);
        textView = findViewById(R.id.b);
        button = findViewById(R.id.c);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
                layoutParams.addRule(LinearLayout.BELOW, button.getId());
                layoutParams.addRule(LinearLayout.ALIGN_LEFT, button.getId());
                textView.setLayoutParams(layoutParams);
            }
        });*/
    //}

}
