package Test;
/**
 * @see Client ,Character
 */
public class Artificial implements Runnable{
    Character F,G,H,I,J;
    Artificial(Character F,Character G,Character H,Character I,Character J){
        this.F=F;  this.G=G;  this.H=H;  this.I=I;  this.J=J;
    }

    /**
     *  revive the characters
     */
    public void run(){
        if(F.Alive==false){
            System.out.println("F dead!");
            try{
                Thread.sleep(6000);
            }catch(InterruptedException e){}
            F.HP=100;
            F.Alive=true;
            System.out.println("F revives");
        }

        if(G.Alive==false){
            System.out.println("G dead!");
            try{
                Thread.sleep(6000);
            }catch(InterruptedException e){}
            G.HP=100;
            G.Alive=true;
            System.out.println("G revives");
        }

        if(H.Alive==false){
            System.out.println("H dead!");
            try{
                Thread.sleep(6000);
            }catch(InterruptedException e){}
            H.HP=100;
            H.Alive=true;
            System.out.println("H revives");
        }

        if(I.Alive==false){
            System.out.println("I dead!");
            try{
                Thread.sleep(6000);
            }catch(InterruptedException e){}
            I.HP=100;
            I.Alive=true;
            System.out.println("I revives");
        }

        if(J.Alive==false){
            System.out.println("J dead!");
            try{
                Thread.sleep(6000);
            }catch(InterruptedException e){}
            J.HP=100;
            J.Alive=true;
            System.out.println("J revives");
        }
    }

}
