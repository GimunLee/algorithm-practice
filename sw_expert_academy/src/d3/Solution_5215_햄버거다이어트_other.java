package d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Solution_5215 {
    static int n,l; 			// n : 재료의 수  // l : 제한 칼로리 
    static int[] score, kalory; // score : 맛 점수 // kalory : 칼로리
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine()); // Test Case 수
        
        for(int i=1;i<=tc;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            n = Integer.parseInt(st.nextToken()); // 재료의 수
            l = Integer.parseInt(st.nextToken()); // 제한 칼로리
            score = new int[n]; // 맛 점수
            kalory = new int[n]; // 칼로리
            
            for(int j=0; j<n; j++) {
                st = new StringTokenizer(br.readLine());
                score[j] = Integer.parseInt(st.nextToken());
                kalory[j] = Integer.parseInt(st.nextToken());
            }
            
            maxScore = 0; 
            check = new boolean[n]; // 사용 재료 배열
             
            dfs(0,0,0); // index, 점수, 칼로리
            System.out.println("#" + i + " " + maxScore);
        }
    }
     
    static int maxScore;
    static boolean[] check;
    private static void dfs(int idx, int s, int k) {
        if(k > l) return; // 재료의 총 칼로리 > 한 칼로리
        if(idx==n) { // 재료의 수가 되면
            maxScore = Math.max(maxScore, s); // maxScore와 s 중 높은 점수 return
            return;
        }
        
        dfs(idx+1,s+score[idx],k+kalory[idx]);
        dfs(idx+1,s,k);
    }
}
