package swea.d4;

/**
 * 시작좌표 (r,c)를 정하고, 사각형의 폭(w), 높이(h)를 정한뒤
 * 사각 영역의 각 꼭지점이 지역을 벗어나지 않는 지 유효성 검사를 하고,
 * 사각형 영역을 돌면서 작업
 * 		디저트 카페 번호를 카운팅하면서 겹치는 번호가 있는지 체크
 * 			겹치는 번호가 없으면 디저트카페 개수 : (w + h) * 2
 * 최대 방문할 수 있는 디저트카페의 개수를 출력
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_디저트카페_Prof {

	private static int[][] m;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int ts = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= ts; t++) {

			int N = Integer.parseInt(br.readLine().trim()); // 지역 크기, 4<=N<=20
			m = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					m[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int maxCnt = -1; // 사각형이 불가능할 경우는 -1로 출력하기 위해 초기값 -1로 지정

			// 모든 정점에 대해서, 모든 사각형 크기를 만들어보자
			for (int r = 0; r < N; r++) { // 행
				for (int c = 0; c < N; c++) { // 열
					for (int w = 1; w < N; w++) { // 폭
						if (r + w >= N || c + w >= N)
							continue; // 지역 벗어남
						for (int h = 1; h < N; h++) { // 높이
							if (r + h + w >= N || c - h < 0)
								continue; // 지역 벗어남

							int cnt = go(r, c, w, h); // 사각형 모양에서 방문할 수 있는 카페 개수를 리턴
							if (maxCnt < cnt)
								maxCnt = cnt;
						}
					}
				}
			}

			System.out.println("#" + t + " " + maxCnt);

		}
	}

	/** 사각형 모양에서 방문할 수 있는 카페 개수를 리턴 */
	public static int go(int r, int c, int w, int h) {
		boolean[] flag = new boolean[101];

		for (int i = 0; i <= w; i++) {

			if (flag[m[r + i][c + i]]) { // 벌써 값이 있으면 -1을 리턴한다.
				return -1;
			} else {
				flag[m[r + i][c + i]] = true;
			}

			if (flag[m[r + h + i][c - h + i]]) { // 벌써 값이 있으면 -1을 리턴한다.
				return -1;
			} else {
				flag[m[r + h + i][c - h + i]] = true;
			}

		}
		for (int i = 1; i < h; i++) { // 사각형의 꼭지점은 체크를 했으므로
			if (flag[m[r + i][c - i]])
				return -1; // 같은 카페번호 중복
			flag[m[r + i][c - i]] = true;

			if (flag[m[r + w + i][c + w - i]])
				return -1;
			flag[m[r + w + i][c + w - i]] = true;
		}

		return (w + h) * 2;
	}

}
