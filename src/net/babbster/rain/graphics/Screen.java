package net.babbster.rain.graphics;

/**
 * Created by jeff on 11/20/2016.
 */
public class Screen {

    private int width, height;
    public int[] pixels;

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }
}
