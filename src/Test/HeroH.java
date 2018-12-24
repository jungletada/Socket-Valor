package Test;

import javax.imageio.ImageIO;
import java.io.IOException;

public class HeroH extends Character implements Runnable{
    public Character hero;
    public boolean canAttack;
    public Shell ball;
    public final int dis=200*200;

    HeroH(int x1,int y1){
        super(x1,y1);
        canAttack=false;
        ball=new Shell();
        try{ ball.shell= ImageIO.read(Shell.class.getResource("ballc.png"));
        }catch (IOException e){ e.printStackTrace();}
    }

    public void setHero(Character hero) {
        this.hero=hero;
    }

    public int getDistance(){
        return (x-hero.x)*(x-hero.x)+(y-hero.y)*(y-hero.y);
    }

    @Override
    public void run() {
        while (Alive)
        {
            //System.out.println("attack");
            if(getDistance()<=dis){
                ball.setShell(x,y);
                ball.moveTo(hero.x,hero.y);
                ball.StillExist=true;
                new Thread(ball).start();
                try{
                    Thread.sleep(2100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
