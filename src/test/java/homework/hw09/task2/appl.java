package homework.hw09.task2;

public class appl {
    public static void main(String[] args) {
        NumberAnalyzer analyzer = new NumberAnalyzer();
        analyzer.fillWithRandomNumbers();
        analyzer.printCollection();
        analyzer.sortCollection();
        analyzer.calculateAverage();
        analyzer.findMax();
        analyzer.removeEvenNumbers();
        analyzer.containsNumber(13);
    }
}
