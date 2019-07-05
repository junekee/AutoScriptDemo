package com.demo.commandpattern;

public class ClickExcuter implements Excuter {

    private Requester reqest;
    private int xoff = 0;
    private int yoff = 0;

    public ClickExcuter(Requester reqest,int x, int y) {
        this.reqest = reqest;
        xoff = x;
        yoff = y;

    }

    @Override
    public void excute() {
        reqest.click(xoff, yoff);
    }

}
