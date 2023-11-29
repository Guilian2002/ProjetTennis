package be.Pierard.JFrames;

import java.awt.EventQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import be.Pierard.pojo.Schedule;
import be.Pierard.pojo.ScheduleType;
import be.Pierard.pojo.Tournament;

public class TennisTournament extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1810563406760036270L;
	private JPanel contentPane;
    private JComboBox<String> comboBox;
    private JButton btnNext;
    private JTable table;
    private Tournament tournament;
    private ArrayList<Schedule> listSchedule = new ArrayList<Schedule>();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TennisTournament frame = new TennisTournament();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TennisTournament() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(1, 1, 1500, 800);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        comboBox = new JComboBox<>();
        comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Wimbledon", "Roland-Garros", "US Open" }));
        comboBox.setBounds(700, 300, 165, 21);
        contentPane.add(comboBox);

        btnNext = new JButton("Suivant");
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedTournament = (String) comboBox.getSelectedItem();
                int cptSchedule = 0,cptRound = 0,cptMatch = 0;
                tournament = new Tournament(selectedTournament,listSchedule);
                tournament.Play();

                String[] columnNames = { "Nom du tournoi", "Round actuel", "Type de programme", "Id du match",
                        "Nom(s) et prénom(s) des joueur(s)"," ", "Nom(s) et prénom(s) des opposant(s)"," ",
                        "Résultat des sets", "Gagnant(s) du match", " ", "Nom et prénom de l'arbitre", 
                        "Nombre de spectateurs", "Terrain couvert/non couvert", "Date"};
                
                Object[][] data = new Object[442][15];

                for (int i = 0; i < data.length; i++) {
                	if(i == 126 || i == 253 || i == 316 || i == 379)
                		cptSchedule++;
                	if(i == 63 || i == 95 || i == 111 || i == 119 || i == 123 || i == 125 || i == 126 ||
                			i == 190 || i == 222 || i == 238 || i == 246 || i == 250 || i == 252 || i == 253 ||
                			i==285||i==301||i==309||i==313||i==315||i==316||i==348||i==364||i==372||i==376||i==378||
                			i==379||i==411||i==427||i==435||i==439||i==441)
                	{
                		cptRound++;
                		cptMatch = 0;
                	}
                	   data[i][0] = selectedTournament;
                	   data[i][1] = cptRound+1;
                	   if(tournament.getListSchedule().get(cptSchedule).getType() == ScheduleType.GentlemenSingle)
                		   data[i][2] = "Gentlemen Single";
                	   if(tournament.getListSchedule().get(cptSchedule).getType() == ScheduleType.LadiesSingle)
                		   data[i][2] = "Ladies Single";
                	   if(tournament.getListSchedule().get(cptSchedule).getType() == ScheduleType.GentlemenDouble)
                		   data[i][2] = "Gentlemen Double";
                	   if(tournament.getListSchedule().get(cptSchedule).getType() == ScheduleType.LadiesDouble)
                		   data[i][2] = "Ladies Double";
                	   if(tournament.getListSchedule().get(cptSchedule).getType() == ScheduleType.MixedDouble)
                		   data[i][2] = "Mixed Double";
                	   data[i][3] = cptMatch+1;
                	   data[i][4] = tournament.getListSchedule().get(cptSchedule).getListMatch().get(cptMatch)
                			   .getOpp1().getPlayerOne().getLastname() + 
                			   " " + tournament.getListSchedule().get(cptSchedule)
                			   .getListMatch().get(cptMatch).getOpp1().getPlayerOne().getFirstname();
                	   if(tournament.getListSchedule().get(cptSchedule).getType() == ScheduleType.GentlemenDouble
                		  ||tournament.getListSchedule().get(cptSchedule).getType() == ScheduleType.LadiesDouble
                		  ||tournament.getListSchedule().get(cptSchedule).getType() == ScheduleType.MixedDouble)
                		   data[i][5] = tournament.getListSchedule().get(cptSchedule).getListMatch().get(cptMatch)
                			   .getOpp1().getPlayerTwo().getLastname() + 
                			   " " + tournament.getListSchedule().get(cptSchedule)
                			   .getListMatch().get(cptMatch).getOpp1().getPlayerTwo().getFirstname();
                	   else
                		   data[i][5] = " ";
                	   data[i][6] = tournament.getListSchedule().get(cptSchedule).getListMatch().get(cptMatch)
                			   .getOpp2().getPlayerOne().getLastname() + 
                			   " " + tournament.getListSchedule().get(cptSchedule)
                			   .getListMatch().get(cptMatch).getOpp2().getPlayerOne().getFirstname();
                	   if(tournament.getListSchedule().get(cptSchedule).getType() == ScheduleType.GentlemenDouble
                		  ||tournament.getListSchedule().get(cptSchedule).getType() == ScheduleType.LadiesDouble
                		  ||tournament.getListSchedule().get(cptSchedule).getType() == ScheduleType.MixedDouble)
                		   data[i][7] = tournament.getListSchedule().get(cptSchedule).getListMatch().get(cptMatch)
                			   .getOpp2().getPlayerTwo().getLastname() + 
                			   " " + tournament.getListSchedule().get(cptSchedule)
                			   .getListMatch().get(cptMatch).getOpp2().getPlayerTwo().getFirstname();
                	   else
                		   data[i][7] = " ";
                	   if(tournament.getListSchedule().get(cptSchedule).getType() == ScheduleType.GentlemenSingle)
                		   	data[i][8] = "Set 1 : "+tournament.getListSchedule().get(cptSchedule).getListMatch().get(cptMatch)
                			   .getListSet().get(0).getScoreOp1() + " - " + tournament.getListSchedule().get(cptSchedule)
                			   .getListMatch().get(cptMatch)
                			   .getListSet().get(0).getScoreOp2()+ ", Set 2 : "+tournament.getListSchedule().get(cptSchedule)
                			   .getListMatch().get(cptMatch)
                			   .getListSet().get(1).getScoreOp1() + " - " + tournament.getListSchedule().get(cptSchedule)
                			   .getListMatch().get(cptMatch)
                			   .getListSet().get(1).getScoreOp2()+ ", Set 3 : "+tournament.getListSchedule().get(cptSchedule)
                			   .getListMatch().get(cptMatch)
                			   .getListSet().get(2).getScoreOp1() + " - " + tournament.getListSchedule().get(cptSchedule)
                			   .getListMatch().get(cptMatch)
                			   .getListSet().get(2).getScoreOp2()+ ", Set 4 : "+tournament.getListSchedule().get(cptSchedule)
                			   .getListMatch().get(cptMatch)
                			   .getListSet().get(3).getScoreOp1() + " - " + tournament.getListSchedule().get(cptSchedule)
                			   .getListMatch().get(cptMatch)
                			   .getListSet().get(3).getScoreOp2()+ ", Set 5 : "+tournament.getListSchedule().get(cptSchedule)
                			   .getListMatch().get(cptMatch)
                			   .getListSet().get(4).getScoreOp1() + " - " + tournament.getListSchedule().get(cptSchedule)
                			   .getListMatch().get(cptMatch)
                			   .getListSet().get(4).getScoreOp2();
                	   else
                		   data[i][8] = "Set 1 : "+tournament.getListSchedule().get(cptSchedule).getListMatch().get(cptMatch)
            			   .getListSet().get(0).getScoreOp1() + " - " + tournament.getListSchedule().get(cptSchedule)
            			   .getListMatch().get(cptMatch)
            			   .getListSet().get(0).getScoreOp2()+ ", Set 2 : "+tournament.getListSchedule().get(cptSchedule)
            			   .getListMatch().get(cptMatch)
            			   .getListSet().get(1).getScoreOp1() + " - " + tournament.getListSchedule().get(cptSchedule)
            			   .getListMatch().get(cptMatch)
            			   .getListSet().get(1).getScoreOp2()+ ", Set 3 : "+tournament.getListSchedule().get(cptSchedule)
            			   .getListMatch().get(cptMatch)
            			   .getListSet().get(2).getScoreOp1() + " - " + tournament.getListSchedule().get(cptSchedule)
            			   .getListMatch().get(cptMatch).getListSet().get(2).getScoreOp2();
                	   data[i][9] = tournament.getListSchedule().get(cptSchedule).getListMatch().get(cptMatch).GetWinner()
                			   .getPlayerOne().getLastname() + "  " + tournament.getListSchedule().get(cptSchedule)
                			   .getListMatch().get(cptMatch).GetWinner().getPlayerOne().getFirstname();
                	   if(tournament.getListSchedule().get(cptSchedule).getType() == ScheduleType.GentlemenDouble
                		  ||tournament.getListSchedule().get(cptSchedule).getType() == ScheduleType.LadiesDouble
                		  ||tournament.getListSchedule().get(cptSchedule).getType() == ScheduleType.MixedDouble)
                		   data[i][10] = tournament.getListSchedule().get(cptSchedule).getListMatch()
                		   .get(cptMatch).GetWinner().getPlayerTwo().getLastname() + "  " + 
                			tournament.getListSchedule().get(cptSchedule)
            			   .getListMatch().get(cptMatch).GetWinner().getPlayerTwo().getFirstname();
                	   else
                		   data[i][10] = " ";
                	   data[i][11] = tournament.getListSchedule().get(cptSchedule).getListMatch()
                    		   .get(cptMatch).getReferee().getLastname() + " " +tournament.getListSchedule()
                    		   .get(cptSchedule).getListMatch().get(cptMatch).getReferee().getFirstname();
                	   data[i][12] = tournament.getListSchedule().get(cptSchedule).getListMatch().get(cptMatch)
                			   .getCourt().getNbSpectators();
                	   if(tournament.getListSchedule().get(cptSchedule).getListMatch().get(cptMatch)
                			   .getCourt().getCovered() == true)
                		   data[i][13] = "Couvert";
                	   else
                		   data[i][13] = "Non Couvert";
                	   data[i][14] = tournament.getListSchedule().get(cptSchedule).getListMatch()
                			   .get(cptMatch).getDate();
                   cptMatch++;
                }

                table = new JTable(data, columnNames);
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBounds(1, 1, 1490, 600);
                contentPane.removeAll();
                contentPane.add(scrollPane);
                contentPane.revalidate();
                contentPane.repaint();
                JButton btnWinners = new JButton("Vainqueurs du Tournoi");
                btnWinners.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		String winners = " ";
                    	for(int i = 0; i < 5;i++) {
                    		if(tournament.getListSchedule().get(i).getType() == ScheduleType.GentlemenSingle)
                    			winners += tournament.getListSchedule().get(i).GetWinner().getPlayerOne()
                    			.toString() + " du programme : "+ 
                    					"GentlemenSingle. \n";
                    		else if(tournament.getListSchedule().get(i).getType() == ScheduleType.LadiesSingle)
                    			winners += tournament.getListSchedule().get(i).GetWinner().getPlayerOne()
                    			.toString() + " du programme : "+ 
                        				"LadiesSingle. \n";
                    		else if(tournament.getListSchedule().get(i).getType() == ScheduleType.GentlemenDouble)
                    			winners += tournament.getListSchedule().get(i).GetWinner().getPlayerOne()
                    			.toString() + " et "
                            			+ tournament.getListSchedule().get(i).GetWinner().getPlayerTwo()
                            			.toString() + " du programme : "+ 
                        				"GentlemenDouble. \n";
                    		else if(tournament.getListSchedule().get(i).getType() == ScheduleType.LadiesDouble)
                    			winners += tournament.getListSchedule().get(i).GetWinner().getPlayerOne()
                    			.toString() + " et "
                            			+ tournament.getListSchedule().get(i).GetWinner().getPlayerTwo()
                            			.toString() + " du programme : "+ 
                        				"LadiesDouble. \n";
                    		else
                    			winners += tournament.getListSchedule().get(i).GetWinner().getPlayerOne()
                    			.toString() + " et "
                    			+ tournament.getListSchedule().get(i).GetWinner().getPlayerTwo()
                    			.toString() + " du programme : " 
                    					+ "MixedDouble. \n";
                    	}
                    	JOptionPane.showMessageDialog(null, "Vainqueurs finaux de " + 
                				selectedTournament + " : \n" + winners);
                    }
                });
                btnWinners.setBounds(750, 700, 200, 21);
                contentPane.add(btnWinners);
            }
        });
        btnNext.setBounds(750, 400, 85, 21);
        contentPane.add(btnNext);
    }
}
