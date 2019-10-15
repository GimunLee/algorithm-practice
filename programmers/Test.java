package programmers;

import java.util.*;

public class Test {
	public static void main(String[] args) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Comparator.reverseOrder());
		
		pq.add(4);
		pq.add(2);
		pq.add(1);
		pq.add(3);
		System.out.println(pq);
	}
}
