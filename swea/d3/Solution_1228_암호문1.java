package swea.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * [S/W 문제해결 기본] 8일차 - 암호문1
 */
public class Solution_1228_암호문1 {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 1; i <= 10; i++) {
			LinkedList<Integer> ll = new LinkedList<Integer>();
			int N = Integer.parseInt(br.readLine().trim()); // 원본 암호문의 개수
			StringTokenizer st = new StringTokenizer(br.readLine(), " "); // 원본 암호문
			for (int j = 0; j < N; j++) {
				ll.add(Integer.parseInt(st.nextToken())); // 원본 암호문 추가
//				System.out.print(ll.get(j) + " ");
			}

			int cmd_n = Integer.parseInt(br.readLine().trim()); // 명령어의 개수
			st = new StringTokenizer(br.readLine(), " "); // 명령어

			for (int j = 0; j < cmd_n; j++) {
//				System.out.println(ll.toString());
				String cmd = st.nextToken(); // 명령어
				int position = Integer.parseInt(st.nextToken()); // 추가되는 위치
				int num_n = Integer.parseInt(st.nextToken()); // 추가되는 명령어의 개수
				for (int k = 0; k < num_n; k++) {
					int encode = Integer.parseInt(st.nextToken());
					ll.add(position + k, encode);
				}
			}
			System.out.print("#" + i);
			for (int j = 0; j < 10; j++) {
				System.out.print(" " + ll.get(j));
			}
			System.out.println();
		}
	}
}
