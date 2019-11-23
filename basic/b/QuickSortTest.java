package basic.b;

public class QuickSortTest {
	static final int SIZE = 10;
	static Pair[] array = new Pair[SIZE];

	public static void main(String[] args) {
		array[0] = new Pair(3, 4);
		array[1] = new Pair(2, 1);
		array[2] = new Pair(1, 4);
		array[3] = new Pair(1, 2);
		array[4] = new Pair(3, 2);
		array[5] = new Pair(1, 0);
		array[6] = new Pair(5, 5);
		array[7] = new Pair(7, 2);
		array[8] = new Pair(0, 3);
		array[9] = new Pair(1, 33);

		quickSort(0, SIZE - 1);

		for (int i = 0; i < SIZE; i++) {
			System.out.println(array[i]);
		}

	}

	private static void quickSort(int left, int right) {
		if (left >= right) {
			return;
		}

		int i = left - 1;
		int j = right + 1;
		int pivot_x = array[(left + right) / 2].x;
		int pivot_y = array[(left + right) / 2].y;

		while (i < j) {
			while ((array[++i].x < pivot_x) || (array[i].x == pivot_x) && (array[i].y < pivot_y)) {
			}

			while ((array[--j].x > pivot_x) || (array[j].x == pivot_x) && (array[j].y > pivot_y)) {
			}

			if (i >= j) {
				break;
			}
			swap(i, j);
		}
		quickSort(left, i - 1);
		quickSort(i + 1, right);
		return;
	}

	private static void swap(int o1, int o2) {
		Pair tmp = array[o1];
		array[o1] = array[o2];
		array[o2] = tmp;
		return;
	}

	private static class Pair {
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "[x=" + x + ", y=" + y + "]";
		}
	}
}
