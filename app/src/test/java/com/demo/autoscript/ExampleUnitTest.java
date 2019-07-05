package com.demo.autoscript;

import com.demo.commandpattern.ClickExcuter;
import com.demo.commandpattern.CommandHandler;
import com.demo.commandpattern.Requester;
import com.demo.commandpattern.RestExcuter;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void run_command(){
        System.out.println("start");
        Requester requester = new Requester();
        CommandHandler ch = new CommandHandler();
        ch.addRequset(new ClickExcuter(requester,300,700));
        ch.addRequset(new RestExcuter(requester,3000));
        ch.addRequset(new ClickExcuter(requester,400,100));
        ch.addRequset(new ClickExcuter(requester,500,400));
        ch.addRequset(new RestExcuter(requester,4000));
        ch.addRequset(new ClickExcuter(requester,600,700));
        ch.addRequset(new ClickExcuter(requester,700,700));

        ch.handleRequest();
    }
}