package com.example.TopQuiz.model;

import java.lang.Math;
import android.os.Parcelable;

import java.io.Serializable;

//serializable in order to pass it threw activites
public class UserInfo implements Serializable {

    private String mname;
    private int    mscore;

    public UserInfo() {}

    public UserInfo(String name, int score) {
        this.mname = name;
        this.mscore = score;
    }

    public void setName(String name) {
        this.mname = name;
    }

    public void setScore(int score) {
        this.mscore = score;
    }

    public void fluctuateScore(char sym, int n) {

        switch(sym) {
            case '+': this.mscore += n; break;
            case '-': this.mscore -= n; break;
            case '/': this.mscore *= n; break;
            case '*': this.mscore /= n; break;
        }
    }

    public String getName() {
        return this.mname;
    }

    public int getScore() {
        return this.mscore;
    }
}
