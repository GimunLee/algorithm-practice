package boj.afgraph1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_2331_반복수열 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		ArrayList<String> list = new ArrayList<String>();
		list.add(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		int index = 0;
		boolean flag = true;
		while (flag) {
			int value = 0;
			for (int i = 0; i < list.get(index).length(); i++) {
				value += (int) Math.pow(list.get(index).charAt(i)-'0',N);
			}
			for (int j = 0; j < list.size(); j++) {
				if(value == Integer.parseInt(list.get(j))){
					flag = false;
					System.out.println(j);
					return;
				}
			}
			list.add(++index,(value+""));
		}
	}
}
