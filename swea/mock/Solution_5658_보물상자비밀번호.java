package swea.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution_5658_보물상자비밀번호 {
	private static char[] lock;
	private static int N, K;
	private static HashMap<String, Integer> map;
	private static ArrayList<Long> list;
	private static ArrayList<String> slist;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			lock = br.readLine().toCharArray();
			list = new ArrayList();
			slist = new ArrayList();
			map = new HashMap<>();

			for (int i = 0; i < N / 4; i++) {
				parseAndStore();
				rotate();
			}
			Collections.sort(list, Comparator.reverseOrder());
			sb.append("#").append(tc).append(" ").append(list.get(K - 1)).append("\n");
		}
		System.out.println(sb.toString());
	} // end of main

	private static void parseAndStore() {
		for (int i = 0; i < 4; i++) {
			String hexa = "";
			for (int j = 0; j < N / 4; j++) {
				int idx = (N / 4) * i + j;
				hexa += lock[idx];
			}
			if (!map.containsKey(hexa)) {
				map.put(hexa, 1);
				slist.add(hexa);
				list.add(hexaToDecimal(hexa));
			}
		}
	}

	private static long hexaToDecimal(String hexa) {
		long ret = 0;
		for (int i = hexa.length() - 1; i >= 0; i--) {
			int num = hexa.charAt(i) - '0';
			if (num >= 17 && num <= 22) { // A ~ F
				num -= 7;
			}
			int digit = (hexa.length() - 1) - i;
			ret += Math.pow(16, digit) * num;
		}
		return ret;
	}

	private static void rotate() {
		char tmp = lock[lock.length - 1];
		for (int i = lock.length - 2; i >= 0; i--) {
			lock[i + 1] = lock[i];
		}
		lock[0] = tmp;
	}
} // end of class
