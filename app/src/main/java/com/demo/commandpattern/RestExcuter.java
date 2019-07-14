package com.demo.commandpattern;

import com.demo.autoscript.ThreadAdbshell;

public class RestExcuter implements Excuter {

    private int time = 0;
    private Requester reqest;
    private ThreadAdbshell shell;
    public RestExcuter(){

    }
    public RestExcuter(Requester reqest,int t) {
        this.reqest = reqest;
        time = t;
    }

    public RestExcuter(ThreadAdbshell sh,int t) {
        this.shell = sh;
        time = t;
    }

    @Override
    public void excute() {
        if (reqest!=null) {
            reqest.rest(time);
        }
        if (shell!=null) {
            shell.delParam();
            shell.setDuration(time);
            shell.run();
        }
    }
}
