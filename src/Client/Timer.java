package Client;

public class Timer {
    long start = System.nanoTime();
    public long getStart() {
		return start;
	}
	public void setStart(long start) {
		this.start = start;
	}
	public long getFinish() {
		return finish;
	}
	public void setFinish(long finish) {
		this.finish = finish;
	}
	long finish = System.nanoTime();

	public long timing() {
    	long totalTime=(start-finish)/1000000;
    	return totalTime;
	}

}
