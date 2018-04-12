/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aratouumea.spider;

/**
 *
 * @author Aratouumea
 */
public class SpiderDetails {

    private final int LIFE_8 =7;
    private final int LIFE_7 =6;
    private final int LIFE_6 =5;
    private final int LIFE_5 =4;
    private final int LIFE_4 =3;
    private final int LIFE_3 =2;
    private final int LIFE_2 =1;
    private final int LIFE_1 =0;
    
    private int life;
    private int jumpRange;
    private int actualLife;
    private int speed;
    private int dmg;

    public SpiderDetails(int life, int speed, int dmg, int jumpRange) {
        this.life = life;
        this.dmg = dmg;
        this.speed = speed;
        this.actualLife= life;
        this.jumpRange=jumpRange;
    }

    public int getSpeed() {
        return speed;
    }

    public int getLife() {
        return life;
    }
    public int getActualLife(){
        return actualLife;
    }

    public int getDmg() {
        return dmg;
    }
    
    public void removeActualLife(int value){
        actualLife-=value;
    }
    public void addActualLife(int value){
        actualLife+=value;
    }
    public int getLifePosition(){
        if(actualLife>= life){
            return LIFE_8;
        }
        if(actualLife>= life *7 /8){
            return LIFE_7;
        }
        if(actualLife>= life *1 /8){
            return LIFE_1;
        }
        if(actualLife>= life *6 /8){
            return LIFE_6;
        }
        if(actualLife>= life *5 /8){
            return LIFE_5;
        }
        if(actualLife>= life *4 /8){
            return LIFE_4;
        }
        if(actualLife>= life *3 /8){
            return LIFE_3;
        }
        if(actualLife>= life *2 /8){
            return LIFE_2;
        }
        return 0;
    }
    public int getJumpRange(){
        return jumpRange;
    }

}
