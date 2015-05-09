//To trzeba mocno przepisa�. Jest skopiowane z neta i dziwne rzeczy z try chatch sie dzieja


package com.example.magisterka;

import java.io.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.*;
//import android.database.sqlite.SQLiteDatabase;

public class SqlHelper extends SQLiteOpenHelper{
    private static String DB_PATH;
    private static String DB_NAME = "test1";
 
    private SQLiteDatabase dataBase; 
    private final Context myContext;
    ///////////////////////do orania////////////////////////////////////////////////////////////
    public SqlHelper(Context context) {
    	super(context, DB_NAME, null, 1);
        this.myContext = context;
        DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
    }
    
    public boolean createDataBase() throws IOException{//sprawdza czy baza istnieje, jesli tak to nic nie robi
    	boolean result;
    	
    	boolean dbExist = checkDataBase();
 
    	if(dbExist){
    		result = false;
    	}else{
    		result = true;
        	this.getReadableDatabase();
 
        	try {
    			copyDataBase();
    		} catch (IOException e) {
        		throw new Error("Error copying database");
        	}
    	}
    	return result;
 
    }
    
    public void UpdateDataBaseOnDevice(){
    	try{
    	copyDataBase();
    	}catch (IOException e){
    		throw new Error("Error copying database");
    	}
    }
    
    private boolean checkDataBase(){//sprawdza czy baza ju� jest
    	SQLiteDatabase checkDB = null;
    	try{
    		String myPath = DB_PATH + DB_NAME;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    	}catch(SQLiteException e){
    		//database does't exist yet.
    	}
 
    	if(checkDB != null){
    		checkDB.close();
    	}
 
    	return checkDB != null ? true : false;
    }
 
    private void copyDataBase() throws IOException{
 
    	//Open your local db as the input stream
    	InputStream myInput = myContext.getAssets().open(DB_NAME);
 
    	// Path to the just created empty db
    	String outFileName = DB_PATH + DB_NAME;
 
    	//Open the empty db as the output stream
    	OutputStream myOutput = new FileOutputStream(outFileName);
 
    	//transfer bytes from the inputfile to the outputfile
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
 
    	//Close the streams
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
 
    }
 
    public void openDataBase() throws SQLException{//open database do odczytu
        String myPath = DB_PATH + DB_NAME;
    	dataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }
    
    public void openDataBaseWrite(){//open database do zapisu
        String myPath = DB_PATH + DB_NAME;
    	dataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }
 
    @Override
	public synchronized void close() {
 
    	    if(dataBase != null)
    		    dataBase.close();
 
    	    super.close();
 
	}
    ///////////do orania koniec///////////////////////////////////////////////////////////////////////////
    
	@Override
	public void onCreate(SQLiteDatabase db) {
 
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
 
	}

	public Cursor Query(String command, String[] values) {
		Cursor result;
		result = dataBase.rawQuery(command, values);
		return result;
	}
	
	public void Exec(String command, String[] values){
		dataBase.execSQL(command, values);
	}
	
	public void SaveTable(String table, ContentValues values, String where, String[] args){
		dataBase.update(table, values, where, args);
	}
	
	public void DeleteRow(String table, String whereClause, String[] whereArgs){
		dataBase.delete(table, whereClause, whereArgs);
	}
	
	public void Insert (String table, String nullColumnHack, ContentValues values){
		dataBase.insert(table, nullColumnHack, values);
	}
	
	public SQLiteDatabase GetDataBase(){
		return dataBase;
	}
}
