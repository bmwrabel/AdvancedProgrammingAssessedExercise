
public interface RailwayNetwork {
	public Segment getSegmentByPosition(Position p);
	public Position getPosition(Segment s);
	public void printRailway();
}
