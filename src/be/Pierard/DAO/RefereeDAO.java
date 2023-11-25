package be.Pierard.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import be.Pierard.pojo.Referee;

public class RefereeDAO extends DAO<Referee>{
	public RefereeDAO(Connection conn){
		super(conn);
	}
	
	public boolean create(Referee obj){		
		return false;
	}
	
	public boolean delete(Referee obj){
		return false;
	}
	
	public boolean update(Referee obj){
		return false;
	}
	
	public Referee find(int id){
		Referee referee = new Referee();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Referee WHERE RefereeId = " + id);
			if(result.first())
				referee = new Referee(result.getString("RefereeFirstname"), result.getString("RefereeLastname"),
						result.getString("RefereeNationality"));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return referee;
	}
}
