package model;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public abstract class BasicCv {
	
	private String name;
	private String mobile;
	private String phone;
	private String address;
	private String mail;
	private ArrayList<Section> sections;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public BasicCv(String name, String mobile, String phone, String address, String mail) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.phone = phone;
		this.address = address;
		this.mail = mail;
		
		sections = new ArrayList<>();
		sections.add(new Section("Proffessional Profile"));
		sections.add(new Section("Skills and Experience"));
		sections.add(new Section("Career"));
		sections.add(new Section("Education and trainning"));
		sections.add(new Section("Further cources"));
		sections.add(new Section("Additional information"));
		sections.add(new Section("Personal interests"));
	}
	
	
	
	public ArrayList<Section> getSections() {
		return sections;
	}
	
	protected void generalInfoToFile(PrintWriter writer){
		writer.println("1. GENERAL INFORMATION");
	    writer.println("Name: "+getName());
	    writer.println("Address: "+getAddress());
	    writer.println("Telephone: (Home) "+getPhone() + " (Mobile) "+getMobile());
	    writer.println("Email: "+getMail());
	}
	
	protected void latexInfo(PrintWriter writer){
		writer.println("\\section{GENERAL INFORMATION}");
	    writer.println("Name: "+getName());
	    writer.println("Address: "+getAddress());
	    writer.println("Telephone: (Home) "+getPhone() + " (Mobile) "+getMobile());
	    writer.println("Email: "+getMail());
	}
	
	protected void proffessionalProfile(PrintWriter writer){
		writer.println("2. PROFESSIONAL PROFILE");
	    Section professionalProfile = getSections().get(0);
	    for(Item item: professionalProfile.getItems()){
	    	writer.println(item.toString());
	    }
	}
	
	protected void skillsToFile(PrintWriter writer){
		Section skills = getSections().get(1);
		writer.println("3. SKILLS AND EXPERIENCE");
		writer.println("");
		int i = 1;
		for(Item item: skills.getItems()){
			writer.println("\t3."+i+" SKILLS AND EXPERIENCE ON "+ item.getTitle());
			printSubItems(writer, item, 2);
			i++;
		}
	}
	
	protected void latexSkills(PrintWriter writer){
		writer.println("\\section{SKILLS AND EXPERIENCE}");
	    writer.println("\\begin{itemize}");
	    for(Item item: getSections().get(1).getItems()){
	    	writer.println("\\item SKILLS AND EXPERIENCE ON "+item.getTitle());
	    	if(item.getItems().size()>0){
	    		writer.println("\\begin{itemize}");
	    		for(Item it:item.getItems()){
	    			latexItemAndSubitems(it, writer);
	    		}
	    		writer.println("\\end{itemize}");
	    	}
	    }
	    writer.println("\\end{itemize}");
	    
	    
	}

	protected void printSubItems(PrintWriter writer, Listable item, int tabs) {
		for(int i=0;i<tabs;i++){
			writer.print("\t");
		}
		writer.println(item.getListableTitle());
		for(Item subitem: item.getItemList()){
			printSubItems(writer, subitem, tabs+1);
		}
	}
	
	protected void education(PrintWriter writer){
		writer.println("5. EDUCATION AND TRAINNING");
		for(Item item: getSections().get(3).getItems()){
			writer.print("\t"+item.getTitle());
			if(item.getDate() != null){
				DateFormat df = new SimpleDateFormat("yyyy-MM-DD");
				writer.println(", "+df.format(item.getDate()));
			}else{
				writer.println();
			}
		}
	}
	
	protected void courses(PrintWriter writer){
		writer.println("6. FURTHER COURSES");
		for(Item item: getSections().get(4).getItems()){
			writer.print("\t"+item.getTitle());
			if(item.getDate() != null){
				DateFormat df = new SimpleDateFormat("yyyy-MM-DD");
				writer.println(", "+df.format(item.getDate()));
			}else{
				writer.println();
			}
		}
	}
	
	protected void information(PrintWriter writer){
		writer.println("7. ADDITIONAL INFORMATION");
		for(Item item: getSections().get(5).getItems()){
			writer.println("\t"+item.getTitle());
		}
	}
	
	protected void interest(PrintWriter writer){
		writer.println("8. INTERESTS");
		for(Item item: getSections().get(6).getItems()){
			writer.println("\t"+item.getTitle());
		}
	}
	
	protected void career(PrintWriter writer){
		Section career = getSections().get(2);
		writer.println("4. PROFFESSIONAL EXPERIENCE");
		writer.println("");

		for(Item item: career.getItems()){
			writer.print("\t"+item.getTitle());
			if(item.getDate() != null){
				DateFormat df = new SimpleDateFormat("yyyy-MM-DD");
				writer.println(", "+df.format(item.getDate()));
			}else{
				writer.println();
			}
			printSubItems(writer, item, 2);

		}
	}
	
	protected void latexItemAndSubitems(Item item, PrintWriter writer){
		writer.print("\\item "+item.getTitle());
		
		if(item.getDate()!=null){
			DateFormat df = new SimpleDateFormat("yyyy-MM-DD");
			writer.println(", "+df.format(item.getDate()));
		}else{
			writer.println();
		}
		
		if(item.getItems().size()>0){
			writer.println("\\begin{itemize}");
			for(Item it:item.getItems()){
				latexItemAndSubitems(it, writer);
			}
			writer.println("\\end{itemize}");
		}
	}
	
	protected void latexSimpleItem(Item item, PrintWriter writer){
		writer.print("\\item "+item.getTitle());
		DateFormat df = new SimpleDateFormat("yyyy-MM-DD");	
		if(item.getDate()!=null){
			writer.println(", "+df.format(item.getDate()));
		}else{
			writer.println();
		}
	}
	
	
	public BasicCv differencesCV(BasicCv other){
		
		BasicCv differences = null;
		
		if(!this.name.equals(other.getName())){
			JOptionPane.showMessageDialog(null, "Different name");
			return null;
		}
		
		if(this instanceof Chronological){
			differences = new Chronological(name, mobile, phone, address, mail);
		}else if(this instanceof Functional){
			differences = new Functional(name, mobile, phone, address, mail);
		}else{
			differences = new Combined(name, mobile, phone, address, mail);
		}
		
		for(int i=0;i<sections.size();i++){
			Section thisSection = sections.get(i);
			Section otherSection = other.sections.get(i);
			
			for(Item item: otherSection.getItems()){
				if(!thisSection.getItems().contains(item)){
					differences.getSections().get(i).getItems().add(item);
				}
			}
			
		}
		
		return differences;
	}
	
	
	public void merge(BasicCv differences){
		for(int i=0;i<sections.size();i++){
			Section thisSection = sections.get(i);
			for(Item item: differences.getSections().get(i).getItems()){
				thisSection.getItems().add(item);
			}
		}
	}
	
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		BasicCv other = (BasicCv) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sections == null) {
			if (other.sections != null)
				return false;
		} else if (!sections.equals(other.sections))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
	}

	public abstract void export();
	public abstract void latex();

}
