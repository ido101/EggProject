package com.example.eggproject;

public class EggManager
{
    private MainActivity ma;
    private int count;
    public EggManager(MainActivity ma){
        this.ma = ma;
        count = 10;
    }
    public int getCount(){
        return count;
    }
    public void setCount(int count){
        this.count = count;
    }
    public boolean isItTime()
    {
        return count <= 1;
    }
}
