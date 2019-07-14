package com.demo.commandpattern;

import com.demo.autoscript.ThreadAdbshell;

public class BackHomeExcuter implements Excuter {

    private Requester requester;
    private ThreadAdbshell threadAdbshell;

    public BackHomeExcuter() {
    }

    @Override
    public void excute() {
        if (threadAdbshell!=null) {
            threadAdbshell.setFullCom("input keyevent 3");
        }
    }
}
