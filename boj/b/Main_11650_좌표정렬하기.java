package boj.b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11650_좌표정렬하기 {
	private static int listSize = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim()); // 1 <= N <= 100,000

		ListNode head = null;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			head = ListNode.appendListNode(head, new Pair(x, y));
			listSize++;
		}

		ListNode node = head;
		do {
			if (head == null) {
				break;
			}
			System.out.print(node.data + " ");
			node = node.next;
		} while (node != head);

	} // end of main

	private static class ListNode {
		Pair data;
		ListNode prev;
		ListNode next;

		public ListNode(Pair data) {
			this.data = data;
			prev = this;
			next = this;
		}

		public static ListNode appendListNode(ListNode head, Pair data) {
			ListNode node = new ListNode(data);
			if (head == null) { // List의 첫 노드일 때,
				head = node;
			} else {
				ListNode last = head.prev; // 마지막 노드가 head의 전을 가르키도록, 어쩌피 head의 prev가 가장 마지막 노드이므로
				last.next = node; // 그 마지막 노드의 next에 지금 들어온 노드를 저장
				head.prev = node; // 다시 마지막 노드를 현재 들어온 노드로 교체함
				node.prev = last; // 현재 노드의 앞에는 이 노드가 들어오기전 가장 마지막 노드를 가르키도록
				node.next = head; // 현재 노드의 다음에는 head를 가르키도록 함
			}
			return head;
		} // end of func(appendListNode)

		public static ListNode removeList(ListNode head, ListNode node) {
			if (head == head.next) { // 리스트에 노드가 하나밖에 없을 때
				return null;
			} else { // 여러개가 있을 때,
				ListNode prevNode = node.prev; // 삭제하고자하는 노드의 앞을 저장
				ListNode nextNode = node.next; // 삭제하고자 하는 노드의 다음을 저장
				prevNode.next = nextNode;
				nextNode.prev = prevNode;
				return (head == node) ? nextNode : head; // 삭제한 것이 가장 첫번째 노드라면
			}
		} // end of func(ListNode)
	} // end of ListNode

	private static class Pair {
		int x;
		int y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "(" + x + ", " + y + ")";
		}
	} // end of Pair
} // end of class
