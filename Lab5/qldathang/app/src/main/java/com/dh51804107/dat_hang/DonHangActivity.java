package com.dh51804107.dat_hang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;

import com.dh51804107.dat_hang.model.Mathang;

public class DonHangActivity extends AppCompatActivity {
    ArrayAdapter<Mathang> adapter;
    ListView lvDonhang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang);
        addControls();
        getIntentData();
    }

    private void addControls() {
        adapter = new ArrayAdapter<>(
                DonHangActivity.this,
                android.R.layout.simple_list_item_1
        );
        lvDonhang = findViewById(R.id.lvDonHang);
        lvDonhang.setAdapter(adapter);
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent.hasExtra("DONHANG")){
            ArrayList<Mathang> donhang =
                    (ArrayList<Mathang>) intent.getSerializableExtra("DONHANG");
            if (donhang !=null){
                adapter.addAll( donhang);
            }
        }

    }
}