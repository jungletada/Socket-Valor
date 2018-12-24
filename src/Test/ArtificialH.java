package Test;

public class ArtificialH implements Runnable{
    HeroH H;
    Character A,B,C,D,E;

    public void delay(){
        try{
            Thread.sleep(20);
        }catch (InterruptedException e){}
    }
    public ArtificialH(HeroH H){
        this.H=H;
    }

    public void setHero(Character A,Character B,Character C,Character D,Character E){
        this.A=A;  this.B=B;  this.C=C;  this.D=D;  this.E=E;
    }
    @Override
    public void run() {
        while(true){
            if(H.Alive){
                int a=(int)(Math.random()*1800);
                int b=(int)(Math.random()*900);
                H.moveTo(a,b);
                while(H.x!=H.Movex || H.y!=H.Movey) {
                    delay();
                    H.moveBystep();
                }
            }
        }
    }
}
