package boj.samsung;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class UP_Main_17135_캐슬디펜스 {
	private static int N; // 격자판 행 제한
	private static int M; // 격자판 열 제한
	private static int D; // 궁수의 공격 거리 제한
	private static int[][] map; // 격자판을 저장할 변수
	private static ArrayList<Enermy> list; // 적군의 정보를 저장할 변수
	private static int enermy_cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 행의 수
		M = Integer.parseInt(st.nextToken()); // 열의 수
		D = Integer.parseInt(st.nextToken()); // 궁수의 공격 거리 제한

		map = new int[N][M]; // 격자판 생성
		list = new ArrayList<>();
		enermy_cnt = 0; // 적군의 총 인원을 저장할 변수

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 1) { // 적군인 경우
					list.add(new Enermy(r, c)); // list에 적군의 정보를 저장
					enermy_cnt++; // 적군 수 증가
				}
			}
		} // end of for(input)
		setDiffenser(0, 0); // 궁수 배치
		System.out.println(ans); // 정답 출력
	} // end of main

	private static int[] set = new int[3]; // 궁수의 열 값을 저장할 변수 (행은 N+1로 고정)

	/** DFS로 궁수의 열 값을 조합으로 뽑음 */
	private static void setDiffenser(int idx, int len) {
		if (len == 3) { // 궁수 3명의 자리를 뽑은 경우
			playGame(set); // 게임을 시작함
			return;
		}

		for (int i = idx; i < M; i++) { // 다음 뽑을 인덱스부터 for문 시작
			set[len] = i;
			setDiffenser(i + 1, len + 1);
		}
	} // end of setDiffenser()

	private static int ans = Integer.MIN_VALUE; // 정답을 저장할 변수

	// (N+1,set)
	private static void playGame(int[] set) {
		int temp_enermy_cnt = enermy_cnt; // 한 조합당 죽이고 남은 적군의 수를 저장할 임시 변수
		int temp_ans = 0; // 한 조합당 죽인 적군의 수를 저장할 변수
		ArrayList<Enermy> tlist = new ArrayList<>(); // 한 조합당 적군의 정보를 저장할 임시 list 변수

		for (int i = 0; i < list.size(); i++) {
			tlist.add(new Enermy(list.get(i))); // 초기 리스트에서 임시 리스트 변수로 복사
		}

		while (temp_enermy_cnt > 0) { // 남은 적군이 없을 때까지 반복
			for (int i = 0; i < set.length; i++) { // 궁수별로 진행
				for (int j = 0; j < tlist.size(); j++) { // 각 적군의 정보를 뽑을 for문
					Enermy e = tlist.get(j);
					if (!e.isDie) { // 죽지 않은 적군인 경우
						e.dis[i] = Math.abs(e.r - N) + Math.abs(e.c - set[i]); // 해당 궁수당 각 적군의 거리를 계산
					}
					e.turn = i; // 해당 궁수의 turn임을 저장
				} // end of for(Calculate Distance)

				Collections.sort(tlist); // 거리 순으로 정렬

				here: for (int j = 0; j < tlist.size(); j++) {
					Enermy e = tlist.get(j);
					if (!e.isDie) { // 아직 적이 죽지 않았으면
						if (e.dis[i] <= D) { // 궁수의 사정거리 안이면
							e.shooted++;
							break here;
						}
					}
				} // end of for(Kill Enermy)
			} // end of for(Calculate and Kill)

			// 화살에 맞았는지 판별
			for (int j = 0; j < tlist.size(); j++) {
				Enermy e = tlist.get(j);
				if (!e.isDie) { // 죽지 않은 경우
					if (e.shooted > 0) { // 화살에 맞았을 때
						e.isDie = true; // 해당 enermy는 죽음
						temp_ans++; // 죽인 적군의 수 증가
						temp_enermy_cnt--; // 남은 적군의 수 감소
					}
				}
				e.r++; // 적군을 한 칸 앞으로 전진
				if (!e.isDie) {
					if (e.r >= N) { // N 제한을 벗어난 경우
						e.isDie = true; // 죽은 것으로 처리
						temp_enermy_cnt--; // 남은 적군 수 감소
					}
				}
			}
		} // end of while(one time)
		ans = (ans < temp_ans) ? temp_ans : ans; // 정답 갱신
		return;
	} // end of playGame()

	/** Comparable를 궁수와 적군의 거리별로 정렬 */
	private static class Enermy implements Comparable<Enermy> {
		int r; // 적군의 행 좌표
		int c; // 적군의 열 좌표
		int turn; // 적을 죽일 때, 어떤 궁수가 쏘는지 판별할 변수 (궁수와 적군의 거리를 정렬할 때 쓰임)
		int[] dis; // 각 index는 각 궁수, 값은 궁수와 적군의 거리
		int shooted; // 각 적군이 화살에 맞았는지 저장할 변수
		boolean isDie; // 적군이 죽었는지 판별할 변수

		public Enermy(int r, int c) {
			this.r = r;
			this.c = c;
			dis = new int[3]; // 궁수는 3명으로 고정이므로 생성
		}

		public Enermy(Enermy e) {
			this.r = e.r;
			this.c = e.c;
			this.turn = e.turn;
			this.dis = e.dis;
			this.shooted = e.shooted;
			this.isDie = e.isDie;
		}

		@Override
		public int compareTo(Enermy o) {
			int temp = this.dis[this.turn] - o.dis[this.turn];
			if (temp == 0) { // 거리가 같을때
				// column 우선 정렬
				temp = this.c - o.c;
			}
			return temp;
		}
	} // end of Enermy
} // end of class
