package ChatGPT.PGV_I;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.Calendar;
import java.util.Date;

public class Server {
    public static void main(String[] args) {
        try {
            // Create a server socket for STREAM connections
            ServerSocket streamServer = new ServerSocket(5555);
            // Create a server socket for DATAGRAM connections
            DatagramSocket datagramServer = new DatagramSocket(6666);
            while (true) {
                // Accept incoming STREAM connections
                Socket streamSocket = streamServer.accept();
                // Create a new thread to handle the connection
                Thread streamThread = new Thread(new ConnectionHandler(streamSocket));
                streamThread.start();
                // Accept incoming DATAGRAM connections
                byte[] buffer = new byte[256];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                datagramServer.receive(packet);
                // Create a new thread to handle the connection
                InetAddress clientIP = packet.getAddress();
                int clientPort = packet.getPort();
                String message = new String(packet.getData(), 0, packet.getLength());
                Thread datagramThread = new Thread(new DatagramHandler(clientIP, clientPort, message));
                datagramThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
