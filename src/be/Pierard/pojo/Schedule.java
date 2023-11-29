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
        System.out.println(listMatch.size());
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
        if (listMatch.size() == 0 || listMatch.isEmpty()) {
            if (type == ScheduleType.GentlemenSingle) {
                listMatch = new ArrayList<Match>();
                listMatch.add(new Match(LocalDateTime.now(), 300, actualRound, this));
            }
            else {
            	listMatch = new ArrayList<Match>();
                listMatch.add(new Match(LocalDateTime.now(), 180, actualRound, this));
            }
        }
        System.out.println(listMatch.size());
        setListMatch(listMatch);
    }

    public int NbWinningSets() {
    	if(type == ScheduleType.GentlemenSingle)
    		return 5;
    	else
    		return 3;
    }

    public void PlayNextRound() {
    	System.out.println("List Match : "+listMatch.size());
    	int[] matchNumberTab  = {63,32,16,8,4,2,1};
    	int[] matchNumberTab2  = {31,16,8,4,2,1};
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
	    System.out.println(listReferee.size());
	    System.out.println(listCourt.size());
        for (int i = 0; i < 256; i++) {
            Player player = playerDAO.find(i + 1);
            listPlayer.add(player);
        }
        System.out.println(listPlayer.size());
        Queue<Opponent> winnersQueue = new LinkedList<Opponent>();
        if(type == ScheduleType.GentlemenSingle || type == ScheduleType.LadiesSingle) {
        	for (int i = 0; i < 7; i++) {
                for (int j = 1; j <= matchNumberTab[i]; j++) {
                    if (listMatch.size() == 1) {
                        if (type == ScheduleType.GentlemenSingle) {
                            listMatch.set(0,new Match(LocalDateTime.now(), 300, actualRound, this, 
                            		new Opponent(listPlayer.get(0)),new Opponent(listPlayer.get(1))));
                            listMatch.add(new Match(LocalDateTime.now(), 300, i, this,
                            		new Opponent(listPlayer.get(2)), 
                            		new Opponent(listPlayer.get(3))));
                        } 
                        else{
                        	listMatch.set(0,new Match(LocalDateTime.now(), 180, actualRound, this, 
                            		new Opponent(listPlayer.get(128)),new Opponent(listPlayer.get(129))));
                        	listMatch.add(new Match(LocalDateTime.now(), 180, i, this,
                            		new Opponent(listPlayer.get(130)), 
                            		new Opponent(listPlayer.get(131))));
                        }
                        winnersQueue.add(listMatch.get(0).Play(nbrSets,listReferee,listCourt));
                    }
                    else if (i == 0)
                    {
                    	if (type == ScheduleType.GentlemenSingle) {
                        	listMatch.add(new Match(LocalDateTime.now(), 300, i, this,
                            		new Opponent(listPlayer.get(j * 2)), 
                            		new Opponent(listPlayer.get(j * 2 + 1))));
                        }
                        else{
                        	listMatch.add(new Match(LocalDateTime.now(), 180, i, this,
                            		new Opponent(listPlayer.get(j * 2 + 128)), 
                            		new Opponent(listPlayer.get(j * 2 + 129))));
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
                actualRound++;
        	}
        }
    	else
    	{
    		for (int i = 0; i < 6; i++) {
                for (int j = 1; j <= matchNumberTab2[i]; j++) {
                    if (listMatch.size()==1) {
                        if (type == ScheduleType.GentlemenDouble) {
                        	listMatch.set(0,new Match(LocalDateTime.now(), 180, actualRound, this, 
                            		new Opponent(listPlayer.get(0),listPlayer.get(1)),
                            		new Opponent(listPlayer.get(2),listPlayer.get(3))));
                        	listMatch.add(new Match(LocalDateTime.now(), 180, i, this,
                            		new Opponent(listPlayer.get(4), listPlayer.get(5)), 
                            		new Opponent(listPlayer.get(6), listPlayer.get(7))));
                        } 
                        else if (type == ScheduleType.LadiesDouble) {
                        	listMatch.set(0,new Match(LocalDateTime.now(), 180, actualRound, this, 
                            		new Opponent(listPlayer.get(128),listPlayer.get(129)),
                            		new Opponent(listPlayer.get(130),listPlayer.get(131))));
                        	listMatch.add(new Match(LocalDateTime.now(), 180, i, this,
                            		new Opponent(listPlayer.get(132), listPlayer.get(133)), 
                            		new Opponent(listPlayer.get(134), listPlayer.get(135))));
                        } 
                        else {
                        	listMatch.set(0,new Match(LocalDateTime.now(), 180, actualRound, this, 
                            		new Opponent(listPlayer.get(0),listPlayer.get(128)),
                            		new Opponent(listPlayer.get(1),listPlayer.get(129))));
                        	listMatch.add(new Match(LocalDateTime.now(), 180, i, this,
                            		new Opponent(listPlayer.get(2), listPlayer.get(130)), 
                            		new Opponent(listPlayer.get(3), listPlayer.get(131))));
                        }
                        winnersQueue.add(listMatch.get(0).Play(nbrSets,listReferee,listCourt));
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
                            		new Opponent(listPlayer.get(j * 2 + 128), listPlayer.get(j * 2 + 129)), 
                            		new Opponent(listPlayer.get(j * 2 + 130), listPlayer.get(j * 2 + 131))));
                        }
                        else{
                        	listMatch.add(new Match(LocalDateTime.now(), 180, i, this,
                            		new Opponent(listPlayer.get(j * 2 + 1), listPlayer.get(j * 2 + 128)), 
                            		new Opponent(listPlayer.get(j * 2 + 2), listPlayer.get(j * 2 + 129))));
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
                actualRound++;
    		}
    	}
        System.out.println("List Match : "+listMatch.size());
    }

    public Opponent GetWinner() {
    	int sumScoreOp1 = 0;
		int sumScoreOp2 = 0;
		for(Set set : listMatch.get(listMatch.size()-1).getListSet())
		{
			if(set.getWinner()== listMatch.get(listMatch.size()-1).getOpp1())
				sumScoreOp1++;
			else
				sumScoreOp2++;
		}
		System.out.println(listMatch.get(listMatch.size()-1).getOpp1().getPlayerOne().getFirstname());
		if(sumScoreOp1 > sumScoreOp2)
			return listMatch.get(listMatch.size()-2).getOpp1();
		else
			return listMatch.get(listMatch.size()-2).getOpp2();
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
