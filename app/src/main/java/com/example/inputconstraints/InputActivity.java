package com.example.inputconstraints;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.inputconstraints.databinding.ActivityInputBinding;

public class InputActivity extends AppCompatActivity {
    ActivityInputBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b=ActivityInputBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        setTitle("Input Activity");


        sendInputBack();

    }

    private void sendInputBack() {
        b.btnSendback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data=b.data.getText().toString().trim();
                if(data.isEmpty()){
                    b.data.setError("Please enter data");
                    return;
                }
                if(!data.matches(checkConstraints())){
                    b.data.setError("Invalid");
                    return;
                }
                Intent intent=new Intent(InputActivity.this,InputConstraintsActivity.class);
                intent.putExtra(Constants.INPUT_DATA,data);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    private String checkConstraints() {
        Bundle bundle=getIntent().getExtras();
        for (String str:bundle.keySet()){
        Log.d("Abhi", "checkConstraints: "+bundle.getString(str));

        }
        StringBuilder regex = new StringBuilder();

        regex.append("^([");
        if(Boolean.parseBoolean(bundle.getString(Constants.UPPER_CASE,"false"))){
            regex.append("A-Z");
        }

        if(Boolean.parseBoolean(bundle.getString(Constants.LOWER_CASE,"false"))){
            regex.append("a-z");

        }

        if(Boolean.parseBoolean(bundle.getString(Constants.DIGITS,"false"))){
            regex.append("0-9");

        }

        if(Boolean.parseBoolean(bundle.getString(Constants.OPERATORS,"false"))){
            regex.append("+-/*%");

        }

        if(Boolean.parseBoolean(bundle.getString(Constants.SYMBOLS,"false"))){
           regex.append("@#\\\\^{}\\]\"\"^()?`~!;:''.,|\\[");
        }
        regex.append("])+");

        return regex.toString();

    }
}