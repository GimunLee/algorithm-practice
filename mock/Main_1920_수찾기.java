package mock;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1920_¼öÃ£±â {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine().trim());
		int[] A = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(A);

		int M = Integer.parseInt(br.readLine().trim());
		st = new StringTokenizer(br.readLine(), " ");
		int[] input = new int[M];
		for (int i = 0; i < M; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < input.length; i++) {
			binarySearch(A, input, i, 0, N);
		}
		System.out.println(sb.toString());
	} // end of main

	static StringBuilder sb = new StringBuilder();

	private static void binarySearch(int[] A, int[] input, int input_idx, int start, int end) {
		if (end <= start) {
			sb.append("0").append("\n");
			return;
		}

		int mid = (start + end) / 2;
//		System.out.println("s : " + start + ", " + "e : " + end + ", " + mid);

		if (A[mid] > input[input_idx]) {
			binarySearch(A, input, input_idx, start, mid);
		} else if (A[mid] < input[input_idx]) {
			binarySearch(A, input, input_idx, mid + 1, end);
		} else {
			sb.append("1").append("\n");
			return;
		}

	}
} // end of class
