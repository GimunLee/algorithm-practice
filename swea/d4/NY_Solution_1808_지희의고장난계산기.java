package swea.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class NY_Solution_1808_지희의고장난계산기 {
	private static int[] canUse;
	private static int X;

	public static void main(String[] args) throws NumberFormatException, IOException {

		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // Test Case 수

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			aList = new ArrayList<>();
			canList = new ArrayList<>();
			canUse = new int[10];

			int idx = 0;
			while (st.hasMoreTokens()) {
				int num = Integer.parseInt(st.nextToken());
				canUse[idx++] = num;
			}
			X = Integer.parseInt(br.readLine().trim()); // 원하는 수
			String tX = X + "";

			// 사용할 수 뽑기 (중복순열)
			H(0, tX.length());

			int min = Integer.MAX_VALUE; // 최소 값

			int sum = 0;
			for (int i = 0; i < canList.size(); i++) {
//				System.out.println(canList.get(i));
				if (X == canList.get(i)) {
					String temp = canList.get(i) + "";
					min = temp.length() + 1;
				}

				if (X % canList.get(i) == 0) { // 나눠지면
					int value = X / canList.get(i);
					for (int j = 0; j < canList.size(); j++) {
						if (value == canList.get(j)) { // 그 조합이 존재하는 경우
							String temp1 = canList.get(i) + "";
							String temp2 = canList.get(j) + "";
							int size1 = temp1.length();
							int size2 = temp2.length();

							sum = size1 + size2 + 2;

							if (sum < min) {
								min = sum;
							}
						}
					}
				}
			}

			if (min == Integer.MAX_VALUE) {
				min = -1;
			}

			sb.append('#').append(tc).append(' ').append(min).append('\n');
		}
		System.out.println(sb.toString());
	} // end of main

	static ArrayList<Integer> aList; // 임시
	static ArrayList<Integer> canList;

	private static void H(int cnt, int limit) {
		if (cnt <= limit) { // 뽑은 것에서 또 조합으로 나누기
			int num = 0;
			if (aList.size() != 0) {
				String temp = "";
				for (int i = 0; i < aList.size(); i++) {
					temp += aList.get(i);
				}
				num = Integer.parseInt(temp);

				if (X < num || num == 0) { // 가지치기
					return;
				}
				canList.add(num); // 가능한 수 조합 저장
			}
		}

		if (cnt == limit) {
			return;
		}

		for (int i = 0; i < canUse.length; i++) {
			if (canUse[i] == 1) {
				aList.add(i);
				H(cnt + 1, limit);
				aList.remove(aList.size() - 1);
			}
		}
	} // end of H()
} // end of class
