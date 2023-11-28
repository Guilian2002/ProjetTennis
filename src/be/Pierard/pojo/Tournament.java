package be.Pierard.pojo;

import java.util.ArrayList;
import java.util.Objects;

public class Tournament {
	private String name;
	private ArrayList<Court> listCourt;
	private ArrayList<Schedule> listSchedule;
	private ArrayList<Referee> listReferee;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Court> getListCourt() {
		return listCourt;
	}
	public void setListCourt(ArrayList<Court> listCourt) {
		this.listCourt = listCourt;
	}
	public ArrayList<Schedule> getListSchedule() {
		return listSchedule;
	}
	public void setListSchedule(ArrayList<Schedule> listSchedule) {
		if (listSchedule.size() == 5) {
            this.listSchedule = listSchedule;
        } else {
            throw new IllegalArgumentException("La liste de schedules doit contenir exactement 5 elements.");
        }
	}
	public ArrayList<Referee> getListReferee() {
		return listReferee;
	}
	public void setListReferee(ArrayList<Referee> listReferee) {
		this.listReferee = listReferee;
	}
	public Tournament(String name,ArrayList<Schedule> listSchedule) {
		super();
		this.name = name;
		this.listCourt = new ArrayList<Court>();
		this.listReferee = new ArrayList<Referee>();
		if(listSchedule.size() == 0)
		{
			listSchedule.add(new Schedule(ScheduleType.GentlemenSingle,1,this));
	        listSchedule.add(new Schedule(ScheduleType.LadiesSingle,1,this));
	        listSchedule.add(new Schedule(ScheduleType.GentlemenDouble,1,this));
	        listSchedule.add(new Schedule(ScheduleType.LadiesDouble,1,this));
	        listSchedule.add(new Schedule(ScheduleType.MixedDouble,1,this));
		}
		setListSchedule(listSchedule);
	}
	public Tournament(String name, ArrayList<Court> listCourt, ArrayList<Schedule> listSchedule,
			ArrayList<Referee> listReferee) {
		super();
		this.name = name;
		this.listCourt = listCourt;
		this.listSchedule = listSchedule;
		this.listReferee = listReferee;
	}
	public void Play(){
        for(int i = 0; i < 5; i++)
        {
        	listSchedule.get(i).PlayNextRound();
        }
	}
	@Override
	public String toString() {
		return "Tournament [name=" + name + ", listCourt=" + listCourt + ", listSchedule=" + listSchedule
				+ ", listReferee=" + listReferee + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(listCourt, listReferee, listSchedule, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tournament other = (Tournament) obj;
		return Objects.equals(listCourt, other.listCourt) && Objects.equals(listReferee, other.listReferee)
				&& Objects.equals(listSchedule, other.listSchedule) && Objects.equals(name, other.name);
	}
}