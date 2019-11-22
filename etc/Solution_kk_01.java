package etc;

import java.util.Stack;

public class Solution_kk_01 {
	private int height, width; // 높이, 너비

	public int solution(int[][] board, int[] moves) {
		height = board.length; // 높이
		width = board[0].length; // 너비
		int[] each_top_index = getTopIndex(board); // map의 최상단 인형 높이 저장
		int answer = getAnswer(board, moves, each_top_index); // 인형뽑기 시작
		return answer;
	} // end of func(solution)

	private int[] getTopIndex(int[][] board) {
		int[] ret = new int[height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (board[y][x] != 0) {
					ret[x] = y; // 최상단 인형 높이 갱신
					break;
				}
			}
		}
		return ret;
	} // end of func(getTopIndex)

	private int getAnswer(int[][] board, int[] moves, int[] each_top_index) {
		Stack<Integer> basket = new Stack<Integer>();
		int ret = 0;
		for (int i = 0; i < moves.length; i++) {
			int idx = moves[i] - 1;
			if (each_top_index[idx] == height) {
				continue;
			}
			int pick = board[each_top_index[idx]][idx];
			each_top_index[idx]++;
			if (basket.isEmpty() || basket.peek() != pick) { // 바구니가 비어있거나, 인형이 같지 않다면
				basket.add(pick); // 바구니에 넣기
			} else {
				basket.pop(); // 바구니에서 폭파
				ret += 2;
			}
		}
		return ret;
	} // end of func(getAnswer)
} // end of Solution