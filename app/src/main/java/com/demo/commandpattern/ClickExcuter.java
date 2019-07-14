package com.demo.commandpattern;

import com.demo.autoscript.ThreadAdbshell;

public class ClickExcuter implements Excuter {

    private Requester request;
    private ThreadAdbshell shell;
    private int xoff = 0;
    private int yoff = 0;

    public ClickExcuter(Requester request,int x, int y) {
        this.request = request;
        xoff = x;
        yoff = y;
    }
    public ClickExcuter(ThreadAdbshell sh,int x, int y) {
        this.shell = sh;
        xoff = x;
        yoff = y;
    }

    @Override
    public void excute() {
        if (request!=null) {
            request.click(xoff, yoff);
        }
        if (shell!=null) {
            shell.delParam();
            shell.setX(xoff);
            shell.setY(yoff);
            shell.run();
        }
    }

}
