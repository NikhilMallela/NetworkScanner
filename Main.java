/**
 * @author: Nikhil Mallela
 * @since: 7/1/23
 *
 * NetworkScanner program: This Java program is designed to scan a list of IP addresses
 * and identify possible hosts on the network. It utilizes ICMP echo requests and TCP scans to
 * determine the reachability and open ports of the target IP addresses.
 */

import java.util.List;
public class Main {
    public static void main(String[] args)
    {
        System.out.println();

        NetworkScanner networkScanner = new NetworkScanner();

        String[] ipAddresses = {
                "192.168.0.1",
                "192.168.0.2",
                "192.168.0.3",
                // Delete above and add your own IP addresses to scan here.
        };

        for (String ipAddress : ipAddresses)
        {
            if (networkScanner.isHostReachable(ipAddress))
            {
                System.out.println("Host is reachable: " + ipAddress);
            }

            else
            {
                System.out.println("Host is not reachable: " + ipAddress);
            }

            if (networkScanner.isPortOpen(ipAddress, 80))
            {
                System.out.println("Port 80 is open on host: " + ipAddress);
            }

            else
            {
                System.out.println("Port 80 is closed on host: " + ipAddress);
            }

            // Add more port numbers to scan here
        }

        System.out.println();

        System.out.println("Scanning local network for devices...");
        List<String> connectedDevices = networkScanner.getConnectedDevices();
        System.out.println("Connected devices:");
        for (String device : connectedDevices)
        {
            System.out.println(device);
        }
    }

}
