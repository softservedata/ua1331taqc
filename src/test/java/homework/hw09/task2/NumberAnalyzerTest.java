package homework.hw09.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class NumberAnalyzerTest {
    private NumberAnalyzer analyzer;

    @BeforeEach
    public void setUp() {
        analyzer = new NumberAnalyzer();
    }

    // Позитивний тест: Перевірка заповнення колекції 20 числами в межах 1–100
    @Test
    public void testFillWithRandomNumbers_Positive() {
        analyzer.fillWithRandomNumbers();
        assertEquals(20, analyzer.getNumbers().size(), "Collection should contain 20 numbers");
        for (Integer num : analyzer.getNumbers()) {
            assertTrue(num >= 1 && num <= 100, "Number should be between 1 and 100");
        }
    }

    // Негативний тест: Перевірка поведінки findMinMax для порожньої колекції
    @Test
    public void testFindMax_Negative_EmptyCollection() {
        // Колекція порожня
        analyzer.findMax();
        assertTrue(analyzer.getNumbers().isEmpty(), "Collection should still be empty");
    }

    // Позитивний тест: Перевірка обчислення середнього значення
    @Test
    public void testCalculateAverage_Positive() {
        analyzer.getNumbers().addAll(Arrays.asList(10, 20, 30, 40, 50));
        double expectedAverage = (10 + 20 + 30 + 40 + 50) / 5.0; // 30.0
        assertEquals(expectedAverage, analyzer.calculateAverage(), 0.001, "Average should be 30.0");
    }

    // Позитивний тест: Перевірка видалення парних чисел
    @Test
    public void testRemoveEvenNumbers_Positive() {
        analyzer.getNumbers().addAll(Arrays.asList(10, 25, 30, 45, 60));
        analyzer.removeEvenNumbers();
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(25, 45));
        assertEquals(expected, analyzer.getNumbers(), "numbers 25 and 45 should remain");
    }

    // Негативний тест: Перевірка видалення парних чисел для порожньої колекції
    @Test
    public void testRemoveEvenNumbers_Negative_EmptyCollection() {
        analyzer.removeEvenNumbers();
        assertTrue(analyzer.getNumbers().isEmpty(), "Collection should still be empty");
    }

    // Позитивний тест: Перевірка наявності числа в колекції
    @Test
    public void testContainsNumber_Positive() {
        analyzer.getNumbers().addAll(Arrays.asList(10, 25, 30, 45, 60));
        assertTrue(analyzer.containsNumber(25), "Should contain 25");
        assertFalse(analyzer.containsNumber(50), "Should not contain 50");
    }

    // Негативний тест: Перевірка наявності числа в порожній колекції
    @Test
    public void testContainsNumber_Negative_EmptyCollection() {
        assertFalse(analyzer.containsNumber(50), "Empty collection should not contain 50");
    }

    // Позитивний тест: Перевірка сортування колекції
    @Test
    public void testSortCollection_Positive() {
        analyzer.getNumbers().addAll(Arrays.asList(30, 10, 45, 25, 60));
        analyzer.sortCollection();
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(10, 25, 30, 45, 60));
        assertEquals(expected, analyzer.getNumbers(), "Collection should be sorted in ascending order");
    }

    // Негативний тест: Перевірка сортування порожньої колекції
    @Test
    public void testSortCollection_Negative_EmptyCollection() {
        analyzer.sortCollection();
        assertTrue(analyzer.getNumbers().isEmpty(), "Collection should still be empty after sorting");
    }


}