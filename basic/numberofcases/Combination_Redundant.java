package com.numberofcases;

import java.util.ArrayList;
import java.util.Arrays;

/** Redundant Combination의 다양한 생성법을 알아봄. (실행 속도 : ArrayList < Array) */

public class Combination_Redundant {
	static final int[] input = { 1, 2, 3 }; // 기저데이터
	static final int N = input.length; // 조합할 기저데이터의 수
	static final int R = 2; // 조합을 완성할 수

	static ArrayList<Integer> list; // ArrayList를 사용시, 조합을 저장할 변수
	static int[] array; // Array를 사용시, 조합을 저장할 변수

	public static void main(String[] args) {
		/** 중복조합 ArrayList */
		System.out.println("-- 중복조합 ArrayList -------------------");
		list = new ArrayList<>();
		nHr_list(0, 0);

		/** 중복조합 Array */
		System.out.println("-- 중복조합 Array -----------------------");
		array = new int[R];
		nHr_array(0, 0);

		/** 중복조합 Function1 */
		System.out.println("-- 중복조합 Function1 -------------------");
		array = new int[R];
		nHr_function1(0, 0);

		/** 중복조합 Function2 */
		System.out.println("-- 중복조합 Function2 -------------------");
		array = new int[R];
		nHr_function2(N, R);
	} // end of main

	/**
	 * ArrayList를 사용한 반복문+재귀 조합 함수
	 * 
	 * @param idx 내가 기저데이터에서 뽑고자하는 인덱스
	 * @param len 내가 뽑은 조합의 길이
	 */
	private static void nHr_list(int idx, int len) {
		if (len == R) { // 종료조건 : 조합이 완성됐을 경우
			System.out.println(list);
			return;
		}
		for (int i = idx; i < N; i++) {
			list.add(input[i]);
			nHr_list(i, len + 1); // Combination과 다르게 'i+1'을 하지 않는다.
			list.remove(list.size() - 1);
		}
	} // end of nHr_list()

	/**
	 * Array를 사용한 반복문+재귀 조합 함수
	 * 
	 * @param idx 내가 기저데이터에서 뽑고자하는 인덱스
	 * @param len 내가 뽑은 조합의 수, 곧 저장할 배열의 인덱스
	 */
	private static void nHr_array(int idx, int len) {
		if (len == R) { // 종료조건 : 조합이 완성됐을 경우
			System.out.println(Arrays.toString(array));
			return;
		}

		for (int i = idx; i < N; i++) {
			array[len] = input[i];
			nHr_array(i, len + 1); // Combination과 다르게 'i+1'을 하지 않는다.
		}
	} // end of nHr_array()

	/**
	 * @param idx 내가 기저데이터에서 뽑고자하는 인덱스
	 * @param len 내가 뽑은 조합의 수, 곧 저장할 배열의 인덱스
	 */
	private static void nHr_function1(int idx, int len) {
		if (len == R) { // 하나의 조합이 완성됨
			System.out.println(Arrays.toString(array));
			return;
		} else if (idx == N) {
			return;
		}

		array[len] = input[idx];
		nHr_function1(idx, len + 1);
		nHr_function1(idx + 1, len);

	} // end of nHr_function1()

	private static void nHr_function2(int n, int r) {
		if (r == 0) { // 하나의 조합이 완성됨
			System.out.println(Arrays.toString(array));
			return;
		}else if (n == 0) {
			return;
		} 
		array[r - 1] = input[n - 1];
		nHr_function2(n, r - 1); // 선택한 경우
		nHr_function2(n - 1, r); // 선택하지 않은 경우
	} // end of nHr_function2()
	
} // end of class
