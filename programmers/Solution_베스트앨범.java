package programmers;

public class Solution_베스트앨범 {
	public static void main(String[] args) {
		String[] genres = {};
		int[] plays = {};
		int[] answer = solution(genres, plays);
		for (int i = 0; i < answer.length; i++) {
			System.out.println(answer[i]);
		}
	}

	// 충돌이 안난다는 전제
	private static final int HASH_VALUE = 257;
	private static final int HASH_SIZE = 1000; // 장르
	private static final int HASH_LEN = 10000;

	private static final int[] count = new int[HASH_SIZE];
	private static final int[] maxCntInGenre = new int[HASH_SIZE];

	public static int[] solution(String[] genres, int[] plays) {
		int[] answer = {};
		
		
		
		

		return answer;
	}

	private static class Music {
		int id, playCnt;
		String genre;

		public Music(int id, int playCnt, String genre) {
			this.id = id;
			this.playCnt = playCnt;
			this.genre = genre;
		}
	}
}
