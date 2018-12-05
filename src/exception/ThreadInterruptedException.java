package exception;

public class ThreadInterruptedException extends Exception {
	
	public ThreadInterruptedException() {
		System.err.println("Thread is interrupted");
	}
	
}
