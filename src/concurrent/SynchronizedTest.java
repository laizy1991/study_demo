package concurrent;

public class SynchronizedTest {

	public synchronized static void foo1() {
		System.out.println("foo1");
	}
	public synchronized static void foo2() {
		System.out.println("foo2");
	}


	public synchronized void foo3() {
		System.out.println("foo3");
	}
	public synchronized void foo4() {
		System.out.println("foo4");
	}
	
	public void foo5() {
		synchronized (this) {
			System.out.println("foo5");
		}
	}
	
	public void foo6() {
		synchronized (SynchronizedTest.class) {
			System.out.println("foo6");
		}
	}
}
