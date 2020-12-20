package com.demo.homework4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {

    private EditText nameContact;
    private EditText communication;
    private DataContact dataContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        nameContact = findViewById(R.id.nameContact);
        communication = findViewById(R.id.communication);

        dataContact = getIntent().getParcelableExtra("contact");


        nameContact.setText(dataContact.getName());
        communication.setText(dataContact.getContact());

    }

    public void onClickEditContact(View view) {
        String name = nameContact.getText().toString();
        String contact = communication.getText().toString();

        dataContact.setName(name);
        dataContact.setContact(contact);

        Intent intent = new Intent();

        intent.putExtras(getIntent().getExtras());
        intent.putExtra("contact",dataContact);

        setResult(RESULT_OK,intent);
        finish();
    }

    public void onClickRemoveContact(View view) {
        Intent intent = new Intent();

        intent.putExtras(getIntent().getExtras());
        intent.removeExtra("contact");

        setResult(RESULT_OK,intent);
        finish();
    }



}