package new_mind;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_15666_N°úM {
	private static int N;
	private static int M;
	private static int[] result;
	private static int[] arr;
	private static int[] arr2;
	private static int size;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		result = new int[M];

		st = new StringTokenizer(br.readLine(), " ");

		arr = new int[N];

		size = 0;
		go: for (int i = 0; i < N; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			for (int j = 0; j < size; j++) {
				if (arr[j] == tmp) {
					continue go;
				}
			}
			arr[size] = tmp;
			size++;
		}
		Arrays.sort(arr);
		arr2 = new int[size];

		int idx = 0;
		for (int i = 0; i < arr.length; i++) {
			while (arr[i] == 0) {
				i++;
			}
			arr2[idx++] = arr[i];
		}

		nHr(0, 0);
		System.out.print(sb.toString());

	}

	private static StringBuilder sb = new StringBuilder();

	private static void nHr(int idx, int len) {
		if (idx == size) {
			return;
		}
		if (len == M) {
			for (int i = 0; i < result.length; i++) {
				sb.append(result[i]).append(' ');
			}
			sb.append('\n');
			return;
		}

		result[len] = arr2[idx];
		nHr(idx, len + 1);
		nHr(idx + 1, len);
	}
}
