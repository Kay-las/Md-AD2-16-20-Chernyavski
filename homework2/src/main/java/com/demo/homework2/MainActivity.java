package com.demo.homework2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_generate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Random random = new Random();
                ArrayList<Integer> integers = new ArrayList<>();
                while (integers.size() < 100) {
                    Integer i = random.nextInt(10000);
                    if (!integers.contains(i)) {
                        integers.add(i);
                    }
                }

                Intent intent = new Intent(MainActivity.this, ActivityAccount.class);
                intent.putIntegerArrayListExtra(ExtraNames.VALUES, integers);
                startActivityForResult(intent, RequestCodes.REQUEST_MATH_RESULT);
            };
    }
        );
    } @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestCodes.REQUEST_MATH_RESULT) {
            if (resultCode == Activity.RESULT_OK) {
                String result = new String();
                result += "Summa " + data.getIntExtra(ExtraNames.SUMMA, 0);
                result += "\n";
                result += "Srednee arif " + data.getIntExtra(ExtraNames.ARIFMET, 0);
                result += "\n";
                result += "Delenie polovin " + data.getFloatExtra(ExtraNames.DELENIE_POLOVIN, 0);
                TextView tvResult = findViewById(R.id.textView_guideline);
                tvResult.setText(result);
            } else {
                TextView tvResult = findViewById(R.id.textView_guideline);
                tvResult.setText("Failed");
            }
        }
    }
}






