package swea.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * [S/W 문제해결 응용] 2일차 - 최대 상금
 */
public class Solution_1244_최대상금_other {
	static int maxPrice = 0;
	static int limit;
	static int[][] check = new int[1000000][11];
	static int[] price;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int C = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= C; tc++) {
			maxPrice = 0;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			String tmp_price = st.nextToken();
			price = new int[tmp_price.length()];
			for (int i = 0; i < tmp_price.length(); i++) {
				price[i] = tmp_price.charAt(i) - '0';
			}
			limit = Integer.parseInt(st.nextToken());
			findMax(0);
			System.out.println("#" + tc + " " + maxPrice);
		}
	}

	static void findMax(int n) {
		int calc = calcPrice();

		if (n == limit) {
			maxPrice = Math.max(maxPrice, calc);
			return;
		}

		if (check[calc][n] == 1)
			return;

		check[calc][n] = 1;

		for (int i = 0; i < price.length; i++) {
			for (int j = i + 1; j < price.length; j++) {
				int temp = price[i];
				price[i] = price[j];
				price[j] = temp;

				findMax(n + 1);

				temp = price[i];
				price[i] = price[j];
				price[j] = temp;
			}
		}
	}

	static int calcPrice() {
		int total = 0;
		for (int i = 0; i < price.length; i++) {
			total *= 10;
			total += price[i];
		}
		return total;
	}
}