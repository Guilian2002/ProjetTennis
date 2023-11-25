package be.Pierard.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import be.Pierard.pojo.Player;

public class PlayerDAO extends DAO<Player>{
	public PlayerDAO(Connection conn){
		super(conn);
	}
	
	public boolean create(Player obj){		
		return false;
	}
	
	public boolean delete(Player obj){
		return false;
	}
	
	public boolean update(Player obj){
		return false;
	}
	
	public Player find(int id){
		Player player = new Player();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Player WHERE PlayerId = " + id);
			if(result.first())
				player = new Player(result.getString("PlayerFirstname"), result.getString("PlayerLastname"),
						result.getString("PlayerNationality"), id, result.getString("Gender"));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return player;
	}
}
