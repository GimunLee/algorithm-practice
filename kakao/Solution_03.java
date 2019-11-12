package kakao;

import java.util.ArrayList;
import java.util.HashSet;

public class Solution_03 {
	
	public static void main(String[] args) {
		
	}
	static int user_cnt, banned_cnt;

	public int solution(String[] user_id, String[] banned_id) {
		user_cnt = user_id.length;
		banned_cnt = banned_id.length;
		ArrayList<ArrayList<Integer>> banned_list = getBannedList(user_id, banned_id); // 제제 리스트 가져오기
		HashSet<HashSet<Integer>> possible_set = new HashSet<HashSet<Integer>>();
		boolean[] check_arr = new boolean[user_cnt];
		makeCases(banned_list, possible_set, check_arr, 0, null);
		int answer = possible_set.size();
		return answer;
	}

	private ArrayList<ArrayList<Integer>> getBannedList(String[] user_id, String[] banned_id) {
		ArrayList<ArrayList<Integer>> banned_list = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < banned_cnt; i++) {
			ArrayList<Integer> user_list = new ArrayList<>();
			for (int j = 0; j < user_cnt; j++) {
				if (isBan(banned_id[i], user_id[j]))
					user_list.add(j);
			}
			banned_list.add(user_list);
		}
		return banned_list;
	}

	/** 경우의 수 만들어보기 */
	private void makeCases(ArrayList<ArrayList<Integer>> banned_list, HashSet<HashSet<Integer>> possible_set,
			boolean[] check_arr, int idx, HashSet<Integer> set) {

		// 탐색을 다한경우
		if (idx == banned_list.size()) {
			HashSet<Integer> tmp = (HashSet<Integer>) set.clone();
			possible_set.add(tmp);
			return;
		}

		// 빈 경우
		if (set == null) {
			set = new HashSet<Integer>();
		}

		for (Integer i : banned_list.get(idx)) {
			if (!check_arr[i]) {
				check_arr[i] = true;
				set.add(i);
				makeCases(banned_list, possible_set, check_arr, idx + 1, set);
				set.remove(i);
				check_arr[i] = false;
			}
		}
	} // end of func(makeCases)

	private boolean isBan(String banned_id, String user_id) {
		if (user_id.length() != banned_id.length()) {
			return false;
		}
		for (int i = 0; i < user_id.length(); i++) {
			if (banned_id.charAt(i) == '*') {
				continue;
			} else if (banned_id.charAt(i) != user_id.charAt(i)) {
				return false;
			}
		}
		return true;
	} // end of func(isBanned)
} // end of Solution
