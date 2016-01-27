package concurrent;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphoreTest {
	static int count = 0;
	static ReentrantLock lock = new ReentrantLock();
	static Semaphore sem = new Semaphore(9);
	
	public static void main(String[] args) {
		ThreadPoolExecutor pools = new ThreadPoolExecutor(10, 10, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10));
		
		for(int i=0; i<20; i++) {
			pools.execute(new Runnable() {
				public void run() {
					try {
						sem.acquire();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					try {
						lock.lock();
						count ++;
						lock.unlock();
						System.err.println(count);
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						lock.lock();
						count --;
						lock.unlock();
						System.err.println(count);
						sem.release();
					}
				}
			});
		}
		
		pools.shutdown();
	}
	
}
