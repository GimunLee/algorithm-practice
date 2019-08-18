import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Codeforces_RegistrationSystem {

	// 보통 100,000 일때, 1000 * {100*4} 로 설정한다.
	private static final int HASH_SIZE = 1000;  
	private static final int HASH_LEN = 400;
	private static final int HASH_VAL = 17; // 19, 21 같은 나누어 떨어지지 않는 수로 나누어준다.

	private static String[][] hash = new String[HASH_SIZE][HASH_LEN];
	
	// 중복되는 데이터를 넣기 싫을때, 탐색용으로 쓸 만한 것 같다.
	private static int[][] idx = new int[HASH_SIZE][HASH_LEN];
	// hash key 값이 충돌났을 때, 해당 키값에 해당하는 value를 찾을때 활용한다.
	private static int[] cnt = new int[HASH_SIZE]; 

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim()); // 1 <= N <= 10^5
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			int key = getHashKey(str); // hash key를 이용해서 key 값을 얻어온다.
			
			boolean flag = false;
			int tmp = 0;
			for (int j = 0; j < cnt[key]; j++) {
				if (hash[key][j].equals(str)) {
					flag = true;
					break;
				}
				tmp++; // 값이 들어온 순서가 보장되기 때문에 해당 tmp로 해쉬에서 값을 찾는다.
			}
			if (!flag) { // 값이 충돌나지 않는 경우
				hash[key][cnt[key]++] = str;  
				sb.append("OK").append("\n");
			} else { // 값이 충돌나는 경우
				idx[key][tmp] += 1;
				sb.append(str).append(idx[key][tmp]).append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	private static int getHashKey(String str) {
		int key = 0; // 초기 key 값
		for (int i = 0; i < str.length(); i++) {
			key = key * HASH_VAL + str.charAt(i); // + HASH_VAL을 해줘도 되지만 크게 의미없다.
		}

		if (key < 0) { // stack overflow가 나는 경우
			key = -key;
		}

		return key % HASH_SIZE; // 해당 사이즈의 나머지에 저장한다.
	}

}
