package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class NY_Solution_1808_지희의고장난계산기_DP {
	private static int X;
	private static int[] dp;
	private static int[] canUse;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= TC; tc++) {
			canUse = new int[10];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			aList = new ArrayList<>();
			X = Integer.parseInt(br.readLine().trim());
			dp = new int[2000000];
			
			for (int i = 0; i < dp.length; i++) {
				dp[i] = Integer.MAX_VALUE;
			}
			
			int idx = 0;
			while (st.hasMoreTokens()) {
				canUse[idx] = Integer.parseInt(st.nextToken());
				idx++;
			}
			
			for (int i = 0; i < canUse.length; i++) {
				if(canUse[i] != 0) {
					dp[i] = 1;
				}
			}
			
			String temp = X + "";
			H(0,temp.length());
			// 1 2 8
			
			
			dp[0] = 0;

			for (int i = 1; i < dp.length; i++) {
				if(i > X) {
					break;
				}
				for (int j = 0; j < canUse.length; j++) {
					if(canUse[j] == 0) {
						continue;
					}
					if(i == j) {
						dp[i] = 1;	
						break;
					}								

					if(j == 0) {
						continue;
					}
					if (i % j == 0) { // 나눠짐
						int value = i / j;
						if (dp[value] != Integer.MAX_VALUE) {
							dp[i] = Math.min(dp[i], dp[value] + 2);
						}
					}
				}
			}
			
			int ANS = -1;
			if(dp[X] == Integer.MAX_VALUE) {
				sb.append('#').append(tc).append(' ').append(ANS).append('\n');
				continue;
			}
			sb.append('#').append(tc).append(' ').append((dp[X]+1)).append('\n');
		}
		System.out.println(sb.toString());
	}
	
	static ArrayList<Integer> aList; // 임시
	
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
				dp[num] = temp.length();
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
	}
}
