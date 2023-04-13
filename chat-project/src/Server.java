import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Server {
    private static int uniqueId;
    private ArrayList<ClientThread> clientThreads;
    private SimpleDateFormat simpleDateFormat;
    private int port;
    private boolean isRunning;
    private String notif = " *** ";

    public void start() {
        isRunning = true;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            display("[서버 시작]");
            while (isRunning) {
                Socket socket = serverSocket.accept();
                if (!isRunning) break;
            }
        } catch (IOException e) {
            display("서버 예외 발생 " + e);
        }
    }

    public void stop() {

    }

    private void display(String message) {
        String time = simpleDateFormat.format(new Date()) + "" + message;
        System.out.println(time);
    }

    private synchronized boolean broadcast(String message) {
        return true;
    }

    private synchronized void remove(int id) {

    }

    public Server(int port) {
        this.port = port;
        this.simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        this.clientThreads = new ArrayList<>();
    }

    class ClientThread extends Thread {
        Socket socket;
        ObjectInputStream inputStream;
        ObjectOutputStream outputStream;

        int id;
        String userName;

        ChatMessage chatMessage;
        boolean keepGoing;

        @Override
        public void run() {
            while (keepGoing) {
                try {
                    chatMessage = (ChatMessage) inputStream.readObject();
                } catch (IOException e) {
                    display(userName + "님 예외 발생" + e);
                    break;
                } catch (ClassNotFoundException e2) {
                    break;
                }
                String message = chatMessage.getMessage();
            }
            remove(id);
            close();
        }

        private boolean writeMsg(String msg) {
            if (!socket.isConnected()) {
                close();
                return false;
            }
            try {
                outputStream.writeUTF(msg);
                outputStream.flush();
            } catch (IOException e) {
                display(notif + "메시지 전송 에러" + userName + notif);
                display(e.toString());
            }
            return true;
        }

        public void close() {
            try {
                if (outputStream != null) outputStream.close();
                if (inputStream != null) inputStream.close();
                if (socket != null) socket.close();
            } catch (Exception e) {
                
            }
        }

        public String getUsername() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public ClientThread(Socket socket) {
            id = ++uniqueId;
            keepGoing = true;
            this.socket = socket;
            display("클라이언트 " + socket.getInetAddress() + ":" + socket.getPort() + " 접속");
            try {
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                inputStream = new ObjectInputStream(socket.getInputStream());
                userName = inputStream.readUTF();
            } catch (IOException e) {
                display(" " + e);
            }
        }
    }
}