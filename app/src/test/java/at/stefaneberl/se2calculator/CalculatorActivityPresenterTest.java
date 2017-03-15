package at.stefaneberl.se2calculator;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class CalculatorActivityPresenterTest {

    private CalculatorActivityView view;

    @Before
    public void setUp() {

        view = mock(CalculatorActivityView.class);
    }

    @Test
    public void aDivisorWithZeroGivesAnError() {

        // given
        when(view.getDividend()).thenReturn("10");
        when(view.getDivisor()).thenReturn("0");

        CalculatorActivityPresenter target = new CalculatorActivityPresenter(view);

        // when
        target.onDivideClicked();

        // then
        verify(view, times(1)).setDivisorZeroError();
        verify(view, times(0)).setDivisionResult(anyString());
    }

    @Test
    public void presenterCalculateDivisionResultCorrectly() {

        // given
        when(view.getDividend()).thenReturn("10");
        when(view.getDivisor()).thenReturn("5");

        CalculatorActivityPresenter target = new CalculatorActivityPresenter(view);

        // when
        target.onDivideClicked();

        // then
        verify(view, times(0)).setDivisorZeroError();
        verify(view, times(1)).setDivisionResult("2.0");
    }
}
