
public class Printer implements Runnable {
	private RailwayNetwork network;
	
	public Printer(RailwayNetwork n) {
		this.network = n;
	}

	@Override
	public void run() {
		try {
			while(true) {
				Thread.sleep(1000);
				network.printRailway(); // printing railway and trains.
			}
		}catch(InterruptedException e) {
		}
	}
}
