package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import static java.util.Objects.nonNull;

public class SortedListsMerge {

    @EpiTest(testDataFile = "sorted_lists_merge.tsv")
    public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> L1,
                                                        ListNode<Integer> L2) {
        // 더미 헤드 생성
        ListNode<Integer> dummyHead = new ListNode<>(0, null);
        ListNode<Integer> current = dummyHead;
        ListNode<Integer> p1 = L1, p2 = L2;

        while(nonNull(p1) && nonNull(p2)) {
            if (p1.data <= p2.data) {
                // p1을 current의 다음 노드로 지정하고, current를 current의 다음 노드로 지정한다.
                current = current.next = p1;
                p1 = p1.next;
            } else {
                // p1이 p2보다 작을 때와 마찬가지로 current 노드 참조를 수정한다.
                current = current.next = p2;
                p2 = p2.next;
            }
        }
        // 남아 있을 정렬된 리스트를 current 다음 노드에 이어 붙인다.
        current.next = nonNull(p1) ? p1 : p2;

        return dummyHead.next;
    }

    public static void main(String[] args) {
        // @formatter:off
        System.exit(
            GenericTest.runFromAnnotations(
                args,
                "SortedListsMerge.java",
                new Object() {}.getClass().getEnclosingClass()
            ).ordinal()
        );
        // @formatter:on
    }
}
