package com.mistershorr.databases;

import android.os.Parcel;
import android.os.Parcelable;

public class Friend implements Parcelable,Comparable<Friend> {
    private int cumsiness;
    private double gymFrequency;
    private boolean isAwesome;
    private double moneyOwed;
    private String name;
    private int trustWorthiness;
    //backendless specific fields
    private String objectId;
    private String ownerId;
    public Friend(){}


    protected Friend(Parcel in) {
        cumsiness = in.readInt();
        gymFrequency = in.readDouble();
        isAwesome = in.readByte() != 0;
        moneyOwed = in.readDouble();
        name = in.readString();
        trustWorthiness = in.readInt();
        objectId = in.readString();
        ownerId = in.readString();
    }

    public static final Creator<Friend> CREATOR = new Creator<Friend>() {
        @Override
        public Friend createFromParcel(Parcel in) {
            return new Friend(in);
        }

        @Override
        public Friend[] newArray(int size) {
            return new Friend[size];
        }
    };

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
    public String getMoneyOwed() {
        return moneyOwed+"";
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
    public String getObjectId() {
        return objectId;
    }
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
    public String getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(cumsiness);
        parcel.writeDouble(gymFrequency);
        parcel.writeByte((byte) (isAwesome ? 1 : 0));
        parcel.writeDouble(moneyOwed);
        parcel.writeString(name);
        parcel.writeInt(trustWorthiness);
        parcel.writeString(objectId);
        parcel.writeString(ownerId);
    }

    @Override
    public int compareTo(Friend friend) {
        return 0;
    }
}
