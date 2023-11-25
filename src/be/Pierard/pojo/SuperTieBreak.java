package be.Pierard.pojo;

public class SuperTieBreak extends Set{
    public SuperTieBreak(int scoreOp1, int scoreOp2,Match match) {
        super(scoreOp1, scoreOp2, match);
    }
    @Override
    public void Play() {
    	
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
