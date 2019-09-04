package boj.mock;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_UP_17140_이차원배열과연산 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		// A[r][c] = k 일 때의 시간구하기
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[][] A = new int[100][100]; // 100을 넘어갈 수 없음

		for (int rr = 0; rr < 3; rr++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int cc = 0; cc < 3; cc++) {
				A[rr][cc] = Integer.parseInt(st.nextToken());
			}
		} // end of for(input)

		int size_r = 3; // 초기의 row : 3
		int size_c = 3; // 초기의 column : 3

		for (int time = 0; time <= 100; time++) { // 100시간을 넘어가면 -1 출력
			if (A[r - 1][c - 1] == k) { // 처음부터 답일 수 있으므로, 맨 처음 확인해줌
				System.out.println(time); // 정답 출력 후 종료
				return;
			}
			
			int size = Integer.MIN_VALUE; // 각 R,C 연산 중 나올 수 있는 최대 길이
			int[][] A_tmp; // 값을 변경해줄 임시 A 변수
			ArrayList<Pair> list; // 각 R,C 연산에서 값과 개수로 정렬할 변수
			
			if (size_r >= size_c) { // R 연산
				A_tmp = new int[size_r][size_c * 2]; // R 연산이므로, 행의 개수는 같고 열은 최대 ({열}*2)칸이 필요
				for (int rr = 0; rr < size_r; rr++) {
					list = new ArrayList<>(); // 각 행별로 list 생성
					int[] chk = new int[101]; // list에 이미 있는 값인지, indexing으로 속도 최적화하여 탐색
					int idx = 1; // 처음에는 무조건 1개 들어있음
					for (int cc = 0; cc < size_c; cc++) {
						int val = A[rr][cc]; // A에서 값 가져오기
						if (val == 0) { // val이 0이면 연산을 하지 않음
							continue;
						}
						if (chk[val] == 0) { // chk 배열을 확인 후, 처음 들어온 경우
							list.add(new Pair(val, 1)); // list에 넣어줌
							chk[val] = idx++; // 확인 배열에 list의 몇번째에 들어가있는지 표시
							
						} else { // chk 배열을 확인 후, 이미 있는 값인 경우
							list.get(chk[val] - 1).cnt++; // list에 있는 개수만 올려줌
						}
					} // end of for(column)
					
					size = size < (idx - 1) ? idx - 1 : size; // 연산으로 나온 개수를 갱신해줌
					Collections.sort(list); // 개수로 정렬
					
					//-- 임시 A 변수에 채워줌
					int temp_c = 0; 
					for (int i = 0; i < list.size(); i++) {
						A_tmp[rr][temp_c++] = list.get(i).val;
						A_tmp[rr][temp_c++] = list.get(i).cnt;
					}
				} // end of for(row)
				size_c = size * 2; // 모든 R 연산이 끝난 후, column의 길이를 갱신해줌
			} // end of if(R연산)
			
			else { // C 연산 (R 연산과 탐색 순서만 다름)
				A_tmp = new int[size_r * 2][size_c]; // C 연산이므로, 열의 개수는 같고 행은 최대 ({행}*2)칸이 필요
				for (int cc = 0; cc < size_c; cc++) {
					list = new ArrayList<>();
					int[] chk = new int[101];
					int idx = 1;
					for (int rr = 0; rr < size_r; rr++) {
						int val = A[rr][cc];
						if (val == 0) {
							continue;
						}
						if (chk[val] == 0) { 
							list.add(new Pair(val, 1));
							chk[val] = idx++;
							
						} else { 
							list.get(chk[val] - 1).cnt++;
						}
					} // end of for(row)
					
					size = size < (idx - 1) ? idx - 1 : size; 
					Collections.sort(list);
					
					int temp_r = 0;
					for (int i = 0; i < list.size(); i++) {
						A_tmp[temp_r++][cc] = list.get(i).val;
						A_tmp[temp_r++][cc] = list.get(i).cnt;
					}
				}
				size_r = size * 2;
			} // end of if(C연산)

			for (int rr = 0; rr < size_r; rr++) {
				for (int cc = 0; cc < size_c; cc++) {
					A[rr][cc] = A_tmp[rr][cc];
				}
			}
		} // end of for(one time)
		System.out.println("-1"); // 100 시간동안 답이 나오지 않은 경우
	} // end of main

	private static class Pair implements Comparable<Pair> {
		int val;
		int cnt;

		Pair(int val, int cnt) {
			this.val = val;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Pair o) {
			int tmp = this.cnt - o.cnt;
			if (tmp == 0) { // 개수가 같을 때, 값으로 정렬
				tmp = this.val - o.val;
			}
			return tmp;
		}
	} // end of Pair
} // end of class
