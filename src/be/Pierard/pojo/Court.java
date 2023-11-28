package be.Pierard.pojo;

import java.util.ArrayList;
import java.util.Objects;

import be.Pierard.DAO.DAO;
import be.Pierard.DAO.TennisConnection;
import be.Pierard.DAO.CourtDAO
;
public class Court {
	private int nbSpectators;
	private boolean covered;
	private Tournament tournament;
	private Match match;
	public int getNbSpectators() {
		return nbSpectators;
	}
	public void setNbSpectators(int nbSpectators) {
		this.nbSpectators = nbSpectators;
	}
	public boolean getCovered() {
		return covered;
	}
	public void setCovered(boolean covered) {
		this.covered = covered;
	}
	public Tournament getTournament() {
		return tournament;
	}
	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}
	public Match getMatch() {
		return match;
	}
	public void setMatch(Match match) {
		this.match = match;
	}
	public Court() {}
	public Court(int nbSpectators, boolean covered) {
		super();
		this.nbSpectators = nbSpectators;
		this.covered = covered;
	}
	public Court(int nbSpectators, boolean covered, Tournament tournament) {
		super();
		this.nbSpectators = nbSpectators;
		this.covered = covered;
		this.tournament = tournament;
	}
	public Court(int nbSpectators, boolean covered, Tournament tournament, Match match) {
		super();
		this.nbSpectators = nbSpectators;
		this.covered = covered;
		this.tournament = tournament;
		this.match = match;
	}
	public Court Available(ArrayList<Court> listCourt) {
	    Court tempCourt = null;
	    for (int i = 0; i < listCourt.size(); i++) {
	        if (listCourt.get(i) != null) {
	            tempCourt = listCourt.get(i);
	            listCourt.set(i, null);
	            break;
	        }
	    }
	    return tempCourt;
	}

	public void Release(ArrayList<Court> listCourt) {
	    DAO<Court> courtDAO = new CourtDAO(TennisConnection.getInstance());
	    for (int i = 0; i < 20; i++) {
	        Court court = courtDAO.find(i + 1);
	        listCourt.set(i, court);
	    }
	}
	@Override
	public String toString() {
	    return "Court [nbSpectators=" + nbSpectators + ", covered=" + covered +
	           ", tournament=" + tournament.getName() + ", match=" + match + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(covered, match, nbSpectators, tournament);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Court other = (Court) obj;
		return covered == other.covered && Objects.equals(match, other.match) && nbSpectators == other.nbSpectators
				&& Objects.equals(tournament, other.tournament);
	}
}
