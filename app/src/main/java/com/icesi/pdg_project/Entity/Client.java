package com.icesi.pdg_project.Entity;

import java.io.Serializable;

public class Client implements Serializable {

    private String college;

    private int income;

    private int overage;

    private int leftover;

    private int house;

    private int handsetPrice;

    private int over15;

    private int averageCallDuration;

    private String reportedSatisfaction;

    private String reportedUsageLevel;


    public Client() {

    }

    public Client(String college, int income, int overage, int leftover, int house, int handsetPrice, int over15, int averageCallDuration, String reportedSatisfaction, String reportedUsageLevel) {
        this.college = college;
        this.income = income;
        this.overage = overage;
        this.leftover = leftover;
        this.house = house;
        this.handsetPrice = handsetPrice;
        this.over15 = over15;
        this.averageCallDuration = averageCallDuration;
        this.reportedSatisfaction = reportedSatisfaction;
        this.reportedUsageLevel = reportedUsageLevel;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getOverage() {
        return overage;
    }

    public void setOverage(int overage) {
        this.overage = overage;
    }

    public int getLeftover() {
        return leftover;
    }

    public void setLeftover(int leftover) {
        this.leftover = leftover;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public int getHandsetPrice() {
        return handsetPrice;
    }

    public void setHandsetPrice(int handsetPrice) {
        this.handsetPrice = handsetPrice;
    }

    public int getOver15() {
        return over15;
    }

    public void setOver15(int over15) {
        this.over15 = over15;
    }

    public int getAverageCallDuration() {
        return averageCallDuration;
    }

    public void setAverageCallDuration(int averageCallDuration) {
        this.averageCallDuration = averageCallDuration;
    }

    public String getReportedSatisfaction() {
        return reportedSatisfaction;
    }

    public void setReportedSatisfaction(String reportedSatisfaction) {
        this.reportedSatisfaction = reportedSatisfaction;
    }

    public String getReportedUsageLevel() {
        return reportedUsageLevel;
    }

    public void setReportedUsageLevel(String reportedUsageLevel) {
        this.reportedUsageLevel = reportedUsageLevel;
    }
}