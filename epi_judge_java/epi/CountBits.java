package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class CountBits {
    @EpiTest(testDataFile = "count_bits.tsv")

    // 자바에서 비트 연산은 `비트 배열`을 지원하는 타입만 가능하다.
    // 자바에서 비트 배열을 지원하는 타입은 int와 long 뿐이다.
    //
    // byte, short, char → 자동으로 int로 승격(promote)
    // int는 그대로 사용
    // long은 long끼리면 long 결과
    // float/double은 비트연산 불가능 (컴파일 오류)
    //
    // int와 long이 섞여서 비트연산을 하면?
    // 자바는 손실 없는 방향으로 변환하므로 int를 long으로 자동 타입 변환(= 승격)후 비트연산을 수행한다.
    public static short countBits(int x) {
        short numBits = 0;
        while (x != 0) {
            // 각 비트마다 O(1)의 연산을 수행
            // 시간 복잡도는 O(n) → n은 비트의 개수
            // e.g.
            // 1st loop: x = 1101, x & 1 = 1 → numBits=1
            // 2nd loop: x = 0110, x & 1 = 0 → numBits=1
            // 3rd loop: x = 0011, x & 1 = 1 → numBits=2
            // 4th loop: x = 0001, x & 1 = 1 → numBits=3
            // 최악의 경우 입력값이 (111...11)₂인 경우 → 시간 복잡도는 O(n)
            // 최선의 경우 입력값이 0인 경우 → 시간 복잡도는 O(1)
            numBits += (short) (x & 1);
            x >>>= 1;
        }
        return numBits;
    }

    public static void main(String[] args) {
        // @formatter:off
        System.exit(
            GenericTest.runFromAnnotations(
                args,
                "CountBits.java",
                new Object() {}.getClass().getEnclosingClass()
            ).ordinal()
        );
        // @formatter:on
    }
}
