package Test;

import javax.imageio.ImageIO;
import javax.swing.plaf.synth.SynthLookAndFeel;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Crystal implements Runnable {
    public int x,y;
    public final int dis=300*300;
    public final int delayTime=2100;
    public BufferedImage image;
    public Star star;
    public Character A,B,C,D,E;
    public int HP;
    public boolean stillExist,canAttack;

    Crystal(int x,int y){
        this.x=x; this.y=y;
        this.HP=3000;
        stillExist = true;
        canAttack = false;
        star=new Star();
        try{
            star.shell= ImageIO.read(Shell.class.getResource("crystar.png"));
        }catch (IOException e){ e.printStackTrace();}
    }
    public void setHero(Character A,Character B,Character C,Character D,Character E){
        this.A=A;  this.B=B;  this.C=C;  this.D=D;  this.E=E;
    }
    public int getDisA(){
        return (x-A.x)*(x-A.x)+(y-A.y)*(y-A.y);
    }
    public int getDisB(){
        return (x-B.x)*(x-B.x)+(y-B.y)*(y-B.y);
    }
    public int getDisC(){
        return (x-C.x)*(x-C.x)+(y-C.y)*(y-C.y);
    }
    public int getDisD(){
        return (x-D.x)*(x-D.x)+(y-D.y)*(y-D.y);
    }
    public int getDisE() { return (x-E.x)*(x-E.x)+(y-E.y)*(y-E.y); }
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    /*   */
    @Override
    public void run() {
        while (stillExist){
            //System.out.println("yes");
            if(getDisA()<=dis){
                star.setShell(x+45,y+60);
                star.moveTo(A.x,A.y);
                star.StillExist=true;
                new Thread(star).start();
                try{
                    Thread.sleep(delayTime);
                }catch (InterruptedException e){}
            }
            if(getDisB()<=dis){
                star.setShell(x+45,y+60);
                star.moveTo(B.x,B.y);
                star.StillExist=true;
                new Thread(star).start();
                try{
                    Thread.sleep(delayTime);
                }catch (InterruptedException e){}
            }
            if(getDisC()<=dis){
                star.setShell(x+45,y+60);
                star.moveTo(C.x,C.y);
                star.StillExist=true;
                new Thread(star).start();
                try{
                    Thread.sleep(delayTime);
                }catch (InterruptedException e){}
            }
            if(getDisD()<=300*300){
                star.setShell(x+45,y+60);
                star.moveTo(D.x,D.y);
                star.StillExist=true;
                new Thread(star).start();
                try{
                    Thread.sleep(delayTime);
                }catch (InterruptedException e){}
            }
            if(getDisE()<=dis){
                star.setShell(x+45,y+60);
                star.moveTo(E.x,E.y);
                star.StillExist=true;
                new Thread(star).start();
                try{
                    Thread.sleep(delayTime);
                }catch (InterruptedException e){}
            }
        }
    }
}
