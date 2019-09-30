package programmers.level3;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class Solution_º£½ºÆ®¾Ù¹ü {
	public static void main(String[] args) {
		String[] genres = { "classic", "pop", "classic", "classic", "pop", "hp" };
		int[] plays = { 500, 600, 150, 800, 2500, 100 };
		int[] answer = solution(genres, plays);

		for (int i = 0; i < answer.length; i++) {
			System.out.println(answer[i]);
		}

	}

	public static int[] solution(String[] genres, int[] plays) {
		HashMap<String, Integer> map = new HashMap<>();
		ArrayList<Genre> genreList = new ArrayList<>();
		ArrayList<Music>[] database = new ArrayList[100];
		int genreIndex = 0;

		for (int i = 0; i < genres.length; i++) {
			String genre = genres[i];
			int play = plays[i];
			if (!map.containsKey(genre)) {
				database[genreIndex] = new ArrayList<>();
				database[genreIndex].add(new Music(i, play));
				genreList.add(new Genre(genreIndex, play));
				map.put(genre, genreIndex++);
			} else {
				int index = map.get(genre);
				genreList.get(index).totalPlay += play;
				database[index].add(new Music(i, play));
			}
		}

		Collections.sort(genreList);

		ArrayList<Integer> answerList = new ArrayList<Integer>();
		for (int i = 0; i < genreList.size(); i++) {
			int index = genreList.get(i).index;
			Collections.sort(database[index]);
			int cnt = 0;
			for (int j = 0; j < database[index].size(); j++) {
				if (cnt == 2)
					break;
				cnt++;
				answerList.add(database[index].get(j).index);
			}
		}
		int[] answer = new int[answerList.size()];
		for (int i = 0; i < answerList.size(); i++) {
			answer[i] = answerList.get(i);
		}

		return answer;
	}

	private static class Genre implements Comparable<Genre> {
		int index, totalPlay;

		public Genre(int index, int totalPlay) {
			this.index = index;
			this.totalPlay = totalPlay;
		}

		@Override
		public int compareTo(Genre o) {
			return o.totalPlay - this.totalPlay;
		}
	} // end of Music

	private static class Music implements Comparable<Music> {
		int index, play;

		public Music(int index, int play) {
			this.index = index;
			this.play = play;
		}

		@Override
		public int compareTo(Music o) {
			int sub = o.play - this.play;
			if (sub == 0) {
				sub = this.index - o.index;
			}
			return sub;
		}
	} // end of Music
}
