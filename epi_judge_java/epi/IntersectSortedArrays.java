package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntersectSortedArrays {

    /**
     * 정렬된 배열 두 개가 주어졌을 때, 두 배열에 동시에 존재하는 원소를 새로운 배열 형태로 반환하라.
     * 입력 배열에는 원소가 중복해서 나타날 수 있지만, 반환되는 배열에선 원소가 중복되면 안된다.
     *
     * @param A 첫 번째 배열
     * @param B 두번째 배열
     * @return 두 배열에 동시에 존재하는 원소들의 배열
     */
    @EpiTest(testDataFile = "intersect_sorted_arrays.tsv")
    public static List<Integer> intersectTwoSortedArrays(List<Integer> A,
                                                         List<Integer> B) {
        // 첫 번째 배열이 두 번째 배열보다 크거나 같다고 가정한다.
        List<Integer> intersectionAB = new ArrayList<>();

        // firstSolution(A, B, intersectionAB);


        return intersectionAB;
    }

    // 방법 1. 무식한 방법
    private static List<Integer> firstSolution(List<Integer> A, List<Integer> B, List<Integer> intersectionAB) {
        for (int i = 0; i < A.size(); ++i) {
            // 중복을 제거하기 위한 조건문
            // [1,2,2,3]과 같은 배열이 있을 때 0번째 요소는 무조건 넣는다.
            // i번째 요소는 i-1번째 요소와 같은 경우 무시한다.
            // 또한 위 두 조건을 만족하며 두번째 배열에 들어있는 요소는 A와 B의 교집합 원소이므로 결과에 담는다.
            if ((i == 0 || !A.get(i).equals(A.get(i - 1))) && B.contains(A.get(i))) {
                intersectionAB.add(A.get(i));
            }
        }
        return intersectionAB;
    }

    // 방법 2. 두 번째 배열을 찾을 때 이진 탐색을 사용한다.
    private static List<Integer> secondSolution(List<Integer> A, List<Integer> B, List<Integer> intersectionAB) {
        for (int i = 0; i < A.size(); ++i) {
            if ((i == 0 || !A.get(i).equals(A.get(i - 1))) && Collections.binarySearch(B, A.get(i)) >= 0) {
                intersectionAB.add(A.get(i));
            }
        }
        return intersectionAB;
    }

    private static List<Integer> thirdSolution(List<Integer> A, List<Integer> B, List<Integer> intersectionAB) {
        int i = 0, j = 0;
        while(i < A.size() && j < B.size()) {
            // 첫 번째 배열과 두 번째 배열은 정렬순서(오름차순, 내림차순 등)가 같다.
            // 만일 오름차순으로 정렬되었다면 가장 작은 요소들이 두 배열의 가장 앞부터 정렬되어있을 것이다.
            // 이때 두 배열의 인덱스로 요소에 접근 했을 때 어느 한 쪽이 다른 요소를 가지고 있다면
            // 해당 요소는 공통 요소가 아니므로 해당 요소를 가진 배열의 인덱스를 증가시킨다.
            // 그리고 두 인덱스가 가리키는 요소가 같다면 두 배열의 인덱스를 전부 증가시킨다.
            if (A.get(i).equals(B.get(j)) && (i == 0 || !A.get(i).equals(A.get(i - 1)))) {
                // A[i] = B[j]인 경우 해당 요소는 A와 B의 교집합 원소이므로 결과에 담는다.
                intersectionAB.add(A.get(i));
                ++i;
                ++j;
            } else if (A.get(i) > B.get(j)) {
                // A[i] > B[j]인 경우 A에만 들어있는 요소이므로 i 인덱스를 증가시킨다.
                ++i;
            } else {
                // A[i] < B[j]인 경우 B에만 들어있는 요소이므로 j 인덱스를 증가시킨다.
                ++j;
            }
        }

        return intersectionAB;
    }


    public static void main(String[] args) {
        //@formatter:off
        System.exit(
            GenericTest.runFromAnnotations(
                args,
                "IntersectSortedArrays.java",
                new Object() {}.getClass().getEnclosingClass()
            ).ordinal()
        );
        //@formatter:on
    }
}
