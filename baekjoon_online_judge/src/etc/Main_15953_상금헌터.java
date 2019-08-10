package etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15953_ªÛ±›«Â≈Õ {
	static final int[] price2017 = { 0, 500, 300, 200, 50, 30, 10 };
	static final int[] price2018 = { 0, 512, 256, 128, 64, 32 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine().trim()); // TestCase
		int[] contest2017 = new int[22];
		int[] contest2018 = new int[32];
		int idx2017 = 1;
		int idx2018 = 1;

		for (int i = 1; i < price2017.length; i++) {
			for (int j = 0; j < i; j++) {
				contest2017[idx2017++] = price2017[i];
			}
			if (i < price2018.length) {
				for (int j = 0; j < Math.pow(2, i - 1); j++) {
					contest2018[idx2018++] = price2018[i];
				}
			}
		}

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int result2017 = Integer.parseInt(st.nextToken());
			int result2018 = Integer.parseInt(st.nextToken());

			int ans = 0;
			if (result2017 <= 21) {
				ans += contest2017[result2017];
			}
			if (result2018 <= 31) {
				ans += contest2018[result2018];
			}
			sb.append(ans);
			if (ans != 0) {
				sb.append("0000");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	} // end of main
} // end of class
