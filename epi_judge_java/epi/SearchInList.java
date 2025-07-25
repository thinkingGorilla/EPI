package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SearchInList {

    public static ListNode<Integer> searchList(ListNode<Integer> node, int key) {
        while (node != null && node.data != key) {
            node = node.next;
        }
        return node;
    }

    @EpiTest(testDataFile = "search_in_list.tsv")
    public static int searchListWrapper(ListNode<Integer> L, int key) {
        ListNode<Integer> result = searchList(L, key);
        return result != null ? result.data : -1;
    }

    public static void main(String[] args) {
        // @formatter:off
        System.exit(
            GenericTest.runFromAnnotations(
                args,
                "SearchInList.java",
                new Object() {}.getClass().getEnclosingClass()
            ).ordinal()
        );
        // @formatter:on
    }
}
