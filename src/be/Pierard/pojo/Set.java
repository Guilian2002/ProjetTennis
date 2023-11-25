package be.Pierard.pojo;

import java.util.Objects;

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
        if (scoreOp1 > scoreOp2) {
            winner = match.getOpp1();
        } else if (scoreOp2 > scoreOp1) {
            winner = match.getOpp2();
        } else {
            winner = null;
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
