package com.example.dtvta.testrestfulapi.model;

import java.io.Serializable;

/**
 * Created by vutuan on 14/07/2017.
 */

public class Detail implements Serializable{
    private int ID_DETAIL, ID_DESCRIPTION;
    private String IMAGE_DETAIL, TITLE;

    public int getID_DETAIL() {
        return ID_DETAIL;
    }

    public void setID_DETAIL(int ID_DETAIL) {
        this.ID_DETAIL = ID_DETAIL;
    }

    public int getID_DESCRIPTION() {
        return ID_DESCRIPTION;
    }

    public void setID_DESCRIPTION(int ID_DESCRIPTION) {
        this.ID_DESCRIPTION = ID_DESCRIPTION;
    }

    public String getIMAGE_DETAIL() {
        return IMAGE_DETAIL;
    }

    public void setIMAGE_DETAIL(String IMAGE_DETAIL) {
        this.IMAGE_DETAIL = IMAGE_DETAIL;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public Detail(int ID_DESCRIPTION, String IMAGE_DETAIL, String TITLE) {

        this.ID_DESCRIPTION = ID_DESCRIPTION;
        this.IMAGE_DETAIL = IMAGE_DETAIL;
        this.TITLE = TITLE;
    }

    public Detail(int ID_DETAIL, int ID_DESCRIPTION, String IMAGE_DETAIL, String TITLE) {

        this.ID_DETAIL = ID_DETAIL;
        this.ID_DESCRIPTION = ID_DESCRIPTION;
        this.IMAGE_DETAIL = IMAGE_DETAIL;
        this.TITLE = TITLE;
    }
}
