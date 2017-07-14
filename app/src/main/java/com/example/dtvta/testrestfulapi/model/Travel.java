package com.example.dtvta.testrestfulapi.model;

import java.io.Serializable;

/**
 * Created by vutuan on 14/07/2017.
 */

public class Travel implements Serializable {
    private int ID_TRAVEL, ID_TYPE, ID_DESCRIPTION;
    private float LATTITUDE, LONGTITUDE;
    private String NAME_TRAVEL;

    public Travel(int ID_TRAVEL, int ID_TYPE, int ID_DESCRIPTION, float LATTITUDE, float LONGTITUDE, String NAME_TRAVEL) {
        this.ID_TRAVEL = ID_TRAVEL;
        this.ID_TYPE = ID_TYPE;
        this.ID_DESCRIPTION = ID_DESCRIPTION;
        this.LATTITUDE = LATTITUDE;
        this.LONGTITUDE = LONGTITUDE;
        this.NAME_TRAVEL = NAME_TRAVEL;
    }

    public Travel(int ID_TYPE, int ID_DESCRIPTION, float LATTITUDE, float LONGTITUDE, String NAME_TRAVEL) {
        this.ID_TYPE = ID_TYPE;
        this.ID_DESCRIPTION = ID_DESCRIPTION;
        this.LATTITUDE = LATTITUDE;
        this.LONGTITUDE = LONGTITUDE;
        this.NAME_TRAVEL = NAME_TRAVEL;
    }

    public int getID_TRAVEL() {
        return ID_TRAVEL;
    }

    public void setID_TRAVEL(int ID_TRAVEL) {
        this.ID_TRAVEL = ID_TRAVEL;
    }

    public int getID_TYPE() {
        return ID_TYPE;
    }

    public void setID_TYPE(int ID_TYPE) {
        this.ID_TYPE = ID_TYPE;
    }

    public int getID_DESCRIPTION() {
        return ID_DESCRIPTION;
    }

    public void setID_DESCRIPTION(int ID_DESCRIPTION) {
        this.ID_DESCRIPTION = ID_DESCRIPTION;
    }

    public float getLATTITUDE() {
        return LATTITUDE;
    }

    public void setLATTITUDE(float LATTITUDE) {
        this.LATTITUDE = LATTITUDE;
    }

    public float getLONGTITUDE() {
        return LONGTITUDE;
    }

    public void setLONGTITUDE(float LONGTITUDE) {
        this.LONGTITUDE = LONGTITUDE;
    }

    public String getNAME_TRAVEL() {
        return NAME_TRAVEL;
    }

    public void setNAME_TRAVEL(String NAME_TRAVEL) {
        this.NAME_TRAVEL = NAME_TRAVEL;
    }
}
