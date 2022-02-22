package vn.edu.stu.quan_ly_nhan_vien_tuy_bien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.w3c.dom.NamedNodeMap;

import java.util.ArrayList;

import vn.edu.stu.quan_ly_nhan_vien_tuy_bien.adapter.NhanvienAdapter;
import vn.edu.stu.quan_ly_nhan_vien_tuy_bien.model.Nhanvien;

public class MainActivity extends AppCompatActivity {
    EditText txtMa, txtTen, txtSDT;
    Button btnAdd;

    ArrayList<Nhanvien> dsNhanvien;
    NhanvienAdapter adapter;
    ListView lvDSNhanvien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addCtrol();
        AddEvt();
    }

    private void addCtrol() {

        btnAdd = findViewById(R.id.btnSave);
        dsNhanvien = new ArrayList<>();
        dsNhanvien.add(new Nhanvien("123", "hao", "09583264"));
        dsNhanvien.add(new Nhanvien("124", "hai", "031262536"));
        adapter = new NhanvienAdapter(
                MainActivity.this,
                R.layout.activity_main,
                dsNhanvien
        );
        lvDSNhanvien = findViewById(R.id.lvDSNhanvien);
        lvDSNhanvien.setAdapter(adapter);
    }

    private void AddEvt() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(MainActivity.this, EditActivity.class);
              startActivity(intent);

                adapter.notifyDataSetChanged();


            }
        });
        lvDSNhanvien.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        if(position>=0&&position<dsNhanvien.size()){
                            dsNhanvien.remove(position);
                            adapter.notifyDataSetChanged();
                        }


                        return true;
                    }
                }
        );
    }
}