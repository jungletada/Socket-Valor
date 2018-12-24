package Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Socket;

public class ClientThread implements Runnable{
    private Socket socket = null;
    private Reader reader = null;
    private int len = 0;
    char chars[] = new char[64];
    private Client client;
    private String temp = "";

    public ClientThread(Socket socket, Client client) {
        try {
            this.socket = socket;
            this.client = client;
            reader = new InputStreamReader(socket.getInputStream());
        } catch (Exception e) {}
    }

    @Override
    public void run() {
        System.out.println("客户端子线程 "+socket.getPort()+"开始工作");
        while (true) {
            try {
                if (!socket.isClosed()) {
                    if (!socket.isInputShutdown()) {
                        while ((len = reader.read(chars)) != -1) {
                            temp = new String(chars, 0, len);
                            client.readOrder(temp);
                            System.out.println("from all: "+temp);
                        }
                    }
                }
                else {
                    if (!socket.getKeepAlive()) {
                        reader.close();
                        socket.close();
                    }
                }
            }catch (IOException e) {}
        }
    }
}

