package programmers;

public class Solution_01 {
	public static void main(String[] args) {
		int stock = 4;
		int[] dates = { 4, 10, 15 };
		int[] supplies = { 20, 5, 10 };
		int k = 30;
		// result : 2
		System.out.println(solution(stock, dates, supplies, k));

	} // end of main

	public static int solution(int stock, int[] dates, int[] supplies, int k) {
		int answer = 0;

		int lastDate = 0; // 0일부터 시작
		here: for (int i = 0; i < dates.length; i++) { // 기준일
			int date = dates[i];
			int supplie = supplies[i];

			int sub = date - lastDate;

			if ((stock - sub) <= 0) { // 반드시 공급받아야 하는 경우
				stock = (stock - sub) + supplie;
				lastDate = date;
				answer++;
				continue;
			} else { // 공급을 안 받았을 때 앞으로 문제가 되는지 확인
				int idx = i + 1;
				while (idx < dates.length) {
					if (stock - (dates[idx] - lastDate) <= 0) {
						i = idx - 1;
						continue here;
					}
					idx++;
				}
				continue;
			}
		}

		return answer;
	}
} // end of class
