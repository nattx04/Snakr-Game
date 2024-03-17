package Runner2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

class Scene extends JPanel implements Runnable{
    public static int WIDTH=800;
    public static int HEIGHT=600;
    private Thread th;
    private boolean running=false;
    private Body b;
    private ArrayList<Body>snakeArr;
    private int x=10,y=10;
    private int size=3;
    
    private boolean right=false,left=false,up=false,down=true;
    private int count=0;
    
    private Item i;
    private ArrayList<Item>itemArr;

public Scene(){
    setPreferredSize(new Dimension(WIDTH,HEIGHT));
    snakeArr=new ArrayList<>();
    itemArr=new ArrayList<>();

    addKeyListener(new KeyInner());
    setFocusable(true);

}  
public void startGame(){
    running=true;
    th=new Thread(this);
    th.start();
}
    @Override
    public void run() {
        while(running){
            tick();
            repaint();
        }       
    }

    private void tick() {
        if(snakeArr.size()==0){
            b=new Body(x,y,10);
            snakeArr.add(b);
        }
        if(itemArr.size()==0){
            int xitem=new Random().nextInt(60);
            int yitem=new Random().nextInt(60);
            i=new Item(xitem,yitem,10);
            itemArr.add(i);
        }
        for(int i=0;i<itemArr.size();i++){
            if(x==itemArr.get(i).getX()&& y==itemArr.get(i).getY()){
               size++; 
               itemArr.remove(i);
               i--;                
            }
        }
        for(int j=0;j<snakeArr.size();j++){
            if(x==snakeArr.get(j).getX()&& y==snakeArr.get(j).getY()){
                if(j!=snakeArr.size()-1){
                    stop();
                }
            }
        }
        if(x<0||x>79||y<0||y>79){
            stop();
        }
        count++;
        if(count>3700000){
            if(right){
                x++;           
            }
            if(left){
                x--;            
            }
            if(up){
                y--;            
            }
            if(down){
                y++;            
            } 
            count=0;
            b=new Body(x,y,10);
            snakeArr.add(b);
            if(snakeArr.size()>size){
                snakeArr.remove(0);
            }
        }            
    }
    @Override
    public void paint(Graphics g){
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.PINK);
        if(running==false){
            g.setColor(Color.MAGENTA);
            g.setFont(new Font("Angsana New",Font.BOLD,50));
            g.drawString("SNAKE GAME",WIDTH/2-110,HEIGHT/2);
            g.setColor(Color.PINK);
            g.setFont(new Font("Angsana New",Font.BOLD,30));
            g.drawString("Please Enter Spacebar To Start The Game.",WIDTH/2-175,HEIGHT/2+30);
        }else{
        for(int i=0; i<WIDTH/10; i++){
            g.drawLine(i*10,0,i*10,HEIGHT);
        }
        for(int i=0; i<HEIGHT/10; i++){
        g.drawLine(0,i*10,WIDTH,i*10);
    }
        for(int i=0;i<snakeArr.size();i++){
            snakeArr.get(i).draw(g);
        }
        for(int j=0;j<itemArr.size();j++){
           itemArr.get(j).draw(g);
        }
       }
   }

    private void stop() {
        running=false;
        try{
            th.join();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    class KeyInner implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); 
        }

        @Override
        public void keyPressed(KeyEvent k) {
            int key=k.getKeyCode();
            if(key==KeyEvent.VK_RIGHT){
                up=false;
                down=false;
                left=false;
                right=true;
            }
            if(key==KeyEvent.VK_LEFT){
                up=false;
                down=false;
                left=true;
                right=false;
            }
            if(key==KeyEvent.VK_UP){
                up=true;
                down=false;
                left=false;
                right=false;
            }
            if(key==KeyEvent.VK_DOWN){
                up=false;
                down=true;
                left=false;
                right=false;
            }
            if(key==KeyEvent.VK_SPACE){
                startGame();
            }
            
        }

        @Override
        public void keyReleased(KeyEvent e) {
            
        }
    }
           
}
