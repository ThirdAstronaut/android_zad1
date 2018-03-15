package example.com.bmi_v20;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import example.com.bmi_v20.Model.BMI;
import example.com.bmi_v20.Model.BmiForImperialMetrics;
import example.com.bmi_v20.Model.BmiForKgM;

public class MainActivity extends AppCompatActivity {

    static final String PASSED_RESULT_VALUE = "passed_result_value";
    private EditText weightInput;
    private EditText heightInput;
    private Switch unitsSwitch;
    private double result = 0.0;
    private boolean isSwitchChecked;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        weightInput = findViewById(R.id.weight_editText);
        heightInput = findViewById(R.id.height_editText);
        unitsSwitch = findViewById(R.id.change_units_switch);
        Button countButton = findViewById(R.id.count_button);

        restoreData();
        final Toast toast = Toast.makeText(this, R.string.wrong_input_data_toast, Toast.LENGTH_SHORT);
        final Toast emptyFiledsToast = Toast.makeText(this, R.string.fill_both_fields_warning, Toast.LENGTH_SHORT);


        countButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    result = countBMI();
                    if (result != -1) {
                        Intent i = new Intent(getApplicationContext(), ShowBmiActivity.class);
                        i.putExtra(PASSED_RESULT_VALUE, result);
                        startActivity(i);
                    } else {
                        toast.show();
                    }
                } catch (IllegalArgumentException e) {
                    emptyFiledsToast.show();
                }
            }
        });

        unitsSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSwitchChecked = true;
                weightInput.setText("");
                heightInput.setText("");
            }
        });
        unitsSwitch.setChecked(isSwitchChecked);


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble(getString(R.string.saved_result_value_text), result);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save_settings)
            saveData();
        else
            startActivity(new Intent(this, AboutAuthorActivity.class));
        return true;
    }


    private double readValue(EditText editText) {

        double data;
        if (editText.getText().toString().isEmpty()) {
            return -1;
        } else {
            data = Double.valueOf(editText.getText().toString());
        }
        return data;
    }

    private void saveData() {
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        if (readValue(weightInput) != -1) {
            editor.putString(String.valueOf(R.string.saved_weight_value_text), String.valueOf(readValue(weightInput)));
        }
        if (readValue(heightInput) != -1) {
            editor.putString(String.valueOf(R.string.saved_height_value_text), String.valueOf(readValue(heightInput)));
        }
        editor.putBoolean(String.valueOf(R.string.saved_isChecked_value_text), isSwitchChecked);


        editor.apply();
        Toast.makeText(this, R.string.saved_toast, Toast.LENGTH_SHORT).show();
    }

    private void restoreData() {
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String defaultValue = getResources().getString(R.string.empty_string);
        if (sharedpreferences.contains(String.valueOf(R.string.saved_weight_value_text))) {
            weightInput.setText(sharedpreferences.getString(String.valueOf(R.string.saved_weight_value_text), defaultValue));
        }
        if (sharedpreferences.contains(String.valueOf(R.string.saved_height_value_text))) {
            heightInput.setText(sharedpreferences.getString(String.valueOf(R.string.saved_height_value_text), defaultValue));
        }
        if (sharedpreferences.contains(String.valueOf(R.string.saved_isChecked_value_text))) {
            isSwitchChecked = sharedpreferences.getBoolean(String.valueOf(R.string.saved_isChecked_value_text), false);
        }
    }

    private double countBMI() throws IllegalArgumentException {
        double[] values = new double[]{readValue(weightInput), readValue(heightInput)};
        if (values[0] == -1 || values[1] == -1) {
            throw new IllegalArgumentException();
        }
        BMI bmi;
        if (isSwitchChecked) {
            bmi = new BmiForImperialMetrics(values[0], values[1]);
            if (bmi.isDataValid())
                return bmi.calculateBMI();

        } else {

            bmi = new BmiForKgM(values[0], values[1]);
            if (bmi.isDataValid()) {
                return bmi.calculateBMI();
            }
        }
        return -1;
    }
}