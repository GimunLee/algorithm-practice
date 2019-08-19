package swea.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 자기 방으로 돌아가기
 */
public class Solution_4408_자기방으로돌아가기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine().trim()); // T : 테스트케이스 수

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine().trim()); // N : 돌아가야 할 학생들의 수

			int[] room = new int[401];

			for (int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int start = Integer.parseInt(st.nextToken()); // 현재 방
				int end = Integer.parseInt(st.nextToken()); // 돌아갈 방

				room[start] = i;
				room[end] = i;
			}

//			System.out.println(Arrays.toString(room));
			
			int ans = 1;
			
			for (int i = 1; i < 401; i++) {
				if(room[i] !=0) { // 가야할 학생이 있으면
//					System.out.println(room[i]+"번째 학생");
					for (int j = i+1; j < 401; j++) {
						if(room[j] != 0) {
							if(room[i] == room[j]) { // 무사 도착
//								System.out.println("if : "+room[i] + " " + room[j]);
								room[i] = 0;
								room[j] = 0;
								break;
								
							}else{
//								System.out.println("else : " +room[i] + " " + room[j]);
								if(room[j] == N) {
									break;
								}
								ans++;
								continue;
							}
						}
					}
				}
			}
			System.out.println("#" + tc + " " + ans);
		}

	}
}
