package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_5052_전화번호목록 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine().trim());

		for (int tc = 0; tc < TC; tc++) {
			int n = Integer.parseInt(br.readLine().trim()); // 전화번호의 수 (1 <= n <= 10000)

			flag = false;

			Node node = new Node(-1); // 루트 노드 생성
			Node temp = node; // 시작점

			for (int i = 0; i < n; i++) {
				String str = br.readLine();

				for (int idx = 0; idx < str.length(); idx++) {
					int num = str.charAt(idx) - '0';

					if (node.childNode[num] == null) {
						node.childNode[num] = new Node(num);
						node.haveChild = true;
					}
					node = node.childNode[num];
				}
				node.isFinished = true;
				node = temp;
			}
			System.out.println();
			searchNode(temp);

			if (!flag) {
				sb.append("YES").append("\n");
			} else {
				sb.append("NO").append("\n");
			}
		} // end of for(TestCase)
		System.out.println(sb.toString());

	} // end of main

	static boolean flag;

	private static void searchNode(Node node) {
		if (!node.haveChild) {
			return;
		}

		for (int i = 0; i < 10; i++) {
			if (node.childNode[i] != null) {
				if (node.isFinished) {
					flag = true;
					return;
				}
				searchNode(node.childNode[i]);
			}
		}
	}

	private static class Node {
		int num; // 해당 숫자
		boolean haveChild;
		boolean isFinished;
		Node[] childNode; // 자식 노드

		public Node(int num) {
			this.num = num;
			this.haveChild = false;
			this.isFinished = false;
			this.childNode = new Node[10];
		}
	} // end of Node
} // end of class
