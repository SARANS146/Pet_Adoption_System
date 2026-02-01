package com.pet.adoption.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.pet.adoption.bean.Adoption;
import com.pet.adoption.util.DBUtil;

public class AdoptionDAO {
	public int generateAdoptionID() {
		Connection connection =DBUtil.getDBConnection();
		String query="select transaction_seq.NEXTVAL from dual";
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			rs.next();
			int seqNumber=rs.getInt(1);
			return seqNumber;
			
			}
		catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}	
	}
	
	public boolean recordAdoption(Adoption adoption) {
		Connection connection=DBUtil.getDBConnection();
		String query="INSERT INTO ADOPTION_TBL VALUES (?,?,?,?,?,?)";
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setInt(1,adoption.getAdoptionlD());
			ps.setString(2,adoption.getPetID());
			ps.setString(3,adoption.getAdopterName());
			ps.setString(4,adoption.getAdopterContact());
			ps.setDate(5,adoption.getAdoptionDate());
			ps.setString(6,adoption.getStatus());
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
	
	public boolean cancelAdoption(int adoptionID)
	{
		Connection connection=DBUtil.getDBConnection();
		String query="UPDATE ADOPTION_TBL SET STATUS='Cancelled' WHERE ADOPTION_ID=?";
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setInt(1, adoptionID);
			int rows=ps.executeUpdate();
			if(rows>0) {
				return true;
			}
			return false;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	 public boolean hasActiveAdoptions(String petID) {
	        String query = "SELECT COUNT(*) FROM ADOPTION_TBL WHERE PET_ID=? AND STATUS='Active'";
	        try (Connection connection = DBUtil.getDBConnection();
	             PreparedStatement ps = connection.prepareStatement(query)) {

	            ps.setString(1, petID);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                return rs.getInt(1) > 0; 
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
}
