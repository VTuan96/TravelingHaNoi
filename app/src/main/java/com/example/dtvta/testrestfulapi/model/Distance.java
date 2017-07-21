package com.example.dtvta.testrestfulapi.model;

import java.io.Serializable;

/**
 * Created by vutuan on 19/07/2017.
 */

public class Distance implements Serializable {
    private int value;
    private String text;
    private boolean target;

    public Distance() {
    }

    public Distance(int value, String text) {
        this.value = value;
        this.text = text;

        if (value>=0&&value<=5000){
            this.target=true; //target is nearest us
        } else {
            this.target=false;
        }
    }

    public boolean isTarget() {
        return target;
    }

    public void setTarget(boolean target) {
        this.target = target;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text+"-"+value;
    }
}
