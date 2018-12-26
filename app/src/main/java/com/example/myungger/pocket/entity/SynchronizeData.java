package com.example.myungger.pocket.entity;

import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class SynchronizeData extends LitePalSupport {
    private int id;
    private int phoneNumber;
    private Date synTime;
    private Date downTime;

    public Date getDownTime() {
        return downTime;
    }

    public void setDownTime(Date downTime) {
        this.downTime = downTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getSynTime() {
        return synTime;
    }

    public void setSynTime(Date synTime) {
        this.synTime = synTime;
    }
}
