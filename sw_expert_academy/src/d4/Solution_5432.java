package d4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/** 
 * 쇠막대기 자르기
 */
public class Solution_5432 {
    static int[] stack = new int[100];
    static int top = -1;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int tc = Integer.parseInt(br.readLine());
        
        for (int p = 1; p <= tc; p++) {
            int sum = 0;
            String tmp = br.readLine();
            String[] input = tmp.split("");
            
            for (int i = 0; i < input.length; i++) {
                if(input[i].equals("(")) {
                	top++;
                }
                else if(input[i].equals(")")) {
                    top--;
                    if(top != -1 && input[i-1].equals("(")) {
                        sum += top + 1;
                    }
                    else if(top == -1){
                        if(input[i-1].equals("(")) {
                        }
                        else {
                            sum++;
                        }
                    }
                    else {
                        sum++;
                    }
                }
            }
            System.out.println("#"+p+" "+sum);
        }
    }
}


