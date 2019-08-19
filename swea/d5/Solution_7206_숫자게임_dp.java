package swea.d5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// Top Down DP. sequence calculation optimized
public class Solution_7206_숫자게임_dp {
   	static int[] counts;
    static int[] sequenceLen;
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder res = new StringBuilder(128);

		/*
		 * DP: in bottom-up way, calculate maximum of all splitted-then-multiplied
		 *     numbers 
		 *  1. make all possible splitted-then-multiplied numbers (recursive splitMultiplied)
		 *  2. find maximum
		 */
		
		int T = Integer.parseInt(br.readLine());
		int[] testNums = new int[T];
		int maxTestNum = 0;
		for (int i = 0; i < T; i++) {
			testNums[i] = Integer.parseInt(br.readLine());
			maxTestNum = (testNums[i] > maxTestNum) ? testNums[i] : maxTestNum;
		}
        
        counts = new int[maxTestNum + 1];
        sequenceLen = new int[(int) Math.log10(maxTestNum) + 2];
		sequenceLen[1] = 1;
			
		for (int tc = 1; tc <= T; tc++) {
			topDownDP(testNums[tc-1]);
			res.append('#').append(tc).append(' ').append(counts[testNums[tc-1]]).append('\n');
		}
        
		bw.write(res.toString());
		bw.close();
		br.close();
	}
    
    private static void topDownDP(int org) {
		if (org < 10)
			return;
		int[] splitMultis = splitMultiplied(org);
		int max = 0;
		for (int i = 2; i <= splitMultis[0]; i++) {
			if (counts[splitMultis[i]] == 0)
				topDownDP(splitMultis[i]);
			int tmp = counts[splitMultis[i]];
			max = (tmp > max) ? tmp : max;
		}
		counts[org] = max + 1;
	}

	static int[] digits = {1, 10, 100, 1000, 10000, 100000};
	private static int[] splitMultiplied(int org) {
		int len = 0;
		for (; digits[len] < org; len++);

		int[] res = new int[sequenceCnt(len) + 1];
		res[0] = 0;
		res[++res[0]] = org;
		for (int i = 1; i < len; i++) {
			int[] left = splitMultiplied(org / digits[i]);
			int[] right = splitMultiplied(org % digits[i]);
			
			for (int idxL = 1; idxL <= left[0]; idxL++)
				for (int idxR = 1; idxR <= right[0]; idxR++)
					res[++res[0]] = left[idxL] * right[idxR];
		}
		return res;
	}
	
	private static int sequenceCnt(int n) {
		if (n <= 1)
			return 1;
		if (sequenceLen[n] == 0) {
			int sum = 0;
			for (int i = 1; i < n; i++)
				sum += sequenceCnt(i) * sequenceCnt(n - i) + 1;
			sequenceLen[n] = sum;
		}
		return sequenceLen[n];
	}
}
