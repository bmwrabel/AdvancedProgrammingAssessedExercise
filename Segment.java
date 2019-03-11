import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Segment {
	private String segmentName;
	private int segmentLength;
	private ArrayList<Train> segmentCapacity;
	private int maximumSegmentCapacity; 
	private final ReentrantLock lock = new ReentrantLock(); 
	private final Condition condition = lock.newCondition();


	public Segment (String name, int capacity, int length) {
		this.segmentName = name;
		this.maximumSegmentCapacity = capacity;
		this.segmentLength = length;
		this.segmentCapacity = new ArrayList<Train>();
	}
	
	public String getSegmentName() {
		return segmentName;
	}
	
	public int getSegmentLength() {
		return segmentLength;
	}
	
	public ArrayList<Train> getSegmentCapacity() {
		return segmentCapacity;
	}

	public void moveIn(Train train) {
		lock.lock();
		try {
			// trains wait if segment is full.
			while(segmentCapacity.size() == maximumSegmentCapacity) {
				condition.await();
			}
			segmentCapacity.add(train);
			condition.signalAll();
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally {
			lock.unlock();
		}
	}

	public void moveOut(Train train) {
		lock.lock();
		segmentCapacity.remove(train);
		condition.signalAll();
		lock.unlock();
	}	
}
