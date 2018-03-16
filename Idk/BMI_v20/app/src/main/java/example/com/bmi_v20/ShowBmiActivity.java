package example.com.bmi_v20;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import static example.com.bmi_v20.MainActivity.PASSED_RESULT_VALUE;

public class ShowBmiActivity extends AppCompatActivity {

    private double resultToShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bmi);

        TextView bmiValueTextView = findViewById(R.id.BMI_value_textView);

        resultToShow = getResultData();
        bmiValueTextView.setText(String.format("%.2f", resultToShow));
        setBacgroundColor();

        LayoutInflater li = LayoutInflater.from(this);
        View customView = li.inflate(R.layout.custom_bmi_menu_layout, null);
        ActionBar mActionBar = getSupportActionBar();

        if (mActionBar != null) {
            mActionBar.setDisplayShowHomeEnabled(false);
            mActionBar.setDisplayShowTitleEnabled(false);
            mActionBar.setCustomView(customView);
            mActionBar.setDisplayShowCustomEnabled(true);
        }

        ImageButton addContent = customView.findViewById(R.id.back_arrow);
        addContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private double getResultData() {
        return getIntent().getDoubleExtra(PASSED_RESULT_VALUE, resultToShow);
    }

    private void setBacgroundColor() {
        double lowerBoundBMI = 18.5;
        double upperBoundBMI = 24.9;

        if (resultToShow < lowerBoundBMI)
            findViewById(R.id.show_bmi_layout).setBackgroundColor(getResources().getColor(R.color.yellow));
        else if (resultToShow >= lowerBoundBMI && resultToShow < upperBoundBMI)
            findViewById(R.id.show_bmi_layout).setBackgroundColor(getResources().getColor(R.color.blue));
        else
            findViewById(R.id.show_bmi_layout).setBackgroundColor(getResources().getColor(R.color.red));
    }
}
