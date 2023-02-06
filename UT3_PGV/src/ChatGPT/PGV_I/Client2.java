package ChatGPT.PGV_I;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client2 {
    public static void main(String[] args) {
        try {
            // Connect to the server using a STREAM socket
            Socket streamSocket = new Socket("localhost", 5555);
            // Get the input and output streams
            InputStream input = streamSocket.getInputStream();
            OutputStream output = streamSocket.getOutputStream();
            // Send a message to the server
            output.write("Hola".getBytes());
            // Read the response from the server
            byte[] buffer = new byte[256];
            int bytesRead = input.read(buffer);
            String message = new String(buffer, 0, bytesRead);
            System.out.println(message);
            // Close the socket
            streamSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}