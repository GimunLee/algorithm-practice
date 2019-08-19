package jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class User {
	int start;
	int end;

	User(int start, int end) {
		this.start = start;
		this.end = end;
	}
}

public class Main_2247_도서관 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim()); //
		User[] times = new User[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken()); // 시작 시간
			int end = Integer.parseInt(st.nextToken()); // 끝나는 시간
			times[i] = new User(start, end);

		}

		Arrays.sort(times, new Comparator<User>() {
			@Override
			public int compare(User a, User b) {
				return a.start <= b.start ? -1 : 1;
			}
		});

		int inTime = Integer.MIN_VALUE;
		int outTime = Integer.MIN_VALUE;

		for (int i = 0; i < times.length - 1; i++) {
			int k = 1;
			while (true) {
				if (i + k >= times.length)
					break;
				if (times[i].end >= times[i + k].start) {
					if (times[i].end <= times[i + k].end)
						times[i].end = times[i + k].end;
					else
						times[i + k].end = times[i].end;
					int v = times[i].end - times[i].start;
					k++;
					if (inTime < v)
						inTime = v;
				} else {
					int v = times[i + k].start - times[i].end;
					if (outTime < v)
						outTime = v;
					break;
				}
			}
		}

//		int use_in = times[0].start;
//		int use_out = times[0].end;

//		for (int i = 1; i < times.length; i++) {
//			User time = times[i];
//			if (use_out >= time.start && use_out <= time.end) {
//				use_out = time.end;
//			}
//			// 갱신
//			if (use_out < time.start) {
//				int tmp_in = time.start;
//				int tmp_out = time.end;
//				i++;
//				while(i < times.length && tmp_out >= times[i].start) {
//					tmp_out = times[i].end;
//					i++;
//					if(i >= times.length) {
//						break;
//					}
//				}
//				if(tmp_out - tmp_in > use_out- use_in) {
//					use_in = time.start;
//					use_out = time.end;	
//				}
//			}
//		}

		System.out.println(inTime + " " + outTime);
	}
}