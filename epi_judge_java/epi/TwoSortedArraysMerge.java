package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class TwoSortedArraysMerge {

    /**
     * 정렬된 정수 배열 두 개가 주어졌을 때, 두 배열을 정렬된 순서로 합친 뒤 그 결과를 첫 번째 배열에 넣는 프로그램을 작성하라.
     * 첫번째 배열의 끝에는 두 번째 배열을 모두 넣을 수 있을 만큼 충분한 빈칸이 있다고 가정해도 좋다.
     *
     * @param A 첫 번째 배열
     * @param m 첫 번째 배열의 길이
     * @param B 두 번째 배열
     * @param n 두 번째 배열의 길이
     */
    @EpiTest(testDataFile = "two_sorted_arrays_merge.tsv")
    public static List<Integer> mergeTwoSortedArraysWrapper(List<Integer> A, int m, List<Integer> B, int n) {
        // 방법 1. 두 배열을 앞에서부터 동시에 순회하면서 더 작은 값을 결과 배열에 작성
        // 첫 번째 배열과 두 번째 배열의 길이가 각각 m과 n일 때 이 방법의 시간 복잡도는 O(m + n)
        // 하지만 두 번째 배열의 값이 첫 번째 배열의 값보다 작을 때, 첫 번째 배열의 모든 값을 오른쪽으로 한 칸씩 옮겨야 함
        // 만약 두 번째 배열의 값이 전부 첫 번째 배열보다 작은 경우 시간 복잡도는 O(m * n)
        //
        // 방법 2. 첫 번째 배열의 뒤쪽은 비어있고 두 번째 배열을 모두 넣을 수 있을 만큼 충분히 크므로
        // 두 배열의 가장 큰 값을 첫 번째 배열의 맨 끝에서부터 값을 채워나가도 된다.
        // 따라서 두 번째 배열의 크기를 고려하여 두 배열의 마지막 배열의 마지막 원소 인덱스를 m + n - 1로 계산한다.
        int a = m - 1, b = n - 1, writeIdx = m + n - 1;
        while (a >= 0 && b >= 0) {
            A.set(writeIdx--, A.get(a) > B.get(b) ? A.get(a--) : B.get(b--));
        }

        // 첫 번째 배열이 두 번째 배열보다 작아 첫 번째 배열의 정렬 작업이 먼저 마친 경우,
        // 남아 있는 두 번째 배열의 정렬 작업을 마치기 위한 반복문
        while (b >= 0) {
            A.set(writeIdx--, B.get(b--));
        }
        return A;
    }

    public static void main(String[] args) {
        //@formatter:off
        System.exit(
            GenericTest.runFromAnnotations(
                args,
                "TwoSortedArraysMerge.java",
                new Object() {}.getClass().getEnclosingClass()
            ).ordinal()
        );
        //@formatter:on
    }
}
