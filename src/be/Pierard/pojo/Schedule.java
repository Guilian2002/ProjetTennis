package be.Pierard.pojo;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

import be.Pierard.DAO.PlayerDAO;
import be.Pierard.DAO.RefereeDAO;
import be.Pierard.DAO.CourtDAO;
import be.Pierard.DAO.DAO;
import be.Pierard.DAO.TennisConnection;

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
    public Schedule(ScheduleType type, int actualRound, Tournament tournament) {
        super();
        this.type = type;
        this.actualRound = actualRound;
        this.tournament = tournament;
        this.listMatch = new ArrayList<Match>();
        if(type == ScheduleType.GentlemenSingle)
        	this.listMatch.add(new Match(LocalDateTime.now(),300,actualRound,this));
        else
        	this.listMatch.add(new Match(LocalDateTime.now(),180,actualRound,this));
    }
    public Schedule(ScheduleType type, int actualRound, Tournament tournament, ArrayList<Match> listMatch) {
        super();
        this.type = type;
        this.actualRound = actualRound;
        this.tournament = tournament;
        if(listMatch.size() == 0 && type == ScheduleType.GentlemenSingle)
        	listMatch.add(new Match(LocalDateTime.now(),300,actualRound,this));
        else
        	listMatch.add(new Match(LocalDateTime.now(),180,actualRound,this));
        setListMatch(listMatch);
    }

    public int NbWinningSets() {
    	if(type == ScheduleType.GentlemenSingle)
    		return 5;
    	else
    		return 3;
    }

    public void PlayNextRound() {
    	int[] matchNumberTab  = {64,32,16,8,4,2,1};
    	int nbrSets = NbWinningSets();
    	ArrayList<Player> listPlayer = new ArrayList<Player>();
    	ArrayList<Referee> listReferee = new ArrayList<Referee>();
    	ArrayList<Court> listCourt = new ArrayList<Court>();
        DAO<Player> playerDAO = new PlayerDAO(TennisConnection.getInstance());
        DAO<Court> courtDAO = new CourtDAO(TennisConnection.getInstance());
        DAO<Referee> refereeDAO = new RefereeDAO(TennisConnection.getInstance());
	    for (int i = 1; i <= 20; i++) {
	        Referee referee = refereeDAO.find(i);
	        listReferee.add(referee);
	        Court court = courtDAO.find(i);
	        listCourt.add(court);
	    }

        for (int i = 0; i < 256; i++) {
            Player player = playerDAO.find(i + 1);
            listPlayer.add(player);
        }
        Queue<Opponent> winnersQueue = new LinkedList<Opponent>();
        if(type == ScheduleType.GentlemenSingle || type == ScheduleType.LadiesSingle) {
        	for (int i = actualRound - 1; i < 7; i++) {
                for (int j = 0; j < matchNumberTab[i]; j++) {
                    if (listMatch.get(j) != null && i == 0) {
                        if (type == ScheduleType.GentlemenSingle) {
                            listMatch.get(j).setOpp1(new Opponent(listPlayer.get(j)));
                            listMatch.get(j).setOpp2(new Opponent(listPlayer.get(j + 1)));
                        } 
                        else{
                            listMatch.get(j).setOpp1(new Opponent(listPlayer.get(j + 128)));
                            listMatch.get(j).setOpp2(new Opponent(listPlayer.get(j + 129)));
                        }
                        winnersQueue.add(listMatch.get(j).Play(nbrSets,listReferee,listCourt));
                    }
                    else if (i == 0)
                    {
                    	if (type == ScheduleType.GentlemenSingle) {
                        	listMatch.add(new Match(LocalDateTime.now(), 300, i, this,
                            		new Opponent(listPlayer.get(j * 2 + 1)), 
                            		new Opponent(listPlayer.get(j * 2 + 2))));
                        }
                        else{
                        	listMatch.add(new Match(LocalDateTime.now(), 180, i, this,
                            		new Opponent(listPlayer.get(j * 2 + 129)), 
                            		new Opponent(listPlayer.get(j * 2 + 130))));
                        }
                    	winnersQueue.add(listMatch.get(j).Play(nbrSets,listReferee,listCourt));
                    }
                    else{
                    	if (type == ScheduleType.GentlemenSingle) {
                        	listMatch.add(new Match(LocalDateTime.now(), 300, i, this,
                            		winnersQueue.poll(), 
                            		winnersQueue.poll()));
                        }
                        else{
                        	listMatch.add(new Match(LocalDateTime.now(), 180, i, this,
                        			winnersQueue.poll(), 
                        			winnersQueue.poll()));
                        }
                    	winnersQueue.add(listMatch.get(j).Play(nbrSets,listReferee,listCourt));
                    }
                }
        	}
        }
    	else
    	{
    		for (int i = actualRound - 1; i < 6; i++) {
                for (int j = 0; j < matchNumberTab[i]; j++) {
                    if (listMatch.get(j) != null && i == 0) {
                        if (type == ScheduleType.GentlemenDouble) {
                            listMatch.get(j).setOpp1(new Opponent(listPlayer.get(j), listPlayer.get(j + 1)));
                            listMatch.get(j).setOpp2(new Opponent(listPlayer.get(j + 2), listPlayer.get(j + 3)));
                        } 
                        else if (type == ScheduleType.LadiesDouble) {
                            listMatch.get(j).setOpp1(new Opponent(listPlayer.get(j + 128), listPlayer.get(j + 129)));
                            listMatch.get(j).setOpp2(new Opponent(listPlayer.get(j+ 130), listPlayer.get(j + 131)));
                        } 
                        else {
                            listMatch.get(j).setOpp1(new Opponent(listPlayer.get(j), listPlayer.get(j + 128)));
                            listMatch.get(j).setOpp2(new Opponent(listPlayer.get(j + 1), listPlayer.get(j + 129)));
                        }
                        winnersQueue.add(listMatch.get(j).Play(nbrSets,listReferee,listCourt));
                    }
                    else if (i == 0)
                    {
                    	if (type == ScheduleType.GentlemenDouble) {
                            listMatch.add(new Match(LocalDateTime.now(), 180, i, this,
                            		new Opponent(listPlayer.get(j * 2 + 1), listPlayer.get(j * 2 + 2)), 
                            		new Opponent(listPlayer.get(j * 2 + 3), listPlayer.get(j * 2 + 4))));
                        } 
                    	else if (type == ScheduleType.LadiesDouble) {
                        	listMatch.add(new Match(LocalDateTime.now(), 180, i, this,
                            		new Opponent(listPlayer.get(j * 2 + 129), listPlayer.get(j * 2 + 130)), 
                            		new Opponent(listPlayer.get(j * 2 + 131), listPlayer.get(j * 2 + 132))));
                        }
                        else{
                        	listMatch.add(new Match(LocalDateTime.now(), 180, i, this,
                            		new Opponent(listPlayer.get(j * 2 + 1), listPlayer.get(j * 2 + 129)), 
                            		new Opponent(listPlayer.get(j * 2 + 2), listPlayer.get(j * 2 + 130))));
                        }
                    	winnersQueue.add(listMatch.get(j).Play(nbrSets,listReferee,listCourt));
                    }
                    else {
                    	listMatch.add(new Match(LocalDateTime.now(), 180, i, this,
                    			winnersQueue.poll(), 
                    			winnersQueue.poll()));
                    	winnersQueue.add(listMatch.get(j).Play(nbrSets,listReferee,listCourt));
                    }
                }
    		}
    	}
    }

    public Opponent GetWinner() {
    	return this.listMatch.get(listMatch.size()-1).GetWinner();
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
