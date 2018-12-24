package Test;

public class ArtificialG implements Runnable {
    HeroG G;
    Character A,B,C,D,E;

    public void delay(){
        try{
            Thread.sleep(20);
        }catch (InterruptedException e){}
    }
    public ArtificialG(HeroG G){
        this.G=G;
    }

    public void setHero(Character A,Character B,Character C,Character D,Character E){
        this.A=A;  this.B=B;  this.C=C;  this.D=D;  this.E=E;
    }
    @Override
    public void run() {
        while(true){
            if(G.Alive){
                int a=(int)(Math.random()*1800);
                int b=(int)(Math.random()*900);
                G.moveTo(a,b);
                while(G.x!=G.Movex || G.y!=G.Movey) {
                    delay();
                    G.moveBystep();
                }
            }
        }
    }
}
