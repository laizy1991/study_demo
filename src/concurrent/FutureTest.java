package concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FutureTest {
	static ThreadPoolExecutor pools = new ThreadPoolExecutor(10, 10, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10));
	
	public static void main(String[] args) throws InterruptedException {
		Future<Map<String, String>> future = getDataFormRemote2();
		pools.shutdown();
		System.err.println(System.currentTimeMillis());
		try {
			System.out.println(future.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println(System.currentTimeMillis());
	}
	
	public static Future<Map<String, String>> getDataFormRemote2() {
		return pools.submit(new Callable<Map<String, String>>() {
			@Override
			public Map<String, String> call() throws Exception {
				System.out.println("start");
				return getDataFormRemote();
			}
		});
	}
	
	public static Map<String, String> getDataFormRemote() throws InterruptedException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "1");
		map.put("1", "1");
		map.put("3", "3");
		Thread.sleep(2000);
		return map;
	}
}
