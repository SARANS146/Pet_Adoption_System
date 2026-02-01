package com.pet.adoption.bean;

import java.sql.Date;

public class Adoption {
	private int adoptionlD;
	private String petID;
	private String adopterName;
	private String adopterContact;
	private Date adoptionDate;
	private String status;
	
	public Adoption(int adoption1D,String petID,String adopterName,
	String adopterContact,Date adoptionDate,String status, int adoptionlD)
	{
		    this.adoptionlD = adoptionlD;
	        this.petID = petID;
	        this.adopterName = adopterName;
	        this.adopterContact = adopterContact;
	        this.adoptionDate = adoptionDate;
	        this.status = status;
	}

	public int getAdoptionlD() {
		return adoptionlD;
	}

	public void setAdoptionlD(int adoptionlD) {
		this.adoptionlD = adoptionlD;
	}

	public String getPetID() {
		return petID;
	}

	public void setPetID(String petID) {
		this.petID = petID;
	}

	public String getAdopterName() {
		return adopterName;
	}

	public void setAdopterName(String adopterName) {
		this.adopterName = adopterName;
	}

	public String getAdopterContact() {
		return adopterContact;
	}

	public void setAdopterContact(String adopterContact) {
		this.adopterContact = adopterContact;
	}

	public Date getAdoptionDate() {
		return adoptionDate;
	}

	public void setAdoptionDate(Date adoptionDate) {
		this.adoptionDate = adoptionDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Pet [adoptionlD=" + adoptionlD + ", petID=" + petID + ", adopterName=" + adopterName
				+ ", adopterContact=" + adopterContact + ", adoptionDate=" + adoptionDate + ", status=" + status + "]";
	}
	
	
	
	
}
