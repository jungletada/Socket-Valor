package Test;

import java.awt.image.BufferedImage;

public class Character {
    public int x,y;
    public int Movex,Movey;
    public int HP,MP;
    public boolean Alive;
    public BufferedImage image;
    public boolean Stillmove;

    Character(int x1,int y1){
        x=x1;y=y1;
        HP=100;MP=100;
        Alive=true;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage(){
        return image;
    }

    /**
     * hero moves to x1,y1
     * @param x1
     * @param y1
     */
    public void moveTo(int x1,int y1){
        Stillmove=true;
        Movex=x1;
        Movey=y1;
    }

    /**
     * Calculate path to move
     */
    public void moveBystep(){
        if(x<Movex)
            x++;
        else if(x>Movex)
            x--;

        if(y<Movey)
            y++;
        else if (y>Movey)
            y--;

        if(x==Movex&&y==Movey)
            Stillmove=false;
    }
}
