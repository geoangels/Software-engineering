package model;

import java.util.ArrayList;

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

	public abstract void export();

}
