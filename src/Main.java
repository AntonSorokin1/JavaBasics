import services.CountingService;

public class Main {
    public static void main(String[] args) {
        String input = "(46 + 2) * ((357 - 60) / 10)";

        CountingService countingService = new CountingService();

        try {
            System.out.println(countingService.solveEquation(input));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
