package epi.test_framework.serialization_traits;

import epi.DutchNationalFlag.FlagColor;
import epi.test_framework.minimal_json.JsonValue;

import java.util.Collections;
import java.util.List;

public class FlagColorTrait extends SerializationTrait {
    @Override
    public String name() {
        // 테스트 데이터에서는 열거형이 정수(ordinal) 값으로 표현되므로 "int"를 반환합니다.
        return "int";
    }

    @Override
    public Object parse(JsonValue jsonObject) {
        try {
            // 우선 문자열로 표현된 열거 상수를 시도
            String colorStr = jsonObject.asString();
            return FlagColor.valueOf(colorStr);
        } catch (Exception e) {
            // 문자열 파싱이 실패하면 정수(ordinal)로 간주
            int ordinal = jsonObject.asInt();
            FlagColor[] colors = FlagColor.values();
            if (ordinal < 0 || ordinal >= colors.length) {
                throw new RuntimeException("Invalid ordinal for FlagColor: " + ordinal);
            }
            return colors[ordinal];
        }
    }

    @Override
    public List<String> getMetricNames(String argName) {
        return Collections.emptyList();
    }

    @Override
    public List<Integer> getMetrics(Object x) {
        if (x instanceof FlagColor) {
            // FlagColor의 ordinal 값을 메트릭 값으로 반환
            return Collections.singletonList(((FlagColor) x).ordinal());
        }
        throw new RuntimeException("Expected FlagColor");
    }
}