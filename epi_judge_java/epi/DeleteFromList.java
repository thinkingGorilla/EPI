package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class DeleteFromList {

    @EpiTest(testDataFile = "delete_from_list.tsv")
    public static ListNode<Integer> deleteList(ListNode<Integer> head, int zeroBasedIndex) {
        ListNode<Integer> tmp = head;
        // e.g.
        // [4, 3, 2, 1]	    1	[4, 2, 1]
        // [4, 2, 1, 5, 3]	3	[4, 2, 1, 3]
        // 삭제할 노드의 이전 노드에 접근하기 위해 zeroBasedIndex를 1씩 감소시키며 이동한다.
        while (--zeroBasedIndex > 0) {
            tmp = tmp.next;
        }
        // tmp.next가 삭제할 노드이므로, tmp의 다음 노드를 삭제할 노드의 다음 노드로 변경한다.
        tmp.next = tmp.next.next;

        return head;
    }

    public static void main(String[] args) {
        // @formatter:off
        System.exit(
            GenericTest.runFromAnnotations(
                args,
                "DeleteFromList.java",
                new Object() {}.getClass().getEnclosingClass()
            ).ordinal()
        );
        // @formatter:on
    }
}
