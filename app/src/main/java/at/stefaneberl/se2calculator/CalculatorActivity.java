package at.stefaneberl.se2calculator;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import at.stefaneberl.se2calculator.databinding.ActivityCalculatorBinding;

/**
 * Activity for the calculator app.
 *
 * <p>
 *     This class also implements the {@link CalculatorActivityView view} interface.
 * </p>
 */
public class CalculatorActivity extends Activity implements CalculatorActivityView {

    private EditText dividend;
    private EditText divisor;
    private TextView output;
    private TextView result;
    private Button divideButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCalculatorBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_calculator);
        binding.setPresenter(new CalculatorActivityPresenter(this));

        dividend = (EditText)findViewById(R.id.editDividend);
        divisor = (EditText)findViewById(R.id.editDivisor);
        output = (TextView)findViewById(R.id.textOutput);
        result = (TextView)findViewById(R.id.textResult);
        divideButton = (Button)findViewById(R.id.buttonDivide);
        divideButton.setEnabled(false);

        // setup text changed listeners on inputs to enable button
        dividend.addTextChangedListener(new TextWatcher());
        divisor.addTextChangedListener(new TextWatcher());
    }

    @Override
    public String getDividend() {

        return dividend.getText().toString();
    }

    @Override
    public String getDivisor() {

        return divisor.getText().toString();
    }

    @Override
    public void setDivisorZeroError() {

        updateOutput(getResources().getText(R.string.error));
        updateResultText(getResources().getText(R.string.divisor_zero));
    }

    @Override
    public void setDivisionResult(String output) {

        updateOutput(getResources().getText(R.string.result));
        updateResultText(output);
    }

    private void enableDivideIfReady() {

        divideButton.setEnabled(!getDividend().isEmpty() && !getDivisor().isEmpty());
    }

    private void updateOutput(CharSequence text) {

        output.setText(text);
        output.setVisibility(View.VISIBLE);
    }

    private void updateResultText(CharSequence text) {

        result.setText(text);
        result.setVisibility(View.VISIBLE);
    }

    private class TextWatcher implements android.text.TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            enableDivideIfReady();
        }
    }
}
