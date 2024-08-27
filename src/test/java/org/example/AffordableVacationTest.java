package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class AffordableVacationTest {

    static List<Arguments> provideTestCases() {
        return List.of(
                Arguments.of(10, 1, new int[]{5}, "money: 5", "Single day rental"),
                Arguments.of(10, 1, new int[]{3, 2, 4}, "money: 2", "Cheapest day rental"),
                Arguments.of(10, 2, new int[]{3, 7, 6}, "money: 10", "Just enough money for two days"),
                Arguments.of(10, 1, new int[]{20, 15, 30}, "days: 0", "Insufficient money at all"),
                Arguments.of(10, 2, new int[]{9, 6, 7, 4}, "days: 1", "Maximum 1 day for vacation"),
                Arguments.of(50, 3, new int[]{10, 40, 5}, "days: 2", "Maximum 2 days for vacation"),
                Arguments.of(100, 4, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, "money: 10", "Increasing cost"),
                Arguments.of(100, 5, new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1}, "money: 15", "Decreasing cost")
        );
    }

    @ParameterizedTest(name = "{4}")
    @MethodSource({"provideTestCases"})
    @DisplayName("Basic Tests")
    void testFindMinCost(int money, int days, int[] cost, String expected, String displayName) {
        assertEquals(expected, AffordableVacation.findMinCost(money, days, cost), String.format("money=%d, days=%d, cost=%s", money, days, Arrays.toString(cost)));
    }
}