package programmers.level2;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class Solution_¿ß¿Â {
	public static void main(String[] args) {
		String[][] clothes = { { "yellow_hat", "headgear" }, { "blue_sunglasses", "headgear" },
				{ "green_turban", "headgear" } };

		System.out.println(solution(clothes));
	}

	public static int solution(String[][] clothes) {
		int answer = 1;
		LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();

		for (int r = 0; r < clothes.length; r++) {
			String rawData = clothes[r][1];
			int cnt = 0;
			if (map.containsKey(rawData)) {
				cnt = map.get(rawData) + 1;
			} else {
				cnt = 1;
			}
			map.put(rawData, cnt);
		}

		Iterator<Integer> values = map.values().iterator();
		while (values.hasNext()) {
			int value = values.next();
			answer *= (value + 1);
		}
		return answer - 1;
	}
}
