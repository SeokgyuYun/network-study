package kr.hs.dgsw;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class InetAddressObject {
    public static void main(String[] args) {

        try {
            InetAddress ia1 = InetAddress.getByName("www.google.com");
            byte[] googleIP = new byte[]{(byte) 172, (byte) 217, (byte) 25,  (byte) 164};
            InetAddress ia2 = InetAddress.getByAddress(googleIP);
            InetAddress ia3 = InetAddress.getByAddress("www.google.com", new byte[]{(byte) 173, (byte) 217, (byte) 161, (byte) 36});

            System.out.println(ia1);
            System.out.println(ia2);
            System.out.println(ia3);
            System.out.println();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        try {
            InetAddress ia = InetAddress.getByName("www.google.com");
            int port = 10000;
            InetSocketAddress isa1 = new InetSocketAddress(port);
            InetSocketAddress isa2 = new InetSocketAddress("www.google.com", port);
            InetSocketAddress isa3 = new InetSocketAddress(ia, port);

            System.out.println(isa1);
            System.out.println(isa2);
            System.out.println(isa3);
            System.out.println();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
