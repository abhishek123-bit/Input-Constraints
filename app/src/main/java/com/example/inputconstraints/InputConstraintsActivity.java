package com.example.inputconstraints;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.inputconstraints.databinding.ActivityInputConstraintsBinding;

public class InputConstraintsActivity extends AppCompatActivity {
    private static final int REQUEST_INPUT = 12;
    ActivityInputConstraintsBinding b;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityInputConstraintsBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        setTitle("InputConstraints Activity");


        sendConstraints();
    }

    private void sendConstraints() {
        b.btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                putConstraints();

                if (bundle.isEmpty()){
                    Toast.makeText(InputConstraintsActivity.this, "Please select a constraint", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent=new Intent(InputConstraintsActivity.this,InputActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent,REQUEST_INPUT);
            }
        });

    }

    private void putConstraints() {
        bundle = new Bundle();
        if (b.checkUppercase.isChecked()) {
            bundle.putString(Constants.UPPER_CASE, "true");
        }

        if (b.checkLowercase.isChecked()) {
            bundle.putString(Constants.LOWER_CASE, "true");
            Log.d("Abhi", "checkConstraints: ");
        }

        if (b.checkDigits.isChecked()) {
            bundle.putString(Constants.DIGITS, "true");
        }

        if (b.checkOperators.isChecked()) {
            bundle.putString(Constants.OPERATORS, "true");
        }

        if (b.checkSymbols.isChecked()) {
            bundle.putString(Constants.SYMBOLS, "true");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_INPUT&&resultCode==RESULT_OK){
            b.Input.setText("Input is :- "+data.getStringExtra(Constants.INPUT_DATA));
            b.Input.setVisibility(View.VISIBLE);
        }
    }
}