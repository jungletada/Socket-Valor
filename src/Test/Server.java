package Test;

import org.apache.log4j.Logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Server implements ActionListener,Runnable{
    private Map<Integer, Socket> clients=new HashMap<Integer, Socket>();
    public final int PORT=8865;
    public boolean Received;
    public String Order;
    private static Logger logger = Logger.getLogger(Server.class);

    public Server(){
        Received =false;
    }

    public void listenClients(){
        try{
            ServerSocket serverSocket=new ServerSocket(PORT);
            while(true) {
                //System.out.println("服务器端正在监听游戏客户端");
                logger.info("服务器端正在监听游戏客户端-logger");
                Socket socket = serverSocket.accept();
                clients.put(socket.getPort(), socket);
                //System.out.println("客户端"+socket.getPort()+":已登录");
                logger.info("客户端"+socket.getPort()+":已登录");
                new ServerThread(socket, this).start();
            }
        }catch (Exception e){}
    }

    /**
     * send the order to all clients except this
     * @param fromSocket
     * @param temp
     */
    public void SendOrderToAll(Socket fromSocket,String temp) {
        Order=temp;
        Set<Integer> KeySet = this.clients.keySet();
        java.util.Iterator<Integer> iterator = KeySet.iterator();
        while(iterator.hasNext()){
            int key = iterator.next();
            Socket socket = clients.get(key);
            if(socket != fromSocket) {
                try {
                    if(!socket.isClosed()){
                        if(!socket.isOutputShutdown()){
                            Writer writer = new OutputStreamWriter(
                                    socket.getOutputStream());
                            writer.write(Order);
                            writer.flush();
                            //System.out.println("send order to clients.");
                            logger.info("send order to clients.");
                        }
                    }
                } catch (SocketException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
    }

    @Override
    public void run() {
        while (Received){
            //SendOrderToAll();
        }
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    }

    /**
     * the main function to play as server
     * @param args
     */
    public static void main(String[] args){
        Server GameServer = new Server();
        GameServer.listenClients();
        //new Thread(GameServer).start();
    }
}
/**
 * Use thread to send orders to all clients
 */
class ServerThread extends Thread{
    private Socket socket;
    private Server server;
    private InputStreamReader reader = null;
    private static Logger logger = Logger.getLogger(ServerThread.class);

    public char chars[] = new char[128];
    public int len;
    private String temp = null;
    public ServerThread(Socket socket, Server server) {
        // TODO Auto-generated constructor stub
        this.socket = socket;
        this.server = server;
        initial();
    }

    /**
     * initial the reader to accept message
     */
    private void initial(){
        try {
            reader = new InputStreamReader(socket.getInputStream());
        } catch (IOException e) {}
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        //System.out.println("客户端子线程开始工作");
        logger.info("客户端子线程开始工作");

        while(true){
            try {
                while ((len = reader.read(chars)) != -1) {
                    temp = new String(chars, 0, len);
                    server.SendOrderToAll(this.socket,temp);
                    //System.out.println("来自客户端 "+socket.getPort()+"的消息:" +temp);
                    logger.info("来自客户端 "+socket.getPort()+"的消息:" +temp);
                    server.Received =true;
                }

                if(socket.getKeepAlive() == false){
                    ((Reader) reader).close();
                    temp = "客户端"+socket.getPort()+":退出";
                    socket.close();
                    //this.stop();
                }
            } catch (Exception e) {
                try {
                    reader.close();
                    socket.close();
                } catch (IOException e1) {}
            }
        }
    }
}
