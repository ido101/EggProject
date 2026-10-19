package com.example.eggproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "EggProject.db";
    private static final int DATABASE_VERSION = 2;
    public static final String TABLE_NAME = "players";
    public static final String UID_PLAYERS = "id";
    public static final String PLAYER_NAME = "name";
    public static final String PASSWORD = "password";
    public static final String NUM_OF_GAMES = "num_of_games";
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " ("
                + UID_PLAYERS + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PLAYER_NAME + " TEXT, "
                + PASSWORD + " TEXT, "
                + NUM_OF_GAMES + " INTEGER);";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public void addPlayer(String name, String password, SQLiteDatabase db){
        ContentValues cv = new ContentValues();
        cv.put(PLAYER_NAME ,name);
        cv.put(PASSWORD ,password);
        cv.put(NUM_OF_GAMES ,1);
        db.insert(TABLE_NAME, null,cv);
    }
    public void addPlayer(String name, String password, int games){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PLAYER_NAME ,name);
        cv.put(PASSWORD ,password);
        cv.put(NUM_OF_GAMES ,games);
        db.insert(TABLE_NAME, null,cv);
        db.close();
    }
    public void updateGames(String name, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (!playerExists(name, db))
            addPlayer(name, password, db);
        else
        {
            int currentGames = getGames(name, db)+1;
            cv.put(NUM_OF_GAMES, currentGames);
            db.update(TABLE_NAME, cv, PLAYER_NAME + " = '" + name + "';", null);
        }
        db.close();
    }
    public boolean playerExists(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + PLAYER_NAME +"= ? " , new String[]{name});
        boolean found = false;
        if(c!=null && c.moveToFirst()){
            found = true;
        }
        if (c != null)
        {
            c.close();
        }
        db.close();
        return found;
    }
    public boolean playerExists(String name, SQLiteDatabase db){
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + PLAYER_NAME +" = '" + name + "';", null);
        boolean found = false;
        if(c!=null && c.moveToFirst()){
            found = true;
        }
        if (c != null)
        {
            c.close();
        }
        return found;
    }
    public boolean checkPass(String name, String password){
        if (!playerExists(name)){
            return true;
        }
        if(password.equals(getPassword(name))){
            return true;
        }
        return false;
    }
    public int getGames(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT " + NUM_OF_GAMES + " FROM " + TABLE_NAME + " WHERE " + PLAYER_NAME + " = '" + name + "'", null);
        if(c != null && c.moveToFirst()){
            return c.getInt(0);
        }
        if(c!=null){
            c.close();
        }
        db.close();
        return 0;
    }
    public int getGames(String name, SQLiteDatabase db) {
        Cursor c = db.rawQuery("SELECT " + NUM_OF_GAMES + " FROM " + TABLE_NAME + " WHERE " + PLAYER_NAME + " = '" + name + "'", null);
        if(c != null && c.moveToFirst()){
            return c.getInt(0);
        }
        if(c!=null){
            c.close();
        }
        return 0;
    }

    public String getPassword(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT " + PASSWORD + " FROM " + TABLE_NAME + " WHERE " + PLAYER_NAME + " = '" + name + "'", null);
        if(c != null && c.moveToFirst()){
            return c.getString(0);
        }
        if(c!=null){
            c.close();
        }
        db.close();
        return "";
    }
    public String getPassword(String name, SQLiteDatabase db) {
        Cursor c = db.rawQuery("SELECT " + PASSWORD + " FROM " + TABLE_NAME + " WHERE " + PLAYER_NAME + " = '" + name + "'" , null);
        if(c != null && c.moveToFirst()){
            return c.getString(0);
        }
        if(c!=null){
            c.close();
        }
        return "";
    }
    public void removePlayer(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, PLAYER_NAME + " = '" + name + "'", null);
        db.close();
    }
    public void clearPlayers(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }
    public ArrayList<String> getNames(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<String> names = new ArrayList<String>();
        if(c != null & c.moveToFirst()){
            int nameIndex = c.getColumnIndex(PLAYER_NAME);
            while(!c.isAfterLast()){
                names.add(c.getString(nameIndex));
                c.moveToNext();
            }
        }
        if(c != null){
            c.close();
        }
        db.close();
        return names;
    }
    public ArrayList<Integer> getGames(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<Integer> names = new ArrayList<Integer>();
        if(c != null & c.moveToFirst()){
            int gamesIndex = c.getColumnIndex(NUM_OF_GAMES);
            while(!c.isAfterLast()){
                names.add(c.getInt(gamesIndex));
                c.moveToNext();
            }
        }
        if(c != null){
            c.close();
        }
        db.close();
        return names;
    }
    public ArrayList<Player> getPlayersInfo()
    {
        ArrayList<Player> players = new ArrayList<Player>();
        Player p;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (c != null && c.moveToFirst())
        {
            int nameIndex = c.getColumnIndex(PLAYER_NAME);
            int scoreIndex = c.getColumnIndex(NUM_OF_GAMES);
            int passwordIndex = c.getColumnIndex(PASSWORD);
            while (!c.isAfterLast()) {
                p = new Player(c.getString(nameIndex), c.getString(passwordIndex),
                        c.getInt(scoreIndex));
                players.add(p);
                c.moveToNext();
            }
        }
        if (c != null)
        {
            c.close();
        }
        db.close();
        return players;
    }
}
