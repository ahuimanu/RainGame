/**
 * Created by jeff on 11/20/2016.
 */

package net.babbster.net;

public class Game implements Runnable{
    public static int width = 300;
    public static int height = width / 16 * 9;
    public static int scale = 3;

    private Thread thread;

    public synchronized void start(){
        thread = new Thread(this, "Display");
        thread.start();
    }

    @Override
    public void run() {

    }
}
