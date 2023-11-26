package com.AppTechInnovationLab.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButton;
    private EditText ageEditText;
    private EditText feetEditText;
    private EditText inchesEditText;
    private EditText weightEditText;
    private Button calculateButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();
        setButtonClickListener();

    }

    private void getViews() {
        maleRadioButton = findViewById(R.id.radio_button_male);
        femaleRadioButton = findViewById(R.id.radio_button_female);
        ageEditText = findViewById(R.id.edit_text_age);
        feetEditText = findViewById(R.id.edit_text_feet);
        inchesEditText = findViewById(R.id.edit_text_inches);
        weightEditText = findViewById(R.id.edit_text_weight);
        calculateButton = findViewById(R.id.button_calculate);
        resultTextView = findViewById(R.id.text_view_result);
    }

    private void setButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double bmi = calculateBMI();
                String ageText = ageEditText.getText().toString();
                int age = Integer.parseInt(ageText);

                if(age >= 18){
                    displayMessage(bmi);
                } else {
                    displayGuidence(bmi);
                }
            }

        });
    }

    private void displayGuidence(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;

        if(maleRadioButton.isChecked()){
            //Display boy result
            fullResultString = bmiTextResult + " - As you are under 18, please consult with your doctor for health range of boys.";
        } else if(femaleRadioButton.isChecked()){
            // Display girl result.
            fullResultString = bmiTextResult + " - As you are under 18, please consult with your doctor for health range of girls.";
        } else {
            // Display General Guidance.
            fullResultString = bmiTextResult + " - As you are under 18, please consult with your doctor for health range";
        }

        resultTextView.setText(fullResultString);
    }

    private double calculateBMI() {

        String feetText = feetEditText.getText().toString();
        String inchesText = inchesEditText.getText().toString();
        String weightText = weightEditText.getText().toString();


        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchesText);
        int weight = Integer.parseInt(weightText);

        //Convert feet to inches.
        int totalInches = (inches * 12) + inches;

        //Convert total inches to meters
        double heightInMeters = totalInches * 0.0254;

        //Calculate BMI
        double bmi = weight / (heightInMeters * heightInMeters);
        return bmi;

    }

    private void displayMessage(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;

        if (bmi < 18.5) {
            fullResultString = bmiTextResult + " - You are Underweight.";
        } else if (bmi > 25) {
            fullResultString = bmiTextResult + " - You are Overweight.";
        } else {
            fullResultString = bmiTextResult + " - You are Healthy weight.";
        }

        resultTextView.setText(fullResultString);
    }


}