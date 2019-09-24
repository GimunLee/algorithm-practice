package programmers;

/**
 * Hash ë°°ìš°ê¸?
 */
public class Programmers_¿ÏÁÖÇÏÁö¸øÇÑ¼±¼ö {

	public static void main(String[] args) {
		String[] a = { "marina", "josipa", "nikola", "vinko", "filipa" };
		String[] b = { "josipa", "filipa", "marina", "nikola" };

		System.out.println(solution(a, b));
	}

	private static final int HASH_SIZE = 1000;
	private static final int HASH_LEN = 400;
	private static final int HASH_VAL = 257; // 19, 23?„ ê°??¥ ë§ì´??

	private static String[][] hash = new String[HASH_SIZE][HASH_LEN];
	private static int[] cnt = new int[HASH_SIZE];

	public static String solution(String[] participant, String[] completion) {

		// ?™„ì£¼í•œ?‚¬?Œ?“¤?„ HashTableë¡? ë§Œë“¤?–´ì¤?
		for (int i = 0; i < completion.length; i++) {
			int key = getHashKey(completion[i]);
			hash[key][cnt[key]++] = completion[i];
		}

		// ì°¸ê?? ëª…ë‹¨?„ ?™„ì£¼í•œ?‚¬?Œ ?…Œ?´ë¸”ì—?„œ ?Œ? ¤ë´?
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
