package com.numberofcases;

import java.util.ArrayList;
import java.util.Arrays;

/** Combination의 다양한 생성법을 알아봄. (실행 속도 : ArrayList < Array) */

public class Combination {
	static final int[] input = { 1, 2, 3, 4 }; // 기저데이터
	static final int N = input.length; // 조합할 기저데이터
	static final int R = 3; // 조합을 완성할 수

	static ArrayList<Integer> list; // ArrayList를 사용시, 조합을 저장할 변수
	static int[] array; // Array를 사용시, 조합을 저장할 변수

	public static void main(String[] args) {

		/** 조합 ArrayList */
		System.out.println("-- 조합 ArrayList -------------------");
		list = new ArrayList<>();
		nCr_list(0,0);

		/** 조합 Array */
		System.out.println("-- 조합 Array -----------------------");
		array = new int[R];
		nCr_array(0, 0);

		/** 조합 Function1 */
		System.out.println("-- 조합 Function1 -------------------");
		array = new int[R];
		nCr_function1(0, 0);

		/** 조합 Function2 */
		System.out.println("-- 조합 Function2 -------------------");
		array = new int[R];
		nCr_function2(4, 3);
		
	} // end of main

	/**
	 * ArrayList를 사용한 반복문+재귀 조합 함수
	 * 
	 * @param idx 내가 기저데이터에서 뽑고자하는 인덱스
	 * @param len 내가 뽑은 조합의 길이
	 */
	private static void nCr_list(int idx, int len) {
		if (len == R) { // 종료조건 : 조합이 완성됐을 경우
			System.out.println(list);
			return;
		}
		for (int i = idx; i < N; i++) {
			list.add(input[i]);
			nCr_list(i + 1, len + 1);
			list.remove(list.size() - 1);
		}
	} // end of nCr_list()
	
	/**
	 * Array를 사용한 반복문+재귀 조합 함수
	 * 
	 * @param idx 내가 기저데이터에서 뽑고자하는 인덱스
	 * @param len 내가 뽑은 조합의 수, 곧 저장할 배열의 인덱스
	 */
	private static void nCr_array(int idx, int len) {
		if (len == R) { // 종료조건 : 조합이 완성됐을 경우
			System.out.println(Arrays.toString(array));
			return;
		}

		for (int i = idx; i < N; i++) {
			array[len] = input[i];
			nCr_array(i + 1, len + 1);
		}
	} // end of nCr_array()

	/**
	 * @param idx 내가 기저데이터에서 뽑고자하는 인덱스
	 * @param len 내가 뽑은 조합의 수, 곧 저장할 배열의 인덱스
	 */
	private static void nCr_function1(int idx, int len) {
		if (len == R) {
			System.out.println(Arrays.toString(array));
			return;
		} else if (idx == N) {
			return;
		}

		array[len] = input[idx];
		nCr_function1(idx + 1, len + 1);
		nCr_function1(idx + 1, len);

	} // end of nCr_function1()

	private static void nCr_function2(int n, int r) {
		if (n < r) {
			return;
		} else if (r == 0) { // 하나의 조합이 완성됨
			System.out.println(Arrays.toString(array));
		} else {
			array[r - 1] = input[n - 1];
			nCr_function2(n - 1, r - 1); // 선택한 경우
			nCr_function2(n - 1, r); // 선택하지 않은 경우
		}
	} // end of nCr_function2()
} // end of class
