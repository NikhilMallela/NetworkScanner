import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
public class NetworkScanner
{
    /**
     * This method uses ICMP echo requests to check the reachability of an IP address.
     * It sends an echo request to the specified IP address and waits for a response
     * within a specified timeout period (5 seconds).
     *
     * @param ipAddress: IP to ping and see whether it's reachable
     * @return: boolean whether host is reachable or not
     */
    public boolean isHostReachable(String ipAddress)
    {
        try
        {
            InetAddress address = InetAddress.getByName(ipAddress);
            return address.isReachable(5000); // Timeout set to 5 seconds
        }

        catch (IOException e)
        {
            return false;
        }
    }

    /**
     * This method uses a TCP scan to check if a specific port is open on the target IP address.
     * It attempts to establish a TCP connection to the specified port and IP address.
     * If the connection is successful, the port is considered open; otherwise, it is considered closed.
     *
     * @param ipAddress: IP to check on whether port is open
     * @param port: the port to be checked on whether it's open or not.
     * @return: boolean whether port is open or not.
     */
    public boolean isPortOpen(String ipAddress, int port)
    {
        try
        {
            InetAddress address = InetAddress.getByName(ipAddress);
            try (Socket socket = new Socket(address, port))
            {
                return true;
            }
        }

        catch (IOException e)
        {
            return false;
        }
    }

    /**
     * This method gets all the connected devices on the network by scanning a range of IP addresses.
     *
     * @return: A list of connected devices on the network
     */
    public List<String> getConnectedDevices()
    {
        try
        {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            List<String> devices = new ArrayList<>();

            while (interfaces.hasMoreElements())
            {
                NetworkInterface networkInterface = interfaces.nextElement();
                if (!networkInterface.isLoopback() && networkInterface.isUp())
                {
                    Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                    while (addresses.hasMoreElements())
                    {
                        InetAddress address = addresses.nextElement();
                        devices.add(address.getHostAddress());
                    }
                }
            }

            return devices;
        }

        catch (SocketException e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
