package com.example.dtvta.testrestfulapi.model;

import java.io.Serializable;

/**
 * Created by vutuan on 14/07/2017.
 */

public class TypeTravel implements Serializable {
    private int ID_TYPE;
    private String NAME_TYPE;

    public TypeTravel() {
    }

    public int getID_TYPE() {
        return ID_TYPE;
    }

    public void setID_TYPE(int ID_TYPE) {
        this.ID_TYPE = ID_TYPE;
    }

    public String getNAME_TYPE() {
        return NAME_TYPE;
    }

    public void setNAME_TYPE(String NAME_TYPE) {
        this.NAME_TYPE = NAME_TYPE;
    }

    public TypeTravel(String NAME_TYPE) {

        this.NAME_TYPE = NAME_TYPE;
    }

    public TypeTravel(int ID_TYPE, String NAME_TYPE) {

        this.ID_TYPE = ID_TYPE;
        this.NAME_TYPE = NAME_TYPE;
    }
}
