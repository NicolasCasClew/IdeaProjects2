package ChatGPT.PGV_I;

import java.net.InetAddress;
import java.util.Calendar;
import java.util.Date;

public class DatagramHandler implements Runnable {
    private InetAddress clientIP;
    private int clientPort;
    private String message;
    public DatagramHandler(InetAddress clientIP, int clientPort, String message) {
        this.clientIP = clientIP;
        this.clientPort = clientPort;
        this.message = message;
    }
    public void run() {
        try {
            String threadName = Thread.currentThread().getName();
            Calendar cal = Calendar.getInstance();
            Date currentTime = cal.getTime();
            System.out.println("Datagram from " + clientIP + ":" + clientPort + " handled by thread " + threadName + " at " + currentTime);
            if (message.equals("Hola")) {
                //handle the response for the message Hola
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}