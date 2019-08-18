package aa_input_output;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_11719 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int sum = 0;
		String str = br.readLine();

		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(str.charAt(i) + "");
			sum += num;
		}

		System.out.println(sum);
	}
}
