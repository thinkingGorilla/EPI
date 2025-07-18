package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

public class StringIntegerInterconversion {

    public static String intToString(int x) {
        boolean isNegative = x < 0;

        StringBuilder sb = new StringBuilder();
        do {
            // 아스키 코드에서 문자 '0'은 십진수로 48이다.
            // '0'의 십진수 48에 숫자(= x % 10)를 더해 아스키 코드에서의 문자로 바꾼다.
            // 나머지 연산으로 마지막 자릿수를 문자로 추출한다.
            sb.append((char) ('0' + Math.abs(x % 10)));
            // 마리막 자릿수를 문자로 추출하고 버리기 위해 몫 연산을 사용한다.
            x /= 10;
        } while (x != 0);

        if (isNegative) {
            sb.append('-');
        }

        // 마지막 자릿수부터 append하였으므로 역정렬하여 문자열을 생성한다.
        // e.g 4176473
        // → append(3), append(7), ..., append(1), append(4)[, if x < 0 then append(-)]
        // → reverse
        return sb.reverse().toString();
    }

    public static int stringToInt(String s) {
        int result = 0;
        for (int i = (s.charAt(0) == '-' || s.charAt(0) == '+') ? 1 : 0; i < s.length(); i++) {
            // 자바에서 문자 연산은 문자의 아스키 코드 값이 사용된다.
            // '0'은 48이므로 숫자 문자열의 문자가 아스키 코드의 48~57까지 사용되었다면
            // 문자 - '0'의 결과는 0부터 9까지 나온다.
            final int digit = s.charAt(i) - '0';
            // 가장 오른쪽 자리의 숫자 자릿수를 올린 뒤 digit을 더한다.
            // e.g. "-4176473"
            // 1st iter → (0 * 10) + 4
            // 2nd iter → (4 * 10) + 1
            // ...
            // 5th iter → (41764 * 10) + 7
            // 6th iter → (417647 * 10) + 3
            result = (result * 10) + digit;
        }

        return s.charAt(0) == '-' ? -result : result;
    }

    @EpiTest(testDataFile = "string_integer_interconversion.tsv")
    public static void wrapper(int x, String s) throws TestFailure {
        if (Integer.parseInt(intToString(x)) != x) {
            throw new TestFailure("Int to string conversion failed");
        }
        if (stringToInt(s) != x) {
            throw new TestFailure("String to int conversion failed");
        }
    }

    public static void main(String[] args) {
        // @formatter:off
        System.exit(
            GenericTest.runFromAnnotations(
                args,
                "StringIntegerInterconversion.java",
                new Object() {}.getClass().getEnclosingClass()
            ).ordinal()
        );
        // @formatter:on
    }
}
