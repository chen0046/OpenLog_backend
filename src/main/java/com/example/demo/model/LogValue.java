package com.example.demo.model;

public class LogValue {
    private int logValID;
    private double kulhydrat;
    private double insulin;
    private double blodsukker;
    private String timestamp;
    private int userID;

    public LogValue(double blodSukker, double kulhydrat, double insulin){
        this.blodsukker = blodSukker;
        this.kulhydrat = kulhydrat;
        this.insulin = insulin;

    }

    public int getLogValID() {
        return logValID;
    }

    public void setLogValID(int logValID) {
        this.logValID = logValID;
    }

    public double getKulhydrat() {
        return kulhydrat;
    }

    public void setKulhydrat(double kulhydrat) {
        this.kulhydrat = kulhydrat;
    }

    public double getInsulin() {
        return insulin;
    }

    public void setInsulin(double insulin) {
        this.insulin = insulin;
    }

    public double getBlodsukker() {
        return blodsukker;
    }

    public void setBlodsukker(double blodsukker) {
        this.blodsukker = blodsukker;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
