package Test;

import java.io.IOException;
import javax.imageio.ImageIO;

public class Rocket extends Shell implements Runnable{
    Rocket(){
        x=0;y=0;
        MoveX=0;MoveY=0;
        StillExist =false;
        ToBoom = false;
        try{
            shell=ImageIO.read(Shell.class.getResource("water.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void moveSteps(){
        if(x<MoveX)
            x+=3;
        else if(x>MoveX)
            x-=3;

        if(y<MoveY)
            y+=3;
        else if (y>MoveY)
            y-=3;
    }
    public void run(){
        while (x!=MoveX || y!=MoveY){
            delay();
            moveSteps();
            int a=(x-MoveX) < 0 ? (MoveX-x):(x-MoveX);
            int b=(y-MoveY) < 0 ? (MoveY-y):(y-MoveY);
            if(a<=6 && b<=6)
                break;
        }
        StillExist = false;
        Arrive = true;
    }
}
