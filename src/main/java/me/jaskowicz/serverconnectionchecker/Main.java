package me.jaskowicz.serverconnectionchecker;

import me.jaskowicz.serverconnectionchecker.Tasks.ServerCheckerTask;
import me.jaskowicz.serverconnectionchecker.Utils.Server;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    // This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License.
    // To view a copy of this license,
    // visit http://creativecommons.org/licenses/by-nc-sa/4.0/ or send a letter to Creative Commons, PO Box 1866, Mountain View, CA 94042, USA.

    // Created by: Archie Jaskowicz.

    public static HashMap<Server, Server> SERVERS = new HashMap<>();

    public static void main(String[] args) {

        System.out.println("---------------------------");
        System.out.println("Server Connection Checker\n\nCreated by: Archie Jaskowicz.");
        System.out.println("---------------------------\n");

        if(args.length == 1) {
            if(args[0].startsWith("--file=")) {

                System.out.println("Custom file prompt acknowledged! Attempting to read custom file...");

                String[] stuff = args[0].split("=");
                String fileName = stuff[1];

                System.out.println(fileName);

                File file = new File(fileName);

                if(file.exists()) {

                    System.out.println("Custom file found! Reading data...");

                    Scanner scanner = null;

                    try {
                        scanner = new Scanner(file);
                    } catch(FileNotFoundException e) {
                        System.out.println("Specified file does not exist or can not be read! Please create the file that you specified if you wish to use it.");
                        return;
                    }

                    if(!scanner.hasNextLine()) {
                        System.out.println("Specified file does not have any information regarding IPs and Ports (IP:PORT)!");
                        return;
                    }

                    while (scanner.hasNextLine()) {
                        String data = scanner.nextLine();
                        String[] ipPorts = data.split(":");

                        if(ipPorts.length != 0) {
                            if(ipPorts.length == 2) {
                                Server server = new Server(ipPorts[0], ipPorts[1]);
                                SERVERS.put(server, server);
                            } else if (ipPorts.length < 2) {
                                System.out.println("Line has too little arguments (colons) on it. Ignoring line.");
                            } else {
                                System.out.println("Line has too many arguments (colons) on it. Ignoring line.");
                            }
                        } else {
                            System.out.println("Specified file does not have any information regarding IPs and Ports (IP:PORT)!");
                            return;
                        }
                    }

                    scanner.close();

                    System.out.println("After reading all the data, I have found " + SERVERS.size() + " server(s) to check upon!");
                } else {
                    System.out.println("Specified file does not exist or can not be read! Please create the file that you specified if you wish to use it.");
                    return;
                }
            }
        } else if (args.length == 0) {
            System.out.println("Running off base file.");

            File file = new File("Servers.txt");

            if(file.exists()) {

                System.out.println("Servers file found! Reading data...");

                Scanner scanner = null;

                try {
                    scanner = new Scanner(file);
                } catch(FileNotFoundException e) {
                    System.out.println("Specified file does not exist or can not be read! Please create the file that you specified if you wish to use it.");
                    return;
                }

                if(!scanner.hasNextLine()) {
                    System.out.println("Specified file does not have any information regarding IPs and Ports (IP:PORT)!");
                    return;
                }

                while (scanner.hasNextLine()) {
                    String data = scanner.nextLine();
                    String[] ipPorts = data.split(":");

                    if(ipPorts.length != 0) {
                        if(ipPorts.length == 2) {
                            Server server = new Server(ipPorts[0], ipPorts[1]);
                            SERVERS.put(server, server);
                        } else if (ipPorts.length < 2) {
                            System.out.println("Line has too little arguments (colons) on it. Ignoring line.");
                        } else {
                            System.out.println("Line has too many arguments (colons) on it. Ignoring line.");
                        }
                    } else {
                        System.out.println("Specified file does not have any information regarding IPs and Ports (IP:PORT)!");
                        return;
                    }
                }

                scanner.close();

                System.out.println("After reading all the data, I have found " + SERVERS.size() + " server(s) to check upon!");
            } else {
                try {
                    boolean created = file.createNewFile();

                    if(created) {
                        System.out.println("The file has been created!");
                    } else {
                        System.out.println("The file failed to be created. I may be lacking permissions to create files, try executing me as administrator.");
                        return;
                    }

                    System.out.println("I have created the file 'Servers.txt' for you! Enter data in there for me to read.");
                    return;

                } catch (Exception ex) {
                    System.out.println("An error occurred when trying to create the file.");
                    return;
                }
            }
        } else {
            System.out.println("You have given me too many arguments! Correct the command you are running to execute me.");
            return;
        }

        System.out.println("Starting threader...");

        Timer timer = new Timer();
        timer.schedule(new ServerCheckerTask(), 0, 5000);

        System.out.println("Threader started!");

        //ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        //executor.scheduleAtFixedRate(new ServerCheckerTask(), 1, 5, TimeUnit.SECONDS);
    }
}
