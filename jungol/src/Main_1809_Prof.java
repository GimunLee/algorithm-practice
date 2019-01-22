
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 정올 탑 1809
 * 다른 아이디어로 풀기
 */
public class Main_1809_Prof {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder(); // 최종 출력할 문자열
		
		int[] arr = new int[N+1]; // 0번은 안씀
		int[] stack = new int[N]; // 스택, 탑의 index를 저장
		int top = -1;
		
//		String[] str = br.readLine().split(" "); // StringTokenizer로 쪼개는 것이 더 좋다.
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		for (int i = 1; i <= N; i++) { // 입력값 받기
			arr[i] = Integer.parseInt(st.nextToken());
			
			// 스택에 나보다 작은 높이의 탑이 있으면 pop 꺼내 버리기
			while(top != -1 && arr[stack[top]] < arr[i]) {
				top--; // index만 내림 (삭제 효과)
			}
			
			if(top == -1) { // 스택이 비어있으면
				sb.append("0 ");
			}else {
				sb.append(stack[top] + " ");
			}
			stack[++top] = i;// 스택에 내 탑을 쌓기
		}
		System.out.println(sb);
	} // end of main
} // end of class


// 2333ms
//public static void main(String[] args) {
//	Scanner sc = new Scanner(System.in);
//	int N = sc.nextInt();
//	
//	int[] arr = new int[N+1]; // 0번은 안씀
//	int[] stack = new int[N]; // 스택, 탑의 index를 저장
//	int top = -1;
//	
//	for (int i = 0; i <= N; i++) { // 입력값 받기
//		arr[i] = sc.nextInt();
//		
//		// 스택에 나보다 작은 높이의 탑이 있으면 pop 꺼내 버리기
//		while(top != -1 && arr[stack[top]] < arr[i]) {
//			top--; // index만 내림 (삭제 효과)
//		}
//		
//		if(top == -1) { // 스택이 비어있으면
//			System.out.print("0 ");
//		}else {
//			System.out.print(stack[top] + " ");
//		}
//		stack[++top] = i;// 스택에 내 탑을 쌓기
//	}
//	System.out.println();
//	
//} // end of main
