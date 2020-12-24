package com.demo.homework4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private EditText nameContact;
    private EditText communication;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        radioGroup = findViewById(R.id.radioGroup);
        nameContact = findViewById(R.id.nameContact);
        communication = findViewById(R.id.communication);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId == R.id.phoneNumber){
                    communication.setHint(R.string.phone_number);
                }else{
                    communication.setHint(R.string.email);
                }
            }
        });
    }

    public void onClickAddContact(View view) {
        String name = nameContact.getText().toString();
        String contact = communication.getText().toString();
        int radioButtonId = radioGroup.getCheckedRadioButtonId();

        DataContact dataContact = new DataContact(name, contact,radioButtonId == R.id.phoneNumber ? DataContact.InfoType.Phone : DataContact.InfoType.Mail );


        Intent intent = new Intent();

        intent.putExtra("contact",dataContact);

        setResult(RESULT_OK,intent);
        finish();

    }
}