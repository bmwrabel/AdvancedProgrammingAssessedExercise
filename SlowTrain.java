
public class SlowTrain extends Train {
	
	// create slow train (speed = 100)
	public SlowTrain(Segment position, MoveTrain moveTrain, RailwayNetwork network) {
		super(position, 100, moveTrain, network);
	}

}
