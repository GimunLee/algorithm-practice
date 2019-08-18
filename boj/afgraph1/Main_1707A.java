package af_graph1;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main_1707A {
	static int K, V, E;
	static int[] group;
	static ArrayList<ArrayList<Integer>> connect;
	
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		K = sc.nextInt();
		
		for (int t = 1; t <= K; t++) {
			V = sc.nextInt(); E = sc.nextInt();
			
			group = new int[V + 1];
			connect = new ArrayList<ArrayList<Integer>>();
			
			for (int v = 0; v <= V; v++) {
				connect.add(new ArrayList<Integer>());
			}
			
			// 대입하기
			int start = 0, end = 0;
			for (int e = 1; e <= E; e++) {
				start = sc.nextInt();
				end = sc.nextInt();
				connect.get(start).add(end);
				connect.get(end).add(start);
			}
			
			Boolean flag = true;
			for (int v = 1; v <= V; v++) {
				if (group[v] == 0) {
					group[v] = 1;
					if(!Search(v, 1)) { // start (그룸 1)에서 시작
						System.out.println("NO");
						flag = false;
						break;
					}
				}
			}
			if (flag) {
				System.out.println("YES");
			}
		}
	}
	public static Boolean Search(int a, int g) {
		ListIterator<Integer> iter = connect.get(a).listIterator();
		
		boolean flag = true;
		
		while(iter.hasNext()) {
			int nxt = iter.next();
			if (group[nxt] == 0) { // 만약에 방문한 적이 없다면, 
				int nxtg = ((g == 1) ? 2  : 1);
				group[nxt] = nxtg;
				if (!Search(nxt, nxtg)) {
					flag = false;
					break;
				}
			}
			else if (group[nxt] == g) { // 그룹이 같다면
				return false;
			}
		}
		return flag;
	}
}