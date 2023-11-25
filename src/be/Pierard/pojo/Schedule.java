package be.Pierard.pojo;

import java.util.ArrayList;
import java.util.Objects;

public class Schedule {
    private ScheduleType type;
    private int actualRound;
    private Tournament tournament;
    private ArrayList<Match> listMatch;

    public ScheduleType getType() {
        return type;
    }

    public void setType(ScheduleType type) {
        this.type = type;
    }

    public int getActualRound() {
        return actualRound;
    }

    public void setActualRound(int actualRound) {
        this.actualRound = actualRound;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public ArrayList<Match> getListMatch() {
        return listMatch;
    }

    public void setListMatch(ArrayList<Match> listMatch) {
        if (listMatch != null && !listMatch.isEmpty()) {
            this.listMatch = listMatch;
        } else {
            throw new IllegalArgumentException("La liste de matches ne peut pas etre nulle.");
        }
    }

    public Schedule(ScheduleType type, int actualRound, Tournament tournament, ArrayList<Match> listMatch) {
        super();
        this.type = type;
        this.actualRound = actualRound;
        this.tournament = tournament;
        setListMatch(listMatch);
    }

    public void NbWinningSets() {
    	
    }

    public void PlayNextRound() {

    }

    public void GetWinner() {

    }

	@Override
	public String toString() {
		return "Schedule [type=" + type + ", actualRound=" + actualRound + ", tournament=" + tournament + ", listMatch="
				+ listMatch + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(actualRound, listMatch, tournament, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Schedule other = (Schedule) obj;
		return actualRound == other.actualRound && Objects.equals(listMatch, other.listMatch)
				&& Objects.equals(tournament, other.tournament) && type == other.type;
	}
}
