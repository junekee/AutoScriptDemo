package com.demo.commandpattern;

import com.demo.autoscript.ThreadAdbshell;

public class PressKeyExcuter implements Excuter {

    private Requester requester;
    private ThreadAdbshell threadAdbshell;
    private int keyNum=0;

    public PressKeyExcuter(ThreadAdbshell shell,int kn) {
        this.keyNum=kn;
        this.threadAdbshell=shell;
    }

    @Override
    public void excute() {
        if (threadAdbshell!=null) {
            threadAdbshell.delParam();
            threadAdbshell.setFullCom("input keyevent "+keyNum);
            threadAdbshell.run();
        }
    }
}
