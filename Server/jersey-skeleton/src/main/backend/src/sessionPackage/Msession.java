package src.sessionPackage;

import java.util.ArrayList;
import java.util.List;

public class Msession extends Thread{

	private List <multitache> LMulti;
	
	
	public Msession(){
		LMulti = new ArrayList <multitache>();
	}

	  /*public void run(){
		  for(int i = 0; i < 10; i++)
			  System.out.println(this.getName());
	  } */
	public boolean activate() {
		boolean act = false;
		
		return act;
	}
}

