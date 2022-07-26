package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxWordCol][gp.maxWordRow];

        getTileImage();
        loadMap();
    }

    public void getTileImage(){

        try{

            tile[0]  = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
            tile[1]  = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[2]  = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tile[3]  = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
            tile[4]  = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tile[5]  = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));

        } catch (IOException e){
            e.printStackTrace();
        }

    }

   public void loadMap(){
        try {

            InputStream is = getClass().getResourceAsStream("/maps/world01.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWordCol && row < gp.maxWordRow){

                String line = br.readLine();

                while (col < gp.maxWordCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;

                }
                if(col == gp.maxWordCol){
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch (Exception e){
            e.printStackTrace();
        }
   }

    public void draw(Graphics2D g2){

//        g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[1].image, 48, 0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[2].image, 96, 0, gp.tileSize, gp.tileSize, null);

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWordCol && worldRow < gp.maxWordRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.wordX + gp.player.screenX;
            int screenY = worldY - gp.player.wordY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.wordX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.wordX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.wordY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.wordY + gp.player.screenY){

                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol++;

            if(worldCol == gp.maxWordCol){
               worldCol = 0;
               worldRow++;
           }
        }

    }


}
