package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ImportHelper {
	
	private static Item anadromi(Item parent, BufferedReader in, String ignore) throws IOException{
		String nextName;
		while((nextName = in.readLine()) != null){
			System.out.println(parent + " tabs: "+countTabs(parent.getTitle()));
			System.out.println(nextName+ " tabs: "+countTabs(nextName));
			if(nextName.equals("")){
				return null;
			}else if(nextName.contains(ignore)){
				continue;
			}else if(countTabs(parent.getTitle())+1==countTabs(nextName)){
				System.out.println("ena diafora");
				Item child = makeItem(nextName);
				parent.getItems().add(child);
				
			}else if(countTabs(parent.getTitle())+2==countTabs(nextName)){
				System.out.println("duo diafora");
				Item ch = makeItem(nextName);
				parent.getItems().get(parent.getItems().size()-1).getItems().add(ch);
				Item lostChild = anadromi(parent.getItems().get(parent.getItems().size()-1), in,ignore);
				if(lostChild == null){
					return null;
				}else if(countTabs(lostChild.getTitle()) == 1+countTabs(parent.getTitle())){
					parent.getItems().add(lostChild);
				}else{
					return lostChild;//finished with parent
				}
				
			}else if(countTabs(parent.getTitle())>countTabs(nextName)){
				System.out.println("back");
				Item lostChild = makeItem(nextName);
				return lostChild;
			}
			
		}
		
		return null;
	}
	
	public static Item makeItem(String nextName) {
		
		String []spl = nextName.split(", ");
		if(spl.length <=1){
			return new Item(nextName);
		}else{
			String last = spl[spl.length-1];
			try{
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date date = format.parse(last);
				String title = "";
				for(int i=0;i<spl.length-1;i++){
					title+=spl[i];
				}
				return new Item(title,date);
			}catch (Exception e){
				return new Item(nextName);
			}
		}
		
	}
	
	private static void clearTab(Item item){
		item.setTitle(item.getTitle().replaceAll("\t", ""));
		for(Item i : item.getItems()){
			clearTab(i);
		}
	}

	public static BasicCv readText(String path){
		try {
			
			BasicCv cv = null;
			
			boolean extendedSkills = false;
			String name = "",address = "",home = "",mobile = "",mail = "";
			ArrayList<Item> pProfile = new ArrayList<Item>();
			ArrayList<Item> skills = new ArrayList<Item>();
			
			BufferedReader in = new BufferedReader(new FileReader(path));
			
			String line;
			
			in.readLine();
			
			line = in.readLine();
			String [] spl = line.split("Name: ");
			if(spl.length>1){
				name = spl[1];
				System.out.println(name);
			}
			
			line = in.readLine();
			spl = line.split("Address:");
			if(spl.length>1){
				address = spl[1];
				System.out.println(address);
			}
			
			line = in.readLine();
			spl = line.split("Mobile");
			if(spl.length>1){
				mobile = spl[1].substring(1);
				System.out.println(mobile);
				String a[] = spl[0].split("Home");
				if(a.length>1){
					home = a[1].substring(1).replace("(", "");
					System.out.println(home);
				}
			}
			
			
			line = in.readLine();
			spl = line.split("Email:");
			if(spl.length>1){
				mail = spl[1];
				System.out.println(mail);
			}
			
			in.readLine();
			in.readLine();//2. PROFESSIONAL PROFILE
			
			while((line = in.readLine()) != null){
				if(line.equals("")){
					break;
				}
			    pProfile.add(new Item(line,null));
			}
			
			line = in.readLine();
			if(line.equals("3. SKILLS AND EXPERIENCE")){
				extendedSkills = true;
				in.readLine();
				Item parent = new Item("	parent");
				anadromi(parent, in,"SKILLS AND EXPERIENCE");
				clearTab(parent);
				skills = parent.getItems();
			}else{
				in.readLine();
				Item parent = new Item("parent");
				anadromi(parent, in,"nothingtoignore");
				clearTab(parent);
				skills = parent.getItems();
			}
			
			
			
			line = in.readLine();
			System.out.println(line+"----------------");
			if(line.equals("4. CAREER SUMMARY")){
				cv = new Functional(name, mobile, home, address, mail);
			}else if(line.equals("4. PROFFESSIONAL EXPERIENCE")){
				if(extendedSkills){
					cv = new Combined(name, mobile, home, address, mail);
					//in.readLine();
				}else{
					cv = new Chronological(name, mobile, home, address, mail);
					//in.readLine();
				}
			}
			
			cv.getSections().get(0).setItems(pProfile);
			cv.getSections().get(1).setItems(skills);
			
			Item parent = new Item("parent");
			if(line.equals("4. CAREER SUMMARY")){
				//TODO ?? parent
				anadromi(parent, in,"nothingtoignore");
				clearTab(parent);
				cv.getSections().get(2).setItems(parent.getItems());
			}else if(line.equals("4. PROFFESIONAL EXPERIENCE")){
				parent = new Item("parent");
				in.readLine();
				anadromi(parent, in,"nothingtoignore");
				clearTab(parent);
				cv.getSections().get(2).setItems(parent.getItems());
				
			}

			
			line = in.readLine();//education
			System.out.println(line+"===================");
			parent = new Item("parent");
			anadromi(parent, in,"nothingtoignore");
			clearTab(parent);
			cv.getSections().get(3).setItems(parent.getItems());
			
			in.readLine();//courses
			parent = new Item("parent");
			anadromi(parent, in,"nothingtoignore");
			clearTab(parent);
			cv.getSections().get(4).setItems(parent.getItems());
			
			in.readLine();//additional info
			parent = new Item("parent");
			anadromi(parent, in,"nothingtoignore");
			clearTab(parent);
			cv.getSections().get(5).setItems(parent.getItems());
			
			in.readLine();//interests
			parent = new Item("parent");
			anadromi(parent, in,"nothingtoignore");
			clearTab(parent);
			cv.getSections().get(6).setItems(parent.getItems());
			
			
			in.close();
			return cv;
			
		} catch (Exception e) {
			return null;
		} 
	}
	
	
	private static int countTabs(String s){
		int counter = 0;
		for( int i=0; i<s.length(); i++ ) {
		    if( s.charAt(i) == '\t' ) {
		        counter++;
		    } 
		}
		return counter;
	}
	
}
