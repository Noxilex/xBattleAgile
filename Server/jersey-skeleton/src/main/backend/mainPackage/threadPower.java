package mainPackage;

public class threadPower extends Thread {

	private GameMecanics meca;

	public threadPower(String name) {
		super(name);
	}

	public void run() {

		while (!finish) {
			//sleep du thread
			tpower.start();
			UserDBResources.map = meca.refreshMap(UserDBResources.map);
			try {
				this.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}