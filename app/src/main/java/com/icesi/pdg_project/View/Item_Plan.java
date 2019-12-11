package com.icesi.pdg_project.View;

import java.io.Serializable;

public class Item_Plan implements Serializable {

    private String variable;

    private String value;

    public Item_Plan(String variable, String value){
        this.variable=variable;
        this.value=value;

    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
