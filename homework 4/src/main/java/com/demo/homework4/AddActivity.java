package com.demo.homework4;

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

        DataContact dataContact = new DataContact(name, contact);
        if (radioButtonId == R.id.phoneNumber) {
            dataContact.setImageId(R.drawable.ic_baseline_contact_phone_24);
        } else {
            dataContact.setImageId(R.drawable.ic_baseline_contact_mail_24);

        }
        MainActivity.dataContacts.add(dataContact);
        finish();
    }
}