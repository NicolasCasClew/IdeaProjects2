package ChatGPT.PGV_I;

import java.io.IOException;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        try {
            // Connect to the server using a DATAGRAM socket
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            byte[] buffer = "Hola".getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, 6666);
            socket.send(packet);
            // Read the response from the server
            byte[] response = new byte[256];
            packet = new DatagramPacket(response, response.length);
            socket.receive(packet);
            String message = new String(response, 0, packet.getLength());

