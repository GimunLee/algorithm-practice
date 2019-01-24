package ab_data_structure1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/** 
 * 쇠막대기 자르기
 */
public class Main_10799 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int sum = 0; // 잘린 막대기 개수의 합
        int open = 0; // 열린 괄호 개수의 합
        
        char[] input = br.readLine().toCharArray(); // char로 저장
        
        for (int i = 0; i < input.length; i++) {
        	char tmp = input[i];
        
        	if(tmp=='(') {
        		open++;
        	}
        	// 닫힌 것이 나왔을 때,
        	else if(input[i-1] == '('){ // 1. 레이저일 경우
        		open--;
        		sum += (open);
        	}else { // 2. 레이저가 아닐 경우
        		open--;
        		sum += 1;
        	}
        }
        System.out.println(sum);
    }
}


