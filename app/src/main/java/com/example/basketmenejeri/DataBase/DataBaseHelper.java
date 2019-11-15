package com.example.basketmenejeri.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.basketmenejeri.TransferPage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataBaseHelper extends SQLiteOpenHelper {
    String DB_Path = null;
    private static String DB_NAME = "BasketDataBasee";
    private SQLiteDatabase myDataBase;
    private final Context myContext;


    public DataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, Context myContext) {
        super(context, name, factory, version);
        this.myContext = myContext;
    }


    public DataBaseHelper(Context context){
        super(context, DB_NAME , null , 10);
        this.myContext = context;
        this.DB_Path = "/data/data/" + context.getPackageName() + "/" +"databases/";
        Log.e("Path 1", DB_Path);
    }




    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist){ }
        else {
            this.getReadableDatabase();
            try {
                copyDataBase();

            }
            catch (IOException e){
                throw new Error("Error copying DataBase");

            }

        }
    }
    public boolean checkDataBase(){
        SQLiteDatabase checkDB= null;
        try{
            String myPath = DB_Path + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READONLY);


        }
        catch (SQLException e){


        }

        if (checkDB != null){

            checkDB.close();

        }
        return checkDB !=null ? true : false;
    }


    private void copyDataBase() throws  IOException {
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_Path + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[10];
        int length;
        while ((length = myInput.read(buffer)) > 0 ) {
            myOutput.write(buffer, 0 ,length);


        }
        myOutput.flush();
        myOutput.close();
        myInput.close();



    }

    public void openDataBase() throws SQLException {

        String myPath = DB_Path + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READONLY);

    }
    @Override
    public synchronized void close(){
        if (myDataBase !=null){

            myDataBase.close();
        }
        super.close();

    }
    @Override
    public void onCreate(SQLiteDatabase db) { }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion>oldVersion)
            try {
                copyDataBase();
            }
            catch (IOException e){
                e.printStackTrace();
            }
    }

    public Cursor queryFB(String table, String[] colums , String selection , String[] selectionArgs , String groupBy, String having , String orderBy ){
        return myDataBase.query("PLAYERS",null,"Player_Team_ID = '100' ",null,null,null,null);
    }

    public Cursor queryGS(String table, String[] colums , String selection , String[] selectionArgs , String groupBy, String having , String orderBy ){
        return myDataBase.query("PLAYERS",null,"Player_Team_ID = '101' ",null,null,null,null);
    }
    public Cursor queryBJK(String table, String[] colums , String selection , String[] selectionArgs , String groupBy, String having , String orderBy ){
        return myDataBase.query("PLAYERS",null,"Player_Team_ID = '102' ",null,null,null,null);
    }
    public Cursor queryAEfes(String table, String[] colums , String selection , String[] selectionArgs , String groupBy, String having , String orderBy ){
        return myDataBase.query("PLAYERS",null,"Player_Team_ID = '103' ",null,null,null,null);
    }
    public Cursor queryNonTeam(String table, String[] colums , String selection , String[] selectionArgs , String groupBy, String having , String orderBy ){
        return myDataBase.query("PLAYERS",null,"Player_Team_ID = '200' ",null,null,null,null);
    }
    public Cursor Teams(String table, String[] colums , String selection , String[] selectionArgs , String groupBy, String having , String orderBy ){
        return myDataBase.query("TEAMS",null,null,null,null,null,null);
    }
    public Cursor FBahceteam(String table, String[] colums , String selection , String[] selectionArgs , String groupBy, String having , String orderBy ){
        return myDataBase.query("TEAMS",null,"Team_ID =100",null,null,null,null);
    }
    public Cursor AEfesteam(String table, String[] colums , String selection , String[] selectionArgs , String groupBy, String having , String orderBy ){
        return myDataBase.query("TEAMS",null,"Team_ID =103",null,null,null,null);
    }
    public Cursor GSarayteam(String table, String[] colums , String selection , String[] selectionArgs , String groupBy, String having , String orderBy ){
        return myDataBase.query("TEAMS",null,"Team_ID =101",null,null,null,null);
    }
    public Cursor BJKteam(String table, String[] colums , String selection , String[] selectionArgs , String groupBy, String having , String orderBy ){
        return myDataBase.query("TEAMS",null,"Team_ID =102",null,null,null,null);
    }
    public boolean updateData(String id,String point,String w,String l) {
        myDataBase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Team_ID",id);
        contentValues.put("Team_Point",point);
        contentValues.put("Team_W",w);
        contentValues.put("Team_L",l);
        myDataBase.update("TEAMS", contentValues, "Team_ID = ?",new String[] { id });
        return true;
    }
    public boolean updateDataTransfer(String id,String team_id) {
        myDataBase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Player_ID",id);
        contentValues.put("Player_Team_ID",team_id);
        myDataBase.update("PLAYERS", contentValues, "Player_ID = ?",new String[] { id });
        return true;
    }
    public boolean updateDataTeam(String id,String cost) {
        myDataBase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Team_ID",id);
        contentValues.put("Team_Cost",cost);
        myDataBase.update("TEAMS", contentValues, "Team_ID = ?",new String[] { id });
        return true;
    }

}
