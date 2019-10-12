package test;

import java.util.*;

public class Solution_01 {
	public static void main(String[] args) {

		int[] answer= solution(1_000_000);
		for (int i = 0; i < answer.length; i++) {
			System.out.println(answer[i]);
		}
	}

	public static int[] solution(int N) {
		int[] answer = new int[2];
		int k = 0;
		int max_mul = Integer.MIN_VALUE;

		for (int i = 2; i < 10; i++) {
			int tmp_n = N;
			int mul = 1;
			while (tmp_n != 0) {
				int mod = (tmp_n % i);
				if (mod != 0) {
					mul *= mod;
				}
				tmp_n /= i;
			}
			
			if(max_mul < mul) {
				max_mul = mul;
				k = i;
			}else if(max_mul == mul){
				k = i;
			}
		}
		answer[0] = k;
		answer[1] = max_mul;

		return answer;

	}
}
