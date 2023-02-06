package ChatGPT.PGV_I;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;

public class ConnectionHandler implements Runnable {
    private Socket socket;

    public ConnectionHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            // Get the IP address of the client
            InetAddress clientIP = socket.getInetAddress();
            // Get the name of the thread
            String threadName = Thread.currentThread().getName();
            // Get the current time
            Calendar cal = Calendar.getInstance();
            Date currentTime = cal.getTime();
            // Print the IP address, thread name, and current time
            System.out.println("Connection from " + clientIP + " handled by thread " + threadName + " at " + currentTime);
            // Get the input and output streams
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();
            // Read the incoming message
            byte[] buffer = new byte[256];
            int bytesRead = input.read(buffer);
            String message = new String(buffer, 0, bytesRead);
            // Handle the incoming message
            if (message.equals("Hola")) {
                output.write("Ok recibido".getBytes());
            }
            // Close the socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
