package Test;

import java.io.IOException;
import javax.imageio.ImageIO;

public class Star extends Shell implements Runnable {
    Star(){
        x=0;y=0;
        MoveX=0;MoveY=0;
        StillExist =false;
        ToBoom = false;
        try{
            shell=ImageIO.read(Shell.class.getResource("star.png"));
        }catch (IOException e){ e.printStackTrace();}
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
        while (x!=MoveX || y!=MoveY){
            delay();
            moveSteps();
            int a=(x-MoveX) < 0 ? (MoveX-x):(x-MoveX);
            int b=(y-MoveY) < 0 ? (MoveY-y):(y-MoveY);
            if(a<=5 && b<=5)
                break;
        }
        StillExist = false;
        Arrive = true;
    }
}
