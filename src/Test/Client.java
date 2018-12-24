package Test;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.io.*;

import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import org.apache.log4j.Logger;


public class Client extends JPanel implements ActionListener{

   public static final int WIDTH=1920;
   public static final int HEIGHT=1080;

   public BufferedImage heroA; public BufferedImage heroB;
   public BufferedImage heroC; public BufferedImage heroD;
   public BufferedImage heroE; public BufferedImage heroF;
   public BufferedImage heroG; public BufferedImage heroH;
   public BufferedImage heroI; public BufferedImage heroJ;
   public BufferedImage victory;

   public BufferedImage background; public BufferedImage Diamond;

   public static final String fileName="D:\\java_test\\static valor\\src\\Test\\Recording.txt";

   public int current, choose, toAttack, beAttacked;
   public int act, pos;
   public String record, Order, player;
   public boolean win, attackCry,rec;

   public Timer timer;
   public Artificial Revive;
   public ArtificialF AI_Fattack;
   public ArtificialG AI_Gattack;
   public ArtificialH AI_Hattack;
   public ArtificialI AI_Iattack;

   public HeroA A;public HeroB B;
   public HeroC C;public HeroD D;
   public HeroE E;public HeroF F;
   public HeroG G;public HeroH H;
   public HeroJ J;public HeroI I;

   public Crystal diamond1, diamond2;
   public Crystal diamond3, diamond4;

   public int []px=new int [10];
   public int []py=new int [10];

   public Shell egg;
   public Rocket rocket;
   public Star star;

   public Client(){
       try{
           Order="Hero send successfully";
           background=ImageIO.read(Client.class.getResource("map.jpg"));
           Diamond=ImageIO.read(Client.class.getResource("crystal.png"));
           victory=ImageIO.read(Client.class.getResource("victory.png"));

           heroA=ImageIO.read(Client.class.getResource("1.png"));
           heroB=ImageIO.read(Client.class.getResource("2.png"));
           heroC=ImageIO.read(Client.class.getResource("3.png"));
           heroD=ImageIO.read(Client.class.getResource("4.png"));
           heroE=ImageIO.read(Client.class.getResource("5.png"));
           heroF=ImageIO.read(Client.class.getResource("6.jpg"));
           heroG=ImageIO.read(Client.class.getResource("7.jpg"));
           heroH=ImageIO.read(Client.class.getResource("8.jpg"));
           heroI=ImageIO.read(Client.class.getResource("9.jpg"));
           heroJ=ImageIO.read(Client.class.getResource("10.jpg"));

           A=new HeroA(0,0);  B=new HeroB(60,0);
           C=new HeroC(120,0);D=new HeroD(180,0);
           E=new HeroE(240,0);F=new HeroF(1500,900);
           G=new HeroG(1580,900);H=new HeroH(1660,900);
           I=new HeroI(1740,900);J=new HeroJ(1820,900);

           diamond1 =new Crystal(680,150);  diamond2 =new Crystal(350,600);
           diamond3 =new Crystal(1000,750); diamond4 =new Crystal(1400,400);

           A.setImage(heroA);B.setImage(heroB); C.setImage(heroC);
           D.setImage(heroD);E.setImage(heroE); F.setImage(heroF);
           G.setImage(heroG);H.setImage(heroH); I.setImage(heroI);
           J.setImage(heroJ);

           diamond1.setImage(Diamond);  diamond2.setImage(Diamond);
           diamond3.setImage(Diamond);  diamond4.setImage(Diamond);
           egg =new Shell(); rocket=new Rocket(); star=new Star();
           Revive =new Artificial(F,G,H,I,J);
           AI_Fattack =new ArtificialF(F);
           AI_Gattack =new ArtificialG(G);
           AI_Hattack =new ArtificialH(H);
           AI_Iattack =new ArtificialI(I);
           AI_Fattack.setHero(A,B,C,D,E);

           beAttacked=6; toAttack=6; record="Game starts!";
           diamond1.setHero(A,B,C,D,E);   diamond2.setHero(A,B,C,D,E);
           diamond3.setHero(A,B,C,D,E);   diamond4.setHero(A,B,C,D,E);
           attackCry=false;   win=false;  rec=false;
       }
       catch(IOException e){e.printStackTrace();}
   }
    /**
     *
     * @param g
     */
   public void paint(Graphics g){
       try{
           Thread.sleep(50);
       }catch (Exception e){}
       switch (choose){
           case 7:try{
               background=ImageIO.read(Client.class.getResource("map.jpg"));
           }catch (IOException e){} break;
           case 8:try{
               background=ImageIO.read(Client.class.getResource("map_2.jpg"));
           }catch (IOException e){} break;
           case 9:try{
               background=ImageIO.read(Client.class.getResource("map_3.jpg"));
           }catch (IOException e){} break;

           default: break;
       }
       g.drawImage(background,0,0,null);
       if(win){
           g.drawImage(victory,0,0,null);
       }
       paintHero(g);
       paintAHero(g);
       paintCrystal(g);
       paintShell(g);
   }
    /**
     * @param g
     */
   public void paintShell(Graphics g){
       if(egg.StillExist){
           g.drawImage(egg.getImage(), egg.x, egg.y,null);
       }
       if(rocket.StillExist){
           g.drawImage(rocket.getImage(),rocket.x,rocket.y,null);
       }
       if(star.StillExist){
           g.drawImage(star.getImage(),star.x,star.y,null);
       }
       if(egg.ToBoom){
           egg.ToBoom=false;
       }
   }

    /**
     *
     * @param g
     */
   public void paintAHero(Graphics g){
       if(F.Alive){
           g.drawImage(F.getImage(),F.x,F.y,null);
           if(F.ball.StillExist){
               g.drawImage(F.ball.shell,F.ball.x,F.ball.y,null);
           }
       }
       if(G.Alive){
           g.drawImage(G.getImage(),G.x,G.y,null);
           if(G.ball.StillExist){
               g.drawImage(G.ball.shell,G.ball.x,G.ball.y,null);
           }
       }
       if(H.Alive){
           g.drawImage(H.getImage(),H.x,H.y,null);
           if(H.ball.StillExist){
               g.drawImage(H.ball.shell,H.ball.x,H.ball.y,null);
           }
       }
       if(I.Alive){
           g.drawImage(I.getImage(),I.x,I.y,null);
           if(I.ball.StillExist){
               g.drawImage(I.ball.shell,I.ball.x,I.ball.y,null);
           }
       }
       if(J.Alive){
           g.drawImage(J.getImage(),J.x,J.y,null);
       }
   }

   public void paintHero(Graphics g){
       if(A.Alive){
           g.drawImage(A.getImage(),A.x,A.y,null);
       }
       if(B.Alive){
           g.drawImage(B.getImage(),B.x,B.y,null);
       }
       if(C.Alive){
           g.drawImage(C.getImage(),C.x,C.y,null);
       }
       if(D.Alive){
           g.drawImage(D.getImage(),D.x,D.y,null);
       }
       if(E.Alive){
           g.drawImage(E.getImage(),E.x,E.y,null);
       }
   }

    /**
     *
     * @param g
     */
    public void paintCrystal(Graphics g){
        if(diamond1.stillExist){
            g.drawImage(diamond1.image, diamond1.x, diamond1.y,null);
            if(diamond1.star.StillExist){
                g.drawImage(diamond1.star.shell,diamond1.star.x,diamond1.star.y,null);
            }
        }
        if(diamond2.stillExist){
            g.drawImage(diamond2.image, diamond2.x, diamond2.y,null);
            if(diamond2.star.StillExist){
                g.drawImage(diamond2.star.shell,diamond2.star.x,diamond2.star.y,null);
            }
        }
        if(diamond3.stillExist){
            g.drawImage(diamond3.image, diamond3.x, diamond3.y,null);
            if(diamond3.star.StillExist){
                g.drawImage(diamond3.star.shell,diamond3.star.x,diamond3.star.y,null);
            }
        }
        if(diamond4.stillExist){
            g.drawImage(diamond4.image, diamond4.x, diamond4.y,null);
            if(diamond4.star.StillExist){
                g.drawImage(diamond4.star.shell,diamond4.star.x,diamond4.star.y,null);
            }
        }
    }

    /**
     *
     */
   JFrame frame = new JFrame("Valor-王者荣耀");
   JFrame beginFrame = new JFrame("Control");
   JPanel beginPanel = new JPanel();
   JPanel panel=new JPanel();

    public JButton b0 = new JButton("Game starts");
    public JButton b1 = new JButton("Hero A");
    public JButton b2 = new JButton("Hero B");
    public JButton b3 = new JButton("Hero C");
    public JButton b4 = new JButton("Hero D");
    public JButton b5 = new JButton("Hero E");
    public JButton b6 = new JButton("Hero F");
    public JButton b7 = new JButton("Hero G");
    public JButton b8 = new JButton("Hero H");
    public JButton b9 = new JButton("Hero I");
    public JButton b10 = new JButton("Hero J");

    JMenuBar menuBar = new JMenuBar();
    JMenu m1=new JMenu("File");
    JMenu m2=new JMenu("Edit");
    JMenu m3=new JMenu("View");

    JMenuItem item1=new JMenuItem("Open");
    JMenuItem item2=new JMenuItem("Save");
    JMenuItem item3=new JMenuItem("Save as");
    JMenuItem item4=new JMenuItem("Exit");

    /**
     *
     */
    public void Menu(){
        beginFrame.setJMenuBar(menuBar);
        menuBar.add(m1);
        menuBar.add(m2);
        menuBar.add(m3);

        m1.add(item1);
        m1.add(item2);
        m1.add(item3);
        m1.addSeparator();
        m1.add(item4);
    }

    /**
     * keyboard to control the attack
     */
    KeyAdapter key= new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode()==KeyEvent.VK_Q){
                Order=current+"Q"+toAttack;
                sendOrder();
                egg.StillExist = true;
                shellMove();
                new Thread(egg).start();
                judgeShellAttack();
            }
            if(e.getKeyCode()==KeyEvent.VK_W){
                Order=current+"W"+toAttack;
                sendOrder();
                rocket.StillExist =true;
                rocketMove();
                new Thread(rocket).start();
                judgeRocketAttack();
            }
            if(e.getKeyCode()==KeyEvent.VK_E){
                Order=current+"E"+toAttack;
                sendOrder();
                star.StillExist=true;
                starMove();
                new Thread(star).start();
                judgeStarAttack();
            }
            if(e.getKeyCode()==KeyEvent.VK_R){
                Order=current+"R"+toAttack;
                sendOrder();
            }
            if(e.getKeyCode()==KeyEvent.VK_1){
                toAttack=1;
            }
            if(e.getKeyCode()==KeyEvent.VK_2){
                toAttack=2;
            }
            if(e.getKeyCode()==KeyEvent.VK_3){
                toAttack=3;
            }
            if(e.getKeyCode()==KeyEvent.VK_4){
                toAttack=4;
            }
            if(e.getKeyCode()==KeyEvent.VK_5){
                toAttack=5;
            }
            if(e.getKeyCode()==KeyEvent.VK_6){
                toAttack=6;
            }
            if(e.getKeyCode()==KeyEvent.VK_7){
                toAttack=7;
            }
            if(e.getKeyCode()==KeyEvent.VK_8){
                toAttack=8;
            }
            if(e.getKeyCode()==KeyEvent.VK_9){
                toAttack=9;
            }
            if(e.getKeyCode()==KeyEvent.VK_0){
                toAttack=0;
            }
            if(e.getKeyCode()==KeyEvent.VK_SPACE){
                Order="Press space in order";
                sendOrder();
                if(!F.Alive){
                    F.HP=100;
                    F.Alive=true;
                }
                if(!G.Alive){
                    G.HP=100;
                    G.Alive=true;
                }
                if(!H.Alive){
                    H.HP=100;
                    H.Alive=true;
                }
                if(!I.Alive){
                    I.HP=100;
                    I.Alive=true;
                }
                if(!J.Alive){
                    J.HP=100;
                    J.Alive=true;
                }
            }
            if(e.getKeyCode()==KeyEvent.VK_ENTER){
                attackCry=true;
            }
        }
        public void keyReleased(KeyEvent e) {}
        public void keyTyped(KeyEvent e) {}
    };

    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (b0 == e.getSource()) {
            choose = 10;
        }
        if (b1 == e.getSource()) {
            current = 1;
        }

        if (b2 == e.getSource()) {
            current = 2;
        }

        if (b3 == e.getSource()) {
            current = 3;
        }

        if (b4 == e.getSource()) {
            current = 4;
        }

        if (b5 == e.getSource()) {
            current = 5;
        }

        if (b6 == e.getSource()) {
            current = 6;
        }
        if (b7 == e.getSource()) {
            current = 7;
        }
        if (b8 == e.getSource()) {
            current = 8;
        }
        if (b9 == e.getSource()) {
            current = 9;

        }
        if (b10 == e.getSource()) {
            current = 0;
        }
    }

    public void Button(){
        b0.addActionListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        b8.addActionListener(this);
        b9.addActionListener(this);
        b10.addActionListener(this);
        beginPanel.add(b0);  beginPanel.add(b1);
        beginPanel.add(b2);  beginPanel.add(b3);
        beginPanel.add(b4);  beginPanel.add(b5);
        beginPanel.add(b6);  beginPanel.add(b7);
        beginPanel.add(b8);  beginPanel.add(b9);
        beginPanel.add(b10);
    }
   public void Frame(){
       frame.addKeyListener(key);
       frame.setSize(WIDTH,HEIGHT);
       panel.add(this);
       frame.setLayout(new BorderLayout());
       frame.add(panel,BorderLayout.NORTH);
       frame.add(this,BorderLayout.CENTER);

       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setVisible(true);
       frame.setFocusable(true);

       beginFrame.setSize(500,300);
       beginPanel.setBackground(Color.BLUE);
       beginFrame.getContentPane().setBackground(Color.red);
       beginFrame.getContentPane().setVisible(true);
       beginFrame.add(beginPanel);

       beginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       beginFrame.setVisible(true);
       this.action();
   }

    /**
     * press the Q, the shell will move
     */
   public void shellMove(){
       if(!rec){
           if(egg.StillExist){
               switch (current){
                   case 1: egg.setShell(A.x,A.y);break;
                   case 2: egg.setShell(B.x,B.y);break;
                   case 3: egg.setShell(C.x,C.y);break;
                   case 4: egg.setShell(D.x,D.y);break;
                   case 5: egg.setShell(E.x,E.y);break;
               }
               switch (toAttack){
                   case 1: egg.moveTo(A.x,A.y);break;
                   case 2: egg.moveTo(B.x,B.y);break;
                   case 3: egg.moveTo(C.x,C.y);break;
                   case 4: egg.moveTo(D.x,D.y);break;
                   case 5: egg.moveTo(E.x,E.y);break;
                   case 6: egg.moveTo(F.x,F.y);break;
                   case 7: egg.moveTo(G.x,G.y);break;
                   case 8: egg.moveTo(H.x,H.y);break;
                   case 9: egg.moveTo(I.x,I.y);break;
                   case 0: egg.moveTo(J.x,J.y);break;
                   default:break;
               }
               if(attackCry){
                   int attackCry = findCrystal();
                   switch (attackCry){
                       case 1: egg.moveTo(diamond1.x+45,diamond1.y+60);break;
                       case 2: egg.moveTo(diamond2.x+45,diamond2.y+60);break;
                       case 3: egg.moveTo(diamond3.x+45,diamond3.y+60);break;
                       case 4: egg.moveTo(diamond4.x+45,diamond4.y+60);break;
                   }
               }
           }
       }
       else{
           egg.StillExist=true;
           switch (act){
               case 1: egg.setShell(A.x,A.y);break;
               case 2: egg.setShell(B.x,B.y);break;
               case 3: egg.setShell(C.x,C.y);break;
               case 4: egg.setShell(D.x,D.y);break;
               case 5: egg.setShell(E.x,E.y);break;
           }
           switch (pos){
               case 1: egg.moveTo(A.x,A.y);break;
               case 2: egg.moveTo(B.x,B.y);break;
               case 3: egg.moveTo(C.x,C.y);break;
               case 4: egg.moveTo(D.x,D.y);break;
               case 5: egg.moveTo(E.x,E.y);break;
               case 6: egg.moveTo(F.x,F.y);break;
               case 7: egg.moveTo(G.x,G.y);break;
               case 8: egg.moveTo(H.x,H.y);break;
               case 9: egg.moveTo(I.x,I.y);break;
               case 0: egg.moveTo(J.x,J.y);break;
               default:break;
           }
       }
    }

    /**
     * judge the attack
     */
    public void judgeShellAttack(){
        int damage=30;
        if(egg.Arrive){
            switch (toAttack){
                case 6:F.HP-=damage;
                    if(F.HP<=0){
                        F.Alive=false;
                        record="F is dead! Waiting for reviving";
                        writeFile();
                        record="After 6 seconds, F revives!";
                        writeFile();
                    }break;
                case 7:G.HP-=damage;
                    if(G.HP<=0){
                        G.Alive=false;
                        record = "G is dead! Waiting for reviving";
                        writeFile();
                        record="After 6 seconds, G revives!";
                        writeFile();
                    }break;
                case 8:H.HP-=30;
                    if(H.HP<=0){
                        H.Alive=false;
                        record="H is dead! Waiting for reviving";
                        writeFile();
                        record="After 6 seconds, H revives!";
                        writeFile();
                    }break;
                case 9:I.HP-=damage;
                    if(I.HP<=0){
                        I.Alive=false;
                        record="I is dead! Waiting for reviving";
                        writeFile();
                        record="After 6 seconds, I revives!";
                        writeFile();
                    }break;
                case 0:J.HP-=damage;
                    if(J.HP<=0){
                        J.Alive=false;
                        record="J is dead! Waiting for reviving";
                        writeFile();
                        record="After 6 seconds, J revives!";
                        writeFile();
                    }break;
            }
            int damageC=1000;
            if(attackCry){
                int attackCry = findCrystal();
                switch (attackCry){
                    case 1:
                        diamond1.HP-=damageC;
                        if(diamond1.HP<=0){
                            record="Crystal 1 is dead!";
                            writeFile();
                        diamond1.stillExist = false;
                        } break;
                    case 2:
                        diamond2.HP-=damageC;
                        if(diamond2.HP<=0){
                            record="Crystal 2 is dead!";
                            writeFile();
                            diamond2.stillExist = false;
                        } break;
                    case 3:
                        diamond3.HP-=damageC;
                        if(diamond3.HP<=0){
                            record="Crystal 3 is dead!";
                            writeFile();
                            diamond3.stillExist = false;
                        } break;
                    case 4:diamond4.HP-=damageC;
                        if(diamond4.HP<=0){
                            record="Crystal 4 is dead!";
                            writeFile();
                            diamond4.stillExist = false;
                        } break;
                }
            }
        }
    }

    public void judgeStarAttack(){
        int damage=40;
        if(star.Arrive){
            switch (toAttack){
                case 6:F.HP-=damage;
                    if(F.HP<=0){
                        F.Alive=false;
                        record="F is dead! Waiting for reviving";
                        writeFile();
                        writeFile();
                    }break;
                case 7:G.HP-=damage;
                    if(G.HP<=0){
                        G.Alive=false;
                        record="G is dead! Waiting for reviving";
                        writeFile();
                        record="After 6 seconds, G revives!";
                        writeFile();
                    }break;
                case 8:H.HP-=damage;
                    if(H.HP<=0){
                        H.Alive=false;
                        record="H is dead! Waiting for reviving";
                        writeFile();
                        record="After 6 seconds, H revives!";
                        writeFile();
                    }break;
                case 9:I.HP-=damage;
                    if(I.HP<=0){
                        I.Alive=false;
                        record="I is dead! Waiting for reviving";
                        writeFile();
                        record="After 6 seconds, I revives!";
                        writeFile();
                    }break;
                case 0:J.HP-=damage;
                    if(J.HP<=0){
                        J.Alive=false;
                        record="J is dead! Waiting for reviving";
                        writeFile();
                        record="After 6 seconds, J revives!";
                        writeFile();
                    }break;
            }
        }
    }

    public void judgeRocketAttack(){
        int damage=35;
        if(rocket.Arrive){
            switch (toAttack){
                case 6:F.HP-=damage;
                    if(F.HP<=0){
                        F.Alive=false;
                        record="F is dead! Waiting for reviving";
                        writeFile();
                        record="After 6 seconds, F revives!";
                        writeFile();
                    }break;
                case 7:G.HP-=damage;
                    if(G.HP<=0){
                        G.Alive=false;
                        record="G is dead! Waiting for reviving";
                        writeFile();
                        record="After 6 seconds, G revives!";
                        writeFile();
                    }break;
                case 8:H.HP-=damage;
                    if(H.HP<=0){
                        H.Alive=false;
                        record="H is dead! Waiting for reviving";
                        writeFile();
                        record="After 6 seconds, H revives!";
                        writeFile();
                    }break;
                case 9:I.HP-=damage;
                    if(I.HP<=0){
                        I.Alive=false;
                        record="I is dead! Waiting for reviving";
                        writeFile();
                        record="After 6 seconds, I revives!";
                        writeFile();
                    }break;
                case 0:J.HP-=damage;
                    if(J.HP<=0){
                        J.Alive=false;
                        record="J is dead! Waiting for reviving";
                        writeFile();
                        record="After 6 seconds, J revives!";
                        writeFile();
                    }break;
            }
        }
    }

    /**
     * press the W, the rocket will move
     */
    public void rocketMove(){
        if(!rec){
            if(rocket.StillExist){
                switch (current){
                    case 1: rocket.setShell(A.x,A.y);break;
                    case 2: rocket.setShell(B.x,B.y);break;
                    case 3: rocket.setShell(C.x,C.y);break;
                    case 4: rocket.setShell(D.x,D.y);break;
                    case 5: rocket.setShell(E.x,E.y);break;
                }
                switch (toAttack){
                    case 6: rocket.moveTo(F.x,F.y);break;
                    case 7: rocket.moveTo(G.x,G.y);break;
                    case 8: rocket.moveTo(H.x,H.y);break;
                    case 9: rocket.moveTo(I.x,I.y);break;
                    case 0: rocket.moveTo(J.x,J.y);break;
                    default:break;
                }
            }
        }
        else{
            rocket.StillExist=true;
            switch (act){
                case 1: rocket.setShell(A.x,A.y);break;
                case 2: rocket.setShell(B.x,B.y);break;
                case 3: rocket.setShell(C.x,C.y);break;
                case 4: rocket.setShell(D.x,D.y);break;
                case 5: rocket.setShell(E.x,E.y);break;
            }
            switch (pos){
                case 6: rocket.moveTo(F.x,F.y);break;
                case 7: rocket.moveTo(G.x,G.y);break;
                case 8: rocket.moveTo(H.x,H.y);break;
                case 9: rocket.moveTo(I.x,I.y);break;
                case 0: rocket.moveTo(J.x,J.y);break;
                default:break;
            }
        }
    }
    /**
     * press the E, the star will move
     */
    private void starMove(){
        if(!rec){
            if(star.StillExist){
                switch (current){
                    case 1: star.setShell(A.x,A.y);break;
                    case 2: star.setShell(B.x,B.y);break;
                    case 3: star.setShell(C.x,C.y);break;
                    case 4: star.setShell(D.x,D.y);break;
                    case 5: star.setShell(E.x,E.y);break;
                }
                switch (toAttack){
                    case 6: star.moveTo(F.x,F.y);break;
                    case 7: star.moveTo(G.x,G.y);break;
                    case 8: star.moveTo(H.x,H.y);break;
                    case 9: star.moveTo(I.x,I.y);break;
                    case 0: star.moveTo(J.x,J.y);break;
                    default:break;
                }
            }
        }
        else{
            star.StillExist=true;
            switch (act){
                case 1: star.setShell(A.x,A.y);break;
                case 2: star.setShell(B.x,B.y);break;
                case 3: star.setShell(C.x,C.y);break;
                case 4: star.setShell(D.x,D.y);break;
                case 5: star.setShell(E.x,E.y);break;
            }
            switch (pos){
                case 6: star.moveTo(F.x,F.y);break;
                case 7: star.moveTo(G.x,G.y);break;
                case 8: star.moveTo(H.x,H.y);break;
                case 9: star.moveTo(I.x,I.y);break;
                case 0: star.moveTo(J.x,J.y);break;
                default:break;
            }
        }

    }
    private void heroMove(int x, int y) {
       switch (current){
           case 1:
               Order="A("+x+","+y+")";
               sendOrder();
               A.moveTo(x,y);
               break;
           case 2:Order="B("+x+","+y+")";
               sendOrder();
               B.moveTo(x,y);
               break;
           case 3:Order="C("+x+","+y+")";
               sendOrder();
               C.moveTo(x,y);
               break;
           case 4:Order="D("+x+","+y+")";
               sendOrder();
               D.moveTo(x,y);
               break;
           case 5:Order="E("+x+","+y+")";
               sendOrder();
               E.moveTo(x,y);
               break;
           case 6:Order="E("+x+","+y+")";
               sendOrder();
               F.moveTo(x,y);
               F.moveTo(x,y);break;
       }
        record="Hero "+current+" moves to"+"("+x+","+y+")";
        writeFile();
    }
    /**
     * hero moves step by step
     */
    private void heroMoveSteps(){
       if(A.Stillmove)
           A.moveBystep();
       if(B.Stillmove)
           B.moveBystep();
        if(C.Stillmove)
            C.moveBystep();
        if(D.Stillmove)
            D.moveBystep();
        if(E.Stillmove)
            E.moveBystep();
    }

    /**
     * Judge the game
     */
    private void JudgeGame(){
        if(!diamond1.stillExist && !diamond2.stillExist){
            if(!diamond3.stillExist && !diamond4.stillExist){
                win=true;
                record="Victory!\n,end of game";
                writeFile();
            }
        }
    }
    /**
     *  click the mouse to move the hero
     */
   private void action(){
       MouseAdapter mouse=new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {
               int x=e.getX();
               int y=e.getY();
               heroMove(x,y);
           }
       };

       this.addMouseListener(mouse);
       this.addMouseMotionListener(mouse);
        /**
         *  the Timer will arrange the taskFPS=25
         */
       timer=new Timer();
       timer.schedule(new TimerTask() {
           public void run() {
               new Thread(Revive).start();
               heroMoveSteps();
               JudgeGame();
               repaint();
           }
       },5, 5);
   }

    /**
     *  get the distance
     */
   public void setDistance(){
       px[1]=A.x; py[1]=A.y;
       px[2]=B.x; py[2]=B.y;
       px[3]=C.x; py[3]=C.y;
       px[4]=D.x; py[4]=D.y;
       px[5]=E.x; py[5]=E.y;
   }

    /**
     * @return the closest
     */
   public int getDistanceF(){
       int attackNum=0;
       setDistance();
       for(int i=1;i<=5;i++)
           if((F.x-px[i])*(F.x-px[i])+(F.y-py[i])*(F.y-py[i])<=500*500){
               F.canAttack=true;
               attackNum=i;
               break;
           }
           return  attackNum;
   }

    /**
     *
     * @return
     */
    public int getDistanceG(){
        int attackNum=0;
        setDistance();
        for(int i=1;i<=5;i++)
            if((G.x-px[i])*(G.x-px[i])+(G.y-py[i])*(G.y-py[i])<=200*200){
                G.canAttack=true;
                attackNum=i;
                break;
            }
        return  attackNum;
    }

    public int getDistanceH(){
        int attackNum=0;
        setDistance();
        for(int i=1;i<=5;i++)
            if((H.x-px[i])*(H.x-px[i])+(H.y-py[i])*(H.y-py[i])<=150*150){
                H.canAttack=true;
                attackNum=i;
                break;
            }
        return  attackNum;
    }

    /**
     * attack the player
     */
   public void attackPlayer(){
       int FAttack= getDistanceF();
       if(FAttack!=0){
           F.ball.StillExist=true;
           F.canAttack=true;
           F.ball.setShell(F.x,F.y);
           switch (FAttack){
               case 1:F.setHero(A);
                   F.ball.moveTo(A.x,A.y);break;
               case 2:F.setHero(B);
                   F.ball.moveTo(B.x,B.y);break;
               case 3:F.setHero(C);
                   F.ball.moveTo(C.x,C.y);break;
               case 4:F.setHero(D);
                   F.ball.moveTo(D.x,D.y);break;
               case 5:F.setHero(E);
                   F.ball.moveTo(E.x,E.y);break;
           }
       }
   }

    /**
     *
     * @param x
     * @param y
     * @return
     */
   public int minDistance(int x,int y){
       int []disX=new int[5]; int []disY=new int[5];
       disX[1]=diamond1.x+45;  disY[1]=diamond1.y+60;
       disX[2]=diamond2.x+45;  disY[2]=diamond2.y+60;
       disX[3]=diamond3.x+45;  disY[3]=diamond3.y+60;
       disX[4]=diamond4.x+45;  disY[4]=diamond4.y+60;
       int Min=1920*1920; int minCrystal=0;

       for(int i=1;i<=4;i++){
           if((x-disX[i])*(x-disX[i])+(y-disY[i])*(y-disY[i])<Min){
               Min=(x-disX[i])*(x-disX[i])+(y-disY[i])*(y-disY[i]);
               minCrystal=i;
           }
       }
       return minCrystal;
   }

    /**
     *
     * @return
     */
   public int findCrystal(){
       switch (current){
           case 1: return minDistance(A.x,A.y);
           case 2: return minDistance(B.x,B.y);
           case 3: return minDistance(C.x,C.y);
           case 4: return minDistance(D.x,D.y);
           case 5: return minDistance(E.x,E.y);
           default:return 0;
       }
   }

    /**
     *
     */
   public void writeFile(){
       try {
           FileWriter writer=new FileWriter(fileName,true);
           writer.write(record+"\n");
           writer.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
   public void fileBegin(){
       try {
           FileWriter writer=new FileWriter(fileName,true);
           SimpleDateFormat format=new SimpleDateFormat();
           String time=format.format(new Date());
           writer.write(time+"\n"+record+"\n");
           writer.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

    /**
     * for internet
     */
    public String host = "172.29.13.188"; // 要连接的服务端IP地址
    public final int port = 8865; // 要连接的服务端对应的监听端口
    public ClientThread clientThread = null;
    public Socket client = null;
    public Writer writer = null;
    private static Logger logger = Logger.getLogger(Client.class);


    public void readOrder(String Received){
        if(Received.length()>3){
           int dot=Received.indexOf(',');
           int left=2;int right=Received.indexOf(')');
           char Currplayer=Received.charAt(0);
           String X=Received.substring(left,dot);
           String Y=Received.substring(dot+1,right);
           int x=Integer.parseInt(X);
           int y=Integer.parseInt(Y);
           switch (Currplayer){
               case 'A':A.moveTo(x,y);break;
               case 'B':B.moveTo(x,y);break;
               case 'C':C.moveTo(x,y);break;
               case 'D':D.moveTo(x,y);break;
               case 'E':E.moveTo(x,y);break;
               case 'F':F.moveTo(x,y);break;
               case 'G':G.moveTo(x,y);break;
               case 'H':H.moveTo(x,y);break;
               case 'I':I.moveTo(x,y);break;
               case 'J':J.moveTo(x,y);break;
           }
        }
        else{
            char a=Received.charAt(0);
            char p=Received.charAt(2);
            act=Integer.parseInt(String.valueOf(a));
            pos=Integer.parseInt(String.valueOf(p));
            rec=true;
           switch (Received.charAt(1)){
               case 'Q': shellMove();
                   new Thread(egg).start();
                   break;
               case 'W':rocketMove();
                   new Thread(rocket).start();
                   break;
               case 'E':starMove();
                   new Thread(star).start();
                   break;
               case 'R':starMove();
                   break;
           }
           rec=false;
        }
    }
    /**
     * send order to all players
     */
    public void sendOrder(){
        try{
            writer.write(Order);
            writer.flush();
        }catch (IOException oe){}
    }

    /**
     * Initial socket
     */
    public void initSocket(){
        try {
            client = new Socket(this.host, this.port);
            writer = new OutputStreamWriter(client.getOutputStream());
            clientThread = new ClientThread(client, this);
            new Thread(clientThread).start();
            //System.out.println("已连上服务器");
            logger.info("已连接上游戏服务器");
        }
        catch (UnknownHostException e) {}
        catch (IOException e) {}
    }

    public void SetGame(){
        this.Menu();
        this.Button();
        this.Frame();
        F.setHero(A);  G.setHero(B);
        H.setHero(C);  I.setHero(D);
        J.setHero(E);

        new Thread(diamond1).start();
        new Thread(diamond2).start();
        new Thread(diamond3).start();
        new Thread(diamond4).start();
        //new Thread(AI_Fattack).start();
        //new Thread(AI_Gattack).start();
        //new Thread(AI_Hattack).start();
        //new Thread(AI_Iattack).start();
        //new Thread(AI_Jattack).start();
        //new Thread(F).start();
        //new Thread(G).start();
        //new Thread(H).start();
        //new Thread(I).start();
        //new Thread(J).start();
        fileBegin();
        initSocket();
    }

   public static void main(String args[]){
        Client gameClient =new Client();
        gameClient.SetGame();
        //new Thread(gameClient).start();
   }
}


