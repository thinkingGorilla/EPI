package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class CountBits {
    @EpiTest(testDataFile = "count_bits.tsv")

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
