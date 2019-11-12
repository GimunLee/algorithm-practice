package kakao;

import java.util.HashMap;

public class Solution_04_2 {
	public static void main(String[] args) {
		long k = 10;
		long[] room_number = { 1, 3, 4, 1, 3, 1 };
		long[] tmp = solution(k, room_number);
		for (int i = 0; i < tmp.length; i++) {
			System.out.println(tmp[i]);
		}
	}

	public static long[] solution(long k, long[] room_number) {
		long[] answer = new long[room_number.length];
		long max = (long) Math.pow(10, 12);

		HashMap<Long, Boolean> map = new HashMap<Long, Boolean>();

		for (int i = 0; i < room_number.length; i++) {
			if (map.get(room_number[i]) == null) {
				answer[i] = room_number[i];
				map.put(room_number[i], true);
			} else {
				for (long j = room_number[i] + 1; j < max; j++) {
					if (map.get(j) == null) {
						answer[i] = j;
						map.put(j, true);
						break;
					}
				}
			}
		}
		return answer;
	}

	public int lowerBoundSearch(int[] arr, int s, int e, int k) {
		if (s >= e) {
			return s + 1;
		}
		int m = (s + e) / 2;
		if (arr[m] < k) {
			return lowerBoundSearch(arr, m + 1, e, k);
		} else { // arr[m] > k
			return lowerBoundSearch(arr, s, m, k);
		}
	}

}
