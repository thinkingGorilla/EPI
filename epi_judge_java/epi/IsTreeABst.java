package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeABst {

    /**
     * 이진 트리가 입력으로 주어졌을 때 해당 트리가 이진 탐색 트리의 속성을 만족하는지 확인하라.
     * @param tree 이진 탐색 트리의 속성을 확인하려는 이진 트리
     * @return 이진 탐색 트리인 경우 <code>true</code> 그 외 <code>false</code>
     */
    @EpiTest(testDataFile = "is_tree_a_bst.tsv")
    public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
        return areKeyInRage(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // 왼쪽 부분 트리에서 가장 큰 키, 오른쪽 부분 트리에서 가장 작은 키를 찾는다.
    // 그리고 루트 키가 왼쪽 부분 트리에서 가장 큰 키, 오른쪽 부분 트리에서 가장 작은 키 사이에 있는지 확인한 후
    // (왜냐하면 이진 탐색 트리의 특성 즉, 왼쪽 부분 트리는 노드 값보다 작은 노드들로, 오른쪽 부분 트리는 노드 값보다 큰 노드들로 이루어져 있어야 하므로)
    // 만족하면다면 왼쪽 부분 트리와 오른쪽 부분 트리에서 재귀적으로 확인한다.
    // 이 방법의 문제는 왼쪽 부분 트리에서 가장 큰 키, 오른쪽 부분 트리에서 가장 작은 키를 찾기 위해 부분 트리를 반복해서 탐색한다는 점이다.
    // 최악의 경우 트리가 한 쪽으로 기울어져 있을 때 O(n^2) 시간이 걸린다.
    //
    // 모든 노드의 키값 범위가 [lower, upper] 그리고 루트의 키를 w라 할 때,
    // 왼쪽 부분 트리의 모든 키는 [lower, w]
    // 오른쪽 부분 트리의 모든 키는 [w, upper] 사이에 있어야하는 제약조건을 확인하면
    // 시간 복잡도 O(n) 이내에 문제를 해결할 수 있다.
    //
    // top-down 탐색 방식으로 복잡도를 줄인 방법
    private static boolean areKeyInRage(BinaryTreeNode<Integer> tree, int lower, int upper) {
        if (tree == null) {
            return true;
        } else if (Integer.compare(tree.data, lower) < 0 || Integer.compare(tree.data, upper) > 0 ) {
            // 루트 노드의 값이 좌측 노드 값과 우측 노드 값 사이에 있어야한다.
            // 만일 그렇지 않다면 이진 탐색 트리의 속성이 아니므로 false를 반환한다.
            return false;
        }

        return areKeyInRage(tree.left, lower, tree.data) && areKeyInRage(tree.right, tree.data, upper);
    }

    public static void main(String[] args) {
        //@formatter:off
        System.exit(
            GenericTest.runFromAnnotations(
                args,
        "IsTreeABst.java",
                new Object() {}.getClass().getEnclosingClass()
            ).ordinal()
        );
        //@formatter:on
    }
}
