package d1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

import javax.print.attribute.standard.OutputDeviceAssigned;

public class Solution_2063 {
	final static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		/*
		 * int N = sc.nextInt(); int[] arr = new int[N];
		 * 
		 * for (int i = 0; i < N; i++) arr[i] = sc.nextInt();
		 * 
		 * // 1. 정렬 // 2. 중간 위치의 값을 찾는다.
		 * 
		 * Arrays.sort(arr); // 오름차순 정렬 : 퀵소트 O[nlogn] -> 시간초과가 나면 다른 sort를 구현해서 써야한다.
		 * System.out.println(arr[N/2]);
		 * 
		 * // 버블 정렬 O[n^2] for(int i = N-1; i >= 0; i--) { for(int j = 0; j < i; j++) {
		 * // 두개씩 비교해서 큰 것 뒤로 if(arr[j] > arr[j+1]) { // swap(j,j+1); // 대회에서는 메소드 만들지
		 * 말고 때려 박아라. 속도 문제! 단 현업에서는 모듈화가 중요하다. int temp = arr[j]; arr[j] = arr[j+1];
		 * arr[j+1] = temp; } if(i == N/2 -1) break; // 중간까지만 처리 } }
		 * System.out.println(arr[N/2]);
		 */

//		 카운팅 정렬
		int[] arr2 = { 3, 3, 4, 1, 2, 5, 3, 2, 2, 2, 2 }; // 11개, 5번째 중간값
		// 정렬할 데이터의 숫자의 범위를 미리 알아야한다. min, max
		int[] cnt = new int[6]; // 최대값이 5이기 때문

		for (int i = 0; i < arr2.length; i++) {
			cnt[arr2[i]]++;
		}

		for (int i = 0; i < cnt.length; i++) {
			for (int j = 0; j < cnt[i]; j++) {
				System.out.print(i + " ");
			}
		}
		System.out.println();

//		 중간 위치 값을 출력
		int x = 0;
		for (int i = 0; i < cnt.length; i++) {
			x += cnt[i];
			if (x > 11 / 2) {
				System.out.println(i); // 중간값
				break;
			}
		}

//		백준 10989번, 수 정렬하기
		/*
		 * Scanner sc = new Scanner(System.in); // jdk 5에 추가됨 int num = sc.next(); //
		 * try-catch가 안에 있음. 속도가 느림
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine(); // 문자열로만 리턴됨

		System.out.println(); // 느리다
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write("Hello \n");
	}
}
