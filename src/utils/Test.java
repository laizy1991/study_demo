package utils;

public class Test {

	public static void main(String[] args) {
		try {
			System.err.println("begin...");
			throw new RuntimeException();
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.err.println("end...");
	}
	
}
