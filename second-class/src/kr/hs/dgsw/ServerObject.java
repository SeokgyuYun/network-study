package kr.hs.dgsw;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerObject {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
//            serverSocket = new ServerSocket(20000);
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(InetAddress.getLoopbackAddress(), 20000));
            System.out.println(serverSocket.isBound());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
//            serverSocket.setSoTimeout(2000);
            Socket socket = serverSocket.accept();
            System.out.println("Client 연결");
            System.out.println("접속 Client 주소 : " + socket.getInetAddress() + " : " + socket.getPort());
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("timeout!");
        }
        System.out.println("종료");
    }
}
