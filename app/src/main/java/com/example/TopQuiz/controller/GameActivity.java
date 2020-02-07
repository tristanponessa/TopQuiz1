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

        //init for all
        LinearLayout parent_layout = findViewById(R.id.dynamicContainer);
        parent_layout.setOrientation(LinearLayout.VERTICAL);
        int h = LinearLayout.LayoutParams.FILL_PARENT;
        int w = LinearLayout.LayoutParams.FILL_PARENT;
        ViewGroup.LayoutParams zl = new LinearLayout.LayoutParams(h, w);
        parent_layout.setLayoutParams(zl);

        TextView t = create_dynamic_textview("n");

        Button b1 = create_dynamic_button("n");
        Button b2 = create_dynamic_button("n");

        parent_layout.addView(t);

        parent_layout.addView(b1);
        parent_layout.addView(b2);
        setContentView(parent_layout);

        /*
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
        /*if (parent_layout != null){

            parent_layout.addView(t);
            parent_layout.addView(x);

            /*for (Button b : bs)
                parent_layout.addView(b);
        }*/

    }

    LinearLayout layout_setup() {

        LinearLayout parent_layout = findViewById(R.id.dynamicContainer);
        parent_layout.setOrientation(LinearLayout.VERTICAL);
        int h = LinearLayout.LayoutParams.FILL_PARENT;
        int w = LinearLayout.LayoutParams.FILL_PARENT;
        ViewGroup.LayoutParams zl = new LinearLayout.LayoutParams(h, w);
        parent_layout.setLayoutParams(zl);
        return parent_layout;
    }

    //stock all widgets in a list
    /*
    void display_widgets(LinearLayout parent_layout) {
        parent_layout.addView(t);
        parent_layout.addView(q);
        setContentView(parent_layout);
    }*/

    private TextView create_dynamic_textview(String txt_msg) {

        TextView titleView = new TextView(this);
        int h = ViewGroup.LayoutParams.WRAP_CONTENT;
        int w = ViewGroup.LayoutParams.MATCH_PARENT;
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(w, h);
        params1.setMargins(0,50,0,200);
        titleView.setLayoutParams(params1);
        titleView.setGravity(Gravity.CENTER);
        titleView.setTextColor(Color.GREEN);
        titleView.setText("txt_msg");
        return titleView;
    }

    private Button create_dynamic_button(String txt_msg) {

        Button b = new Button(this);
        int h = ViewGroup.LayoutParams.WRAP_CONTENT;
        int w = ViewGroup.LayoutParams.MATCH_PARENT;
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(w, h);
        b.setLayoutParams(params1);
        b.setGravity(Gravity.CENTER);//corresponds ot text
        b.setText("txt_msg");
        return b;
    }

    /*private Button create_dynamic_button(String bt_msg) {

    }*/





}
