package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NY_Solution_7966_보석상홍준 {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim()); // 보석의 종류

			Jewelry[] jewelryArray = new Jewelry[N];

			int[] price = new int[N + 1]; // 보석의 가격
			int[] beauty = new int[N + 1]; // 보석의 미적 가치
			double[] beautyOfOnePrice = new double[N + 1];

			StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
			StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");

			int beautySum = 0;

			for (int i = 1; i <= N; i++) {
				price[i] = Integer.parseInt(st1.nextToken());
				beauty[i] = Integer.parseInt(st2.nextToken());
				beautySum += beauty[i];
				// 가치/원 : 가격 대비 가치 (가성비 확인 필요)
				beautyOfOnePrice[i] = beauty[i] / (double) price[i];
				jewelryArray[i - 1] = new Jewelry(i, price[i], beauty[i], beautyOfOnePrice[i]);
			} // end of for(보석정보받기)

			int X = Integer.parseInt(br.readLine().trim()); // 필요한 미적가치
			int K = Integer.parseInt(br.readLine().trim()); // 가지고 있는 보석의 수

			boolean[] isGotten = new boolean[K + 1];
			if (K != 0) {
				StringTokenizer st3 = new StringTokenizer(br.readLine(), " ");
				for (int i = 1; i <= K; i++) {
					int idx = Integer.parseInt(st3.nextToken());
					isGotten[idx] = true;
				}
			}
			// -- end of input

			if (beautySum < X) { // 불가능한 경우
				sb.append("#").append(tc).append(" ").append("-1").append("\n");
				continue;
			}

			Arrays.sort(jewelryArray); // 가성비 높은 것 | 가격 낮은 것

			for (int i = 0; i < jewelryArray.length; i++) {
				System.out.println(jewelryArray[i].toString());
			}
			System.out.println();
			
			
			

			sb.append("#").append(tc).append(" ").append("답").append("\n");
		} // end of for(TestCase)
		System.out.println(sb.toString());
	} // end of main

	private static class Jewelry implements Comparable<Jewelry> {
		int idx; // 보석 번호
		int price; // 보석 가격
		int beauty; // 보석 미적 가치
		double beautyOfOnePrice; // 보석 가성비 (가치/가격)

		public Jewelry(int idx, int price, int beauty, double beautyOfOnePrice) {
			this.idx = idx;
			this.price = price;
			this.beauty = beauty;
			this.beautyOfOnePrice = beautyOfOnePrice;
		}

		@Override
		public String toString() {
			return "Jewelry [idx=" + idx + ", price=" + price + ", beauty=" + beauty + ", beautyOfOnePrice="
					+ beautyOfOnePrice + "]";
		}

		// 가성비가 같다면 가격이 낮은 순으로 정렬
		@Override
		public int compareTo(Jewelry o) {
			double tmp = o.beautyOfOnePrice - this.beautyOfOnePrice;
			if (tmp == 0.0) {
				int tmp_price = this.price - o.price;
				return tmp_price;
			} else if (tmp > 0) {
				return 1;
			} else {
				return -1;
			}
		}
	} // end of Jewelry
} // end of class
