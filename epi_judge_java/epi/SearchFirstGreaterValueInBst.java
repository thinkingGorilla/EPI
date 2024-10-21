package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SearchFirstGreaterValueInBst {

    /**
     * 이진 탐색 트리와 값 하나가 입력으로 주어졌을 때, 중위 순회의 결과 해당 값보다 큰 첫 번째 키를 반환하는 프로그램을 작성하라.
     *
     * @param tree k보다 큰 첫 번째 키를 찾을 이진 탐색 트리
     * @param k    첫 번째 키를 찾을 조건
     * @return k보다 큰 첫 번째 키를 가진 노드
     */
    public static BstNode<Integer> findFirstGreaterThanK(BstNode<Integer> tree, Integer k) {
        // 이진 트리를 단순하게 중위 순회를 하면 O(n) 시간에 해당 노드를 찾을 수 있다.
        // 하지만 이진 탐색 트리의 특성(왼쪽 부분 트리는 노드 값보다 작은 노드들로, 오른쪽 부분 트리는 노드 값보다 큰 노드들로 이루어져 있음)을
        // 이용하면 O(h) = O(log2 n) 시간 복잡도 내에 해결할 수 있다.

        BstNode<Integer> subtree = tree, firstSoFar = null;
        while (subtree != null) {

            if (subtree.data > k) {
                // 지금 탐색한 노드 키가 k보다 크다는 것은
                // 현재 노드가 k보다 큰 첫 번째 키를 가진 노드가 될 가능성이 있음을 말한다.
                // 따라서 k보다 큰 첫 번째 키를 가진 노드 후보로 저장한다.
                firstSoFar = subtree;
                // 노드 키가 k보다 크다면 노드 키 보다 큰 부분 트리는 탐색할 필요가 없다.
                // 왜냐하면 k보다 큰 첫 번째 노드를 찾는 것이기 때문이다.
                // 따라서 k보다 큰 노드가 있을 수 있는 부분 트리를 왼쪽 부분 트리로 한정한다.
                subtree = subtree.left;
            } else {
                // 지금 탐색한 노드 키가 k보다 작다는 것은
                // 현재 노드가 k보다 큰 첫 번째 키를 가진 노드가 될 수 없으므로 firstSoFar에 저장하지 않는다.

                // 노드 키가 k보다 작다면 노드 키 보다 작은 부분 트리는 탐색할 필요가 없다.
                // 따라서 k보다 큰 노드가 있을 수 있는 부분 트리를 오른쪽 부분 트리로 한정한다.
                subtree = subtree.right;
            }
        }

        return firstSoFar;
    }

    @EpiTest(testDataFile = "search_first_greater_value_in_bst.tsv")
    public static int findFirstGreaterThanKWrapper(BstNode<Integer> tree, Integer k) {
        BstNode<Integer> result = findFirstGreaterThanK(tree, k);
        return result != null ? result.data : -1;
    }

    public static void main(String[] args) {
        int[] sortedArray = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53};
        BstNode<Integer> root = sortedArrayToBST(sortedArray, 0, sortedArray.length - 1);
        System.out.println(findFirstGreaterThanK(root, 10).data);

        //@formatter:off
        System.exit(
            GenericTest.runFromAnnotations(
                args,
                "SearchFirstGreaterValueInBst.java",
                    new Object() {}.getClass().getEnclosingClass()
                ).ordinal()
        );
        //@formatter:on
    }

    public static BstNode<Integer> sortedArrayToBST(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }

        // 중간 값을 루트로 선택
        int mid = (start + end) / 2;
        BstNode<Integer> node = new BstNode<>(arr[mid]);

        // 왼쪽 서브트리와 오른쪽 서브트리를 재귀적으로 생성
        node.left = sortedArrayToBST(arr, start, mid - 1);
        node.right = sortedArrayToBST(arr, mid + 1, end);

        return node;
    }
}
