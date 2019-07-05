package com.demo.commandpattern;

public class RestExcuter implements Excuter {

    private int time = 0;
    private Requester reqest;
    public RestExcuter(){

    }
    public RestExcuter(Requester reqest,int t) {
        this.reqest = reqest;
        time = t;
    }

    @Override
    public void excute() {
        reqest.rest(time);
    }
}
