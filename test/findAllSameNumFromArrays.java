package etc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/*
문제)
오름차순으로 정렬되어 있고 크기가 같은 숫자 배열 3개를 입력받는다.
배열 세곳에서 모두 존재하는 숫자를 반환하라. 
시간복잡도는 O(N) 이하여야 하며, 해시맵을 사용해서는 안 된다.

{1, 2, 3, 4, 5}
{3, 4, 5, 6, 7}
{2, 3, 9, 10, 11}

실수한 것 :
(1) continue loop를 하더라도, 증감 연산자를 먹는다.
즉 i = 0으로 하고, continue를 하면 i++일 때, 다음에서 1로 바뀐다.

(2) cnt 늘리는 방식으로 하면, 원소가 겹칠 수 있을 때 문제가 발생함.

(3) 후위 증가가 아니라. binary search로 찾기

 */

public class findAllSameNumFromArrays {
	static int number_bound = 100_000;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder("");

		System.out.println("배열 갯수 (행) : ");
		int cnt_array = sc.nextInt();

		System.out.println("원소 갯수 (렬) : ");
		int cnt_element = sc.nextInt();

		Random r = new Random();
		int[][] input_array = new int[cnt_array][cnt_element];

		for (int a = 0; a < cnt_array; a++) {
			for (int e = 0; e < cnt_element; e++) {
				input_array[a][e] = r.nextInt(number_bound) + 1;
			}
			Arrays.sort(input_array[a]);
		}

		long start_time = System.currentTimeMillis();
		ArrayList<Integer> answer = Solution(input_array);
		long end_time = System.currentTimeMillis();

		System.out.println("수행 시간 : " + (end_time - start_time));

//      print_input_array(input_array);

		System.out.println("정답");

//      for (int a = 0; a < answer.size(); a++) {
//         sb.append(answer.get(a) + " ");
//      }
//      System.out.println(sb.toString());
	}

	static ArrayList<Integer> Solution(int[][] input_array) {
		ArrayList<Integer> ret = new ArrayList();

		// 기본 행렬 크기 변수 저장
		int array_cnt = input_array.length;
		int element_cnt = input_array[0].length;

		// 각 행렬별 시작 인덱스
		int[] search_idx = new int[array_cnt];

		// 찾는 과정
		searching: while (true) {

			// 최대 시작값 찾기
			int cur_max = Integer.MIN_VALUE;

			// 최댓값 찾기
			for (int ac = 0; ac < array_cnt; ac++) {
				if (cur_max < input_array[ac][search_idx[ac]]) {
					cur_max = input_array[ac][search_idx[ac]];
				}
			}

			loop: for (int ac = 0; ac < array_cnt; ac++) {

				// case 1: 최댓값과 같거나 (후보 등록)
				// case 2 : 커질 때 (새로운 후보)
				// 2 case 가 나올 때 까지 아래 반복문.
				while (input_array[ac][search_idx[ac]] < cur_max) {
					search_idx[ac]++;

					// 더 이상 볼 수 없을 때, 탐색 끝
					if (search_idx[ac] == element_cnt)
						break searching;
				}

				// 최댓값 갱신된 경우
				if (input_array[ac][search_idx[ac]] > cur_max) {
					cur_max = input_array[ac][search_idx[ac]];
					ac = -1;
					continue loop;
				}
			}

			// 위의 반복문을 모두 통과한 경우
			ret.add(cur_max);

			// 정답이 나왔으니, 이제 한 칸씩 늘려주기.
			for (int ac = 0; ac < array_cnt; ac++) {
				search_idx[ac]++;

				if (search_idx[ac] == element_cnt) {
					break searching;
				}
			}
		}
		return ret;
	}

	private static void print_input_array(int[][] input) {
		for (int r = 0; r < input.length; r++) {
			System.out.println(Arrays.toString(input[r]));
		}
	}
}