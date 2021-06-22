package model;

import java.util.ArrayList;

public class Section implements Listable{
	
	private String description;
	private ArrayList<Item> items;
	
	
	public Section(String description) {
		super();
		this.description = description;
		items = new ArrayList<>();
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public ArrayList<Item> getItems() {
		return items;
	}


	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}


	@Override
	public ArrayList<Item> getItemList() {
		return items;
	}


	@Override
	public String getListableTitle() {
		return description;
	}
	
	

}
