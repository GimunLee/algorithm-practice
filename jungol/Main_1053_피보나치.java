import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1053_피보나치 {
	static long mod = 10000;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			int N = Integer.parseInt(br.readLine().trim()); // Test Case 수
			if (N == -1) {
				return;
			}

			long temp = fibonacci(N) % mod;
			String ans = temp + "";
			System.out.println(temp);
		}
	}

	public static matrix multi(matrix A, matrix B) {
		matrix result = new matrix();
		result.data[0][0] = (A.data[0][0] * B.data[0][0] + A.data[0][1] * B.data[1][0]) % mod;
		result.data[0][1] = (A.data[0][0] * B.data[1][0] + A.data[0][1] * B.data[1][1]) % mod;
		result.data[1][0] = (A.data[1][0] * B.data[0][0] + A.data[1][1] * B.data[1][0]) % mod;
		result.data[1][1] = (A.data[1][0] * B.data[1][0] + A.data[1][1] * B.data[1][1]) % mod;

		return result;
	}

	public static matrix pow(matrix A, int n) {
		if (n > 1) {
			A = pow(A, n / 2);
			A = multi(A, A);

			if (n % 2 == 1) {
				matrix B = new matrix();
				A = multi(A, B);
			}
		}
		return A;
	}

	public static long fibonacci(int n) {
		if (n == 0)
			return 0;
		matrix A = new matrix();
		A = pow(A, n);

		return A.data[0][1];
	}
}

class matrix {
	long[][] data = new long[2][2];

	matrix() {
		data[0][0] = 1;
		data[0][1] = 1;
		data[1][0] = 1;
		data[1][1] = 0;
	}
}