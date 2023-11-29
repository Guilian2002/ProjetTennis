package be.Pierard.pojo;

import java.util.ArrayList;
import java.util.Objects;

public class Player extends Person{
	private int rank;
	private String gender;
	private ArrayList<Opponent> listOpponent;
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public ArrayList<Opponent> getListOpponent() {
		return listOpponent;
	}
	public void setListOpponent(ArrayList<Opponent> listOpponent) {
		if (listOpponent != null && !listOpponent.isEmpty()) {
            this.listOpponent = listOpponent;
        } else {
            throw new IllegalArgumentException("La liste des opposants ne peut pas etre nulle.");
        }
	}
	public Player(){}
	public Player(String firstname, String lastname, String nationality, int rank, String gender) {
		super(firstname, lastname, nationality);
		this.rank = rank;
		this.gender = gender;
	}
	public Player(String firstname, String lastname, String nationality, int rank, String gender,
			ArrayList<Opponent> listOpponent) {
		super(firstname, lastname, nationality);
		this.rank = rank;
		this.gender = gender;
		setListOpponent(listOpponent);
	}
	@Override
	public String toString() {
		return getLastname() +" "+ getFirstname() +" " + getGender();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(gender, listOpponent, rank);
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
		Player other = (Player) obj;
		return Objects.equals(gender, other.gender) && Objects.equals(listOpponent, other.listOpponent)
				&& rank == other.rank;
	}
}
