package swea.database;

import java.util.Scanner;

interface Field {
	public final static int NAME = 0;
	public final static int NUMBER = 1;
	public final static int BIRTHDAY = 2;
	public final static int EMAIL = 3;
	public final static int MEMO = 4;
}

class Solution {
	private final static int CMD_INIT = 0;
	private final static int CMD_ADD = 1;
	private final static int CMD_DELETE = 2;
	private final static int CMD_CHANGE = 3;
	private final static int CMD_SEARCH = 4;

	static class Result {
		public int count;
		public String str;
	}

	private static Scanner sc;
	private static UserSolution userSolution = new UserSolution();

	private static int Score;
	private static int ScoreIdx;
	private static String name, number, birthday, email, memo;

	private static String lastname[] = { "kim", "lee", "park", "choi", "jung", "kang", "cho", "oh", "jang", "lim" };
	private static int lastname_length[] = { 3, 3, 4, 4, 4, 4, 3, 2, 4, 3 };

	private static int mSeed;

	private static int mrand(int num) {
		mSeed = mSeed * 1103515245 + 37209;
		if (mSeed < 0)
			mSeed *= -1;
		return ((mSeed >> 8) % num);
	}

	private static void make_field(int seed) {
		StringBuilder sbname = new StringBuilder();
		StringBuilder sbnumber = new StringBuilder();
		StringBuilder sbbirthday = new StringBuilder();
		StringBuilder sbemail = new StringBuilder();
		StringBuilder sbmemo = new StringBuilder();

		int name_length, email_length, memo_length;
		int num;

		mSeed = seed;

		name_length = 6 + mrand(10);
		email_length = 10 + mrand(10);
		memo_length = 5 + mrand(10);

		num = mrand(10);
		sbname.append(lastname[num]);
		for (int i = 0; i < name_length - lastname_length[num]; i++)
			sbname.append((char) ('a' + mrand(26)));

		for (int i = 0; i < memo_length; i++)
			sbmemo.append((char) ('a' + mrand(26)));

		for (int i = 0; i < email_length - 6; i++)
			sbemail.append((char) ('a' + mrand(26)));
		sbemail.append("@");
		sbemail.append((char) ('a' + mrand(26)));
		sbemail.append(".com");

		sbnumber.append("010");
		for (int i = 0; i < 8; i++)
			sbnumber.append((char) ('0' + mrand(10)));

		sbbirthday.append("19");
		num = mrand(100);
		sbbirthday.append((char) ('0' + num / 10));
		sbbirthday.append((char) ('0' + num % 10));
		num = 1 + mrand(12);
		sbbirthday.append((char) ('0' + num / 10));
		sbbirthday.append((char) ('0' + num % 10));
		num = 1 + mrand(30);
		sbbirthday.append((char) ('0' + num / 10));
		sbbirthday.append((char) ('0' + num % 10));

		name = sbname.toString();
		number = sbnumber.toString();
		birthday = sbbirthday.toString();
		email = sbemail.toString();
		memo = sbmemo.toString();
	}

	private static void cmd_init() {
		ScoreIdx = Integer.parseInt(sc.next());

		userSolution.InitDB();
	}

	private static void cmd_add() {
		int seed = Integer.parseInt(sc.next());

		make_field(seed);

		userSolution.Add(name, number, birthday, email, memo);
	}

	private static int count = 0;

	private static void cmd_delete() {
		int field = Integer.parseInt(sc.next());
		String str = sc.next();
		int check = Integer.parseInt(sc.next());

		int result = userSolution.Delete(field, str);
		if (result != check) {
			Score -= ScoreIdx;
		}
	}

	private static void cmd_change() {
		int field = Integer.parseInt(sc.next());
		String str = sc.next();
		int changefield = Integer.parseInt(sc.next());
		String changestr = sc.next();
		int check = Integer.parseInt(sc.next());
		int result = userSolution.Change(field, str, changefield, changestr);
		if (result != check) {
			Score -= ScoreIdx;
		}
	}

	private static void cmd_search() {
		int field = Integer.parseInt(sc.next());
		String str = sc.next();
		int returnfield = Integer.parseInt(sc.next());
		String checkstr = sc.next();
		int check = Integer.parseInt(sc.next());

		Result result = userSolution.Search(field, str, returnfield);
		if (result.count != check || (result.count == 1 && !checkstr.equals(result.str))) {
			Score -= ScoreIdx;
		}
	}

	private static void run() {
		int N = Integer.parseInt(sc.next());
		for (int i = 0; i < N; i++) {
			int cmd = Integer.parseInt(sc.next());
			switch (cmd) {
			case CMD_INIT:
				cmd_init();
				break;
			case CMD_ADD:
				cmd_add();
				break;
			case CMD_DELETE:
				cmd_delete();
				break;
			case CMD_CHANGE:
				cmd_change();
				break;
			case CMD_SEARCH:
				cmd_search();
				break;
			default:
				break;
			}
		}
	}

	private static void init() {
		Score = 1000;
		ScoreIdx = 1;
	}

	public static void main(String[] args) throws Exception {
		String path = Solution.class.getResource("").getPath();
		StringBuilder sb = new StringBuilder();
		System.setIn(new java.io.FileInputStream(path + "eval_input.txt"));

		sc = new Scanner(System.in);

		int T = sc.nextInt();
		int TotalScore = 0;
		long start = System.currentTimeMillis();
		for (int tc = 1; tc <= T; tc++) {
			init();
			run();
			if (Score < 0)
				Score = 0;
			TotalScore += Score;
			sb.append("#").append(tc).append(" ").append(Score).append("\n");
//			System.out.println("#" + tc + " " + Score);
		}
		long end = System.currentTimeMillis();
		System.out.println(sb.toString());
		System.out.println("TotalScore = " + TotalScore);
		System.out.println("TotalTime  = " + (end - start) + "ms");
		sc.close();
	}
}
