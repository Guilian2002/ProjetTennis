package be.Pierard.pojo;

import java.util.Objects;
import java.util.Random;

public class Set {
    private int scoreOp1;
    private int scoreOp2;
    private Opponent winner;
    private Match match;

    public int getScoreOp1() {
        return scoreOp1;
    }

    public void setScoreOp1(int scoreOp1) {
        if (scoreOp1 >= 0) {
            this.scoreOp1 = scoreOp1;
        } else {
            throw new IllegalArgumentException("Le score ne peut pas etre negatif.");
        }
    }

    public int getScoreOp2() {
        return scoreOp2;
    }

    public void setScoreOp2(int scoreOp2) {
        if (scoreOp2 >= 0) {
            this.scoreOp2 = scoreOp2;
        } else {
            throw new IllegalArgumentException("Le score ne peut pas etre negatif.");
        }
    }

    public Opponent getWinner() {
        return winner;
    }

    public void setWinner(Opponent winner) {
        this.winner = winner;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Set(int scoreOp1, int scoreOp2, Match match) {
        this.scoreOp1 = scoreOp1;
        this.scoreOp2 = scoreOp2;
        this.match = match;
    }

    public Set(int scoreOp1, int scoreOp2, Opponent winner, Match match) {
        this.scoreOp1 = scoreOp1;
        this.scoreOp2 = scoreOp2;
        this.winner = winner;
        this.match = match;
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
                	scoreOp1++;
                	scorePointPlayer1 = 0;
            		scorePointPlayer2 = 0;
                }
                if(scoreOp1 == 6)
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
            		scoreOp2++;
            		scorePointPlayer1 = 0;
            		scorePointPlayer2 = 0;
            	}
                	
            	if(scoreOp2 == 6)
            		scoreForOp2TieBreak = true;
            }
        }while(scoreOp1 > 6 || scoreOp2 > 6|| (scoreForOp1TieBreak == true && scoreForOp2TieBreak == true));
		if(scoreOp1 > 6 && scoreForOp2TieBreak == false) 
			winner = match.getOpp1();
		else if(scoreOp2 > 6 && scoreForOp1TieBreak == false)
			winner = match.getOpp2();
		else
		{
			boolean tieBreakEnded = false;
		    do {
		        if (random.nextBoolean()) {
		            scoreDuringTieBreakPlayer1 += 1;
		        } else {
		            scoreDuringTieBreakPlayer2 += 1;
		        }
		        if ((scoreDuringTieBreakPlayer1 >= 7 && scoreDuringTieBreakPlayer1 - scoreDuringTieBreakPlayer2 >= 2) ||
		            (scoreDuringTieBreakPlayer2 >= 7 && scoreDuringTieBreakPlayer2 - scoreDuringTieBreakPlayer1 >= 2)) {
		            tieBreakEnded = true;
		        }
		    } while (!tieBreakEnded);
		    if (scoreDuringTieBreakPlayer1 - scoreDuringTieBreakPlayer2 >= 2)
		        winner = match.getOpp1();
		    if (scoreDuringTieBreakPlayer2 - scoreDuringTieBreakPlayer1 >= 2)
		        winner = match.getOpp2();
		}
    }

	@Override
	public String toString() {
		return "Set [scoreOp1=" + scoreOp1 + ", scoreOp2=" + scoreOp2 + ", winner=" + winner + ", match=" + match + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(match, scoreOp1, scoreOp2, winner);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Set other = (Set) obj;
		return Objects.equals(match, other.match) && scoreOp1 == other.scoreOp1 && scoreOp2 == other.scoreOp2
				&& Objects.equals(winner, other.winner);
	}
}
