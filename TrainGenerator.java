import java.util.Random;

public class TrainGenerator implements Runnable {
	private RailwayNetwork network;
	private final Position startPosition;
	private final MoveTrain moveTrain;
	private Random randomTrainGenerator = new Random();
	private Random randomWaitingTimeGenerator = new Random();
	private int countTrainID = 1;


	public TrainGenerator(Position startPosition, MoveTrain moveTrain, RailwayNetwork network) {
		this.startPosition = startPosition;
		this.moveTrain = moveTrain;
		this.network = network;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				// used to genenerate time 
				int wait = randomWaitingTimeGenerator.nextInt(10000) + 1000;
				// random number between 0 and 1, used to generate either fast or slow train.
				int randomTrain = randomTrainGenerator.nextInt(2);
				// get the first segment.
				Segment segment = network.getSegmentByPosition(this.startPosition);
				// put trains randomly into the first segment of the railway.
				Thread.sleep(wait);
				// generate trains randomly. fast train = 1, slow train = 0.
				if(randomTrain == 1) {
					System.out.println("Fast train generated. ID: " + countTrainID++);
					new Thread(new FastTrain(segment, this.moveTrain, network)).start();
				}
				else if(randomTrain == 0) {
					System.out.println("Slow train generated. ID: " + countTrainID++);
					new Thread(new SlowTrain(segment, this.moveTrain, network)).start();
				}
			}
		}
		catch(InterruptedException e) {}
	}
}