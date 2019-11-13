package boj.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_17825_주사위윷놀이 {
	private static final int[] map = new int[21];
	private static final int[] map10 = new int[4];
	private static final int[] map20 = new int[3];
	private static final int[] map25 = new int[3];
	private static final int[] map30 = new int[4];

	private static HashMap<Horse, Integer> hashMap;
	private static int[] input;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		input = new int[10];
		for (int i = 0; i < 10; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		} // -- end of for(Input)

		init();
		map[20] = 0;
		map30[0] = 30;
		map30[1] = 28;
		map30[2] = 27;
		map30[3] = 26;
		System.out.println(Arrays.toString(map));
		System.out.println(Arrays.toString(map10));
		System.out.println(Arrays.toString(map20));
		System.out.println(Arrays.toString(map25));
		System.out.println(Arrays.toString(map30));

		answer = Integer.MIN_VALUE;
		dfs(0, 0);
		System.out.println(answer);
	} // end of main

	private static int answer;

	private static void dfs(int len, int score) {

		if (len == 10) {
			answer = Math.max(score, answer);
			return;
		}

		for (int j = 0; j < horseArray.length; j++) {
			Horse tmpHorse = new Horse(horseArray[j].mapIdx, horseArray[j].position);
			boolean tmp = go(j, input[len]);
			if (!tmp) {
				continue;
			}
			dfs(len + 1, score + getScore(j));
			horseArray[j] = tmpHorse;
		}
	}

	private static Horse[] horseArray;

	// idx번째 말을 보내보기
	private static boolean go(int idx, int diceNum) {
		Horse horse = horseArray[idx];
		Horse origin = new Horse(horse.mapIdx, horse.position);

		int tmpPos = horse.position + diceNum;
		switch (horse.mapIdx) {
		case 0:
			if (tmpPos == 4) {
				horse.mapIdx = 10;
				horse.position = 0;
				break;
			} else if (tmpPos == 9) {
				horse.mapIdx = 20;
				horse.position = 0;
				break;
			} else if (tmpPos == 14) {
				horse.mapIdx = 30;
				horse.position = 0;
				break;
			} else {
				horse.position = tmpPos;
				break;
			}
		case 10:
			if (tmpPos >= map10.length) {
				horse.mapIdx = 25;
				horse.position = tmpPos - map10.length;
				break;
			} else {
				horse.position = tmpPos;
				break;
			}
		case 20:
			if (tmpPos >= map20.length) {
				horse.mapIdx = 25;
				horse.position = tmpPos - map20.length;
				break;
			} else {
				horse.position = tmpPos;
				break;
			}
		case 25:
			if (tmpPos >= map25.length) {
				horse.mapIdx = 0;
				int tmp = tmpPos - map25.length;
				if (tmp >= 1) {
					horse.position = map.length - 1;
				} else {
					horse.position = map.length - 2;
				}
			} else {
				horse.position = tmpPos;
				break;
			}
		case 30:
			if (tmpPos >= map30.length) {
				horse.mapIdx = 0;
				horse.position = tmpPos - map30.length;
				break;
			} else {
				horse.position = tmpPos;
				break;
			}
		}
		
		if (hashMap.containsKey(horse)) {
			horse.mapIdx = origin.mapIdx;
			horse.position = origin.position;
			return false;
		} else {
			hashMap.remove(origin);
			Horse tmpHorse = new Horse(horse.mapIdx, horse.position);
			hashMap.put(tmpHorse, 1);
			return true;
		}
	}

	private static void init() {
		hashMap = new HashMap<>();
		horseArray = new Horse[4];
		for (int i = 0; i < horseArray.length; i++) {
			horseArray[i] = new Horse(0, -1);
		}
		for (int i = 0; i < map.length; i++) {
			map[i] = (i + 1) * 2;
		}
		map10[0] = 10;
		map30[0] = 30;
		for (int i = 1; i < map10.length; i++) {
			map10[i] = map10[i - 1] + 3;
		}
		map20[0] = 20;
		map25[0] = 25;
		for (int i = 1; i < map20.length; i++) {
			map20[i] = map20[i - 1] + 2;
			map25[i] = map25[i - 1] + 5;
		}
	}

	private static int getScore(int i) {
		int mapIdx = horseArray[i].mapIdx;
		int position = horseArray[i].position;
		switch (mapIdx) {
		case 0:
			if (position >= map.length) {
				return 0;
			}
			return map[position];
		case 10:
			if (position >= map10.length) {
				return 0;
			}
			return map10[position];
		case 20:
			if (position >= map20.length) {
				return 0;
			}
			return map20[position];
		case 25:
			if (position >= map25.length) {
				return 0;
			}
			return map25[position];
		case 30:
			if (position >= map30.length) {
				return 0;
			}
			return map30[position];
		}
		return 0;
	}

	private static class Horse {
		int mapIdx, position;

		public Horse(int mapIdx, int position) {
			this.mapIdx = mapIdx;
			this.position = position;
		}

		@Override
		public boolean equals(Object o) {
			Horse oo = (Horse) o;
			if (this.mapIdx == oo.mapIdx && this.position == oo.position) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public String toString() {
			return "Horse [mapIdx=" + mapIdx + ", position=" + position + "]";
		}
	}
} // end of class
