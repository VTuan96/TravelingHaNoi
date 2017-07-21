package com.example.dtvta.testrestfulapi.model;

import java.io.Serializable;

/**
 * Created by vutuan on 14/07/2017.
 */

public class Description implements Serializable {
    private int ID_DESCRIPTION;
    private String IMAGE_DESCRIPTION, INTRODUCTION;

    public Description() {
    }

    public int getID_DESCRIPTION() {

        return ID_DESCRIPTION;
    }

    public void setID_DESCRIPTION(int ID_DESCRIPTION) {
        this.ID_DESCRIPTION = ID_DESCRIPTION;
    }



    public String getIMAGE_DESCRIPTION() {
        return IMAGE_DESCRIPTION;
    }

    public void setIMAGE_DESCRIPTION(String IMAGE_DESCRIPTION) {
        this.IMAGE_DESCRIPTION = IMAGE_DESCRIPTION;
    }

    public String getINTRODUCTION() {
        return INTRODUCTION;
    }

    public void setINTRODUCTION(String INTRODUCTION) {
        this.INTRODUCTION = INTRODUCTION;
    }

    public Description(String IMAGE_DESCRIPTION, String INTRODUCTION) {

        this.IMAGE_DESCRIPTION = IMAGE_DESCRIPTION;
        this.INTRODUCTION = INTRODUCTION;
    }

    public Description(int ID_DESCRIPTION,String IMAGE_DESCRIPTION, String INTRODUCTION) {

        this.ID_DESCRIPTION = ID_DESCRIPTION;
        this.IMAGE_DESCRIPTION = IMAGE_DESCRIPTION;
        this.INTRODUCTION = INTRODUCTION;
    }
}
