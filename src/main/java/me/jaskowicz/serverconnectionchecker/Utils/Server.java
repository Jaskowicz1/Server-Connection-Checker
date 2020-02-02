package me.jaskowicz.serverconnectionchecker.Utils;

public class Server {

    private String ip;
    private String port;
    private boolean serverDown = true;

    public Server(String ip, String port) {
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public String getPort() {
        return port;
    }

    public boolean isServerDown() {
        return serverDown;
    }




    // All voids after this line.

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setServerDown(boolean serverDown) {
        this.serverDown = serverDown;
    }
}
