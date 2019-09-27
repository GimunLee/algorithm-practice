package programmers;

import java.util.Arrays;

public class Solution_전화번호부목록 {
	public static void main(String[] args) {
		String[] phone_book = { "23434", "23" };
		System.out.println(solution(phone_book));
	}

	private static final int HASH_VALUE = 257;
	private static final int HASH_SIZE = 10000;
	private static final int HASH_LEN = 400;

	private static String[][] hash_table = new String[HASH_SIZE][HASH_LEN];
	private static int[] count;

	static String[] sortPhoneBook;

	public static boolean solution(String[] phone_book) {
		count = new int[HASH_SIZE];

		sortPhoneBook = new String[phone_book.length];
		for (int i = 0; i < phone_book.length; i++) {
			sortPhoneBook[i] = phone_book[i];
		}
		quickSort(0, sortPhoneBook.length - 1);

		int firstKey = getKey(sortPhoneBook[0]);
		hash_table[firstKey][count[firstKey]++] = sortPhoneBook[0];

		for (int i = 1; i < sortPhoneBook.length; i++) {
			String str = "";
			int key = 0;
			for (int j = 0; j < sortPhoneBook[i].length(); j++) {
				str += sortPhoneBook[i].charAt(j);
				key = getKey(str);
				for (int k = 0; k < count[key]; k++) {
					String str2 = hash_table[key][k];
					if (isEquals(str, str2)) {
						return false;
					}
				}
			}
			hash_table[key][count[key]++] = str;
		}
		return true;
	}

	private static boolean isEquals(String str1, String str2) {
		if (str1.length() != str2.length()) {
			return false;
		}
		for (int i = 0; i < str1.length(); i++) {
			if (str1.charAt(i) != str2.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	private static void quickSort(int first, int last) {
		if (first >= last) {
			return;
		}
		int pivot = sortPhoneBook[(first + last) / 2].length();
		int i = first - 1;
		int j = last + 1;
		while (true) {
			while (sortPhoneBook[++i].length() < pivot) {
			}
			while (sortPhoneBook[--j].length() > pivot) {
			}
			if (i >= j) {
				break;
			}
			String tmp = sortPhoneBook[i];
			sortPhoneBook[i] = sortPhoneBook[j];
			sortPhoneBook[j] = tmp;
		}
		quickSort(first, i - 1);
		quickSort(j + 1, last);
		return;
	}

	public static int getKey(String str) {
		int key = 0;
		for (int i = 0; i < str.length(); i++) {
			key = key * HASH_VALUE + str.charAt(i);
		}
		if (key < 0) {
			key = -key;
		}
		return key % HASH_SIZE;
	}
}
