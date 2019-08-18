package d4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_3752_가능한시험점수_other {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int numberOfTestcase = Integer.parseInt(br.readLine());

		ArrayList<Integer> list = new ArrayList<Integer>();
		boolean arr[];
		for (int t = 1; t <= numberOfTestcase; t++) {
			arr = new boolean[10000];
			arr[0] = true;
			list.clear();
			list.add(0);
			int N = Integer.parseInt(br.readLine());
			int num;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				num = Integer.parseInt(st.nextToken());

				int size = list.size();
				int temp;
				for (int j = 0; j < size; j++) {
					temp = list.get(j) + num;
					if (arr[temp] == false) {
						arr[temp] = true;
						list.add(temp);
					}
				}
			}

			sb.append("#").append(t).append(" ").append(list.size()).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();

	}

}

// using hashset
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.StringTokenizer;
//
//public class Solution {
//
//	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//		StringBuilder sb = new StringBuilder();
//		int numberOfTestcase = Integer.parseInt(br.readLine());
//		HashSet<Integer> set = new HashSet<Integer>();
//		ArrayList<Integer> arr = new ArrayList<Integer>();
//		Iterator<Integer> iter;
//		for (int t = 1; t <= numberOfTestcase; t++)
//		{
//			set.clear();
//			set.add(0);
//			int N = Integer.parseInt(br.readLine());
//			int num;
//			StringTokenizer st = new StringTokenizer(br.readLine());
//			int curNum;
//			for (int i = 0; i < N; i++)
//			{
//				num = Integer.parseInt(st.nextToken());
//				iter = set.iterator();
//				arr.clear();
//				while (iter.hasNext())
//				{
//					curNum = iter.next();
//					arr.add(curNum + num);
//				}
//				arr.add(num);
//				
//				iter = arr.iterator();
//				while (iter.hasNext())
//				{
//					set.add(iter.next());
//				}
//			}
//			
//			sb.append("#").append(t).append(" ").append(set.size()).append("\n");
//		}
//		
//		bw.write(sb.toString());
//		bw.flush();
//		
//	}
//
//}
