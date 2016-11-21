/**
 * Created by jeff on 11/20/2016.
 * Rain game from TheCherno
 */

package net.babbster.rain;

import net.babbster.rain.graphics.Screen;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.*;

public class Game extends Canvas implements Runnable{

    private static final long serialVersionUID = 1L;
    //
    public static int width = 300;
    public static int height = width / 16 * 9;
    public static int scale = 3;

    private Thread thread;
    private JFrame frame;
    private boolean running = false;

    private Screen screen;

    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    public Game(){
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);

        screen = new Screen(width, height);

        frame = new JFrame();
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
            //logic
            update();
            //rendering
            render();
        }
    }

    public void update(){

    }

    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            //triple buffering
            createBufferStrategy(3);
            return;
        }

        screen.render();

        for(int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }

        //apply buffer to graphics object
        Graphics g = bs.getDrawGraphics();
        //all graphics here

        g.setColor(Color.CYAN);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

        //release resources
        g.dispose();

        bs.show();

    }

    public static void main(String[] args){
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle("Rain");

        //we can do this because the Game is a Canvas
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();
    }
}
