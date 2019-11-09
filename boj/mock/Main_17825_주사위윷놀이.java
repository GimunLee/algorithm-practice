package boj.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_17825_주사위윷놀이 {
	private static final int[] map = new int[20];
	private static final int[] map10 = new int[4];
	private static final int[] map20 = new int[3];
	private static final int[] map25 = new int[3];
	private static final int[] map30 = new int[4];

	private static HashMap<Horse, Integer> hashMap;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] input = new int[10];
		for (int i = 0; i < 10; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		} // -- end of for(Input)

		init();

	} // end of main

	private static boolean[] isEnd = new boolean[4];
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
				horse.position = tmpPos - map25.length;
			} else {
				horse.position = tmpPos;
				break;
			}
		case 30:
			if (tmpPos >= map30.length) {
				horse.mapIdx = 25;
				horse.position = tmpPos - map10.length;
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
			hashMap.put(horse, 1);
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
			map30[i] = map30[i - 1] - 2;
		}
		map20[0] = 20;
		map25[0] = 25;
		for (int i = 1; i < map20.length; i++) {
			map20[i] = map20[i - 1] + 2;
			map25[i] = map25[i - 1] + 5;
		}
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

	}
} // end of class
