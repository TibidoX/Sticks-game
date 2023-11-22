/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sticks;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vovab
 */
public class Server implements RemoteInterfaceServer{
    List<MyLine> lines = new ArrayList();
    List<Integer> X = new ArrayList();
    List<Integer> O = new ArrayList();
    List<Integer> squares = new ArrayList();
    List<RemoteInterfaceClient> clients = new ArrayList();
    int move = -1;
    
    public void move(int x, int y, int size, int id) {
        if (move != id) return;
        Positions pos;
        int index_x, index_y;
        if (x % size < 10) {
            pos = Positions.VERTICAL;
            index_x = x/size;
            index_y = y/size;
            MyLine l = new MyLine(index_x*size, index_y*size, pos);
            if (lines.contains(l)) return;
            lines.add(l);
        } else if (x % size > 35) {
            pos = Positions.VERTICAL;
            index_x = x/size + 1;
            index_y = y/size;
            MyLine l = new MyLine(index_x*size, index_y*size, pos);
            if (lines.contains(l)) return;
            lines.add(l);
        } else {
            pos = Positions.HORIZONTAL;
            index_x = x/size;
            index_y = y/size;
            MyLine l = new MyLine(index_x*size, index_y*size, pos);
            if (lines.contains(l)) return;
            lines.add(l);
        }
        int index = checkSquare(size);
        if (index != -1) {
            if (id == 8081) X.add(index);
            else O.add(index);
        } else {
            switchMove();
        }
        updateClients();
        if (checkWinner() != -1) reset();
    }
    
    public int checkMove() {
        return move;
    }
    
    private void switchMove() {
        if (move == 8081) move = 8082;
        else move = 8081;
    }
    
    public int checkWinner() {
        if (X.size() == 3) {
            return 8081;
        } else if (O.size() == 3) {
            return 8082;
        }
        return -1;
    }
    
    @Override
    public void addClient(int port, String name) {
        if (clients.size() < 2) {
            String host = null;
            try {
                Registry registry = LocateRegistry.getRegistry(host, port);
                System.out.println("registry: " + host + ":" + port);
                RemoteInterfaceClient stub = (RemoteInterfaceClient) registry.lookup(name);
                System.out.println(stub);
                clients.add(stub);
            } catch (Exception e) {
                System.err.println("RMI Exception: " + e.toString());
                e.printStackTrace();
            }
            if (clients.size() == 2) switchMove();
            updateClients();
        }
    }
    
    @Override
    public int getClientsCount() {
        return clients.size();
    }
    
    private int checkSquare(int size) {
        //int size = this.getSize().height/6;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < 7; i++) 
            for (int j = 0; j < 7; j++) {
                List<MyLine> tmp = new ArrayList();
                tmp.add(new MyLine(j*size, i*size, Positions.HORIZONTAL));
                tmp.add(new MyLine(j*size+size, i*size, Positions.VERTICAL));
                tmp.add(new MyLine(j*size, i*size+size, Positions.HORIZONTAL));
                tmp.add(new MyLine(j*size, i*size, Positions.VERTICAL));

                if (lines.containsAll(tmp) ) {
                    if (!squares.contains(i*6+j)) {
                        squares.add(i*6+j);
                        return i*6+j;
                    }
                }
            }
        return -1;
    }
    
    private void reset() {
        lines.clear();
        X.clear();
        O.clear();
        squares.clear();
        move = 8081;
        updateClients();
    }
    
    private void updateClients() {
        for (RemoteInterfaceClient client: clients) {
            try {
                client.update(lines, X, O);
                client.showWinner(checkWinner());
            } catch (RemoteException ex) {
            }
        }
    }
}
