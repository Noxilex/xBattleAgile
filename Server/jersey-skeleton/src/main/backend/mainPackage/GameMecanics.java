package mainPackage;

import utilities.Case;

public class GameMecanics {

	private Case[][] map;

	public GameMecanics() {}

	public Case[][] getNewMap(Case[][] map) {

		map=calculateEverything(map);

		return map;
	}

	private Case[][] calculateEverything(Case[][] map) {
		this.map = map;
		setDirections();
		return map;
	}

	private void setDirections() {

	}
}