package d5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 0900 ~ 0950
public class Solution_7206_숫자게임_dp {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder res = new StringBuilder(128);

		/*
		 * DP: in bottom-up way, calculate maximum of all splitted-then-multiplied
		 * numbers 1. make all possible splitted-then-multiplied numbers (recursive
		 * splitMultiplied) 2. find maximum
		 */

		/*
		 * for (int i = 0; i < 10; i++) System.out.println(i + ": " + sequenceCnt(i));
		 */

		int T = Integer.parseInt(br.readLine());
		int[] testNums = new int[T];
		int maxTestNum = 0;
		for (int i = 0; i < T; i++) {
			testNums[i] = Integer.parseInt(br.readLine());
			maxTestNum = (testNums[i] > maxTestNum) ? testNums[i] : maxTestNum;
		}

		int[] counts = new int[maxTestNum + 1];

		for (int n = 10; n < counts.length; n++) {
			int[] splitMultis = splitMultiplied(n);
			int max = 0;
			for (int i = 1; i <= splitMultis[0]; i++) {
				int tmp = counts[splitMultis[i]];
				max = (tmp > max) ? tmp : max;
			}
			counts[n] = max + 1;
		}

		for (int tc = 1; tc <= T; tc++)
			res.append('#').append(tc).append(' ').append(counts[testNums[tc - 1]]).append('\n');

		bw.write(res.toString());
		bw.close();
		br.close();
	}

	private static int[] splitMultiplied(int org) {
		String numStr = Integer.toString(org);
		int len = numStr.length();

		int[] res = new int[sequenceCnt(len) + 1]; // ?!?!? how to calculate this length...
		res[0] = 0;
		res[++res[0]] = org;
		for (int i = 1; i < len; i++) {
			int[] left = splitMultiplied(Integer.parseInt(numStr.substring(0, i)));
			int[] right = splitMultiplied(Integer.parseInt(numStr.substring(i)));

			for (int idxL = 1; idxL <= left[0]; idxL++) {
				for (int idxR = 1; idxR <= right[0]; idxR++) {
					res[++res[0]] = left[idxL] * right[idxR];
				}
			}
		}

		return res;
	}

	private static int sequenceCnt(int n) {
		if (n <= 1)
			return 1;

		int sum = 0;
		for (int i = 1; i < n; i++)
			sum += sequenceCnt(i) * sequenceCnt(n - i) + 1;

		return sum;
	}
}
