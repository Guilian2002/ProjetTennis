package be.Pierard.pojo;

import java.util.Random;

public class SuperTieBreak extends Set{
    public SuperTieBreak(int scoreOp1, int scoreOp2, Match match) {
        super(scoreOp1, scoreOp2, match);
    }
    public void Play() {
    	Random random = new Random();
    	int scorePointPlayer1 = 0;
    	int scorePointPlayer2 = 0;
    	boolean scoreForOp1TieBreak = false, scoreForOp2TieBreak = false;
    	int scoreDuringTieBreakPlayer1 = 0;
    	int scoreDuringTieBreakPlayer2 = 0;
    	do{
            if(random.nextBoolean()) {
            	if (scorePointPlayer1 == 0)
            		scorePointPlayer1 = 15;
                else if (scorePointPlayer1 == 15)
                	scorePointPlayer1 = 30;
                else if (scorePointPlayer1 == 30)
                	scorePointPlayer1 = 40;
                else {
                    if (scorePointPlayer1 == scorePointPlayer2)
                        scorePointPlayer1 += 10;
                    else if (scorePointPlayer1 > scorePointPlayer2)
                        scorePointPlayer1 += 5;
                }
                if(scorePointPlayer1 == scorePointPlayer2+15) {
                	setScoreOp1(getScoreOp1()+1);
                	scorePointPlayer1 = 0;
            		scorePointPlayer2 = 0;
                }
                if(getScoreOp1() == 6)
            		scoreForOp1TieBreak = true;
            } 
            else {
            	if (scorePointPlayer2 == 0)
            		scorePointPlayer2 = 15;
                else if (scorePointPlayer2 == 15)
                	scorePointPlayer2 = 30;
                else if (scorePointPlayer2 == 30)
                	scorePointPlayer2 = 40;
                else {
                    if (scorePointPlayer2 == scorePointPlayer1)
                        scorePointPlayer2 += 10;
                    else if (scorePointPlayer2 > scorePointPlayer1)
                        scorePointPlayer2 += 5;
                }
            	if(scorePointPlayer2 == scorePointPlayer1+15) {
            		setScoreOp2(getScoreOp2()+1);
            		scorePointPlayer1 = 0;
            		scorePointPlayer2 = 0;
            	}
                	
            	if(getScoreOp2() == 6)
            		scoreForOp2TieBreak = true;
            }
        }while(getScoreOp1() > 6 || getScoreOp2() > 6|| (scoreForOp1TieBreak == true && scoreForOp2TieBreak == true));
		if(getScoreOp1() > 6 && scoreForOp2TieBreak == false) 
			setWinner(getMatch().getOpp1());
		else if(getScoreOp2() > 6 && scoreForOp1TieBreak == false)
			setWinner(getMatch().getOpp2());
		else
		{
			boolean tieBreakEnded = false;
		    do {
		        if (random.nextBoolean()) {
		            scoreDuringTieBreakPlayer1 += 1;
		        } else {
		            scoreDuringTieBreakPlayer2 += 1;
		        }
		        if ((scoreDuringTieBreakPlayer1 >= 10 && scoreDuringTieBreakPlayer1 - scoreDuringTieBreakPlayer2 >= 2) ||
		            (scoreDuringTieBreakPlayer2 >= 10 && scoreDuringTieBreakPlayer2 - scoreDuringTieBreakPlayer1 >= 2)) {
		            tieBreakEnded = true;
		        }
		    } while (!tieBreakEnded);
		    if (scoreDuringTieBreakPlayer1 - scoreDuringTieBreakPlayer2 >= 2)
		    	setWinner(getMatch().getOpp1());
		    if (scoreDuringTieBreakPlayer2 - scoreDuringTieBreakPlayer1 >= 2)
		    	setWinner(getMatch().getOpp2());
		}
    }
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}
}
