package programmers;
/**
 * Hash 배우기
 */
public class Programmers_완주하지못한선수 {

	public static void main(String[] args) {
		String[] a = { "marina", "josipa", "nikola", "vinko", "filipa" };
		String[] b = { "josipa", "filipa", "marina", "nikola" };

		System.out.println(solution(a, b));
	}

	private static final int HASH_SIZE = 1000;
	private static final int HASH_LEN = 400;
	private static final int HASH_VAL = 17; // 19, 23을 가장 많이씀

	private static String[][] hash = new String[HASH_SIZE][HASH_LEN];
	private static int[] cnt = new int[HASH_SIZE];

	public static String solution(String[] participant, String[] completion) {

		// 완주한사람들을 HashTable로 만들어줌
		for (int i = 0; i < completion.length; i++) {
			int key = getHashKey(completion[i]);
			hash[key][cnt[key]++] = completion[i];
		}

		// 참가자 명단을 완주한사람 테이블에서 돌려봄
		for (int i = 0; i < participant.length; i++) {
			int key = getHashKey(participant[i]);
			if (cnt[key] == 0) {
				return participant[i];
			} else {
				boolean flag = false;
				for (int j = 0; j < cnt[key]; j++) {
					if (hash[key][j].equals(participant[i])) {
						flag = true;
						hash[key][j] = "";
						break;
					}
				}

				if (!flag) {
					return participant[i];
				}
			}
		}
		
		return null;
	}

	private static int getHashKey(String str) {
		int key = 0;
		for (int i = 0; i < str.length(); i++) {
			key = key * HASH_VAL + str.charAt(i);
		}
		if (key < 0) {
			key = -key;
		}

		return key % HASH_SIZE;
	}
}
