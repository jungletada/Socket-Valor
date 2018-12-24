package Test;

public class HeroJ extends Character implements Runnable {
    public Character hero;
    public boolean canAttack;

    HeroJ(int x1,int y1){
        super(x1,y1);
        canAttack=false;
    }
    public void setHero(Character hero) {
        this.hero=hero;
    }

    public void delay(){
        try{
            Thread.sleep(20);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true){
            int a=(int)(Math.random()*1800);
            int b=(int)(Math.random()*900);
            moveTo(a,b);
            while(x!=Movex || y!=Movey) {
                delay();
                moveBystep();
            }
        }
    }
}
