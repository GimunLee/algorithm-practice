package swea.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3499_∆€∆Â∆Æº≈«√ {

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= TC; tc++) {
			sb.append("#").append(tc).append(" ");
			int N = Integer.parseInt(br.readLine());
			String[] array = new String[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				array[i] = st.nextToken();
			} // end of for(Input)

			String[] output = new String[N];
			int leftIdx = 0;
			int rightIdx = N / 2;

			if (N % 2 != 0) {
				rightIdx += 1;
			}

			for (int i = 0; i < array.length; i++) {
				sb.append(array[leftIdx++]).append(" ");
				if (i == array.length - 1) {
					break;
				}
				sb.append(array[rightIdx++]).append(" ");
				i++;
			}
			sb.append("\n");
		} // end of func(TestCase)
		System.out.println(sb.toString());
	}

}
