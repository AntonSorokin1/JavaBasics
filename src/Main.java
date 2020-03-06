import services.CountingService;

public class Main {
    public static void main(String[] args) {
        String input = "46 + 2 * ((357 - 60) / 10)";

        CountingService countingService = new CountingService();

        System.out.println(countingService.solveEquation(input));
    }
}
