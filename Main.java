
public class Main {
	public static void main(String[] args) {

		// create a railway consisting of 7 segments.
		Railway railway = new Railway(7);

		// display the railway.
		Thread rThread = new Thread(new Printer(railway));
		rThread.start();
		
		// generate trains that move along the semgnets of the railway.
		MoveTrain moveTrain = new MoveTrain();

		// always place train in the first segment of the railway.
		Position startPosition = new Position(0); 

		// generate trains.
		new Thread(new TrainGenerator(startPosition, moveTrain, railway)).start();
	}
}