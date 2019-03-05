import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2634_사냥꾼 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int M = Integer.parseInt(st.nextToken()); // 사대의 수
		int N = Integer.parseInt(st.nextToken()); // 동물의 수
		long L = Long.parseLong(st.nextToken()); // 사정거리

		int[] arr_m = new int[M];

		st = new StringTokenizer(br.readLine(), " "); // 사대의 x좌표를 저장할 리스트
		for (int i = 0; i < M; i++) {
			arr_m[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr_m);

		int ans = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " "); // 동물들 좌표 x,y
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			if (L < y || x < arr_m[0] - L || x > arr_m[M - 1] + L) {
				continue;
			}

			for (int m_i = 0; m_i < M; m_i++) {
				if (L >= Math.abs(arr_m[m_i] - x) + y) {
					ans++;
					break;
				}
			}
		}
		System.out.println(ans);
	}
}
