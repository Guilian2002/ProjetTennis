package be.Pierard.pojo;

import java.util.ArrayList;
import java.util.Objects;

public class Opponent {
	private Player player1;
    private Player player2;
    private ArrayList<Set> listSet;
    private ArrayList<Match> listMatch;

    public Player getPlayerOne() {
        return player1;
    }

    public void setPlayerOne(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayerTwo() {
        return player2;
    }

    public void setPlayerTwo(Player player2) {
        this.player2 = player2;
    }

    public ArrayList<Set> getListSet() {
        return listSet;
    }

    public void setListSet(ArrayList<Set> listSet) {
        this.listSet = listSet;
    }

    public ArrayList<Match> getListMatch() {
        return listMatch;
    }

    public void setListMatch(ArrayList<Match> listMatch) {
        this.listMatch = listMatch;
    }

    public Opponent(Player player, ArrayList<Set> listSet, ArrayList<Match> listMatch) {
        this.player1 = player;
        this.listSet = listSet;
        this.listMatch = listMatch;
    }
    public Opponent(Player player1, Player player2, ArrayList<Set> listSet, ArrayList<Match> listMatch) {
        this.player1 = player1;
        this.player2 = player2;
        this.listSet = listSet;
        this.listMatch = listMatch;
    }
    public Opponent(Player player) {
        this.player1 = player;
        this.listSet = new ArrayList<>();
        this.listMatch = new ArrayList<>();
    }
    public Opponent(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.listSet = new ArrayList<>();
        this.listMatch = new ArrayList<>();
    }

	public Opponent() {}

	@Override
	public String toString() {
		return "Opponent [player1=" + player1 + ", player2=" + player2 + ", listSet=" + listSet + ", listMatch="
				+ listMatch + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(listMatch, listSet, player1, player2);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Opponent other = (Opponent) obj;
		return Objects.equals(listMatch, other.listMatch) && Objects.equals(listSet, other.listSet)
				&& Objects.equals(player1, other.player1) && Objects.equals(player2, other.player2);
	}
}
