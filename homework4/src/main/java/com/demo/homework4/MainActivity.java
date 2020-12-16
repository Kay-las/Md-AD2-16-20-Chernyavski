package com.demo.homework4;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.widget.SwitchCompat;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import java.nio.channels.Selector;

public class MainActivity extends AppCompatActivity {
    private CustomView customView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customView = findViewById(R.id.center_circle);
        SwitchCompat switchCompat = findViewById(R.id.onOff);
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });


        ClickCustomListener clickCustomListener = new ClickCustomListener() {
            @Override
            public void onClicked(ClickedData clickedData) {
                if (switchCompat.isChecked()) {
                    Snackbar.make(customView, "Нажаты координаты " + "["+ clickedData.x + " ; " + clickedData.y + "]",  + 1000)
                        .setTextColor(clickedData.color).show();
                }else Snackbar.make(customView, "Нажаты координаты " + "["+ clickedData.x + " ; " + clickedData.y + "]",  + 1000)
                        .show();

            }
        };
        customView.setClickCustomListener(clickCustomListener);


    }


}