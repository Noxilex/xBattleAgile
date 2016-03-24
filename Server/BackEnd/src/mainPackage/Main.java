package mainPackage;

public class Main {

	public static void main(String[] args) {
		Map map = new Map();
		Player playerOne = new Player("GhostOne", 1, "one");
		Player playerTwo = new Player("GhostTwo", 2, "two");
		System.out.println(map.jsonMap());
	}
	
}
