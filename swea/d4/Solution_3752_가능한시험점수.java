package swea.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution_3752_가능한시험점수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // Test Case 수

		for (int tc = 1; tc <= TC; tc++) {
			HashSet<Integer> hs = new HashSet<Integer>();
			ArrayList<Integer> al = new ArrayList<Integer>();

			int N = Integer.parseInt(br.readLine().trim());

			int[] arr = new int[N];

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken()); // 각각 배점
			}

			al.add(0);

			int cnt = 1;
			for (int i = 0; i < N; i++) {
				int size = al.size();
				for (int j = 0; j < size; j++) {
					int temp = al.get(j) + arr[i];
					if (!hs.contains(temp)) {
						al.add(al.get(j) + arr[i]);
						hs.add(al.get(j) + arr[i]);
					}
				}
			}
//			System.out.println(hs);
			System.out.println("#" + tc + " " + (hs.size() + 1));
		}
	}

//	private static void comb(int[] arr, int idx, int score) {
//		hs.add(score);
//		
//		if(idx == N) {
//			return;
//		}
//		
//		comb(arr, idx+1, score + arr[idx]);
//		comb(arr, idx+1, score);
//	}
}
