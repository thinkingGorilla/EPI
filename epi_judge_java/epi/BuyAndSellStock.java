package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class BuyAndSellStock {
    @EpiTest(testDataFile = "buy_and_sell_stock.tsv")
    public static double computeMaxProfit(List<Double> prices) {
        // 최초 최소 가격은 최대값으로 설정한다.
        double minPrice = Double.MAX_VALUE, maxProfit = 0.0;
        // 가격을 순회하면서 최소 가격을 기록한다.
        // 이전 최대 이익과 현재 가격과 최대 가격의 차가 이전 최대 이익을 넘겼다면,
        // 해당 이익을 최대 이익으로 저장한다.
        for (double price : prices) {
            maxProfit = Math.max(maxProfit, price - minPrice);
            minPrice = Math.min(minPrice, price);
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        // @formatter:off
        System.exit(
            GenericTest.runFromAnnotations(
                args,
                "BuyAndSellStock.java",
                new Object() {}.getClass().getEnclosingClass()
            ).ordinal()
        );
        // @formatter:on
    }
}
