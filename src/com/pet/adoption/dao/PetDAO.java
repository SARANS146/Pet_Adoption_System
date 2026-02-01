package com.pet.adoption.dao;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.pet.adoption.bean.Pet;
import com.pet.adoption.util.DBUtil;

public class PetDAO {
	public Pet findPet(String petID) {

	    String query = "SELECT * FROM PET_TBL WHERE PET_ID=?";
	    Pet pet = null;

	    try (Connection con = DBUtil.getDBConnection();
	         PreparedStatement ps = con.prepareStatement(query)) {

	        ps.setString(1, petID);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            pet = new Pet();
	            pet.setPetID(rs.getString("PET_ID"));
	            pet.setName(rs.getString("NAME"));
	            pet.setSpecies(rs.getString("SPECIES"));
	            pet.setBreed(rs.getString("BREED"));
	            pet.setAge(rs.getInt("AGE"));
	            pet.setMedicalStatus(rs.getString("MEDICAL_STATUS"));
	            pet.setAvailable(rs.getString("AVAILABLE"));
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return pet;
	}
	
	public List<Pet> viewAllPets()
	{
		   List<Pet> petList = new ArrayList<>();
		Connection connection = DBUtil.getDBConnection();
		String query="SELECT * FROM PET_TBL";
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			 while(rs.next())
					 {
				 Pet pet = new Pet();

		            pet.setPetID(rs.getString("PET_ID"));
		            pet.setName(rs.getString("PET_NAME"));
		            pet.setBreed(rs.getString("BREED"));
		            pet.setSpecies(rs.getString("PET_SPECIES"));
		            pet.setAge(rs.getInt("AGE"));
		            pet.setMedicalStatus(rs.getString("MEDICAL STATUS"));
		            pet.setAvailable(rs.getString("AVAILABLE"));
		            
		            petList.add(pet);
					 }
	}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return petList;
		
}
	
	public boolean insertPet(Pet pet) {
		Connection connection = DBUtil.getDBConnection();
		String query="insert into PET_TBL valuES(?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			 ps.setString(1, pet.getPetID());
		        ps.setString(2, pet.getName());
		        ps.setString(3, pet.getSpecies());
		        ps.setString(4, pet.getBreed());
		        ps.setInt(5, pet.getAge());
		        ps.setString(6, pet.getMedicalStatus());
		        ps.setString(7, pet.getAvailable());
			 int rows =ps.executeUpdate();
			 if(rows>0) {
				 return true;
			 }
			
			}
			catch(SQLException e){
				e.printStackTrace();
				return false;
			}
			return false;


	}
	public boolean updateAvailability(String petID,String available)
	{
		Connection connection = DBUtil.getDBConnection();
		String query="UPDATE PET_TBL SET AVAILABLE=? WHERE PET_ID=?";
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1, petID);
			ps.setString(2, available);
			int rows=ps.executeUpdate();
			if(rows>0)
			{
				return true;
			}
			

	}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public boolean updateMedicalStatus(String petID,String medicalStatus) {
		Connection connection = DBUtil.getDBConnection();
		String query="UPDATE PET_TBL SET MEDICAL_STATUS=? WHERE PET_ID=?";
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1, medicalStatus);
			ps.setString(2, petID);
			int rows=ps.executeUpdate();
			if(rows>0)
			{
				return true;
			}
			

	}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public boolean deletePet(String petID) {
		Connection connection = DBUtil.getDBConnection();
		String query="DELETE FROM PET_TBL WHERE PET_ID=?";
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1, petID);
			int rows=ps.executeUpdate();
			if(rows>0) {
				return true;
			}

	}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		return false;
	}
}
