package concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ExchangerTest {
	static int count = 0;
	static ReentrantLock lock = new ReentrantLock();
	static Exchanger exchanger = new Exchanger();
	
	public static void main(String[] args) {
		ThreadPoolExecutor pools = new ThreadPoolExecutor(10, 10, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10));
		
		pools.execute(new Runnable() {
			List<Integer> list = new ArrayList<Integer>();
			public void run() {
				list.add(1);
				list.add(2);
				try {
					list = (List<Integer>) exchanger.exchange(list);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.err.println("Thread1:" + list);
			}
		});
		pools.execute(new Runnable() {
			List<Integer> list = new ArrayList<Integer>();
			public void run() {
				list.add(3);
				list.add(4);try {
					list = (List<Integer>) exchanger.exchange(list);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.err.println("Thread2:" + list);
			}
		});
		pools.shutdown();
	}
	
}
