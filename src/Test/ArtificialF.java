package Test;

public class ArtificialF implements Runnable {
    HeroF F;
    Character A,B,C,D,E;

    public void delay(){
        try{
            Thread.sleep(20);
        }catch (InterruptedException e){}
    }
    public ArtificialF(HeroF F){
        this.F=F;
    }

    public void setHero(Character A,Character B,Character C,Character D,Character E){
        this.A=A;  this.B=B;  this.C=C;  this.D=D;  this.E=E;
    }
    @Override
    public void run() {
        while(true){
            if(F.Alive){
                int a=(int)(Math.random()*1800);
                int b=(int)(Math.random()*900);
                F.moveTo(a,b);
                while(F.x!=F.Movex || F.y!=F.Movey) {
                    delay();
                    F.moveBystep();
                }
            }
        }
    }
}

