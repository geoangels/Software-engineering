package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ImportLatexhelper {
	
	
	public static BasicCv readLatex(String path) {
		try{
			
			BasicCv cv = null;
			
			boolean extendedSkills = false;
			String name = "",address = "",home = "",mobile = "",mail = "";
			ArrayList<Item> pProfile = new ArrayList<Item>();
			ArrayList<Item> skills = new ArrayList<Item>();
			
			BufferedReader in = new BufferedReader(new FileReader(path));
			
			String line;
			in.readLine();
			in.readLine();
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
			
			
			in.readLine();//proffessional profile
			in.readLine();//begin
			pProfile = makeList(in);
			
			line = in.readLine();//skills
			if(line.contains("SKILLS AND EXPERIENCE")){
				extendedSkills = true;
			}
			in.readLine();//begin
			skills = makeList(in);
			
			
			line = in.readLine();//career
			System.out.println("................"+line);
			if(line.contains("CAREER SUMMARY")){
				cv = new Functional(name, mobile, home, address, mail);
			}else if(line.contains("PROFFESSIONAL EXPERIENCE")){
				if(extendedSkills){
					cv = new Combined(name, mobile, home, address, mail);
				}else{
					cv = new Chronological(name, mobile, home, address, mail);
				}
			}
			cv.getSections().get(0).setItems(pProfile);
			cv.getSections().get(1).setItems(skills);
			
			for(int i=2;i<7;i++){
				if(i>2)
					in.readLine();//
				in.readLine();//begin
				ArrayList<Item> temp = makeList(in);
				cv.getSections().get(i).setItems(temp);
			}
			
			return cv;
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		} 
		
		
		
	}
	
	
	private static ArrayList<Item> makeList(BufferedReader in) throws IOException{
		ArrayList<Item> myList = new ArrayList<Item>();
		String line;
		while((line = in.readLine()) != null ){
			System.out.println(line);
			if(line.contains("item ")){
				String name = line.substring(6);
				name = name.replaceAll("SKILLS AND EXPERIENCE ON ", "");
				Item item = ImportHelper.makeItem(name);
				myList.add(item);
			}else if(line.contains("begin")){
				ArrayList<Item> items = makeList(in);
				myList.get(myList.size()-1).setItems(items);
			}else if(line.contains("end")){
				return myList;
			}
		}
		
		return myList;
	}

}
