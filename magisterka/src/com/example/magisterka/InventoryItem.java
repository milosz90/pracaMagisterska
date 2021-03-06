package com.example.magisterka;
import android.widget.ImageView;
import android.content.Context;

public class InventoryItem extends ImageView {
	private int _id;//baza danych PK /////////////////////do orania, to moze byc string
	private int idItem;//baza danych idItem
	private String name;
	private int slotNumber;
	private boolean isInUse;
	private String allowSlot;
	
	public InventoryItem(Context context, int _id) {
		super(context);
		idItem = _id;
	}
	
	public void Set_id(int id){
		_id = id;
	}
	public int Get_id(){
		return _id;
	}
	
	public void SetIdItem(int id){
		idItem = id;
	}
	public int GetIdItem(){
		return idItem;
	}
	
	public void SetName(String s){
		name = s;
	}
	public String GetName(){
		return name;
	}
	
	public void SetSlotNumber(int i){
		slotNumber = i;
	}
	public int GetSlotNumber(){
		return slotNumber;
	}
	
	public void SetInUse(boolean b){
		isInUse = b;
	}
	public boolean IsInUse(){
		return isInUse;
	}
	
	public void SetAllowSlot(String s){
		allowSlot = s;
	}
	public String GetAllowSlot(){
		return allowSlot;
	}
}
