package vn.edu.stu.quan_ly_nhan_vien;

import vn.edu.stu.quan_ly_nhan_vien.model.Nhanvien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText txtMa, txtTen, txtSDT;
    Button btnLuu;
    ArrayList<Nhanvien> dsNhanvien;
    ArrayAdapter<Nhanvien> adapter;
    ListView lvDSNhanvien;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        addCtrol();
        addEvent();
    }

    private void addCtrol() {
        txtMa = findViewById(R.id.txtMa);
        txtTen = findViewById(R.id.txtTen);
        txtSDT = findViewById(R.id.txtSDT);
        btnLuu = findViewById(R.id.btnLuu);

        dsNhanvien = new ArrayList<>();
        adapter = new ArrayAdapter<>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                dsNhanvien
        );
        lvDSNhanvien = findViewById(R.id.lvDSNhanvien);
        lvDSNhanvien.setAdapter(adapter);
    }

    private void addEvent() {
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ma = txtMa.getText().toString();
                String ten = txtTen.getText().toString();
                String sdt = txtSDT.getText().toString();
                Nhanvien nv = new Nhanvien(ma, ten, sdt);
                dsNhanvien.add(nv);

                adapter.notifyDataSetChanged();
                txtMa.setText("");
                txtTen.setText("");
                txtSDT.setText("");
                txtMa.requestFocus();
            }
        });
        lvDSNhanvien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if(position>=0 && position<dsNhanvien.size()){
                    dsNhanvien.remove(position);
                    adapter.notifyDataSetChanged();
                }
                return true;
            }
        });
    }
}