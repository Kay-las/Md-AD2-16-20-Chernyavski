package com.demo.homework4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_EDIT = 1001;

    private RecyclerView recyclerView;
    public static final ArrayList<DataContact> dataContacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerView);
        dataContacts.add(new DataContact(R.drawable.ic_baseline_contact_phone_24, "Вяся Пупкин", "+3752545632"));
        dataContacts.add(new DataContact(R.drawable.ic_baseline_contact_mail_24, "Елочка Гори", "fire2020"));
        dataContacts.add(new DataContact(R.drawable.ic_baseline_contact_phone_24, "Снегурочка", "+3752545632"));
        dataContacts.add(new DataContact(R.drawable.ic_baseline_contact_mail_24, "Дед Мороз", "liberty-26"));
        dataContacts.add(new DataContact(R.drawable.ic_baseline_contact_phone_24, "Вяся Пупкин", "+3752545632"));
        dataContacts.add(new DataContact(R.drawable.ic_baseline_contact_mail_24, "Елочка Гори", "fire2020"));
        dataContacts.add(new DataContact(R.drawable.ic_baseline_contact_phone_24, "Снегурочка", "+3752545632"));

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

        }
    }

    public void onClickAddContact(View view) {
        Intent intent = new Intent(MainActivity.this, AddActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}