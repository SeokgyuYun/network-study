package kr.hs.dgsw;

import java.io.*;
import java.net.*;

public class TcpServerObject {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket();
            SocketAddress socketAddress = new InetSocketAddress(InetAddress.getLoopbackAddress(), 20000);
            serverSocket.bind(socketAddress);
            System.out.println("Client접속 대기..");

            Socket socket = serverSocket.accept();
            System.out.println("Client 연결 수락");
            System.out.println("접속 Client 주소 : " + socket.getInetAddress() + " : " + socket.getPort());

            BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
            DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(bufferedOutputStream);

            String str = dataInputStream.readUTF();
            System.out.println("client : " + str);

            dataOutputStream.writeUTF("어서오세요!");
            dataOutputStream.flush();

            socket.close();
            serverSocket.close();
        } catch (IOException e) {
        }
    }
}
