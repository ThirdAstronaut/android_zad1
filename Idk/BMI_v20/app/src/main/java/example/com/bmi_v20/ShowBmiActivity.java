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
    private ImageButton addContent;
    private TextView bmiValueTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bmi);

        initViews();

        resultToShow = getResultData();
        bmiValueTextView.setText(String.format("%.2f", resultToShow));

        setBacgroundColor();

        addContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initViews() {
        bmiValueTextView = findViewById(R.id.BMI_value_text_view);
        LayoutInflater li = LayoutInflater.from(this);
        View customView = li.inflate(R.layout.custom_bmi_menu_layout, null);
        addContent = customView.findViewById(R.id.back_arrow);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setCustomView(customView);
            actionBar.setDisplayShowCustomEnabled(true);
        }
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
