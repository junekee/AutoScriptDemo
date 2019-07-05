package com.demo.commandpattern;

import java.util.ArrayList;

public class CommandHandler {
    private static final String TAG="CommandHandler";

    private ArrayList<Excuter> excuters = new ArrayList<>();

    public void addRequset(Excuter exc){
        excuters.add(exc);
    }
    public void handleRequest(){
        for (Excuter e: excuters) {
            e.excute();
        }
        excuters.clear();
    }
}
