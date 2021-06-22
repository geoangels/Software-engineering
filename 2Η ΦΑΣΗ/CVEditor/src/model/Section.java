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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Section other = (Section) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else {
			for(Item it:this.items){
				if(!other.items.contains(it)){
					return false;
				}
			}
		}
		return true;
	}


	@Override
	public ArrayList<Item> getItemList() {
		return items;
	}


	@Override
	public String getListableTitle() {
		return description;
	}


	@Override
	public String toString() {
		return "Section [description=" + description + ", items=" + items + "]";
	}
	
	

}
