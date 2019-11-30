package com.company;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class WorkerWriter implements Runnable{
    private BufferedReader fromUser = new BufferedReader(new InputStreamReader(System.in));
    private DatagramSocket server;
    private InetAddress IP;
    private int port;
    private String str;
    private String name = "Kto-to";
    private byte[] sendData = new byte[1024];

    public WorkerWriter(DatagramSocket ss, InetAddress ip, int p){
        this.server = ss;
        this.IP = ip;
        this.port = p;
    }

    public void run(){
        while (true) {
            try {
                str = fromUser.readLine();
                if (str.startsWith("@name")){
                    name = str.substring(6);
                    continue;
                }
                if (str.equals("@quit")) {
                    System.exit(0);
                }
                str = name + ": " + str;
                sendData = str.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IP, port);
                server.send(sendPacket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
