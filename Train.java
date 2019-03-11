
public class Train implements Runnable {
	
	private Segment currentPosition;
	private RailwayNetwork network;
	private MoveTrain moveTrain;
	private int trainID;
	private int trainSpeed;
	private static int generateTrainID = 1;

	
	public Train (Segment startPosition, int speed, MoveTrain moveTrain, RailwayNetwork n) {
		currentPosition = startPosition;
		this.trainSpeed = speed;
		this.moveTrain = moveTrain;
		this.network = n;
		this.trainID = generateTrainID++; 
	}
	
	// calculate how long train should stay in station.
	public int timeInStation(int length)  {
		int timeInStation = 1000 * length / this.trainSpeed + 5000;
		return timeInStation;
	}
	
	// calculate how long train should stay in track.
	public int timeInTrack(int length) {
		int timeInTrack = 1000 * length / this.trainSpeed;
		return timeInTrack;
	}

	// get next segment.
	public Segment getNext() {
		return this.moveTrain.getNext(currentPosition, network);
	}

	public int getTrainID() {
		return trainID;
	}
	
	@Override
	public void run() {
		while(true) {
			currentPosition.moveIn(this);
			try {
				// if train is in station it sleeps for 
				if(currentPosition instanceof Station) {
					Thread.sleep(timeInStation(currentPosition.getSegmentLength()));
				}
				else if(currentPosition instanceof Track) {
					Thread.sleep(timeInTrack(currentPosition.getSegmentLength()));
				}
	 		}
			catch(InterruptedException e) {}
			// get the next segment from the array of segments(located in the Railway class).
			Segment nextPosition = getNext();
			// exit the loop if the next segment doesn't exist.
			if(nextPosition == null) {
				break;
			}
			// train moves in to the next position.	
			nextPosition.moveIn(this);
			// train moves out of the current position.
			currentPosition.moveOut(this);
			// next position is now current position.
			currentPosition = nextPosition;
			// train moves out of the next position.
			currentPosition.moveOut(this);
		}
		// train leaves the last segment.
		currentPosition.moveOut(this);
	}
}