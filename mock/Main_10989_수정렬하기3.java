package mock;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_10989_수정렬하기3 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		boolean[] arr = new boolean[10000001];
		int[] red = new int[10000001];

		for (int i = 0; i < N; i++) {
			int val = Integer.parseInt(br.readLine());
			arr[val] = true;
			red[val] += 1;
		}

		int temp = 0;
		for (int i = 1; i <= 10000000; i++) {
			if (arr[i]) {
				for (int j = 0; j < red[i]; j++) {
					sb.append(i).append("\n");
					temp++;
				}

				if (temp == N) {
					break;
				}
			}
		}
		System.out.println(sb.toString());
	}
}
