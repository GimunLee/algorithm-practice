package SWEXPERT;

import java.util.Scanner;
 
public class Solution_3234_준환이의양팔저울_공병두 {
    static int N;
    static int[] w;
    static int cnt;
    static int[][] memo;
    static int f(int left, int right, int visit1, int visit2) {
        if(left<right) {
        	return memo[visit1][visit2]=0;
        }
        if((visit1+ visit2)==(1<<N)-1) {
            return memo[visit1][visit2] = 1;
        }
        if(memo[visit1][visit2]>0) {
        	return memo[visit1][visit2];
        }
        for(int i=0; i<N; i++) {
            if((visit1&(1<<i))==0 && (visit2&(1<<i))==0) {
                memo[visit1][visit2] += f(left+w[i], right, visit1|(1<<i), visit2);
				memo[visit1][visit2] += f(left, right+w[i], visit1, visit2|(1<<i));
            }
        }
        return memo[visit1][visit2];
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int tc = s.nextInt();
        for(int k=1; k<=tc; k++) {
            N = s.nextInt();
            w = new int[N];
            for(int i=0; i<N; i++) {
                w[i] = s.nextInt();
            }
            memo = new int[1<<10][1<<10];
            System.out.println("#" + k + " " + f(0, 0, 0, 0));
        }
    }
}