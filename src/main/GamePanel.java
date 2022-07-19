package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTTINGS

    final int originalTileSize  = 16; // 16x16 tile
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenHow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenHow; // 576 pixels

    Thread gameThread;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        while(gameThread != null){

            // TEST PROCESS LOPP IN THE GAME
            // System.out.println("The game loop is run ");

            // 1 UPDATE: update information such as character positions
            update();

            // 2 DRAW: draw the screen with the update information
            repaint();
        }
    }

    public void update(){

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = ((Graphics2D)g);

        g2.setColor(Color.white);
        g2.fillRect(100, 100, tileSize, tileSize);

        g2.dispose();
    }
}
