package be.Pierard.pojo;

import java.util.ArrayList;
import java.util.Objects;

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
	public void Available() {
		
	}
	public void Release() {
		
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
