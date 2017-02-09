package com.app.alex.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.app.alex.calculadora.math.Addition;
import com.app.alex.calculadora.math.ArithmeticCalculation;
import com.app.alex.calculadora.math.ArithmeticOperation;
import com.app.alex.calculadora.math.Division;
import com.app.alex.calculadora.math.Multiplication;
import com.app.alex.calculadora.math.Subtraction;

import java.util.ArrayList;
import java.util.List;

public class CalculadoraActivity extends AppCompatActivity {

    private TextView textViewNum1;
    private TextView textViewNum2;

    private ToggleButton toggleButtonAddition;
    private ToggleButton toggleButtonSubtraction;
    private ToggleButton toggleButtonMultiplication;
    private ToggleButton toggleButtonDivision;
    private List<ToggleButton> toggleButtons;
    private Button buttonEquals;
    private TextView textViewResult;
    private ArithmeticCalculation arithmeticCalculation;
    private ArithmeticOperation arithmeticOperation;
    private ToggleButton currentToggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);
        initViewComponents();


    }

    private void initViewComponents() {
        textViewNum1 = (TextView) findViewById(R.id.editTextNum1);
        textViewNum2 = (TextView) findViewById(R.id.editTextNum2);
        textViewResult = (TextView) findViewById(R.id.textViewResult);
        initButtons();
        initToggleButtons();
    }

    private void initButtons() {
        buttonEquals = (Button) findViewById(R.id.buttonEquals);
        buttonEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation(v);
            }
        });
    }

    private void initToggleButtons() {
        toggleButtonAddition = (ToggleButton) findViewById(R.id.toggleButtonAddition);
        toggleButtonAddition.setOnCheckedChangeListener(new MasterToggleButtonListener(new Addition()));

        toggleButtonSubtraction = (ToggleButton) findViewById(R.id.toggleButtonSubtraction);
        toggleButtonSubtraction.setOnCheckedChangeListener(new MasterToggleButtonListener(new Subtraction()));

        toggleButtonMultiplication = (ToggleButton) findViewById(R.id.toggleButtonMultiplication);
        toggleButtonMultiplication.setOnCheckedChangeListener(new MasterToggleButtonListener(new Multiplication()));

        toggleButtonDivision = (ToggleButton) findViewById(R.id.toggleButtonDivision);
        toggleButtonDivision.setOnCheckedChangeListener(new MasterToggleButtonListener(new Division()));

        toggleButtons = new ArrayList<>();
        toggleButtons.add(toggleButtonAddition);
        toggleButtons.add(toggleButtonSubtraction);
        toggleButtons.add(toggleButtonMultiplication);
        toggleButtons.add(toggleButtonDivision);

    }

    private class MasterToggleButtonListener implements CompoundButton.OnCheckedChangeListener {

        private ArithmeticOperation _arithmeticOperation;

        public MasterToggleButtonListener(ArithmeticOperation arithmeticOperation) {
            this._arithmeticOperation = arithmeticOperation;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                arithmeticOperation = _arithmeticOperation;
                currentToggleButton = (ToggleButton) findViewById(buttonView.getId());
                setOtherToggleButtonsOff(currentToggleButton);
            }
        }
    }

    private void performOperation(View view) {
        Double num1 = Double.valueOf(textViewNum1.getText().toString());
        Double num2 = Double.valueOf(textViewNum2.getText().toString());
        if (num1 != null && num2 != null && this.arithmeticOperation != null) {
            ArithmeticCalculation arithmeticCalculation = new ArithmeticCalculation(
                    this.arithmeticOperation,
                    num1,
                    num2);
            textViewResult.setText(arithmeticCalculation.solve().toString());
            cleanUp();
        } else {
            Toast.makeText(this,
                    "Both inputs must not be empty and and an arithmetic operation must be chosen",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void cleanUp() {
        currentToggleButton.setChecked(false);
        arithmeticOperation = null;
    }

    private void setOtherToggleButtonsOff(ToggleButton toggleButton) {
        for (ToggleButton tgb : toggleButtons) {
            if (toggleButton.getId() != (tgb.getId())) {
                tgb.setChecked(false);
            }
        }
    }
}
