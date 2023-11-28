package be.Pierard.pojo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Match {
	private LocalDateTime date;
	private int duration;
	private int round;
	private Schedule schedule;
	private Court court;
	private ArrayList<Set> listSet;
	private Opponent opp1;
	private Opponent opp2;
	private Referee referee;
	
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public Schedule getSchedule() {
		return schedule;
	}
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	public Court getCourt() {
		return court;
	}
	public void setCourt(Court court) {
		this.court = court;
	}
	public ArrayList<Set> getListSet() {
		return listSet;
	}
	public void setListSet(ArrayList<Set> listSet) {
		if (listSet.size() <= 5) {
            this.listSet = listSet;
        } 
		else {
            throw new IllegalArgumentException("La liste de sets ne peut pas depasser 5 sets.");
        }
	}
	public Opponent getOpp1() {
		return opp1;
	}
	public void setOpp1(Opponent opp1) {
		this.opp1 = opp1;
	}
	public Opponent getOpp2() {
		return opp2;
	}
	public void setOpp2(Opponent opp2) {
		this.opp2 = opp2;
	}
	public Referee getReferee() {
		return referee;
	}
	public void setReferee(Referee referee) {
		this.referee = referee;
	}
	public Match(LocalDateTime date, int duration, int round, Schedule schedule) {
		super();
		this.date = date;
		this.duration = duration;
		this.round = round;
		this.schedule = schedule;
        this.listSet = new ArrayList<>();
        setOpp1(new Opponent());
        setOpp2(new Opponent());
	}
	public Match(LocalDateTime date, int duration, int round, Schedule schedule, 
			Opponent opp1, Opponent opp2) {
		super();
		this.date = date;
		this.duration = duration;
		this.round = round;
		this.schedule = schedule;
		this.opp1 = opp1;
        this.opp2 = opp2;
        this.listSet = new ArrayList<>();
	}
	
	public Match(LocalDateTime date, int duration, int round, Schedule schedule, Court court, ArrayList<Set> listSet,
			Opponent opp1, Opponent opp2, Referee referee) {
		super();
		this.date = date;
		this.duration = duration;
		this.round = round;
		this.schedule = schedule;
		this.court = court;
		setListSet(listSet);
		this.opp1 = opp1;
		this.opp2 = opp2;
		this.referee = referee;
	}
	public Opponent GetWinner() {
		int sumScoreOp1 = 0;
		int sumScoreOp2 = 0;
		for(Set set : listSet)
		{
			if(set.getWinner()== opp1)
				sumScoreOp1++;
			else
				sumScoreOp2++;
		}
		if(sumScoreOp1 > sumScoreOp2)
			return opp1;
		else
			return opp2;
	}
	public Opponent Play(int nbrSets, ArrayList<Referee> listReferee, ArrayList<Court>listCourt) {
		Court court = new Court();
		court = court.Available(listCourt);
		Referee referee = new Referee();
		referee = referee.Available(listReferee);
	    if(court==null)
	    {
	    	court.Release(listCourt);
	    	court = court.Available(listCourt);
	    }
	    setCourt(court);
    	if(referee==null)
    	{
    		referee.Release(listReferee);
    		referee = referee.Available(listReferee);
    	}
    	setReferee(referee);
    	
    	for(int i = 0; i < nbrSets-1;i++)
    	{
    		Set set = new Set(0,0,this);
    		set.Play();
    		listSet.add(set);
    	}
    	SuperTieBreak superTieBreak = new SuperTieBreak(0,0,this);
    	superTieBreak.Play();
		listSet.add(superTieBreak);

		return GetWinner();
	}
	@Override
	public String toString() {
	    return "Court [nbSpectators=" + court.getNbSpectators() + ", covered=" + court.getCovered() + 
	    		", tournament=" + schedule.getTournament().getName() + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(court, date, duration, listSet, opp1, opp2, referee, round, schedule);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Match other = (Match) obj;
		return Objects.equals(court, other.court) && Objects.equals(date, other.date) && duration == other.duration
				&& Objects.equals(listSet, other.listSet) && Objects.equals(opp1, other.opp1)
				&& Objects.equals(opp2, other.opp2) && Objects.equals(referee, other.referee) && round == other.round
				&& Objects.equals(schedule, other.schedule);
	}
}