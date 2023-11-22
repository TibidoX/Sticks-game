/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sticks;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;
/**
 *
 * @author vovab
 */
public class RunServer {

    public static void main(String[] args) {
        try {
            Server server = new Server();
            RemoteInterfaceServer stub = 
            (RemoteInterfaceServer) UnicastRemoteObject.exportObject(server, 0);
            Registry registry = LocateRegistry.createRegistry(8080);
            registry.bind("Server", stub);
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("RMI Exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
