# algorithm_practice
알고리즘 연습 저장소입니다.

<br>

## 알고리즘 사이트

- [BAEKJOON Online Judge](https://www.acmicpc.net/)

- [SW Expert Academy](https://swexpertacademy.com/main/main.do)

- [Programmers](https://programmers.co.kr/)

- [Jungol](http://jungol.co.kr/)
- [Codeforces](https://codeforces.com/)

<br>

## 간단하지만 알면 좋은 최적화

- for문의 ++i 와 i++ 차이

  ```java
  for(int i = 0; i < 1000; i++){}
  
  for(int i = 0; i < 1000; ++i){}
  ```

  내부 operator 로직을 보면 i++은 한번더 연산을 거칩니다. 따라서 ++i 가 미세하게 조금 더 빠릅니다.  하지만 요즘 컴파일러는 거의 차이가 없어지게 됐습니다.

- if/else if 와 switch case 차이

  `20개의 가지 수, 10억번의 연산일때 -> if/else : 약 20초 / switch : 약 15초`

  경우를 찾아서 접근하기 때문에 switch case가 더 빠릅니다. if-else 같은 경우는 다 타고 들어가야하기 때문에 더 느립니다.

- 임시 변수의 선언 위치 (for문 안에서 변수 선언 vs for문 밖에서 변수 선언)

  for문 밖에서 변수를 선언하는 것이 더 빠릅니다.

- 재귀함수의 파라미터 (전역으로 선언하기 vs 파라미터로 넘겨주기)

  `10억번의 연산을 했을 때 전역으로 선언 : 약 6.8초 파라미터로 넘겨준 것 : 약 9.6초`

  함수를 계속해서 호출할 때, 스택에서 쌓임. 파라미터들은 함수를 호출할 때마다 메모리 할당하는 동작을 반복하게 됩니다. 따라서 지역 변수로 사용하지 않는 것들은 전역 변수로 빼야합니다. BFS에서 매개변수를 늘려가는 것보다 전역으로 선언 후 활용하는 것이 훨씬 시간 단축할 수 있는 습관을 기를 수 있습니다.

- Map을 한번에 내릴 때 (벽돌깨기 같은 문제에서 좋음)

  ```java
  static void down() {
      for (int j = 0; j < W; j++) {
          int ti = H; // 이동해야할 위치
          for (int i = H - 1; i >= 0; i--) {
              if (map[i][j] != 0) {
                  ti--;
                  map[ti][j] = map[i][j];
                  if (i != ti)
                      map[i][j] = 0;
              }
          }
      }
  }
  ```

<br>

## Reference & Additional Resources

- https://github.com/GimunLee/algorithm/blob/master/swea/%EC%82%BC%EC%84%B1%20SW%20%EC%97%AD%EB%9F%89%ED%85%8C%EC%8A%A4%ED%8A%B8%20Tip.md#%EC%82%BC%EC%84%B1-sw-%EC%97%AD%EB%9F%89-%ED%85%8C%EC%8A%A4%ED%8A%B8-tip
