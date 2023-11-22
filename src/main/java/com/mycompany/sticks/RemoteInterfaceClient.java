/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.sticks;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author vovab
 */
public interface RemoteInterfaceClient extends Remote {
    void update(List<MyLine> lines, List<Integer> X, List<Integer> O) throws RemoteException;
    void showWinner(int id) throws RemoteException;
}
