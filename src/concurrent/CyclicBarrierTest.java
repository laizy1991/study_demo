package concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class CyclicBarrierTest {

	static int count = 0;
	static ReentrantLock lock = new ReentrantLock();
	static CyclicBarrier barrier = new CyclicBarrier(11);
	
	public static void main(String[] args) {
		ThreadPoolExecutor pools = new ThreadPoolExecutor(10, 10, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10));
		
		for(int i=0; i<10; i++) {
			pools.execute(new Runnable() {
				public void run() {
					System.out.println("start : " + count);
					lock.lock();
					count ++;
					lock.unlock();
					System.out.println("inr : " + count);
					try {
						barrier.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
					System.out.println(count);
				}
			});
		}
		
		try {
			barrier.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
		System.out.println("main:" + count);
		pools.shutdown();
	}
	
	
}
