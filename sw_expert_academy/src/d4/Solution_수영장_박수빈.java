package d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_수영장_박수빈 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			int[] price = new int[4];//1일, 1달, 3달, 1년 요금
			for(int i = 0; i < 4; i++)
				price[i] = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine(), " ");
			int[] plan = new int[13];//이용 계획
			for(int i = 1; i < 13; i++)
				plan[i] = Integer.parseInt(st.nextToken());
			
			int[] min = new int[13];//각 달까지의 최소 비용
			for(int i = 1; i < 13; i++) {
				if(i >= 3)//3월 이상부터는 3달요금까지 고려한다
					min[i] = Math.min(min[i - 1] + Math.min(price[0] * plan[i], price[1]), min[i - 3] + price[2]);
				else//1월과 2월은 1일, 1달 요금만 고려한다
					min[i] = min[i - 1] + Math.min(price[0] * plan[i], price[1]);
			}
			//마지막에 1년 요금과 비교하여 최솟값을 출력
			System.out.println("#" + tc + " " + Math.min(min[12], price[3]));
		}
	}

}