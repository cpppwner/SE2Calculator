package at.stefaneberl.se2calculator;

/**
 * View interface for Calculator.
 */
public interface CalculatorActivityView {

    String getDividend();

    String getDivisor();

    void setDivisorZeroError();

    void setDivisionResult(String output);
}
