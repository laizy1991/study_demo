package concurrent;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池测试
 * @author Administrator
 *
 */
public class TreadPoolTest {
	private static final int count = 100;
	private static final Random random = new Random();
	
	public static void main(String[] args) {
		useThreadPool();
		useThread();
	}
	
	private static void useThreadPool() {
		long startTime = System.currentTimeMillis();
		final List<Integer> list = new LinkedList<>();
		ThreadPoolExecutor pools = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(count));
		for(int i=0; i<count; i++) {
			pools.execute(new Runnable() {
				@Override
				public void run() {
					list.add(random.nextInt());
				}
				
			});
		}
		pools.shutdown();
		try {
			pools.awaitTermination(1, TimeUnit.DAYS);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("usePools:" + (System.currentTimeMillis() - startTime));
		System.out.println("usePools" + list.size());
	}
	
	private static void useThread() {
		long startTime = System.currentTimeMillis();
		final List<Integer> list = new LinkedList<Integer>();
		for(int i=0; i<count; i++) {
			Thread thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					list.add(random.nextInt());
				}
			});
			
			thread.start();
			try {
				thread.join();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("useThread:" + (System.currentTimeMillis() - startTime));
		System.out.println("useThread" + list.size());
	}
}
