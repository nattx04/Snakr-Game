package Runner2;

import java.awt.Color;
import java.awt.Graphics;

public class Item {
    private int x,y,width,height;
    
    public Item(int x,int y,int size){
        this.x = x;
        this.y = y;
        this.width = size;
        this.height = size;
    }
    public void draw(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(x*width, y*height, width, height);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }   
}
