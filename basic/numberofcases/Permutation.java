package com.numberofcases;

import java.util.ArrayList;
import java.util.Arrays;

/** Permutation의 다양한 생성법을 알아봄. (실행 속도 : ArrayList < Array) */

public class Permutation {
	static final int[] input = { 1, 2, 3, 4 }; // 기저데이터
	static final int N = input.length; // 순열할 기저데이터의 수
	static final int R = 3; // 순열을 완성할 수

	static ArrayList<Integer> list; // ArrayList를 사용시, 순열을 저장할 변수
	static int[] array; // Array를 사용시, 조합을 저장할 변수

	static boolean[] visited; // 순열은 순서가 중요하기 때문에 필요한 변수
	public static void main(String[] args) {

		/** 순열 ArrayList */
		System.out.println("-- 순열 ArrayList -------------------");
		list = new ArrayList<>();
		visited = new boolean[R];
		nPr_list(0,0);

		/** 순열 Array */
		System.out.println("-- 순열 Array -----------------------");
		array = new int[R];
		nPr_array(0, 0);

		/** 순열 Function1 */
		System.out.println("-- 순열 Function1 -------------------");
		array = new int[R];
		nPr_function1(0, 0);

		/** 순열 Function2 */
		System.out.println("-- 순열 Function2 -------------------");
		array = new int[R];
		nPr_function2(4, 3);
		
	} // end of main

	/**
	 * ArrayList를 사용한 반복문+재귀 조합 함수
	 * 
	 * @param idx 내가 기저데이터에서 뽑고자하는 인덱스
	 * @param len 내가 뽑은 조합의 길이
	 */
	private static void nPr_list(int idx, int len) {
		if (len == R) { // 종료조건 : 조합이 완성됐을 경우
			System.out.println(list);
			return;
		}
		for (int i = idx; i < N; i++) {
			list.add(input[i]);
			nPr_list(i + 1, len + 1);
			list.remove(list.size() - 1);
		}
	} // end of nPr_list()
	
	/**
	 * Array를 사용한 반복문+재귀 조합 함수
	 * 
	 * @param idx 내가 기저데이터에서 뽑고자하는 인덱스
	 * @param len 내가 뽑은 조합의 수, 곧 저장할 배열의 인덱스
	 */
	private static void nPr_array(int idx, int len) {
		if (len == R) { // 종료조건 : 조합이 완성됐을 경우
			System.out.println(Arrays.toString(array));
			return;
		}

		for (int i = idx; i < N; i++) {
			array[len] = input[i];
			nPr_array(i + 1, len + 1);
		}
	} // end of nPr_array()

	/**
	 * @param idx 내가 기저데이터에서 뽑고자하는 인덱스
	 * @param len 내가 뽑은 조합의 수, 곧 저장할 배열의 인덱스
	 */
	private static void nPr_function1(int idx, int len) {
		if (len == R) {
			System.out.println(Arrays.toString(array));
			return;
		} else if (idx == N) {
			return;
		}

		array[len] = input[idx];
		nPr_function1(idx + 1, len + 1);
		nPr_function1(idx + 1, len);

	} // end of nPr_function1()

	private static void nPr_function2(int n, int r) {
		if (n < r) {
			return;
		} else if (r == 0) { // 하나의 조합이 완성됨
			System.out.println(Arrays.toString(array));
		} else {
			array[r - 1] = input[n - 1];
			nPr_function2(n - 1, r - 1); // 선택한 경우
			nPr_function2(n - 1, r); // 선택하지 않은 경우
		}
	} // end of nPr_function2()
} // end of class