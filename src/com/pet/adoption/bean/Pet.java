package com.pet.adoption.bean;

public class Pet {

    private String petID;
    private String name;
    private String species;
    private String breed;
    private int age;
    private String medicalStatus;
    private String available;

    public Pet() {
    }

    public Pet(String petID, String name, String species, String breed, int age,
               String medicalStatus, String available) {

        this.petID = petID;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.age = age;
        this.medicalStatus = medicalStatus;
        this.available = available;
        
        
    }

	public String getPetID() {
		return petID;
	}

	public void setPetID(String petID) {
		this.petID = petID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMedicalStatus() {
		return medicalStatus;
	}

	public void setMedicalStatus(String medicalStatus) {
		this.medicalStatus = medicalStatus;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	@Override
	public String toString() {
		return "Pet [petID=" + petID + ", name=" + name + ", species=" + species + ", breed=" + breed + ", age=" + age
				+ ", medicalStatus=" + medicalStatus + ", available=" + available + "]";
	}
	
	

}
