import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 각 노드의 부모가 몇개인지 저장하는 배열
 * 진출차수, 진입차수 더한게 N-1이면 가능함
 */

public class Main_2462_키순서 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine().trim()); // 학생들의 수
		int C = Integer.parseInt(br.readLine().trim()); // 두 학생키 비교 횟수

		ArrayList<Integer>[] list = new ArrayList[N + 1];
		Queue<Integer> q = new LinkedList<Integer>();
		boolean flag = false;
		
		for (int i = 0; i < C; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken()); // a 학생
			int b = Integer.parseInt(st.nextToken()); // b 학생

			if (list[a] == null) {
				list[a] = new ArrayList<Integer>();
			}
			list[a].add(b);
			if(!flag) {
				q.add(a);
				flag = true;
			}
			
		}

		int[] order = new int[N + 1];

		
		boolean[] visited = new boolean[N+1];
		
		for (int i = 1; i < N+1; i++) {
			if(!visited[i]) {
				if(q.isEmpty()) {
					q.add(i);
				}
				
				while(!q.isEmpty()) {
					int p = q.poll();
					visited[p] = true;
					
					if(list[p] == null) {
						continue;
					}
					
					if(order[p] == 0) {
						order[p] = 1;
					}
					
					for (int j = 0; j < list[p].size(); j++) {
						if(order[list[p].get(j)] == 0) {
							order[list[p].get(j)] = order[p]+1;
						}else {
							if(order[list[p].get(j)] < order[p]+1) {
								order[list[p].get(j)] = order[p] + 1;
							}
						}
						q.add(list[p].get(j));
					}
				}
			}
		}
		
		System.out.println(Arrays.toString(order));
		
//		boolean[] order_chk = new boolean[N+1];
//		int ans = 0;
//		
//		for (int i = 1; i < order.length; i++) {
//			if(order[i] == 0) {
//				System.out.println("0");
//				return;
//			}
//			
//			int temp = 0;
//			if(order_chk[i]) {
//				continue;
//			}
//			
//			for (int j = i+1; j < order_chk.length; j++) {
//				if(order[j] == 0) {
//					System.out.println("0");
//					return;
//				}
//				if(order_chk[j]) {
//					continue;
//				}
//				
//				if(order[i] == order[j]) {
//					order_chk[j] = true;
//					temp++;
//				}
//			}
//			if(temp == 0) {
//				ans++;
//			}
//		}
		int ans = 0;
		for (int i = 1; i < N+1; i++) {
			int temp = 0;
			for (int j = 1; j < N+1; j++) {
				if(order[i] < order[j]) {
					temp++;
				}
				if(order[i] > order[j]) {
					temp++;
				}
			}
			if(temp == N-1) {
				System.out.println(i);
				ans++;
			}
		}
		
		System.out.println(ans);

	} // end of main()
} // end of class