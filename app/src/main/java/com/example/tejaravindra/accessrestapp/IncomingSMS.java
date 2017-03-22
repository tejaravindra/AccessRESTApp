package com.example.tejaravindra.accessrestapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by tejaravindra on 3/4/17.
 */

public class IncomingSMS extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("message is received");

    }

//    public static void main(String... args){
//        int bitmask = 0x000F;
//        int val = 0x2222;
//        // prints "2"
//        System.out.println(val & bitmask);
//
//    }
}
