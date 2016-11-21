/**
 * Created by jeff on 11/20/2016.
 * Rain game from TheCherno
 */

package net.babbster.net;

import java.awt.*;
import javax.swing.*;

public class Game extends Canvas implements Runnable{
    public static int width = 300;
    public static int height = width / 16 * 9;
    public static int scale = 3;

    private Thread thread;
    private JFrame frame;
    private boolean running = false;

    public Game(){
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);
    }

    public synchronized void start(){
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop(){
        try {
            thread.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(running){
            //game loop
        }
    }
}
