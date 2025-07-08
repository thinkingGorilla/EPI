package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EvenOddArray {

    /**
     * 추가 공간 복잡도를 O(1)로 풀기 위해 배열의 양쪽 끝을 사용한다.
     */
    @EpiTest(testDataFile = "even_odd_array.tsv")
    public static void evenOdd(List<Integer> A) {
        int nextEven = 0;
        int nextOdd = A.size() - 1;
        // 시간 복잡도는 배열의 길이 만큼이다. → O(n)
        // 배열의 앞에서 부터는 짝수를, 뒤에서 부터는 홀수로 채운다.
        while (nextEven < nextOdd) {
            // 배열의 요소가 짝수라면 홀수 포인터를 증가시킨다.
            if (A.get(nextEven) % 2 == 0) {
                nextEven++;
            } else {
                // nextEven 위치에 있는 배열의 요소가 홀수라면
                // 마지막 짝수 포인터와 자리를 바꾼 후 짝수 포인터를 감소시킨다.
                Collections.swap(A, nextEven, nextOdd--);
            }
        }
    }

    public static void main(String[] args) {
        // @formatter:off
        System.exit(
            GenericTest.runFromAnnotations(
                args,
                "EvenOddArray.java",
                new Object() {}.getClass().getEnclosingClass()
            ).ordinal()
        );
        // @formatter:on
    }
}
