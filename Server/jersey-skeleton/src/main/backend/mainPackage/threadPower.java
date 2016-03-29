package mainPackage;

import utilities.Case;
import fr.iutinfo.skeleton.api.UserDBResource;

public class threadPower extends Thread {

	private GameMecanics meca;
	private boolean finish;
	
	public threadPower(String name) {
		super(name);
		this.meca = new GameMecanics();
		this.finish = false;
	}

	public void run() {

		while (!finish) {
			UserDBResource.map = meca.refreshMap(UserDBResource.map);
			//UserDBResource.map.logDisplayMap(UserDBResource.map.getCaseMap());
			try {
				this.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}