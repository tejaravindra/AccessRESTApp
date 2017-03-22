package com.example.tejaravindra.accessrestapp;

import android.graphics.Bitmap;
import android.widget.ListView;

/**
 * Created by tejaravindra on 2/27/17.
 */

public  class ListElements {

    private String name;
    private String phoneNumber;
    private Bitmap imageURL;

    public Bitmap getImageURL() {
        return imageURL;
    }

    public void setImageURL(Bitmap imageURL) {
        this.imageURL = imageURL;
    }








    ListElements(String name,String phoneNumber,Bitmap imageURL){

        this.name=name;
        this.phoneNumber=phoneNumber;
        this.imageURL=imageURL;

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
