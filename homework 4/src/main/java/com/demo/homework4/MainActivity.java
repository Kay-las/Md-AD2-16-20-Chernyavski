package com.demo.homework4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public  int REQUEST_CODE_EDIT = 1001;
    public  int REQUEST_CODE_ADD = 1002;

    private RecyclerView recyclerView;
    public ArrayList<DataContact> dataContacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerView);

        if(savedInstanceState!= null){
            dataContacts = savedInstanceState.getParcelableArrayList("contacts");
        }else{
            dataContacts.add(new DataContact( "Год Быка", "358545656", DataContact.InfoType.Phone ));
            dataContacts.add(new DataContact( "Год Крысы", "@mail", DataContact.InfoType.Mail ));
        }

        ContactAdapter adapter = new ContactAdapter(dataContacts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        adapter.setOnContactClickListener(new ContactAdapter.OnContactClickListener() {
            @Override
            public void onContactClick(int position) {
//                Toast.makeText(MainActivity.this, "Номер позиции" + position,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra("contact",dataContacts.get(position));
                intent.putExtra("position",position);
                startActivityForResult(intent, REQUEST_CODE_EDIT);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_EDIT){
            if(resultCode == RESULT_OK){
                DataContact dataContact = data.getParcelableExtra("contact");
               int position = data.getIntExtra("position",-1);
                if(dataContact == null){
                    dataContacts.remove(position);
                }else{
                    dataContacts.set(position,dataContact);
                }
            }

        }else if(requestCode == REQUEST_CODE_ADD){
            if(resultCode == RESULT_OK){
                DataContact dataContact = data.getParcelableExtra("contact");

                    dataContacts.add(dataContact);
            }

        }
    }

    public void onClickAddContact(View view) {
        Intent intent = new Intent(MainActivity.this, AddActivity.class);
        startActivityForResult(intent,REQUEST_CODE_ADD);
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("contacts",dataContacts);
    }
}