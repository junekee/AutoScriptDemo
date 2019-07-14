package com.demo.commandpattern;

import com.demo.autoscript.ThreadAdbshell;

public class SwipeExcuter implements Excuter {
    private final ThreadAdbshell shell;
    private final String positionStr;

    public SwipeExcuter(ThreadAdbshell sh, String str) {
        this.shell = sh;
        this.positionStr = str;
    }
    @Override
    public void excute() {
        shell.delParam();
        shell.setComStr(positionStr);
        shell.run();
    }
}
