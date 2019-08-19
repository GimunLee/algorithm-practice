package swea.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Info {
	int num;
	int time;

	Info(int num, int time) {
		this.num = num;
		this.time = time;
	}
}

public class Solution_1238_Contact {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= 10; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");

			int data_len = Integer.parseInt(st.nextToken()); // 데이터 길이
			int start_node = Integer.parseInt(st.nextToken()); // 시작점

			st = new StringTokenizer(br.readLine().trim(), " ");

			ArrayList<Info>[] contact = new ArrayList[101];
			Queue<Info> q = new LinkedList<Info>();
			boolean[] visited = new boolean[101];
			q.add(new Info(start_node, 0));

			for (int i = 0; i < data_len; i += 2) {
				int from = Integer.parseInt(st.nextToken()); // from
				int to = Integer.parseInt(st.nextToken()); // to
				if (contact[from] == null) {
					contact[from] = new ArrayList<Info>();
				}
				contact[from].add(new Info(to, 0));
			}

			ArrayList<Info> last_list = new ArrayList<Info>();

			int time = 1;
			while (!q.isEmpty()) {
				time++;
				int term = q.size();
				for (int i = 0; i < term; i++) {
					Info info = q.poll(); // 시작점
					int start = info.num;
					visited[start] = true;

					if (contact[start] == null) {
//						last_list.add(new Info(start, time));
						continue;
					}

					// 연결된 인원들 연락돌리기
					for (int j = 0; j < contact[start].size(); j++) {
						int linked = contact[start].get(j).num; // 연락가능 인원들

						if (!visited[linked]) {
							q.add(new Info(linked, time));
							last_list.add(new Info(linked, time));
							visited[linked] = true;
						}
					}
				}
			}

			Collections.sort(last_list, new Comparator<Info>() {
				@Override
				public int compare(Info o1, Info o2) {
					return o1.time - o2.time;
				}
			});
			
//			for (int i = 0; i < last_list.size(); i++) {
//				System.out.println(last_list.get(i).num + " " + last_list.get(i).time);
//			}
			
			int last_time = last_list.get(last_list.size()-1).time;
			int max = Integer.MIN_VALUE;
					
			for (int i = 0; i < last_list.size(); i++) {
				if(last_list.get(i).time == last_time) {
					if(max < last_list.get(i).num) {
						max = last_list.get(i).num;
					}
				}
			}
			
			sb.append('#').append(tc).append(' ').append(max).append('\n');
		} // end of for of TestCase
		System.out.println(sb.toString());

	} // end of main
} // end of class
