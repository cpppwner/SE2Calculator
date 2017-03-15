package at.stefaneberl.se2calculator;

/**
 * Presenter class for the Calculator.
 */
public class CalculatorActivityPresenter {

    private final CalculatorActivityView view;

    public CalculatorActivityPresenter(CalculatorActivityView view) {

        this.view = view;
    }

    public void onDivideClicked() {

        double dividend = Double.parseDouble(view.getDividend());
        double divisor = Double.parseDouble(view.getDivisor());

        if (divisor == 0) {
            view.setDivisorZeroError();
        } else {
            double result = dividend / divisor;
            view.setDivisionResult(Double.toString(result));
        }
    }
}
