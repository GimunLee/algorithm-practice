package swea.database;

import java.util.Arrays;

import swea.database.Solution.Result;

public class UserSolution {
	public static final int HASH_VALUE = 257;
	public static final int HASH_SIZE = 2000;
	public static final int HASH_LEN = 400;
	public static final int DB_SIZE = 50000; // 데이터 크기
	public static final int FIELD_SIZE = 5;

	public static int[][] count = new int[FIELD_SIZE][HASH_SIZE]; // 충돌나는 것 처리

	public static String[][] database = new String[DB_SIZE][FIELD_SIZE]; // 데이터베이스
	public static int databaseCount = 0;

	public static int[][][] hashTable = new int[FIELD_SIZE][HASH_SIZE][HASH_LEN]; // Hash Table 생성
	public static boolean[] isDeletedByDatabase = new boolean[DB_SIZE];

//	public static void main(String[] args) {
//		Add("A", "1111", "1101", "a.com", "aaaa");
//		Add("B", "2222", "2101", "b.com", "bbbb");
//		Add("C", "3333", "3101", "c.com", "cccc");
//		Add("D", "4444", "4101", "d.com", "dddd");
//		Add("D", "4444", "3101", "d.com", "dddd");
//		printDataBase();
//		Result result = Search(2, "4101", 1); // 2 나와야함
//		System.out.println("4101 Search : " + result.str + ", " + result.count);
//		System.out.println(Change(2, "4101", 3, "d.com"));
//		printDataBase();
//		result = Search(3, "d.com", 1); // 2 나와야함
//		System.out.println("d.com Search : " + result.str + ", " + result.count);
//		printDataBase();
//		result = Search(3, "d.com", 1); // 2 나와야함
//		System.out.println("d.com Search : " + result.str + ", " + result.count);
//		printDataBase();
//	}

	static void InitDB() {
		count = new int[FIELD_SIZE][HASH_SIZE];
		databaseCount = 0;
	}

	static void Add(String name, String number, String birthday, String email, String memo) {
		int[] keyArray = { getKey(name), getKey(number), getKey(birthday), getKey(email), getKey(memo) };
		// 데이터베이스에서 삭제된 곳이 있는지 확인한다.
		for (int fieldIndex = 0; fieldIndex < keyArray.length; fieldIndex++) {
			int key = keyArray[fieldIndex]; // 각 key를 꺼낸다.
			hashTable[fieldIndex][key][count[fieldIndex][key]++] = databaseCount;
		}
		database[databaseCount][0] = name;
		database[databaseCount][1] = number;
		database[databaseCount][2] = birthday;
		database[databaseCount][3] = email;
		database[databaseCount][4] = memo;
		isDeletedByDatabase[databaseCount] = false;
		databaseCount++;
		return;
	} // end of func(Add)

	static int Delete(int field, String str) {
		int key = getKey(str); // 키 가져오기
		int keyCrashCount = count[field][key];
		if (keyCrashCount == 0) { // 데이터가 없는 경우
			return 0;
		}
		int count = 0;
		for (int i = 0; i < keyCrashCount; i++) {
			int databaseIndex = hashTable[field][key][i];
			if (databaseIndex == -1) {
				continue;
			}
			String str1 = database[databaseIndex][field];
			if (str1 == null || isDeletedByDatabase[databaseIndex]) { // 없는 경우
				continue;
			}
			if (isEquals(str1, str)) { // 찾은 경우
				isDeletedByDatabase[databaseIndex] = true; // 데이터베이스에서 삭제
				count++;
			}
		}
		return count;
	} // end of func(Delete)

	static int Change(int field, String str, int changefield, String changestr) {
		int key = getKey(str); // 키 가져오기
		int keyCrashCount = count[field][key];
		if (keyCrashCount == 0) { // 데이터가 없는 경우
			return 0;
		}
		int ccount = 0;
		for (int i = 0; i < keyCrashCount; i++) {
			int databaseIndex = hashTable[field][key][i];
			if (databaseIndex == -1) {
				continue;
			}
			String str1 = database[databaseIndex][field];
			if (str1 == null || isDeletedByDatabase[databaseIndex]) { // 없는 경우
				continue;
			}
			if (isEquals(str1, str)) { // 바꿔야하는 데이터베이스를 찾은 경우
				int key2 = getKey(database[databaseIndex][changefield]);
				int count2 = count[changefield][key2];
				// 원래 해쉬테이블로 가야함
				for (int k = 0; k < count2; k++) {
					if (hashTable[changefield][key2][k] == databaseIndex) {
						hashTable[changefield][key2][k] = -1;
						break;
					}
				}
				int newKey = getKey(changestr);
				database[databaseIndex][changefield] = changestr;
				hashTable[changefield][newKey][count[changefield][newKey]++] = databaseIndex;
				ccount++;
			}
		}
		return ccount;
	} // end of func(Change)

	static Result Search(int field, String str, int returnfield) {
		Result result = new Result();
		result.count = 0;
		result.str = "";

		int key = getKey(str); // 키 가져오기
		int keyCrashCount = count[field][key];
		if (keyCrashCount == 0) { // 데이터가 없는 경우
			return result;
		}

		int count = 0;
		for (int i = 0; i < keyCrashCount; i++) {
			int databaseIndex = hashTable[field][key][i];
			if (databaseIndex == -1) {
				continue;
			}
			String str1 = database[databaseIndex][field];
			if (str1 == null || isDeletedByDatabase[databaseIndex]) { // 없는 경우
				continue;
			}
			if (isEquals(str1, str)) { // 찾은 경우
				result.str = database[databaseIndex][returnfield];
				count++;
			}
		}
		result.count = count;
		if (count == 0 || count >= 2) {
			result.str = "";
		}
		return result;
	} // end of func(Search)

	static void printDataBase() {
		System.out.println("-----------------------------");
		for (int i = 0; i < databaseCount; i++) {
			if (isDeletedByDatabase[i]) {
				continue;
			}
			System.out.println(Arrays.toString(database[i]));
		}
	}

	static boolean isEquals(String str1, String str2) {
		if (str1.length() != str2.length()) { // 길이가 다르면 같을 수가 없다.
			return false;
		}
		for (int i = 0; i < str1.length(); i++) {
			if (str1.charAt(i) != str2.charAt(i)) {
				return false;
			}
		}
		return true;
	} // end of func(isEquals)

	/** Hash Function */
	static int getKey(String raw) {
		int key = 0;
		for (int i = 0; i < raw.length(); i++) {
			key = key * HASH_VALUE + raw.charAt(i);
		}
		if (key < 0) {
			key = -key;
		}
		return key % HASH_SIZE;
	} // end of func(getKey)
}
