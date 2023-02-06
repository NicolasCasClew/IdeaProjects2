package ChatGPT;

import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(2000);
        System.out.println("Server started");

        while (true) {
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);

            String input = new String(receivePacket.getData());
            InetAddress address = receivePacket.getAddress();
            int port = receivePacket.getPort();

            System.out.println("Client: " + input.trim());

            String output = "Server: " + input;
            byte[] sendData = output.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
            socket.send(sendPacket);
        }
    }
}
