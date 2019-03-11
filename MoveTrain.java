// this is the engine of a train. It allows train moving from one segment to the next one.
public class MoveTrain {
	public Segment getNext(Segment current, RailwayNetwork network) {
		Position currentPosition = network.getPosition(current);
		Position newPosition = new Position(currentPosition.getPositon() + 1);
		return network.getSegmentByPosition(newPosition);
	}
}
