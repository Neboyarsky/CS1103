package Week6.Network;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class MyNetwork {
    public static void showNetwork() {
        Enumeration<NetworkInterface> netInterfaces;

        System.out.println();

        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
        }
        catch (Exception e){
            System.out.println();
            System.out.println("Sorry, an error occurred while looking for network");
            System.out.println("interfaces.  The error was:");
            System.out.println(e);
            return;
        }

        if (! netInterfaces.hasMoreElements() ) {
            System.out.println("No network interfaces found.");
            return;
        }

        System.out.println("Network interfaces found on this computer:");

        while (netInterfaces.hasMoreElements()) {
            NetworkInterface net = netInterfaces.nextElement();
            String name = net.getName();
            System.out.print("   " + name + " :  ");
            Enumeration<InetAddress> inetAddresses = net.getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                InetAddress address = inetAddresses.nextElement();
                System.out.print(address + "  ");
            }
            System.out.println();
        }

        System.out.println();
    }
}
