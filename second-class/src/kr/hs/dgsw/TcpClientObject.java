package kr.hs.dgsw;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class TcpClientObject {
    public static void main(String[] args) {
        System.out.println("<<< Client >>>");
        try {
            Socket socket = new Socket(InetAddress.getLoopbackAddress(), 20000);
            System.out.println("Server에 접속");
            System.out.println("접속 Server 주소 : " + socket.getInetAddress() + " : " + socket.getPort());

            BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
            DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(bufferedOutputStream);

            dataOutputStream.writeUTF("안녕하세요.");
            dataOutputStream.flush();

            String str = dataInputStream.readUTF();
            System.out.println("server : " + str);

            socket.close();
        } catch(IOException e) {
        }
    }
}
