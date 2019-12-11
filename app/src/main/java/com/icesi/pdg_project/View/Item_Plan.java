package com.icesi.pdg_project.View;

import java.io.Serializable;

public class Item_Plan implements Serializable {

    private String variable;

    private String symbol;

    private String value;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Item_Plan(String variable, String symbol, String value){
        this.variable=variable;
        this.symbol=symbol;
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
