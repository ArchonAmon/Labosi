package gol;

public class SimulationThread extends Thread {
	private boolean stopping = false;
	private Board board;
	public int spavanje = 50;

	public SimulationThread(Board board) {
		this.board = board;
	}

	@Override
	public void run() {
		while(!stopping) {
			board.playOneIteration();
			try {
				Thread.sleep(spavanje);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void setStopping() {
		stopping = true;
	}
}
