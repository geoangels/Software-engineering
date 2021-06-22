package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Item implements Listable{
	
	private String title;
	private Date date;
	private ArrayList<Item> items;
	
	public Item(String title) {
		super();
		this.title = title;
		this.date = null;
		items = new ArrayList<Item>();
	}
	
	

	public Item(String title, Date date) {
		super();
		this.title = title;
		this.date = date;
		items = new ArrayList<Item>();
	}

	

	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}


	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	
	@Override
	public boolean equals(Object obj) {
		Item other = (Item) obj;
		
		if(title.trim().equals(other.getTitle().trim())){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public String toString() {
		String d = "";
		if(date !=null){
			DateFormat df = new SimpleDateFormat("yyyy-MM-DD");
			d = ", "+df.format(date);
		}
		return title +d;
	}

	@Override
	public ArrayList<Item> getItemList() {
		return items;
		
	}



	@Override
	public String getListableTitle() {
		return title;
	}
	
	

}
