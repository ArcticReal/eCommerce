package com.companyname.skytalaPlugin.services;

public class Product {

	private String name;
	private String id;
	
	Product(String newName, String newId){
		setName(newName);
		setId(newId);
	}

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}
	
}
