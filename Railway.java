
public class Railway implements RailwayNetwork {
	private Segment station1, track1, station2, track2, station3, track3, station4;
	private Segment[] railway;
	private final int numberOfSegments;
	
	// create railway stating the number of segments.
	public Railway(int numOfSegments) {
		this.numberOfSegments = numOfSegments;
		buildRailway();
	}
	
	// create stations and tracks and add them to the array of segments(named railway).
	public void buildRailway() {
		try{
		this.station1 = new Station("Glasgow", 3);
		this.station2 = new Station("Stirling", 2);
		this.station3 = new Station("Perth", 2);
		this.station4 = new Station("Inverness", 2);
		this.track1 = new Track();
		this.track2 = new Track();
		this.track3 = new Track();
		this.railway = new Segment[this.numberOfSegments];
		this.railway[0] = station1; 
		this.railway[1] = track1; 
		this.railway[2] = station2;
		this.railway[3] = track2; 
		this.railway[4] = station3; 
		this.railway[5] = track3; 
		this.railway[6] = station4;
		}
		catch(ArrayIndexOutOfBoundsException ex) {}
	}

	@Override
	public Segment getSegmentByPosition(Position p) {
		try {
			return railway[p.getPositon()];
		}catch(ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	@Override
	public Position getPosition(Segment s) {
		Position position = null;
		for(int i=0;i<railway.length;i++) {
			if(s == railway[i]) {
				position = new Position(i);
				return position;
			}
		}
		return position;
	}
	
	// print segments' names and trains' ids.
	@Override
	public void printRailway()  {
		for(int i=0;i<railway.length;i++) {
			System.out.print("|----" + railway[i].getSegmentName() + "--");
			for(int j=0;j<railway[i].getSegmentCapacity().size();j++) {
				System.out.print(railway[i].getSegmentCapacity().get(j).getTrainID() + ",");
 			}
			System.out.print("----|");
		}
		System.out.println();
	}
}
