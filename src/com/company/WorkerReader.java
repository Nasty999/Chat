package com.company;


import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class WorkerReader implements Runnable{
    private DatagramSocket serverSocket;
    private byte[] receiveData = new byte[1024];

    public WorkerReader(DatagramSocket serverSocket){
        this.serverSocket = serverSocket;
    }

    public void run(){
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        int i = 0;
        while (true) {
            try {
                serverSocket.receive(receivePacket);
                int size = receivePacket.getLength();
                String sentence = new String(receivePacket.getData(),0, size);
                String[] str = sentence.split(" ");
                if(str[1].equals("@kill")){
                    System.exit(0);
                }
                System.out.println(sentence);
                //System.out.println(size);

                while (receiveData[i] != 0)
                    receiveData[i++] = 0;
                i = 0;
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
