package com.example.eggproject;

public class Player implements Comparable<Player> {
    private String name;
    private String password;
    private Integer score;
    public Player(String name, String password, Integer score){
        this.name = name;
        this.password = password;
        this.score = score;
    }
    public String getName(){
        return this.name;
    }
    public int getScore(){
        return this.score;
    }
    @Override
    public int compareTo(Player other) {
        return (other.score - this.score);
    }
}
