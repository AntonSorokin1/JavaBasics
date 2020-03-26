package calculatorTests;

import exceptions.DivisionByZeroException;
import exceptions.ForbiddenSymbolsException;
import exceptions.MultipleActionException;
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
        String input = "2.5+2.5";
        String res = calculator.solveEquation(input);
        Assert.assertEquals("5", res);
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

    @Test(expected = DivisionByZeroException.class)
    public void test_divide_zero_exception() throws Exception {
        String input = "2 / 0";
        calculator.solveEquation(input);
    }

    @Test(expected = ForbiddenSymbolsException.class)
    public void test_forbidden_symbol_exception() throws Exception {
        String input = "2 + 2#";
        calculator.solveEquation(input);
    }

    @Test(expected = MultipleActionException.class)
    public void test_multiple_action_exception() throws Exception {
        String input = "2 + + 2";
        calculator.solveEquation(input);
    }

    @Test
    public void test_equate() throws Exception {
        String input = "(46 + 2) * ((357 - 60) / 10)";
        String res = calculator.solveEquation(input);
        Assert.assertEquals("1392", res);
    }
}
