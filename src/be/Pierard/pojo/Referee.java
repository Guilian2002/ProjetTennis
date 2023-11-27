package be.Pierard.pojo;

import java.util.ArrayList;
import java.util.Objects;

import be.Pierard.DAO.DAO;
import be.Pierard.DAO.RefereeDAO;
import be.Pierard.DAO.TennisConnection;

public class Referee extends Person{
	private ArrayList<Tournament> listTournament;
	private Match match;
	
	public ArrayList<Tournament> getListTournament() {
		return listTournament;
	}
	public void setListTournament(ArrayList<Tournament> listTournament) {
		this.listTournament = listTournament;
	}
	public Match getMatch() {
		return match;
	}
	public void setMatch(Match match) {
		this.match = match;
	}
	public Referee() {}
	public Referee(String firstname, String lastname, String nationality) {
		super(firstname, lastname, nationality);
	}
	public Referee(String firstname, String lastname, String nationality, ArrayList<Tournament> listTournament,
			Match match) {
		super(firstname, lastname, nationality);
		this.listTournament = listTournament;
		this.match = match;
	}
	public Referee Available(ArrayList<Referee> listReferee) {
	    Referee tempReferee = null;
	    for (int i = 0; i < listReferee.size(); i++) {
	        if (listReferee.get(i) != null) {
	            tempReferee = listReferee.get(i);
	            listReferee.set(i, null);
	            break;
	        }
	    }
	    return tempReferee;
	}

	public void Release(ArrayList<Referee> listReferee) {
	    DAO<Referee> refereeDAO = new RefereeDAO(TennisConnection.getInstance());
	    for (int i = 0; i < 20; i++) {
	        Referee referee = refereeDAO.find(i + 1);
	        listReferee.set(i, referee);
	    }
	}
	@Override
	public String toString() {
		return "Referee [listTournament=" + listTournament + ", match=" + match + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(listTournament, match);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Referee other = (Referee) obj;
		return Objects.equals(listTournament, other.listTournament) && Objects.equals(match, other.match);
	}
}
