package xyz.bmi;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import xyz.util.BMI;
import xyz.util.S;

public class MainActivity extends AppCompatActivity {

    private EditText mHeightEt;
    private EditText mWeightEt;
    private TextView mBmiValueTv;
    private TextView mBmiAdviceTv;
    private Button mCalcButton;
    private String[] mAdvices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        findViews();
        setListener();
        Resources res=getResources();
        String bmiValue=String.format(res.getString(R.string.bmi_value),"");
        mBmiValueTv.setText(bmiValue);
        String bmiAdvice=String.format(res.getString(R.string.advice),"");
        mBmiAdviceTv.setText(bmiAdvice);
        mAdvices = res.getStringArray(R.array.advice);
    }


    private void findViews() {
        mHeightEt =  findViewById(R.id.et_height);
        mWeightEt =  findViewById(R.id.et_weight);
        mBmiValueTv = findViewById(R.id.tv_bmi_value);
        mBmiAdviceTv =  findViewById(R.id.tv_bmi_advice);
        mCalcButton =   findViewById(R.id.btn_calc);
    }

    private void setListener() {
        mCalcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BMI bmi = new BMI(S.Unit.METRIC_SYSTEM);
                String height=  mHeightEt.getText().toString();
                String weight=mWeightEt.getText().toString();
                if(height.equals("")||weight.equals("")){
                    return;
                }
                bmi.setHeight(Integer.parseInt(height));
                bmi.setWeight(Integer.parseInt(weight));
                mBmiValueTv.setText(getString(R.string.bmi_value,bmi.getBmiOfFormat()));
                mBmiAdviceTv.setText(getString(R.string.advice, mAdvices[bmi.getBmiAdvice()]));

            }
        });
    }

}
