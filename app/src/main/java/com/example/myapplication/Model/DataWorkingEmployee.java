package com.example.myapplication.Model;

public class DataWorkingEmployee {
    private String WorkingDate;
    private int W;
    private int P;
    private int K;

    public String getWorkingDate() {
        return WorkingDate;
    }

    public void setWorkingDate(String workingDate) {
        WorkingDate = workingDate;
    }

    public int getW() {
        return W;
    }

    public void setW(int w) {
        W = w;
    }

    public int getP() {
        return P;
    }

    public void setP(int p) {
        P = p;
    }

    public int getK() {
        return K;
    }

    public void setK(int k) {
        K = k;
    }

    public int getOT1() {
        return OT1;
    }

    public void setOT1(int OT1) {
        this.OT1 = OT1;
    }

    public int getOT150() {
        return OT150;
    }

    public void setOT150(int OT150) {
        this.OT150 = OT150;
    }

    public int getOTNight() {
        return OTNight;
    }

    public void setOTNight(int OTNight) {
        this.OTNight = OTNight;
    }

    private int OT1;
    private int OT150;
    private int OTNight;
}
