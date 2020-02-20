package com.mistershorr.databases;

public class Friend {
    private int cumsiness;
    private double gymFrequency;
    private boolean isAwesome;
    private double moneyOwed;
    private String name;
    private int trustWorthiness;
    public Friend(){}






    //Getters and Setters
    public int getTrustWorthiness() {
        return trustWorthiness;
    }
    public void setTrustWorthiness(int trustWorthiness) {
        this.trustWorthiness = trustWorthiness;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getMoneyOwed() {
        return moneyOwed;
    }
    public void setMoneyOwed(double moneyOwed) {
        this.moneyOwed = moneyOwed;
    }
    public boolean isAwesome() {
        return isAwesome;
    }
    public void setAwesome(boolean awesome) {
        isAwesome = awesome;
    }
    public double getGymFrequency() {
        return gymFrequency;
    }
    public void setGymFrequency(double gymFrequency) {
        this.gymFrequency = gymFrequency;
    }
    public int getCumsiness() {
        return cumsiness;
    }
    public void setCumsiness(int cumsiness) {
        this.cumsiness = cumsiness;
    }
}
