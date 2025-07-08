package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class Parity {
    @EpiTest(testDataFile = "parity.tsv")
    public static short parity(long x) {
        // return simple(x);
        // return clearLowestSetBit(x);
        // return usingBitMask(x);
        return usingXorFold(x);
    }

    /**
     * 입력값의 마지막 비트를 AND 연산으로 추출하고 XOR 연산의 성질을 이용하여 패리티 비트를 구한다.
     * 시간 복잡도는 입력 크기 = n, O(n)
     */
    public static short simple(long x) {
        // 패리티 계산에서는 단지 1의 개수가 홀수인지 짝수인지 알면 되므로 결과값이 1 또는 0이어야 한다.
        short result = 0;
        while (x != 0) {
            result ^= (short) (x & 1);
            x >>>= 1;
        }
        return result;
    }

    /**
     * 1을 뺐을 때 빌림이 없었다면 해당 자리의 1이 곧바로 0으로 바뀐다.
     * 반대로 빌림이 발생하면 0 비트를 따라 전파되다가 처음 1을 만나는 자리에서 빌림이 해결되며, 그 자리의 1이 0으로 바뀐다.
     * 빌림이 멈추는 자리가 바로 가장 낮은 1비트 자리이며, 패리티 알고리즘에서 계산하는 대상이 된다.
     *
     * 시간 복잡도는 1로 세팅된 비트 개수 = k, O(k)
     * e.g. (00101100)₂ & (00101100)₂ - 1 = (00101100)₂ & (00101011)₂
     * 빌림이 발생한 자릿수부터 하위 비트를 AND 연산을 통해 0으로 지운다.
     */
    public static short clearLowestSetBit(long x) {
        // 패리티 계산에서는 단지 1의 개수가 홀수인지 짝수인지 알면 되므로 결과값이 1 또는 0이어야 한다.
        short result = 0;
        while (x != 0) {
            x &= (x - 1);
            result ^= 1;
        }
        return result;
    }

    static final short[] PRECOMPUTED_PARITY;

    static {
        // 1 = (0000 0000 0000 0000)₂을 좌측으로 16비트 이동 → (1000 0000 0000 0000)₂ = 2¹⁶
        PRECOMPUTED_PARITY = new short[1 << 16];
        for (int i = 0; i < (1 << 16); ++i) {
            PRECOMPUTED_PARITY[i] = simple(i);
        }
    }

    /**
     * 입력값을 워드 크기르 분할하고 각 워드의 패리티를 사전에 계산된 룩업 테이블에서 가져와
     * 각 워드의 패리티 값을 XOR 연산하여 전체 입력값의 패리티를 구한다.
     * 해시 테이블에 사용될 키값의 비트 수가 L이고 전체 비트 수가 n이라면, 시간복잡도는 O(n/L)
     */
    public static short usingBitMask(long x) {
        // 시간 복잡도는 O(x₂/WORD_SIZE) = O(2¹⁶/16) = O(4)
        final int WORD_SIZE = 16;
        final int BIT_MASK = 0xFFFF;
        // @formatter:off
        return (short) (
            PRECOMPUTED_PARITY[(int) ((x >>> (3 * WORD_SIZE)) & BIT_MASK)]
                ^ PRECOMPUTED_PARITY[(int) ((x >>> (2 * WORD_SIZE)) & BIT_MASK)]
                ^ PRECOMPUTED_PARITY[(int) ((x >>> (WORD_SIZE)) & BIT_MASK)]
                ^ PRECOMPUTED_PARITY[(int) (x & BIT_MASK)]
        );
        // @formatter:on
    }

    /**
     * 룩업테이블 개념에 착안하여, 워드 크기 만큼 입력값을 나눈 후 워드 크기로 분할된 개수 만큼
     * XOR을 수행하여 전체 입력값의 패리티를 구한다.
     * n = 입력값의 비트 배열 크기, 시간 복잡도는 O(log n)이다.
     */
    public static short usingXorFold(long x) {
        x ^= x >>> 32;
        x ^= x >>> 16;
        x ^= x >>> 8;
        x ^= x >>> 4;
        x ^= x >>> 2;
        x ^= x >>> 1;
        return (short) (x & 1);
    }

    public static void main(String[] args) {

        // @formatter:off
        System.exit(
            GenericTest.runFromAnnotations(
                args,
                "Parity.java",
                new Object() {}.getClass().getEnclosingClass()
            ).ordinal()
        );
        // @formatter:on
    }
}
