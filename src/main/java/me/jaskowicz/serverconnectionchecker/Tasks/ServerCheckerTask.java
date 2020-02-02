package me.jaskowicz.serverconnectionchecker.Tasks;

import me.jaskowicz.serverconnectionchecker.Main;
import me.jaskowicz.serverconnectionchecker.Utils.Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.TimerTask;

// implements Runnable
public class ServerCheckerTask extends TimerTask {

    @Override
    public void run() {
        for(Server server : Main.SERVERS.values()) {

            if (localServerIsUp(Integer.parseInt(server.getPort()))) {
                if (server.isServerDown()) {
                    server.setServerDown(false);
                    System.out.println("Server under the IP " + server.getIp() + " and port " + server.getPort() + " has come online!");
                }
            } else {
                if (!server.isServerDown()) {
                    server.setServerDown(true);
                    System.out.println("Server under the IP " + server.getIp() + " and port " + server.getPort() + " has gone offline!");
                }
            }
        }
    }

    private boolean localServerIsUp(int port) {

        Socket socket = null;

        try {
            socket = new Socket("0.0.0.0", port);
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("An error occurred. This could possibly mean that you set the IP to a non-local IP. If the IP is an ip for a different host, I will not be able to connect.");
                }
            }
        }
    }
}
