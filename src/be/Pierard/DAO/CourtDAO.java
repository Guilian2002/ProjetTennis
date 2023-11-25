package be.Pierard.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import be.Pierard.pojo.Court;

public class CourtDAO extends DAO<Court>{
	public CourtDAO(Connection conn){
		super(conn);
	}
	
	public boolean create(Court obj){		
		return false;
	}
	
	public boolean delete(Court obj){
		return false;
	}
	
	public boolean update(Court obj){
		return false;
	}
	
	public Court find(int id){
		Court court = new Court();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Court WHERE CourtId = " + id);
			if(result.first())
				court = new Court(result.getInt("NbSpectator"), result.getBoolean("Covered"));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return court;
	}
}
