/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ddos_util;

import java.io.Serializable;

/**
 *
 * @author karol
 */
public class Identifier implements Serializable{
    public static final int ZOMBIE = 0;
    public static final int USER = 1;
    
    private int type;
    
    public Identifier(int type){
        this.type = type;
    }
    
    public boolean isZombie(){
        return type == ZOMBIE;
    }
    
    public boolean isUser(){
        return type == USER;
    }
}
