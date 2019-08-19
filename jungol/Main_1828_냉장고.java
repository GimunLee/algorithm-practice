package jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

class Cami {
	int min;
	int max;

	Cami(int min, int max) {
		this.min = min;
		this.max = max;
	}
}

public class Main_1828_냉장고 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine().trim());

		ArrayList<Cami> clist = new ArrayList<Cami>();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			clist.add(new Cami(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		Collections.sort(clist, new Comparator<Cami>() {
			@Override
			public int compare(Cami arg0, Cami arg1) {
				return arg0.min <= arg1.min ? -1 : 1;
			}
		});

		ArrayList<Cami> rlist = new ArrayList<Cami>();
		rlist.add(new Cami(clist.get(0).min, clist.get(0).max)); // 처음 냉장고 온도 범위
		int index = 0;

		for (int i = 1; i < clist.size(); i++) {
			Cami prev = rlist.get(index); // 전 온도
			Cami cur = clist.get(i); // 현 온도

			if (cur.min <= prev.max) {
				int tmp_min = cur.min;
				int tmp_max = (prev.max > cur.max) ? cur.max : prev.max;
				rlist.set(index, new Cami(tmp_min, tmp_max));
			} else {
				index++;
				rlist.add(new Cami(cur.min, cur.max));
			}
		}
		System.out.println(rlist.size());

	} // end of main
} // end of class
