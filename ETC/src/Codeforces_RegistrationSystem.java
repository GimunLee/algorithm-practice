import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Codeforces_RegistrationSystem {

	private static final int HASH_SIZE = 1000;
	private static final int HASH_LEN = 400;
	private static final int HASH_VAL = 17;

	private static String[][] hash = new String[HASH_SIZE][HASH_LEN];
	private static int[][] idx = new int[HASH_SIZE][HASH_LEN];
	private static int[] cnt = new int[HASH_SIZE];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim()); // 1 <= N <= 10^5
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			int key = getHashKey(str);
			boolean flag = false;
			int tmp = 0;
			for (int j = 0; j < cnt[key]; j++) {
				if (hash[key][j].equals(str)) {
					flag = true;
					break;
				}
				tmp++;
			}
			if (!flag) {
				hash[key][cnt[key]++] = str;
				sb.append("OK").append("\n");
			} else {
				idx[key][tmp] += 1;
				sb.append(str).append(idx[key][tmp]).append("\n");
			}
		}
		System.out.println(sb.toString());

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
