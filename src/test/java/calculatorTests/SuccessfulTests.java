package calculatorTests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import services.CountingService;

public class SuccessfulTests {
    private static CountingService calculator;

    @BeforeClass
    public static void init() {
        calculator = new CountingService();
    }

    @Test
    public void test_add_2_plus_2() throws Exception {
        String input = "2+2";
        String res = calculator.solveEquation(input);
        Assert.assertEquals("4", res);
    }

    @Test
    public void test_sub_4_minus_2() throws Exception {
        String input = "4-2";
        String res = calculator.solveEquation(input);
        Assert.assertEquals("2", res);
    }

    @Test
    public void test_mult_2_and_2() throws Exception {
        String input = "2*2";
        String res = calculator.solveEquation(input);
        Assert.assertEquals("4", res);
    }

    @Test
    public void test_div_4_and_2() throws Exception {
        String input = "4/2";
        String res = calculator.solveEquation(input);
        Assert.assertEquals("2", res);
    }

    @Test
    public void test_equate() throws Exception {
        String input = "(46 + 2) * ((357 - 60) / 10)";
        String res = calculator.solveEquation(input);
        Assert.assertEquals("1392", res);
    }
}
