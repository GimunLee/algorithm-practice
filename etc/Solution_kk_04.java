package etc;

import java.util.HashMap;

public class Solution_kk_04 {
	public static void main(String[] args) {
		long k = 10;
		long[] room_number = { 1, 3, 4, 1, 3, 1 };
		long[] tmp = solution(k, room_number);

		for (int i = 0; i < tmp.length; i++) {
			System.out.println(tmp[i]);
		}

	}

	static HashMap<Long, Long> map = new HashMap<Long, Long>();

	public static long[] solution(long k, long[] room_number) {
		long[] answer = new long[room_number.length];

		for (int i = 0; i < room_number.length; i++) {
			long number = room_number[i];
			if (map.get(number) == null) {
				answer[i] = number;
				map.put(number, number + 1);
			} else { // 이미 배정 받은 경우
				long origin_next = map.get(number);
				while (true) {
					long next = map.get(number);
					if (map.get(next) == null) {
						answer[i] = map.get(next);
						map.put(next, next + 1);
					} else {
						map.put(next, map.get(next));

					}
					number++;
				}
			}

		}

		return answer;
	}

}
