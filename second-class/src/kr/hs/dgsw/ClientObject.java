package kr.hs.dgsw;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientObject {
    public static void main(String[] args) {
        Socket socekt = null;

        try {
            socekt = new Socket(InetAddress.getLoopbackAddress(), 20000);
            System.out.println("Server에 접속");
            System.out.println("접속 Server 주소 : " + socekt.getInetAddress() + " : " + socekt.getPort());
            socekt.close();
        } catch (IOException e) {

        }
    }
}
