package Test;

public class ArtificialI implements Runnable {
    HeroI I;
    Character A,B,C,D,E;

    public void delay(){
        try{
            Thread.sleep(20);
        }catch (InterruptedException e){}
    }
    public ArtificialI(HeroI I){
        this.I=I;
    }

    public void setHero(Character A,Character B,Character C,Character D,Character E){
        this.A=A;  this.B=B;  this.C=C;  this.D=D;  this.E=E;
    }
    @Override
    public void run() {
        while(true){
            if(I.Alive){
                int a=(int)(Math.random()*1800);
                int b=(int)(Math.random()*900);
                I.moveTo(a,b);
                while(I.x!=I.Movex || I.y!=I.Movey) {
                    delay();
                    I.moveBystep();
                }
            }
        }
    }
}
