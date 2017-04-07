/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ddos_util;

import java.io.Serializable;

/**
 * Stores information about a type of DDOS attack
 * 
 * @author karol
 */
public class Attack implements Serializable{
    
    public static final int TYPE_SLOWLORIS = 0;
    public static final int TYPE_UDPFLOOD = 1;
    public static final int TYPE_PING_FLOOD = 2;
    public static final int TYPE_HTTP_FLOOD = 3;
    
    private String ipAddress;   //Victims ip 
    private int port;           //Victims port
    private int attackType;     //DDOS attack type
    private int duration;         //length of attack in seconds

    public Attack(String ipAddress, int port, int attackType, int length){
        this.ipAddress = ipAddress;
        this.port = port;
        this.attackType = attackType;
        this.duration = duration;
    }
    
    public String getIpAddress() {
        return ipAddress;
    }

    public int getPort() {
        return port;
    }

    public int getAttackType() {
        return attackType;
    }

    public int getDuration() {
        return duration;
    }
    
    @Override
    public String toString(){
        String type = "";
        
        switch(attackType){
            case TYPE_SLOWLORIS:
                type = "Slowloris";
                break;
                
            case TYPE_UDPFLOOD:
                type = "UDP Flood";
                break;
            
            case TYPE_PING_FLOOD:
                type = "Ping Flood";
                break;
                
            case TYPE_HTTP_FLOOD:
                type = "HTTP Flood";
        }
        
        return "Type: "+type+" , Duration: "+duration+"s, IP: "+ipAddress+", Port:"+port;
    }
}