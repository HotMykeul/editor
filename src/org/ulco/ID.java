package org.ulco;

public class ID {
    static public int ID = 0;

    private ID(){

    }

    private static ID INSTANCE = new ID();

    public static ID getInstance(){
        return INSTANCE;
    }

    public int getID(){
        ID++;
        return ID;
    }
}