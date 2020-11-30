package com.demo.homework2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivityAccount extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        final ArrayList<Integer> values = getIntent().getIntegerArrayListExtra(ExtraNames.VALUES);
        String valuesToshow = "";
        for (int i = 0; i < values.size(); i++){
            valuesToshow += values.get(i);
            valuesToshow += ",";
        }
        TextView textViewAccount = findViewById(R.id.textViewAccount);
        textViewAccount.setText(valuesToshow);

        findViewById(R.id.button_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int summa = 0;
                for (int i = 0; i < values.size(); i++) {
                    summa = summa + values.get(i);
                }
                int sredneeAref = summa / values.size();

                int polovina1 = 0;
                int polovina2 = 0;

                int seredina = values.size() / 2;

                for (int i = 0; i < seredina; i++){
                    polovina1 = polovina1 + values.get(i);
                }
                for (int i = seredina; i < values.size(); i++) {
                    polovina2 = polovina2 - values.get(i);
                }
                float deleniepolovin = (float) polovina1 / polovina2;

                Intent data = new Intent();
                data.putExtra(ExtraNames.SUMMA, summa);
                data.putExtra(ExtraNames.ARIFMET, sredneeAref);
                data.putExtra(ExtraNames.DELENIE_POLOVIN, deleniepolovin);
                setResult(Activity.RESULT_OK, data);
                finish();


            }
        });

    }
}