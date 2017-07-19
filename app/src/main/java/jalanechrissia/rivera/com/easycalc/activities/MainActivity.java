package jalanechrissia.rivera.com.easycalc.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Pattern;

import jalanechrissia.rivera.com.easycalc.R;
import jalanechrissia.rivera.com.easycalc.controllers.Operations;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Operations mOperation;
    private EditText mFirstNum;
    private EditText mSecondNum;
    private TextView mResult;
    private Button btnAdd;
    private Button btnSub;
    private Button btnClear;
    private Button btnMult;
    private Button btnDiv;
    private Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOperation = new Operations();
        findViews();

        mFirstNum.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange (View v, boolean hasFocus){
                if(mFirstNum.getText().length()<=0){
                    mFirstNum.setError("Required field");
                }
            }
        });
        mSecondNum.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange (View v, boolean hasFocus){
                if(mSecondNum.getText().length()<=0){
                    mSecondNum.setError("Required field");
                }
            }
        });
    }

    private void findViews() {
        mFirstNum = (EditText) findViewById(R.id.num1);
        mSecondNum = (EditText) findViewById(R.id.num2);
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnSub = (Button) findViewById(R.id.btn_sub);
        btnClear = (Button) findViewById(R.id.btn_clear);
        btnMult = (Button) findViewById(R.id.btn_mult);
        btnDiv = (Button) findViewById(R.id.btn_div);
        btnExit = (Button) findViewById(R.id.btn_exit);
        mResult = (TextView) findViewById(R.id.tv_result);

        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_sub).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(this);
        findViewById(R.id.btn_mult).setOnClickListener(this);
        findViewById(R.id.btn_div).setOnClickListener(this);
        findViewById(R.id.btn_exit).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String firstText = mFirstNum.getText().toString();
        String secondText = mSecondNum.getText().toString();
        double n1 = Double.parseDouble(firstText);
        double n2 = Double.parseDouble(secondText);
        Double result;
        String fresult;
        DecimalFormat formatter = new DecimalFormat("#,###.00");

        switch (view.getId()){
            case R.id.btn_add:
                result = mOperation.add(n1,n2);
                fresult = formatter.format(result);
                mResult.setText(String.valueOf(fresult));
                break;
            case R.id.btn_sub:
                result = mOperation.sub(n1, n2);
                fresult = formatter.format(result);
                mResult.setText(String.valueOf(fresult));
                break;
            case R.id.btn_clear:
                mFirstNum.setText("");
                mSecondNum.setText("");
                mResult.setText("");
                break;
            case R.id.btn_mult:
                result = mOperation.mult(n1, n2);
                fresult = formatter.format(result);
                mResult.setText(String.valueOf(fresult));
                break;
            case R.id.btn_div:
                try{
                    result = mOperation.div(n1, n2);
                    fresult = formatter.format(result);
                    mResult.setText(String.valueOf(fresult));
                }catch (Exception e){
                    mResult.setText("Cannot be divided");
                }
                break;
            case R.id.btn_exit:
                System.exit(0);
                break;
        }


    }

}
