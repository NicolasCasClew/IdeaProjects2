package ChatGPT;

import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        System.out.println("Connected to server");

        InetAddress address = InetAddress.getByName("localhost");
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String input = userInput.readLine();
            byte[] sendData = input.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, 2000);
            socket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);

            String output = new String(receivePacket.getData());
            System.out.println("Server: " + output.trim());
        }
    }
}
