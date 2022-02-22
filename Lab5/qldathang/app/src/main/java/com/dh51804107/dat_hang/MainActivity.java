package com.dh51804107.dat_hang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

import com.dh51804107.dat_hang.adapter.MathangAdapter;
import com.dh51804107.dat_hang.model.Mathang;

public class MainActivity extends AppCompatActivity {
    Button btnDatHang;
    ArrayList<Mathang> dsMatHang;
    MathangAdapter adapter;
    ListView lvMatHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        fakeData();
        addEvent();
    }

    private void addControls() {
        btnDatHang = findViewById(R.id.btnDatHang);
        dsMatHang = new ArrayList<>();
        adapter = new MathangAdapter(
                MainActivity.this,
                R.layout.item_mathang,
                dsMatHang
        );
        lvMatHang = findViewById(R.id.lvMatHang);
        lvMatHang.setAdapter(adapter);
    }

    private void fakeData() {
        Random rd = new Random();
        for (int i=0; i<10 ; i++){
            dsMatHang.add(new Mathang("Món hàng " + i,rd.nextInt(10000),0));
        }
        adapter.notifyDataSetChanged();
    }

    private void addEvent() {
        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Mathang> donhang = new ArrayList<>();
                for(Mathang mh : dsMatHang){
                    if (mh.getSoluong() > 0){
                        donhang.add(mh);
                    }
                }
                Intent intent = new Intent(
                        MainActivity.this,
                        DonHangActivity.class
                );
                intent.putExtra("DONHANG",donhang);
                startActivity(intent);
            }
        });

    }
}