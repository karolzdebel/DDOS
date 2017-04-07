/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ddosserver;

import ddos_util.Attack;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Listens for attacks from a particular user
 * 
 * @author karol
 */
public class AttackReceiver implements Runnable{
    
    private ObjectInputStream in;
    final private DdosServer server;
    
    public AttackReceiver(ObjectInputStream in, DdosServer server){
        this.in = in;
        this.server = server;
    }

    @Override
    public void run() {
        while (true){
            try{
                Attack a = (Attack)in.readObject();
                System.out.println("Broadcasting attack: "+a.toString());
                server.broadcastAttack(a);
            }
            catch(IOException e){
                break;
            }
            catch(ClassNotFoundException e){
                break;
            }
        }
    }
    
    
}
