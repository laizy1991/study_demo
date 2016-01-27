package concurrent;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class NotifyTest {
	public static final Object obj = new Object();
	public static int count = 0;
	
	public static void main(String[] args) {
		ThreadPoolExecutor pools = new ThreadPoolExecutor(100, 100, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(100));
		for(int i=0; i<100; i++)
			pools.execute(new Runnable() {
	
				@Override
				public void run() {
					try {
						test(count ++);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} 
				}
				
			});
		
		pools.shutdown();
		try {
			pools.awaitTermination(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("notify");
		synchronized (obj) {
			obj.notify();
		}
		System.out.println("notify");
		synchronized (obj) {
			obj.notify();
		}
		System.out.println("notify");
		synchronized (obj) {
			obj.notify();
		}
		System.out.println("notifyAll");
		synchronized (obj) {
			obj.notifyAll();
		}
	}
	
	private static void test(int index) throws InterruptedException {
		synchronized (obj) {
			obj.wait();
		}
		
		System.out.println(index);
	}
	
}
