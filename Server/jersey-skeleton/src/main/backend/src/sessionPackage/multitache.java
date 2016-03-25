package src.sessionPackage;

public class multitache extends Thread{
	
	  public multitache(String name){
		    super(name);
		  }

		  public void run(){
		    for(int i = 0; i < 10; i++)
		      System.out.println(this.getName());
		  }  
	
	
}