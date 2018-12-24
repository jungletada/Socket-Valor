package Test;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Shell implements Runnable{
    public boolean StillExist;
    public int x,y,MoveX,MoveY;
    public boolean ToBoom;
    public boolean Arrive;
    BufferedImage shell;

    Shell(){
        x=0;y=0;
        MoveX=0;MoveY=0;
        StillExist =false;
        Arrive=false;
        ToBoom = false;
        try{
            shell=ImageIO.read(Shell.class.getResource("ball.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public BufferedImage getImage(){
        return shell;
    }

    public void delay() {
        try{
            Thread.sleep(20);
        }catch (InterruptedException e){}
    }

    public void setShell(int x,int y){
        this.x=x+10;
        this.y=y+10;
    }
    public void moveTo(int ToX,int ToY){
        MoveX=ToX+20;
        MoveY=ToY+15;
    }
    public void moveSteps(){
        if(x<MoveX)
            x+=2;
        else if(x>MoveX)
            x-=2;

        if(y<MoveY)
            y+=2;
        else if (y>MoveY)
            y-=2;
    }

    public void run(){
        int times=0;
        while (x!=MoveX || y!=MoveY){
            delay();
            moveSteps();
            times++;
            if(times>=800){
                StillExist = false;
                break;
            }
            int a=(x-MoveX) < 0 ? (MoveX-x):(x-MoveX);
            int b=(y-MoveY) < 0 ? (MoveY-y):(y-MoveY);
            if(a<=5 && b<=5)
                break;
        }
        StillExist = false;

        int a=(x-MoveX) < 0 ? (MoveX-x):(x-MoveX);
        int b=(y-MoveY) < 0 ? (MoveY-y):(y-MoveY);

        if(a<=5 && b<=5)
            Arrive = true;
    }
}
