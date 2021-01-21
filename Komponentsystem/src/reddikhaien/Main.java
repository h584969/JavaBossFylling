package reddikhaien;

public class Main {
	
	public static void main(String[] args) {
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				GameLoop loop = new GameLoop();
				loop.run();
			}
			
			
		});
		t.start();
	}
}
