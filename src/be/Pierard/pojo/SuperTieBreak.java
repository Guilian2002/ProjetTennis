package be.Pierard.pojo;

import java.util.Random;

public class SuperTieBreak extends Set{
    public SuperTieBreak(int scoreOp1, int scoreOp2, Match match) {
        super(scoreOp1, scoreOp2, match);
    }
    @Override
    public void Play() {
    	Random random = new Random();
    	int scorePointPlayer1 = 0;
    	int scorePointPlayer2 = 0;
    	int scoreOp1 = 0, scoreOp2 = 0;
    	boolean scoreForOp1TieBreak = false, scoreForOp2TieBreak = false;
    	int scoreDuringTieBreakPlayer1 = 0;
    	int scoreDuringTieBreakPlayer2 = 0;
    	do {
    	    if (random.nextBoolean()) {
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

    	        if (scorePointPlayer1 == scorePointPlayer2 + 15) {
    	            scoreOp1++;
    	            scorePointPlayer1 = 0;
    	            scorePointPlayer2 = 0;
    	        }

    	        if (scoreOp1 == 6) {
    	            scoreForOp1TieBreak = true;
    	        }
    	    } else {
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

    	        if (scorePointPlayer2 == scorePointPlayer1 + 15) {
    	            scoreOp2++;
    	            scorePointPlayer1 = 0;
    	            scorePointPlayer2 = 0;
    	        }

    	        if (scoreOp2 == 6) {
    	            scoreForOp2TieBreak = true;
    	        }
    	    }
    	} while((scoreOp1 < 11 && scoreOp2 < 11) || (scoreForOp1TieBreak == false && scoreForOp2TieBreak == false));
    	if(scoreOp1 == 11 && scoreForOp2TieBreak == false)
    	{
    		setScoreOp1(scoreOp1);
    		setScoreOp2(scoreOp2);
    		setWinner(getMatch().getOpp1());
    	}
    	else if(scoreOp2 == 11 && scoreForOp1TieBreak == false)
    	{
    		setScoreOp1(scoreOp1);
    		setScoreOp2(scoreOp2);
    		setWinner(getMatch().getOpp2());
    	}
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
    	    {
        		setScoreOp1(scoreOp1);
        		setScoreOp2(scoreOp2);
        		setWinner(getMatch().getOpp1());
        	}
    	    if (scoreDuringTieBreakPlayer2 - scoreDuringTieBreakPlayer1 >= 2)
    	    {
        		setScoreOp1(scoreOp1);
        		setScoreOp2(scoreOp2);
        		setWinner(getMatch().getOpp2());
        	}
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
