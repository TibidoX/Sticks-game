/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.sticks;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author vovab
 */
public interface RemoteInterfaceServer extends Remote {
    void move(int x, int y, int size, int id) throws RemoteException;
    void addClient(int port, String name) throws RemoteException;
    int getClientsCount() throws RemoteException;
    int checkWinner() throws RemoteException;
    int checkMove() throws RemoteException;
}
