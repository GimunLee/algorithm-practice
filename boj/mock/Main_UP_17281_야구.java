package boj.mock;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_UP_17281_야구 {
	private static int N; // 이닝 수
	private static int[][] hitter; // idx : 타자 번호 // [n][x] : n이닝 때 x번 선수의 친 결과

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim()); // 이닝 수
		hitter = new int[N][9]; // 이닝, 타자 수만큼 생성

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < hitter[i].length; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				hitter[i][j] = tmp;
			}
		} // end of for(input)

		set[3] = 0; // 4번 타자는 1번 타자로 고정
		setHitter(0); // 타자의 순서를 설정하고 게임 시작
		System.out.println(ans); // 정답 출력
	} // end of main

	private static int[] set = new int[9]; // 타자 순서 결정
	private static boolean[] isSet = new boolean[9]; // 순열을 뽑기 위한 변수
	private static int ans = 0; // 정답 변수

	/** 9명의 타자의 순서를 결정하는 순열 함수 */
	private static void setHitter(int len) {
		if (len == 3) { // 4번 타자는 1번 타자로 고정이므로, 바로 다음으로 타자로 넘깁니다.
			setHitter(len + 1);
			return;
		}
		
		if (len == 9) { // 9명의 타자의 순서를 모두 정했을 때
			int tmp_ans = 0; // 임시 점수 
			int nHitter = 0; // 다음 타자를 담고 있는 set 배열의 인덱스

			// 게임 진행
			here: for (int i = 0; i < N; i++) {
				int out_cnt = 0; // 한 게임당 out 개수 변수
				boolean[] base = new boolean[4]; // 1: 1루, 2: 2루, 3: 3루
				
				while (true) { // 쓰리아웃이 될 때까지 게임 진행
					for (int j = nHitter; j < set.length; j++) { // 타자 수만큼 반복
						nHitter = (j + 1 == 9) ? 0 : j + 1; // 다음 이닝 때, 칠 순서의 타자 정보를 저장  
						int state = hitter[i][set[j]]; // 해당 타자의 해당 이닝 때의 친 값
						if (state == 4) { // 홈런일 때, 1점 더하기
							tmp_ans++;
						}

						if (state != 0) { // 타자가 쳤다면
							for (int k = base.length - 1; k > 0; k--) { // 역순으로 베이스 확인하며 친 값만큼 더해주기 (진루 현황)
								if (base[k]) { // 베이스에 주자가 있다면
									if (k + state >= 4) { // 득점
										tmp_ans++;
										base[k] = false; // 해당 베이스는 비어있음
									} else { // 득점하지 않은 경우 진루
										base[k + state] = true;
										base[k] = false;
									}
								}
							}
							if (state < 4) { // 베이스에 있던 선수들 진루하고, 현재 타자 진루
								base[state] = true;
							}
						} else { // 타자가 치지 못했다면
							out_cnt++; // 아웃 카운트 증가
							if (out_cnt >= 3) { // 쓰리아웃이면 
								continue here; // 다음 이닝으로 보낸다.
							}
						}
					}
				} // end of while(until three out)
			} // end of for(inning)
			ans = tmp_ans > ans ? tmp_ans : ans; // 정답 갱신
			return;
		}
		
		for (int i = 1; i < set.length; i++) {
			if (!isSet[i]) { // 뽑지 않은 타자라면
				set[len] = i; // set에 해당 타자 번호 저장
				isSet[i] = true; // 뽑은 것을 표시
				setHitter(len + 1); // 다음 타자로 재귀 호출
				isSet[i] = false; // 다시 뽑을 수 있도록 false 처리
			}
		}
	} // end of setHitter
} // end of class
