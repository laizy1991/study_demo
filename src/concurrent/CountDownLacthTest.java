package concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class CountDownLacthTest {
	public static volatile int count = 0;
	static CountDownLatch latch = new CountDownLatch(1000);
	static ReentrantLock lock = new ReentrantLock();
	
	public static void main(String[] args) throws InterruptedException {
		ThreadPoolExecutor pools = new ThreadPoolExecutor(10, 20, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1000));
		
		for(int i=0; i<1000; i++) {
			pools.execute(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					lock.lock();
					count ++;
					lock.unlock();
					latch.countDown();
				}
			});
		}
		
		pools.shutdown();
		latch.await();
		System.out.println("main:count:" + count);
	}
	
}
