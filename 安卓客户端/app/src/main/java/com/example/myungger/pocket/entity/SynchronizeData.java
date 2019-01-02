package com.example.myungger.pocket.entity;

import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class SynchronizeData extends LitePalSupport {
    private int id;
    private String phoneNumber;
    private Date synTime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getSynTime() {
        return synTime;
    }

    public void setSynTime(Date synTime) {
        this.synTime = synTime;
    }
}
