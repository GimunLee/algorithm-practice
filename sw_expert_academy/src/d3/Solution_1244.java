package d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [S/W 문제해결 응용] 2일차 - 최대 상금 / 다시 풀기
 */
public class Solution_1244 {
	static int Answer = 0;
	static char[] price;
	static int limit;

	static void func(int now_cnt) {
		if (now_cnt == limit) {
			String tmp = "";
			for (int i = 0; i < price.length; i++) {
				tmp += Character.toString(price[i]);
			}
			int int_tmp = Integer.parseInt(tmp);
			Answer = (Answer > int_tmp) ? Answer : int_tmp;
			return;
		} else {
			for (int i = 0; i < price.length; i++) {
				for (int j = i; j < price.length; j++) {
					if (price[i] == price[j]) {
						continue;
					}

					if ((price[i] - '0') <= (price[j] - '0')) {
						char tmp = price[j];
						price[j] = price[i];
						price[i] = tmp;
						func(now_cnt+1);
						tmp = price[j];
						price[j] = price[i];
						price[i] = tmp;
					}
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int C = Integer.parseInt(br.readLine().trim()); // test Caet 수

		for (int tc = 1; tc <= C; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			String price_tmp = st.nextToken(); // 상금판

			price = new char[price_tmp.length()];

			Answer = Integer.parseInt(price_tmp);

			for (int i = 0; i < price.length; i++) {
				price[i] = price_tmp.charAt(i);
			}

			limit = Integer.parseInt(st.nextToken()); // 교환 횟수

			func(0);

			System.out.print("#" + tc + " " + Answer + "\n");
		}
	}
}
