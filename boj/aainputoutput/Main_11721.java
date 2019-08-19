package boj.aainputoutput;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_11721 {
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = br.readLine();

		for (int i = 1; i <= str.length(); i++) {
			System.out.print(str.charAt(i - 1));
			if (i % 10 == 0) {
				System.out.println();
			}
		}
	}
}
