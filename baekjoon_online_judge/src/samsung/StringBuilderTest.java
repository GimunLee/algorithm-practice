package samsung;

/**
 * StringBuilder ¼º´É Test
 * */
public class StringBuilderTest {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
		for (int i = 0; i <= 10000000; i++) {
			System.out.println(i);
		}
		long end = System.currentTimeMillis();
		
		StringBuilder sb = new StringBuilder();
		
		long start2 = System.currentTimeMillis();
		for (int i = 0; i <= 10000000; i++) {
			sb.append(i).append("\n");
		}
		System.out.println(sb.toString());
		long end2 = System.currentTimeMillis();
		
		System.out.println("System.out.println : " + (end-start));
		System.out.println("StringBuilder      : " + (end2-start2));
		

	}
}
