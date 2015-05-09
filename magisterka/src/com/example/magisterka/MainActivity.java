package com.example.magisterka;

import java.io.IOException;

import com.example.magisterka.Inventory.MyDragListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.view.*;
import android.content.Intent;
import android.database.SQLException;
import android.widget.*;
import android.widget.TableRow.LayoutParams;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        

        PrepareDataBase();
 	
      
    }
    
    public void UpdateDataBase(View view){//prze�adowanie bazy nawet je�li urochomiony jest program po raz kolejny
    	SqlHelper sqlReader = new SqlHelper(this);
    	sqlReader.UpdateDataBaseOnDevice();
    }
    
	public void PrepareDataBase(){//oranie///////////////////////////////////////
		SqlHelper myDbHelper;// = new SqlHelper();
        myDbHelper = new SqlHelper(this);
 
        try {
            	if(myDbHelper.createDataBase()){
            		ChangeText2("nowa baza danych");
            	}else{
            		ChangeText2("baza danych juz istnieje");
            	}
     	} catch (IOException ioe) {
     		throw new Error("Unable to create database");
     	}
     	try {
     		myDbHelper.openDataBase();
     	}catch(SQLException sqle){
     		throw sqle;
     	}
	}
	
    public void ChangeText(View view)
    {
    	 TextView text = (TextView)findViewById(R.id.txtItemDescription);
         text.setText("aaaa");
         
         Intent intent = new Intent(this, CodeLocker.class);
         startActivity(intent);
    }
    public void ChangeText2(String s)
    {
    	 TextView text = (TextView)findViewById(R.id.txtItemDescription);
         text.setText(s);
    }
    
    public void GoToInventory(View view){
    	Intent intent = new Intent(this, Inventory.class);
    	startActivity(intent);
    }
     
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
