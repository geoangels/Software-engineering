package model;

import java.io.IOException;
import java.io.PrintWriter;

public class Chronological extends BasicCv{

	public Chronological(String name, String mobile, String phone, String address, String mail) {
		super(name, mobile, phone, address, mail);
	}
	
	@Override
	protected void skillsToFile(PrintWriter writer) {
		Section skills = getSections().get(1);
		writer.println("3. CORE STRENGTHS");
		writer.println("");
		for(Item item: skills.getItems()){
			writer.println("\t"+ item.getTitle());
		}
	}

	@Override
	public void export() {
		try{
		    PrintWriter writer = new PrintWriter(getName()+".txt", "UTF-8");
		    generalInfoToFile(writer);
		    writer.println("");
		    proffessionalProfile(writer);
		    writer.println("");
		    
		    skillsToFile(writer);
		    writer.println("");
		    career(writer);
		    
		    writer.println("");
		    education(writer);
		    
		    writer.println("");
		    courses(writer);
		    
		    writer.println("");
		    information(writer);
		    
		    writer.println("");
		    interest(writer);
		    
		    writer.close();
		} catch (IOException e) {
		   // do something
		}	
	}

	public void latex(){
		try{
		    PrintWriter writer = new PrintWriter(getName()+".tex", "UTF-8");
		    
		    writer.println("\\documentclass{article}");
		    writer.println("\\begin{document} ");
		    
		    latexInfo(writer);
		    
		    writer.println("\\section{PROFFESSIONAL PROFILE}");
		    if(getSections().get(0).getItems().size()>0){
			    writer.println("\\begin{itemize}");
			    for(Item item : getSections().get(0).getItems()){
			    	latexItemAndSubitems(item, writer);
			    }
			    writer.println("\\end{itemize}");
		    }
		    
		    writer.println("\\section{CORE STRENGTHS}");
		    if(getSections().get(1).getItems().size()>0){
			    writer.println("\\begin{itemize}");
			    for(Item item : getSections().get(1).getItems()){
			    	latexItemAndSubitems(item, writer);
			    }
			    writer.println("\\end{itemize}");
		    }
		    
		    writer.println("\\section{PROFFESSIONAL EXPERIENCE}");
		    if(getSections().get(2).getItems().size()>0){
			    writer.println("\\begin{itemize}");
			    for(Item item : getSections().get(2).getItems()){
			    	latexItemAndSubitems(item, writer);
			    }
			    writer.println("\\end{itemize}");
		    }
		    
		    writer.println("\\section{EDUCATION AND TRAINNING}");
		    if(getSections().get(3).getItems().size()>0){
			    writer.println("\\begin{itemize}");
			    for(Item item : getSections().get(3).getItems()){
			    	latexItemAndSubitems(item, writer);
			    }
			    writer.println("\\end{itemize}");
		    }
		    
		    writer.println("\\section{FURTHER COURSES}");
		    if(getSections().get(4).getItems().size()>0){
			    writer.println("\\begin{itemize}");
			    for(Item item : getSections().get(4).getItems()){
			    	latexItemAndSubitems(item, writer);
			    }
			    writer.println("\\end{itemize}");
		    }
		    
		    writer.println("\\section{ADDITIONAL INFORMATION}");
		    if(getSections().get(5).getItems().size()>0){
			    writer.println("\\begin{itemize}");
			    for(Item item : getSections().get(5).getItems()){
			    	latexItemAndSubitems(item, writer);
			    }
			    writer.println("\\end{itemize}");
		    }
		    
		    writer.println("\\section{INERESTS}");
		    if(getSections().get(6).getItems().size()>0){
			    writer.println("\\begin{itemize}");
			    for(Item item : getSections().get(6).getItems()){
			    	latexItemAndSubitems(item, writer);
			    }
			    writer.println("\\end{itemize}");
		    }
    
		    
		    writer.println("\\end{document} ");
		    writer.close();
		} catch (IOException e) {
		   // do something
		}	
	}
	
}
