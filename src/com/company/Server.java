package com.company;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {

    public static void main(String[] args) throws Exception {
        DatagramSocket clientSocket = new DatagramSocket(55555);
        InetAddress IPAddress = InetAddress.getByName("localhost");
        Thread  workerWriter = new Thread(new WorkerWriter(clientSocket, IPAddress, 55556));
        Thread  workerReader = new Thread(new WorkerReader(clientSocket));
        workerWriter.start();
        workerReader.start();

    }

}
