package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class InsertInList {

    @EpiTest(testDataFile = "insert_in_list.tsv")
    public static ListNode<Integer> insertAfter(ListNode<Integer> node, int oneBasedIndex, int newNodeData) {
        // 이때 tmp와 node는 "같은" 객체(= 첫번째 노드)를 가리킴
        ListNode<Integer> tmp = node;
        // e.g.
        // [2, 4, 3]	 1	1	[2, 1, 4, 3]
        // [2, 1, 4, 3]  3	10	[2, 1, 4, 10, 3]
        // 새로운 노드의 앞 노드가 될 노드에 접근하기 위해 oneBasedIndex를 1씩 감소시키며 이동한다.
        while (--oneBasedIndex > 0) {
            // tmp는 n 번째 노드를 가리키게 변경됨
            // node는 여전히 첫 번째 노드만 가리킴
            tmp = tmp.next;
        }

        // tmp 노드의 다음 위치에 새 노드를 삽입한다.
        // 새 노드의 데이터는 newNodeData이며, 새 노드의 다음 노드는 기존 tmp.next가 가리키던 노드가 된다.
        tmp.next = new ListNode<>(newNodeData, tmp.next);
        return node;
    }

    public static void main(String[] args) {
        // @formatter:off
        System.exit(
            GenericTest.runFromAnnotations(
                args,
                "InsertInList.java",
                new Object() {}.getClass().getEnclosingClass()
            ).ordinal()
        );
        // @formatter:on
    }
}
