package com.example.dtvta.testrestfulapi.model;

import java.io.Serializable;

/**
 * Created by vutuan on 14/07/2017.
 */

public class Description implements Serializable {
    private int ID_DESCRIPTION, ID_DETAIL;
    private String IMAGE_DESCRIPTION, INTRODUCTION;

    public int getID_DESCRIPTION() {
        return ID_DESCRIPTION;
    }

    public void setID_DESCRIPTION(int ID_DESCRIPTION) {
        this.ID_DESCRIPTION = ID_DESCRIPTION;
    }

    public int getID_DETAIL() {
        return ID_DETAIL;
    }

    public void setID_DETAIL(int ID_DETAIL) {
        this.ID_DETAIL = ID_DETAIL;
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

    public Description(int ID_DETAIL, String IMAGE_DESCRIPTION, String INTRODUCTION) {

        this.ID_DETAIL = ID_DETAIL;
        this.IMAGE_DESCRIPTION = IMAGE_DESCRIPTION;
        this.INTRODUCTION = INTRODUCTION;
    }

    public Description(int ID_DESCRIPTION, int ID_DETAIL, String IMAGE_DESCRIPTION, String INTRODUCTION) {

        this.ID_DESCRIPTION = ID_DESCRIPTION;
        this.ID_DETAIL = ID_DETAIL;
        this.IMAGE_DESCRIPTION = IMAGE_DESCRIPTION;
        this.INTRODUCTION = INTRODUCTION;
    }
}
