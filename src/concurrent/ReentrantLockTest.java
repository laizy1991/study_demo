package concurrent;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
	private static final ReentrantLock lock = new ReentrantLock();
	private static final ReentrantLock lockFair = new ReentrantLock(true);
	
	private static volatile int count = 0;
	
	public static void inc() {
		lock.lock();
		try {
			count ++;
		} finally {
			lock.unlock();
		}
	}
	
	public static void incFair() {
		lockFair.lock();
		try {
			count ++;
		} finally {
			lockFair.unlock();
		}
	}

	public static void inc2() {
		if(!lock.tryLock()) {
			return;
		}
		try {
			count ++;
		} finally {
			lock.unlock();
		}
	}
	
	public static void incFair2() {
		if(!lockFair.tryLock()) {
			return;
		}
		try {
			count ++;
		} finally {
			lockFair.unlock();
		}
	}
	public static void main(String[] args) {
		ThreadPoolExecutor pools = new ThreadPoolExecutor(10, 100, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10000));
		for(int i=0; i<10000; i++) {
			/*pools.execute(new Runnable() {
				@Override
				public void run() {
					ReentrantLockTest.inc();
				}
				
			});
			
			pools.execute(new Runnable() {
				@Override
				public void run() {
					ReentrantLockTest.incFair();
				}
				
			});

			pools.execute(new Runnable() {
				@Override
				public void run() {
					ReentrantLockTest.inc2();
				}
				
			});
*/
			pools.execute(new Runnable() {
				@Override
				public void run() {
					ReentrantLockTest.incFair2();
				}
				
			});
		}
		
		pools.shutdown();
		
		try {
			pools.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.err.println(count);
	}
}
