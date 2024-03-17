package Runner2;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;


public class Frame extends JFrame {
    public Frame(){
        setTitle("HYDRUS GAME");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(1,1,0,0));
        start();
        setVisible(true);       
    }
    public void start(){
        Scene sc= new Scene();
        add(sc);
        pack();
        setLocationRelativeTo(null);   
    }
    public static void main(String[]args){
        new Frame();
    }   
}

